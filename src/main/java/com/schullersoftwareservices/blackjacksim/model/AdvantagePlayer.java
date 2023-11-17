package com.schullersoftwareservices.blackjacksim.model;

import com.schullersoftwareservices.blackjacksim.strategy.BasicStrategy;
import com.schullersoftwareservices.blackjacksim.strategy.Strategy;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvantagePlayer implements Player {

  List<Card> cards = new ArrayList<>();
  int softValue;
  int hardValue;
  Action nextAction;
  Shoe shoe;
  Dealer dealer;

  Strategy strategy;

  public AdvantagePlayer(Dealer dealer, Shoe shoe) {
    this.dealer = dealer;
    this.shoe = shoe;
    this.strategy = new BasicStrategy();
  }

  @Override
  public void receiveCard(Card card) {
    cards.add(card);
    softValue += card.getSoftValue();
    hardValue += card.getHardValue();
  }

  @Override
  public void playHand() {

    Card dealerUpCard = dealer.getUpCard();
    nextAction = strategy.determingNextAction(dealerUpCard, softValue, hardValue);

    while (nextAction == Action.HIT || nextAction == Action.DOUBLE || nextAction == Action.DOUBLE_OTHERWISE_STAND) {
      receiveCard(shoe.getNextCard());
      nextAction = strategy.determingNextAction(dealerUpCard, softValue, hardValue);
    }

    log.info("Cards: {}", cards);
    log.info("Player values: {}, {}", softValue, hardValue);
    log.info("Dealer upcard : {}", dealerUpCard);
    reset();
  }

  private void reset() {
    softValue = 0;
    hardValue = 0;
    cards = new ArrayList<>(10); // most likely never need to hit more than 10 cards
    nextAction = Action.HIT;
  }
}
