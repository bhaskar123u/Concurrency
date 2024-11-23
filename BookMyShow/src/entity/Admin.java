package entity;

public class Admin {
  public static void addTheater(City city, Theater theater) {
    city.addTheater(theater);
  }

  public static void addMovie(Theater theater, Movie movie) {
    theater.addMovie(movie);
  }

  public static void addShow(Movie movie, Show show) {
    movie.addShow(show);
  }
}
