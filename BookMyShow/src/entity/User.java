package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
  private final String id;
  private final List<List<int[]>> bookings = new ArrayList<>();
  private final List<String> addOns = new ArrayList<>();

  public User(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public List<List<int[]>> getBookings() {
    return bookings;
  }

  public List<String> getAddOns() {
    return addOns;
  }
}
