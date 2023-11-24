package com.schullersoftwareservices.blackjacksim.strategy;


import com.schullersoftwareservices.blackjacksim.model.Action;
import com.schullersoftwareservices.blackjacksim.model.Card;

public class DealerStrategy implements Strategy {

  @Override
  public Action determingNextAction(Card dealerUpcard, Integer softTotal, Integer hardTotal) {
    Action nextAction = Action.HIT;
    if (softTotal > 17) {
      nextAction = Action.STAND;
    }
    if (softTotal > 21 && hardTotal < 17) {
      nextAction = Action.HIT;
    }
    return nextAction;
  }
}
