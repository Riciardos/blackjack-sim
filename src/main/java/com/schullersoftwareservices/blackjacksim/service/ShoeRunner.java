package com.schullersoftwareservices.blackjacksim.service;

import static com.schullersoftwareservices.blackjacksim.model.Deck.DECK_SIZE;

import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import com.schullersoftwareservices.blackjacksim.model.Table;
import com.schullersoftwareservices.blackjacksim.shuffle.ShuffleBehaviour;
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
  private Integer cutPoint;
  private Table table;

  private String shuffleBehaviourName;

  public ShoeRunner(Integer numOfDecks, ShuffleBehaviour shuffleBehaviour, Integer cutPoint) {
    this.shoe = new Shoe(numOfDecks, shuffleBehaviour);
    this.visualiser = new Visualiser();
    this.cutPoint = cutPoint;
    this.shuffleBehaviourName = shuffleBehaviour.name();
    this.table = new Table(this.shoe);
  }

  public void analyseMultipleShoes(Integer numOfShoes) {

    List<Integer> topCounts = new ArrayList<>(numOfShoes);
    List<Integer> lowCounts = new ArrayList<>(numOfShoes);
    List<Integer> topTrueCounts = new ArrayList<>(numOfShoes);
    List<Integer> lowTrueCounts = new ArrayList<>(numOfShoes);
    List<Integer> cuttingCardCounts = new ArrayList<>(numOfShoes);
    List<Integer> cuttingCardTrueCounts = new ArrayList<>(numOfShoes);
    List<Integer> playerBustCounts = new ArrayList<>(numOfShoes);
    List<Integer> dealerBustCounts = new ArrayList<>(numOfShoes);


    log.info("Running single shoe a {} times...", numOfShoes);

    for (int i = 0; i < numOfShoes; i++) {
      shoe = runShoe(shoe);
      topCounts.add(shoe.getStatistics().getTopCount());
      lowCounts.add(shoe.getStatistics().getLowCount());
      topTrueCounts.add(shoe.getStatistics().getTopTrueCount());
      lowTrueCounts.add(shoe.getStatistics().getLowTrueCount());
      cuttingCardCounts.add(shoe.getCurrentCount());
      cuttingCardTrueCounts.add(shoe.getCurrentTrueCount() / cutPoint);
      playerBustCounts.add(shoe.getStatistics().getPlayerBustCount());
      dealerBustCounts.add(shoe.getStatistics().getDealerBustCount());
      shoe.reset();
    }

    float topAverage = (float) topCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float lowAverage = (float) lowCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float topTrueAverage = (float) topTrueCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float lowTrueAverage = (float) lowTrueCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float bustCountAverage = (float) playerBustCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    visualiser.setDataSet(DataSet.builder()
        .topCounts(topCounts)
        .lowCounts(lowCounts)
        .topTrueCounts(topTrueCounts)
        .lowTrueCounts(lowTrueCounts)
        .cuttingCardCounts(cuttingCardCounts)
        .cuttingCardTrueCounts(cuttingCardTrueCounts)
        .shuffleBehaviourName(shuffleBehaviourName)
            .playerBustCount(playerBustCounts)
            .dealerBustCount(dealerBustCounts)
        .build());
    visualiser.visualise();
    log.info("Average top count: {}", topAverage);
    log.info("Average low count: {}", lowAverage);
    log.info("Average top true count: {}", topTrueAverage);
    log.info("Average low true count: {}", lowTrueAverage);
    log.info("Average bust count: {}", bustCountAverage);
  }

  private Shoe runShoe(Shoe shoe) {
    shoe.shuffle();
    int cutPosition = cutPoint * DECK_SIZE;
    while (shoe.getAllCards().size() > cutPosition) {
      table.dealCards();
      table.playHands();
      log.debug("Current count: {}", shoe.getCurrentCount());
    }

    shoe.resetCards();
    log.debug("Count at cutting card: {}", shoe.getCurrentCount());
    log.debug("Top count: {}", shoe.getStatistics().getTopCount());
    log.debug("Low count: {}", shoe.getStatistics().getLowCount());
    return shoe;
  }
}
