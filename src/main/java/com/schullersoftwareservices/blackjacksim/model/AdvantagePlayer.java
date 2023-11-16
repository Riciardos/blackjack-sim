package com.schullersoftwareservices.blackjacksim.model;

import java.util.List;


public class AdvantagePlayer implements Player {

  List<Card> cards;
  int softValue;
  int hardValue;
  Action nextAction;
  Shoe shoe;
  Dealer dealer;


  @Override
  public void receiveCard(Card card) {
    cards.add(card);
    softValue += card.getSoftValue();
    hardValue += card.getHardValue();
  }

  @Override
  public void playHand() {

    Card dealerUpCard = dealer.getUpCard();
    if (softValue == hardValue) {
      // no aces

    }

  }
}
