package controller;

import java.util.List;

import entity.Booking;
import entity.Show;
import entity.Ticket;
import entity.User;
import service.BookingService;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Ticket bookTicket(User user, Show show, List<int[]> seatChoices) {
        if (user == null || show == null || seatChoices == null || seatChoices.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking details provided.");
        }

        Booking booking = new Booking(user.getId(), show, seatChoices);

        try {
            Ticket ticket = bookingService.bookTicket(booking);
            System.out.println("Booking successful for user: " + user.getId());
            return ticket;
        } catch (IllegalStateException e) {
            System.out.println("Booking failed for user: " + user.getId() + ". Reason: " + e.getMessage());
            return null;
        }
    }
}
