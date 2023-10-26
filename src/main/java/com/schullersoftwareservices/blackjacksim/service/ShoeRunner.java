package com.schullersoftwareservices.blackjacksim.service;

import com.schullersoftwareservices.blackjacksim.model.Dealer;
import com.schullersoftwareservices.blackjacksim.model.Shoe;

import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoeRunner {
  public void analyseMultipleShoes(Integer numOfShoes) {

    AtomicReference<Integer> topCounts = new AtomicReference<>(0);
    AtomicReference<Integer> lowCounts = new AtomicReference<>(0);

    log.info("Starting shoe run with {} shoes...", numOfShoes);

    for (int i=0; i<numOfShoes; i++) {
      new Thread(() -> {
        Shoe shoe = runShoe();
        topCounts.updateAndGet(v -> v + shoe.getTopCount());
        lowCounts.updateAndGet(v -> v + shoe.getLowCount());
      }).start();
    }

    float topAverage = topCounts.get() / numOfShoes;
    float lowAverage = lowCounts.get() / numOfShoes;
    log.info("Average top count: {}", topAverage);
    log.info("Average low count: {}", lowAverage);
  }

  private Shoe runShoe() {
    Shoe shoe = new Shoe(8);
    shoe.randomShuffle();
    Dealer dealer = new Dealer(shoe);
    while (shoe.getAllCards().size() > 52) {
      dealer.playHand();
      log.debug("Current count: {}", shoe.getCurrentCount());
    }

    log.debug("Top count: {}", shoe.getTopCount());
    log.debug("Low count: {}", shoe.getLowCount());
    return shoe;
  }
}
