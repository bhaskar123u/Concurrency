package entity;

import java.util.HashMap;
import java.util.Map;

public class City {
  private final String name;
  private final Map<String, Theater> theaters = new HashMap<>();

  public City(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Map<String, Theater> getTheaters() {
    return theaters;
  }

  public void addTheater(Theater theater) {
    theaters.put(theater.getName(), theater);
  }
}
