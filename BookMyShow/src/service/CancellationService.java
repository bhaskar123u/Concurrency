package service;

import java.util.List;

import entity.Show;
import entity.User;

public class CancellationService {
  public void cancelTicket(User user, Show show, List<int[]> seatsToCancel) {
    // Update show to release seats (not implemented here for simplicity)
    user.getBookings().remove(seatsToCancel);
  }
}
