package entity;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seat {
  private boolean isBooked;
  private final Lock lock = new ReentrantLock();

  public boolean book() {
    if (lock.tryLock()) {
      try {
        if (!isBooked) {
          isBooked = true;
          return true;
        }
      } finally {
        lock.unlock();
      }
    }
    return false;
  }

  public void release() {
    isBooked = false;
  }

  public boolean isBooked() {
    return isBooked;
  }
}