package com.schullersoftwareservices.blackjacksim.model;

public interface Player {

  void receiveCard(Card card);

  Hand playHand();
}
