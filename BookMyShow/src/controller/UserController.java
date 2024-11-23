package controller;

import java.util.List;

import entity.Booking;
import entity.City;
import entity.Movie;
import entity.Show;
import entity.Theater;
import entity.Ticket;
import entity.User;
import service.AddOnsService;
import service.BookingService;
import service.CancellationService;
import service.SearchService;

public class UserController {

  private final SearchService searchService;
  private final BookingService bookingService;
  private final CancellationService cancellationService;
  private final AddOnsService addOnsService;

  public UserController(SearchService searchService, BookingService bookingService,
      CancellationService cancellationService, AddOnsService addOnsService) {
    this.searchService = searchService;
    this.bookingService = bookingService;
    this.cancellationService = cancellationService;
    this.addOnsService = addOnsService;
  }

  /**
   * Search movies available in a given city.
   *
   * @param city The city to search movies in.
   * @return List of movie titles in the city.
   */
  public List<String> searchMoviesByCity(City city) {
    if (city == null) {
      throw new IllegalArgumentException("City cannot be null.");
    }

    List<Movie> movies = searchService.searchMoviesByCity(city);
    if (movies.isEmpty()) {
      System.out.println("No movies currently showing in " + city.getName());
      return List.of();
    }

    System.out.println("Movies currently showing in " + city.getName() + ":");
    movies.forEach(movie -> System.out.println("- " + movie.getTitle()));
    return movies.stream().map(Movie::getTitle).toList();
  }

  /**
   * Search movies in a given theater.
   *
   * @param theater The theater to search movies in.
   * @return List of movie titles in the theater.
   */
  public List<String> searchMoviesInTheater(Theater theater) {
    if (theater == null) {
      throw new IllegalArgumentException("Theater cannot be null.");
    }

    List<Movie> movies = searchService.searchMoviesInTheater(theater);
    if (movies.isEmpty()) {
      System.out.println("No movies currently showing in theater " + theater.getName());
      return List.of();
    }

    System.out.println("Movies currently showing in theater " + theater.getName() + ":");
    movies.forEach(movie -> System.out.println("- " + movie.getTitle()));
    return movies.stream().map(Movie::getTitle).toList();
  }

  /**
   * Book tickets for a user.
   *
   * @param user        The user attempting to book.
   * @param show        The show to book tickets for.
   * @param seatChoices List of seats the user wants to book.
   * @return True if booking was successful, false otherwise.
   */
  public boolean bookTicket(User user, Show show, List<int[]> seatChoices) {
    if (user == null || show == null || seatChoices == null || seatChoices.isEmpty()) {
      throw new IllegalArgumentException("Invalid booking details provided.");
    }

    try {
      Booking booking = new Booking(user.getId(), show, seatChoices);
      Ticket ticket = bookingService.bookTicket(booking);
      if (ticket != null) {
        System.out.println("Booking successful for user: " + user.getId());
        return true;
      }
    } catch (Exception e) {
      System.out.println("Booking failed for user: " + user.getId() + ". Seats may already be booked.");
    }
    return false;
  }

  /**
   * Cancel tickets for a user.
   *
   * @param user          The user attempting to cancel.
   * @param show          The show for which tickets need to be canceled.
   * @param seatsToCancel List of seats the user wants to cancel.
   * @return True if cancellation was successful, false otherwise.
   */
  public boolean cancelTicket(User user, Show show, List<int[]> seatsToCancel) {
    if (user == null || show == null || seatsToCancel == null || seatsToCancel.isEmpty()) {
      throw new IllegalArgumentException("Invalid cancellation details provided.");
    }

    try {
      cancellationService.cancelTicket(user, show, seatsToCancel);
      System.out.println("Cancellation successful for user: " + user.getId());
      return true;
    } catch (Exception e) {
      System.err.println("Error during cancellation for user " + user.getId() + ": " + e.getMessage());
      return false;
    }
  }

  /**
   * Add an add-on to a user's booking.
   *
   * @param user  The user adding the add-on.
   * @param addon The add-on to be added.
   * @return True if add-on was successfully added, false otherwise.
   */
  public boolean addAddOns(User user, String addon) {
    if (user == null || addon == null || addon.isBlank()) {
      throw new IllegalArgumentException("Invalid add-on details provided.");
    }

    try {
      addOnsService.addAddOns(user, addon);
      System.out.println("Add-on '" + addon + "' successfully added for user: " + user.getId());
      return true;
    } catch (Exception e) {
      System.err.println("Error adding add-on for user " + user.getId() + ": " + e.getMessage());
      return false;
    }
  }
}