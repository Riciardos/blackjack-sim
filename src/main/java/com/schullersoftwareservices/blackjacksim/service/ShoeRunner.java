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

  public ShoeRunner(Integer numOfDecks, ShuffleBehaviour shuffleBehaviour, Integer cutPoint,
      Integer numOfPlayers) {
    this.shoe = new Shoe(numOfDecks, shuffleBehaviour);
    this.visualiser = new Visualiser();
    this.cutPoint = cutPoint;
    this.shuffleBehaviourName = shuffleBehaviour.name();
    this.table = new Table(this.shoe, numOfPlayers);
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
    List<List<Integer>> playerHandSize = new ArrayList<>(numOfShoes);
    List<List<Integer>> dealerHandSize = new ArrayList<>(numOfShoes);
    List<Float> dealer6BustPercentage = new ArrayList<>(numOfShoes);

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
      playerHandSize.add(shoe.getStatistics().getPlayerNumOfCardsInHand());
      dealerHandSize.add(shoe.getStatistics().getDealerNumOfCardsInHand());
      if (shoe.getStatistics().getBustPercentages().get(Card.SIX).getBustCount() > 0) {
        dealer6BustPercentage.add(
             (float) shoe.getStatistics().getBustPercentages().get(Card.SIX).getBustCount() / (
                shoe.getStatistics().getBustPercentages().get(Card.SIX).getBustCount() + shoe.getStatistics().getBustPercentages().get(Card.SIX).getNotBustCount()));
      }
      if (shoe.getStatistics().getBustPercentages().get(Card.SIX).getNotBustCount() > 0) {
        dealer6BustPercentage.add(0f);
      }
      shoe.reset();
    }

    float topAverage = (float) topCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float lowAverage = (float) lowCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float topTrueAverage = (float) topTrueCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float lowTrueAverage = (float) lowTrueCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float playerBustCountAverage =
        (float) playerBustCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float dealerBustCountAverage =
        (float) dealerBustCounts.stream().reduce(0, Integer::sum) / numOfShoes;
    float dealer6BustPercentageAverage =
        dealer6BustPercentage.stream().reduce(0f, Float::sum) / numOfShoes;
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
        .playerHandSize(playerHandSize)
        .dealerHandSize(dealerHandSize)
            .dealer6BustPercentage(dealer6BustPercentage)
        .build());
    visualiser.visualise();
    log.info("Average top count: {}", topAverage);
    log.info("Average low count: {}", lowAverage);
    log.info("Average top true count: {}", topTrueAverage);
    log.info("Average low true count: {}", lowTrueAverage);
    log.info("Average player bust count: {}", playerBustCountAverage);
    log.info("Average dealer bust count: {}", dealerBustCountAverage);
    log.info("Average dealer 6 percentage: {}", dealer6BustPercentageAverage);
  }

  private Shoe runShoe(Shoe shoe) {
    shoe.shuffle();
    int cutPosition = cutPoint * DECK_SIZE;
    while (shoe.getAllCards().size() > cutPosition) {
      table.dealCards();
      table.playHands();
      table.registerOutcomes();
      log.debug("Current count: {}", shoe.getCurrentCount());
    }

    shoe.resetCards();
    log.debug("Count at cutting card: {}", shoe.getCurrentCount());
    log.debug("Top count: {}", shoe.getStatistics().getTopCount());
    log.debug("Low count: {}", shoe.getStatistics().getLowCount());
    return shoe;
  }
}
