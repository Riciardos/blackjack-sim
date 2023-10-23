package com.schullersoftwareservices.blackjacksim.model;

import static com.schullersoftwareservices.blackjacksim.model.Deck.DECK_SIZE;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Shoe {

  List<Card> allCards;
  List<Deck> decks;

  public Shoe(Integer numOfDecks) {
    decks = new ArrayList<>(numOfDecks);
    allCards = new ArrayList<>(numOfDecks * DECK_SIZE);

    for (int i = 0; i<numOfDecks; i++) {
      Deck deck = new Deck();
      decks.add(deck);
      for(Card card: deck.getCards()) {
        allCards.add(card);
      }
    }
  }

  void randomShuffle() {
    List<Card> newPile = new ArrayList<>(allCards.size());
    int i = (int) Math.floor(Math.random() * DECK_SIZE);
    while ( 0 < allCards.size()) {
      newPile.add(allCards.remove(i));
      i = (int) Math.floor(Math.random() * allCards.size());
    }
    allCards = newPile;
  }
}
