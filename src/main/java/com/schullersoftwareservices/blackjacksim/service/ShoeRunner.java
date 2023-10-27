package com.schullersoftwareservices.blackjacksim.service;

import static com.schullersoftwareservices.blackjacksim.model.Deck.DECK_SIZE;

import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Dealer;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import com.schullersoftwareservices.blackjacksim.shuffle.ComputerRandomShuffle;
import com.schullersoftwareservices.blackjacksim.visualise.DataSet;
import com.schullersoftwareservices.blackjacksim.visualise.Visualiser;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ShoeRunner {

  private Shoe shoe;
  private Visualiser visualiser;

  public ShoeRunner(Integer numOfDecks) {
    this.shoe = new Shoe(numOfDecks, new ComputerRandomShuffle());
    this.visualiser = new Visualiser();
  }

  public void analyseMultipleShoes(Integer numOfShoes) {

    List<Integer> topCounts = new ArrayList<>(numOfShoes * DECK_SIZE);
    List<Integer> lowCounts = new ArrayList<>(numOfShoes * DECK_SIZE);
    List<Integer> topTrueCounts = new ArrayList<>(numOfShoes * DECK_SIZE);
    List<Integer> lowTrueCounts = new ArrayList<>(numOfShoes * DECK_SIZE);

    log.info("Starting shoe run with {} shoes...", numOfShoes);

    for (int i = 0; i < numOfShoes; i++) {
      shoe = runShoe(shoe);
      topCounts.add(shoe.getTopCount());
      lowCounts.add(shoe.getLowCount());
      topTrueCounts.add(shoe.getTopTrueCount());
      lowTrueCounts.add(shoe.getLowTrueCount());
      shoe.reset();
    }

    float topAverage = (float) topCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float lowAverage = (float) lowCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float topTrueAverage = (float) topTrueCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float lowTrueAverage = (float) lowTrueCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    visualiser.setDataSet(DataSet.builder()
        .topCounts(topCounts)
        .lowCounts(lowCounts)
        .topTrueCounts(topTrueCounts)
        .lowTrueCounts(lowTrueCounts)
        .build());
    visualiser.visualise();
    log.info("Average top count: {}", topAverage);
    log.info("Average low count: {}", lowAverage);
    log.info("Average top true count: {}", topTrueAverage);
    log.info("Average low true count: {}", lowTrueAverage);
  }

  private Shoe runShoe(Shoe shoe) {
    shoe.shuffle();
    Dealer dealer = new Dealer(shoe);
    int cutPosition = shoe.getAllCards().size() / 4;
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
