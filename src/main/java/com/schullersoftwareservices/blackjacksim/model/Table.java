package com.schullersoftwareservices.blackjacksim.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

  Dealer dealer;

  List<Player> players;

  Shoe shoe;

  public Table(Shoe shoe) {
    this.shoe = shoe;
    this.dealer = new Dealer(shoe);
    this.players = new ArrayList<>();

    players.add(new AdvantagePlayer(dealer, shoe));
  }

  public void dealCards() {
    for (int i = 0; i < 2; i++) {
      for (Player player : players) {
        player.receiveCard(shoe.getNextCard());
      }
      dealer.receiveCard(shoe.getNextCard());
    }
  }

  public void playHands() {
    for (Player player : players) {
      player.playHand();
    }
    dealer.playHand();
  }
}
