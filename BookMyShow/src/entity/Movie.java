package entity;

import java.util.ArrayList;
import java.util.List;

public class Movie {
  private final String title;
  private final List<Show> shows = new ArrayList<>();

  public Movie(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public List<Show> getShows() {
    return shows;
  }

  public void addShow(Show show) {
    shows.add(show);
  }
}
