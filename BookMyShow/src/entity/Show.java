package entity;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

// public class Show {
//   private final String time;
//   private final Seat[][] seats;
//   private final ReentrantLock[][] seatLocks;

//   public Show(String time, int rows, int cols) {
//     this.time = time;
//     seats = new Seat[rows][cols];
//     seatLocks = new ReentrantLock[rows][cols];

//     for (int i = 0; i < rows; i++) {
//       for (int j = 0; j < cols; j++) {
//         seats[i][j] = new Seat(i, j);
//         seatLocks[i][j] = new ReentrantLock();
//       }
//     }
//   }

//   public String getTime() {
//     return time;
//   }

//   public boolean bookSeats(List<int[]> seatChoices) {
//     // Lock all seats
//     for (int[] choice : seatChoices) {
//       if (!seatLocks[choice[0]][choice[1]].tryLock()) {
//         releaseLocks(seatChoices);
//         return false;
//       }
//     }

//     // Validate and book
//     for (int[] choice : seatChoices) {
//       if (seats[choice[0]][choice[1]].isBooked()) {
//         releaseLocks(seatChoices);
//         return false;
//       }
//       seats[choice[0]][choice[1]].setBooked(true);
//     }

//     releaseLocks(seatChoices);
//     return true;
//   }

//   private void releaseLocks(List<int[]> seatChoices) {
//     for (int[] choice : seatChoices) {
//       seatLocks[choice[0]][choice[1]].unlock();
//     }
//   }
// }


public class Show {
  private final String time;
  private final Seat[][] seats;

  public Show(String time, int rows, int cols) {
    this.time = time;
    this.seats = new Seat[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        seats[i][j] = new Seat();
      }
    }
  }

  public synchronized boolean bookSeats(List<int[]> seatChoices) {
    for (int[] choice : seatChoices) {
      if (choice[0] >= seats.length || choice[1] >= seats[0].length || seats[choice[0]][choice[1]].isBooked()) {
        return false; // If any seat is already booked
      }
    }

    // Book all selected seats
    for (int[] choice : seatChoices) {
      seats[choice[0]][choice[1]].book();
    }
    return true;
  }
}