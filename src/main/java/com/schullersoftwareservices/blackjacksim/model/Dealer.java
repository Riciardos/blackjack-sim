package com.schullersoftwareservices.blackjacksim.model;

import java.util.ArrayList;
import java.util.List;

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

  @Override
  public Action nextAction() {
    return nextAction;
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
  public void playHand() {
    reset();
    while(nextAction == Action.HIT) {
      receiveCard(shoe.getNextCard());
    }
    System.out.println(String.format("Cards: %s", cards));
    System.out.println(String.format("Dealer value: %d %d", softValue, hardValue));
  }

  private void reset() {
    softValue = 0;
    hardValue = 0;
    cards = new ArrayList<>();
    nextAction = Action.HIT;
  }
}
