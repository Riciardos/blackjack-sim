package com.schullersoftwareservices.blackjacksim.strategy;

import com.schullersoftwareservices.blackjacksim.model.Action;
import com.schullersoftwareservices.blackjacksim.model.Card;

public interface Strategy {

  Action determingNextAction(Card dealerUpcard, Integer softTotal, Integer hardTotal);

}
