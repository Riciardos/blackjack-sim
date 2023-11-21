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

  Integer numOfDecks;
  List<Card> allCards;
  List<Card> discardPile;
  List<Deck> decks;
  Integer currentCount;
  Integer currentTrueCount;

  ShoeStatistics statistics;

  ShuffleBehaviour shuffleBehaviour;

  public Shoe(Integer numOfDecks, ShuffleBehaviour shuffleBehaviour) {
    this.numOfDecks = numOfDecks;
    decks = new ArrayList<>(numOfDecks);
    allCards = new ArrayList<>(numOfDecks * DECK_SIZE);
    discardPile = new ArrayList<>(numOfDecks * DECK_SIZE);
    currentCount = 0;
    currentTrueCount = 0;
    statistics = new ShoeStatistics();
    this.shuffleBehaviour = shuffleBehaviour;

    for (int i = 0; i < numOfDecks; i++) {
      Deck deck = new Deck(new ComputerRandomShuffle());
      decks.add(deck);
      allCards.addAll(deck.getCards());
    }
    allCards = shuffleBehaviour.shuffle(allCards);
  }

  public Card getNextCard() {
    Card card = allCards.remove(0);
    updateCounts(card, allCards.size());
    return card;
  }

  public void shuffle() {
    allCards = shuffleBehaviour.shuffle(allCards);
  }

  public void addToDiscardPile(List<Card> cards) {
    discardPile.addAll(cards);
  }

  public void reset() {
    currentCount = 0;
    currentTrueCount = 0;
    statistics.reset();
  }

  public void resetCards() {
    allCards.addAll(discardPile);
    discardPile = new ArrayList<>(numOfDecks * DECK_SIZE);
  }

  private void updateCounts(Card card, Integer numOfCardsLeft) {
    updateCurrentCounts(card, numOfCardsLeft);
    statistics.updateTopCounts(currentCount, currentTrueCount);
    statistics.updateLowCounts(currentCount, currentTrueCount);

  }

  private void updateCurrentCounts(Card card, Integer numOfCardsLeft) {
    currentCount += card.getCountValue();
    currentTrueCount = currentCount / (numOfCardsLeft / DECK_SIZE);
  }
}
