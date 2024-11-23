package service;

import java.util.ArrayList;
import java.util.List;

import entity.City;
import entity.Movie;
import entity.Theater;

public class SearchService {
  public List<Movie> searchMoviesByCity(City city) {
    List<Movie> movies = new ArrayList<>();
    for (Theater theater : city.getTheaters().values()) {
      movies.addAll(theater.getMovies().values());
    }
    return movies;
  }

  public List<Movie> searchMoviesInTheater(Theater theater) {
    return new ArrayList<>(theater.getMovies().values());
  }
}
