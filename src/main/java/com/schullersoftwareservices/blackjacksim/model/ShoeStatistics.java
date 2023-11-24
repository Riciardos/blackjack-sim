package com.schullersoftwareservices.blackjacksim.model;

import static com.schullersoftwareservices.blackjacksim.model.Card.ACE;
import static com.schullersoftwareservices.blackjacksim.model.Card.SIX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ShoeStatistics {

  Integer topCount;
  Integer lowCount;
  Integer topTrueCount;
  Integer lowTrueCount;
  Integer playerBustCount;
  Integer dealerBustCount;

  List<Integer> playerNumOfCardsInHand;
  List<Integer> dealerNumOfCardsInHand;
  Map<Card, BustPercentage> bustPercentages;

  public ShoeStatistics() {
    reset();
  }


  public void registerPlayerBust() {
    playerBustCount += 1 ;
  }

  public void registerDealerBust() {
    dealerBustCount += 1 ;
  }

  public void reset() {
    topCount = 0;
    lowCount = 0;
    topTrueCount = 0;
    lowTrueCount = 0;
    playerBustCount = 0;
    dealerBustCount = 0;
    playerNumOfCardsInHand = new ArrayList<>();
    dealerNumOfCardsInHand = new ArrayList<>();
    bustPercentages = new HashMap<>();

    for (Card card: Card.values()) {
      bustPercentages.put(card, new BustPercentage(card, 0, 0));
    }

  }

  public void registerPlayerNumOfCardsInHand(Hand hand) {
    playerNumOfCardsInHand.add(hand.getCards().size());
  }

  public void registerDealerNumOfCardsInHand(Hand hand) {
    dealerNumOfCardsInHand.add(hand.getCards().size());
    if (hand.isBust()) {
      bustPercentages.get(hand.getCards().get(0)).registerBust();
    } else {
      bustPercentages.get(hand.getCards().get(0)).registerNotBust();
    }
  }

  public void updateTopCounts(Integer currentCount, Integer currentTrueCount) {
    if (currentCount > topCount) {
      topCount = currentCount;
    }
    if (currentTrueCount > topTrueCount) {
      topTrueCount = currentTrueCount;
    }
  }

  public void updateLowCounts(Integer currentCount, Integer currentTrueCount) {
    if (currentCount < lowCount) {
      lowCount = currentCount;
    }
    if (currentTrueCount < lowTrueCount) {
      lowTrueCount = currentTrueCount;
    }
  }
}
