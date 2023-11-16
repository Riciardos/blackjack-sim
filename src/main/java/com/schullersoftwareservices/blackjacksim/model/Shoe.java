package com.schullersoftwareservices.blackjacksim.model;

import static com.schullersoftwareservices.blackjacksim.model.Deck.DECK_SIZE;

import com.schullersoftwareservices.blackjacksim.shuffle.ComputerRandomShuffle;
import com.schullersoftwareservices.blackjacksim.shuffle.ShuffleBehaviour;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Shoe {

  List<Card> allCards;
  List<Deck> decks;
  Integer currentCount;
  Integer topCount;
  Integer lowCount;
  Integer currentTrueCount;
  Integer topTrueCount;
  Integer lowTrueCount;

  ShuffleBehaviour shuffleBehaviour;

  public Shoe(Integer numOfDecks, ShuffleBehaviour shuffleBehaviour) {
    decks = new ArrayList<>(numOfDecks);
    allCards = new ArrayList<>(numOfDecks * DECK_SIZE);
    currentCount = 0;
    topCount = 0;
    lowCount = 0;
    currentTrueCount = 0;
    topTrueCount = 0;
    lowTrueCount = 0;

    this.shuffleBehaviour = shuffleBehaviour;

    for (int i = 0; i<numOfDecks; i++) {
      Deck deck = new Deck(new ComputerRandomShuffle());
      decks.add(deck);
      allCards.addAll(deck.getCards());
    }
    allCards = shuffleBehaviour.shuffle(allCards);
  }

  public Card getNextCard() {
    Card card = allCards.remove(0);
    updateCount(card, allCards.size());
    return card;
  }

  public void shuffle() {
    allCards = shuffleBehaviour.shuffle(allCards);
  }

  public void reset() {
    currentCount = 0;
    topCount = 0;
    lowCount = 0;
    currentTrueCount = 0;
    topTrueCount = 0;
    lowTrueCount = 0;
  }

  private void updateCount(Card card, Integer numOfCardsLeft) {
    currentCount += card.getCountValue();
    currentTrueCount = currentCount / (numOfCardsLeft / DECK_SIZE);
    if (currentCount > topCount) {
      topCount = currentCount;
    }
    if (currentCount < lowCount) {
      lowCount = currentCount;
    }

    if (currentTrueCount > topTrueCount) {
      topTrueCount = currentTrueCount;
    }
    if (currentTrueCount < lowTrueCount) {
      lowTrueCount = currentTrueCount;
    }
  }
}
