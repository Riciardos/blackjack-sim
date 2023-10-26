package com.schullersoftwareservices.blackjacksim.model;

import com.schullersoftwareservices.blackjacksim.shuffle.ShuffleBehaviour;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Deck {

  public static Integer DECK_SIZE = 52;
  List<Card> cards;

  private ShuffleBehaviour shuffleBehaviour;

  public Deck(ShuffleBehaviour shuffleBehaviour) {
    this.cards = new ArrayList<>(DECK_SIZE);
    for (int i = 0; i < 4; i++) {
      for (Card card: Card.values()) {
        cards.add(card);
      }
    }
    this.shuffleBehaviour = shuffleBehaviour;
    this.cards = shuffleBehaviour.shuffle(cards);
  }
}
