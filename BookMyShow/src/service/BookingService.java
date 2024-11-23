package service;

import entity.Booking;
import entity.Ticket;

public class BookingService {
  public Ticket bookTicket(Booking booking) {
    boolean success = booking.getShow().bookSeats(booking.getSeatChoices());
    if (success) {
      return new Ticket(booking.getUserId(), booking.getSeatChoices(), booking.getPaymentId());
    } else {
      throw new IllegalStateException("Booking failed: Seats already booked!");
    }
  }
}
