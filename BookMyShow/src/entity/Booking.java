package entity;

import java.util.List;

public class Booking {
  private final String userId;
  private final Show show;
  private final List<int[]> seatChoices;
  private String paymentId;

  public Booking(String userId, Show show, List<int[]> seatChoices) {
    this.userId = userId;
    this.show = show;
    this.seatChoices = seatChoices;
  }

  public String getUserId() {
    return userId;
  }

  public Show getShow() {
    return show;
  }

  public List<int[]> getSeatChoices() {
    return seatChoices;
  }

  public String getPaymentId() {
    return paymentId;
  }
}
