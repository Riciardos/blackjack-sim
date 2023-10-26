package com.schullersoftwareservices.blackjacksim.model;

import static com.schullersoftwareservices.blackjacksim.model.Deck.DECK_SIZE;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Shoe {

  List<Card> allCards;
  List<Deck> decks;
  Integer currentCount;
  Integer topCount;
  Integer lowCount;

  public Shoe(Integer numOfDecks) {
    decks = new ArrayList<>(numOfDecks);
    allCards = new ArrayList<>(numOfDecks * DECK_SIZE);
    currentCount = 0;
    topCount = 0;
    lowCount = 0;

    for (int i = 0; i<numOfDecks; i++) {
      Deck deck = new Deck();
      decks.add(deck);
      for(Card card: deck.getCards()) {
        allCards.add(card);
      }
    }
  }

  public Card getNextCard() {
    Card card = allCards.remove(0);
    updateCount(card);
    return card;
  }

  public void randomShuffle() {
    List<Card> newPile = new ArrayList<>(allCards.size());
    int i = (int) Math.floor(Math.random() * DECK_SIZE);
    while ( 0 < allCards.size()) {
      newPile.add(allCards.remove(i));
      i = (int) Math.floor(Math.random() * allCards.size());
    }
    allCards = newPile;
  }

  private void updateCount(Card card) {
    currentCount += card.getCountValue();
    if (currentCount > topCount) {
      topCount = currentCount;
    }
    if (currentCount < lowCount) {
      lowCount = currentCount;
    }
  }
}
