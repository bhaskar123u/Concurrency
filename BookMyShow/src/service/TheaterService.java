package service;

import entity.City;
import entity.Theater;

public class TheaterService {
  public void addTheater(City city, String theaterName) {
    city.addTheater(new Theater(theaterName));
  }
  
}
