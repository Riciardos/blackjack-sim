package com.schullersoftwareservices.blackjacksim.model;

import lombok.Data;

@Data
public class ShoeStatistics {

  Integer topCount;
  Integer lowCount;
  Integer topTrueCount;
  Integer lowTrueCount;
  Integer playerBustCount;
  Integer dealerBustCount;

  public ShoeStatistics() {
    reset();
  }


  public void registerPlayerBust() {
    playerBustCount++ ;
  }

  public void registerDealerBust() {
    dealerBustCount++ ;
  }

  public void reset() {
    topCount = 0;
    lowCount = 0;
    topTrueCount = 0;
    lowTrueCount = 0;
    playerBustCount = 0;
    dealerBustCount = 0;
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
