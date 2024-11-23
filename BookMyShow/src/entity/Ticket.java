package entity;

import java.util.List;

public class Ticket {
  private final String userId;
  private final List<int[]> seats;
  private final String paymentId;

  public Ticket(String userId, List<int[]> seats, String paymentId) {
    this.userId = userId;
    this.seats = seats;
    this.paymentId = paymentId;
  }

  @Override
  public String toString() {
    return "Ticket{" +
        "userId='" + userId + '\'' +
        ", seats=" + seats +
        ", paymentId='" + paymentId + '\'' +
        '}';
  }
}
