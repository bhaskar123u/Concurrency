package service;

import entity.City;
import entity.Movie;
import entity.Show;
import entity.Theater;

public class AdminService {
  public void addTheater(City city, String theaterName) {
    city.addTheater(new Theater(theaterName));
  }

  public void addMovie(Theater theater, String movieTitle) {
    theater.addMovie(new Movie(movieTitle));
  }

  public void addShow(Movie movie, String time, int rows, int cols) {
    movie.addShow(new Show(time, rows, cols));
  }
}
