package entity;

import java.util.HashMap;
import java.util.Map;

public class Theater {
  private final String name;
  private final Map<String, Movie> movies = new HashMap<>();

  public Theater(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Map<String, Movie> getMovies() {
    return movies;
  }

  public void addMovie(Movie movie) {
    movies.put(movie.getTitle(), movie);
  }
}
