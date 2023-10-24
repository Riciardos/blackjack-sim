package com.schullersoftwareservices.blackjacksim.model;

public interface Player {

  Action nextAction();
  void receiveCard(Card card);
  void playHand();
}
