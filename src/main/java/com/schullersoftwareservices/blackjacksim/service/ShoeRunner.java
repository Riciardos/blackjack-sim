package com.schullersoftwareservices.blackjacksim.service;

import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Dealer;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import com.schullersoftwareservices.blackjacksim.shuffle.ComputerRandomShuffle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ShoeRunner {

  private Shoe shoe;

  public ShoeRunner() {
    this.shoe = new Shoe(8, new ComputerRandomShuffle());
  }

  public void analyseMultipleShoes(Integer numOfShoes) {

    AtomicReference<Integer> topCounts = new AtomicReference<>(0);
    AtomicReference<Integer> lowCounts = new AtomicReference<>(0);

    log.info("Starting shoe run with {} shoes...", numOfShoes);

    for (int i = 0; i < numOfShoes; i++) {
      shoe = runShoe(shoe);
      topCounts.updateAndGet(v -> v + shoe.getTopCount());
      lowCounts.updateAndGet(v -> v + shoe.getLowCount());
      shoe.reset();
    }

    float topAverage = (float) topCounts.get() / numOfShoes;
    float lowAverage = (float) lowCounts.get() / numOfShoes;
    log.info("Average top count: {}", topAverage);
    log.info("Average low count: {}", lowAverage);
  }

  private Shoe runShoe(Shoe shoe) {
    shoe.shuffle();
    Dealer dealer = new Dealer(shoe);
    int cutPosition = shoe.getAllCards().size() / 2;
    List<Card> initialCards = new ArrayList<>(shoe.getAllCards());
    while (shoe.getAllCards().size() > cutPosition) {
      dealer.playHand();
      log.debug("Current count: {}", shoe.getCurrentCount());
    }
    shoe.setAllCards(initialCards);
    log.info("Top count: {}", shoe.getTopCount());
    log.info("Low count: {}", shoe.getLowCount());
    return shoe;
  }
}
