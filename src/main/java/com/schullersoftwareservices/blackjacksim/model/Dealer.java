package com.schullersoftwareservices.blackjacksim.model;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dealer implements Player {

  List<Card> cards;
  int softValue;
  int hardValue;
  Action nextAction;
  Shoe shoe;

  public Dealer(Shoe shoe) {
    cards = new ArrayList<>();
    softValue = 0;
    hardValue = 0;
    nextAction = Action.HIT;
    this.shoe = shoe;
  }

  public Card getUpCard() {
    return cards.get(0);
  }

  @Override
  public void receiveCard(Card card) {
    cards.add(card);
    softValue += card.getSoftValue();
    hardValue += card.getHardValue();
    if (softValue > 17) {
      nextAction = Action.STAND;
    }
    if (softValue > 21 && hardValue < 17) {
      nextAction = Action.HIT;
    }
  }

  @Override
  public Hand playHand() {
    while (nextAction == Action.HIT) {
      receiveCard(shoe.getNextCard());
    }
    log.debug("Cards: {}", cards);
    log.debug("Dealer value: {}, {}", softValue, hardValue);
    Hand hand = Hand.builder().cards(cards).softTotal(softValue).hardTotal(hardValue).build();
    reset();
    return hand;
  }

  private void reset() {
    softValue = 0;
    hardValue = 0;
    cards = new ArrayList<>(10); // most likely never need to hit more than 10 cards
    nextAction = Action.HIT;
  }
}
