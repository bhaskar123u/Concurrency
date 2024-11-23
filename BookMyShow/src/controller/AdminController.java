package controller;

import entity.City;
import entity.Movie;
import entity.Theater;
import service.AdminService;

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void addTheater(City city, String theaterName) {
        adminService.addTheater(city, theaterName);
        System.out.println("Theater added: " + theaterName + " in " + city.getName());
    }

    public void addMovie(Theater theater, String movieTitle) {
        adminService.addMovie(theater, movieTitle);
        System.out.println("Movie added: " + movieTitle + " in theater " + theater.getName());
    }

    public void addShow(Movie movie, String time, int rows, int cols) {
        adminService.addShow(movie, time, rows, cols);
        System.out.println("Show added: " + time + " for movie " + movie.getTitle());
    }
}
