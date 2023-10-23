package com.schullersoftwareservices.blackjacksim.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Deck {

  public static Integer DECK_SIZE = 52;
  List<Card> cards;

  public Deck() {
    this.cards = new ArrayList<>(DECK_SIZE);
    for (int i = 0; i < 4; i++) {
      for (Card card: Card.values()) {
        cards.add(card);
      }
    }
    randomShuffle();
  }

  public void randomShuffle() {
    List<Card> newPile = new ArrayList<>(DECK_SIZE);
    int i = (int) Math.floor(Math.random() * DECK_SIZE);
    while ( 0 < cards.size()) {
      newPile.add(cards.remove(i));
      i = (int) Math.floor(Math.random() * cards.size());
    }
    this.cards = newPile;
  }

}
