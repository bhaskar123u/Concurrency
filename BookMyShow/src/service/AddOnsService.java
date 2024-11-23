package service;

import entity.User;

public class AddOnsService {
  public void addAddOns(User user, String addon) {
    user.getAddOns().add(addon);
  }
}
