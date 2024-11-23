import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import controller.AdminController;
import controller.UserController;
import entity.City;
import entity.Movie;
import entity.Show;
import entity.Theater;
import entity.User;
import service.AddOnsService;
import service.AdminService;
import service.BookingService;
import service.CancellationService;
import service.SearchService;
import service.TheaterService;

public class BookMyShow {
  public static void main(String[] args) {

    // Services
        AdminService adminService = new AdminService();
        SearchService searchService = new SearchService();
        BookingService bookingService = new BookingService();
        CancellationService cancellationService = new CancellationService();
        AddOnsService addOnsService = new AddOnsService();
        TheaterService theaterService = new TheaterService();

        // Controllers
        AdminController adminController = new AdminController(adminService, theaterService);
        UserController userController = new UserController(searchService, bookingService, cancellationService, addOnsService);

        // Entities
        City city = new City("Patna");
        adminController.addTheater(city, "PNM");

        Theater theater = city.getTheaters().get("PNM");
        adminController.addMovie(theater, "Inception");

        Movie movie = theater.getMovies().get("Inception");
        adminController.addShow(movie, "7 PM", 5, 5);
        adminController.addShow(movie, "4 PM", 5, 5);

        Show eveningShow = movie.getShows().get(0);
        Show lateEveningShow = movie.getShows().get(1);

        // Thread simulation: 10 users attempting to book tickets
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            int userId = i;
            executorService.submit(() -> {
                User user = new User("User" + userId);
                List<int[]> seats = Arrays.asList(new int[] { 0, userId % 5 }, new int[] { 1, userId % 5 });

                try {
                    userController.bookTicket(user, (userId%2==0)?eveningShow:lateEveningShow, seats);
                    System.out.println(user.getId() + " successfully booked seats: " + seats.toString());
                } catch (IllegalStateException e) {
                    System.out.println(user.getId() + " failed to book seats: " + seats + ". Reason: " + e.getMessage());
                }
            });
        }

        // Shutdown the executor service
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
  }
}