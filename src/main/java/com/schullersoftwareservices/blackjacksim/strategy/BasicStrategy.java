package com.schullersoftwareservices.blackjacksim.strategy;

import com.schullersoftwareservices.blackjacksim.model.Action;
import com.schullersoftwareservices.blackjacksim.model.Card;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicStrategy implements  Strategy {

  private Map<Integer, Map<Card, Action>> hardTotalLookup = new HashMap<>();
  private Map<Integer, Map<Card, Action>> softTotalLookup = new HashMap<>();

  public BasicStrategy() {
    setHardTotalLookup();
    setSoftTotalLookup();
  }

  @Override
  public Action determingNextAction(Card dealerUpcard, Integer softTotal, Integer hardTotal) {
    if (hardTotal >= 21) {
      return Action.STAND;
    }
    if (Objects.equals(softTotal, hardTotal) || softTotal > 21) {
      // no aces, use hard total lookup
      log.debug("hard total: {}", hardTotal);
      return hardTotalLookup.get(hardTotal).get(dealerUpcard);
    } else {
      log.debug("soft total: {}", softTotal);
      return softTotalLookup.get(softTotal).get(dealerUpcard);
    }
  }


  private void setHardTotalLookup() {
    hardTotalLookup.put(2, valueBelowNine());
    hardTotalLookup.put(3, valueBelowNine());
    hardTotalLookup.put(4, valueBelowNine());
    hardTotalLookup.put(5, valueBelowNine());
    hardTotalLookup.put(6, valueBelowNine());
    hardTotalLookup.put(7, valueBelowNine());
    hardTotalLookup.put(8, valueBelowNine());
    hardTotalLookup.put(9, valueNine());
    hardTotalLookup.put(10, valueTen());
    hardTotalLookup.put(11, valueEleven());
    hardTotalLookup.put(12, valueTwelve());
    hardTotalLookup.put(13, valueThirteen());
    hardTotalLookup.put(14, valueFourteen());
    hardTotalLookup.put(15, valueFifteen());
    hardTotalLookup.put(16, valueSixteen());
    hardTotalLookup.put(17, valueSeventeenOrHigher());
    hardTotalLookup.put(18, valueSeventeenOrHigher());
    hardTotalLookup.put(19, valueSeventeenOrHigher());
    hardTotalLookup.put(20, valueSeventeenOrHigher());
    hardTotalLookup.put(21, valueSeventeenOrHigher());
  }

  private void setSoftTotalLookup() {
    softTotalLookup.put(13, valueSoftThirteen());
    softTotalLookup.put(14, valueSoftFourteen());
    softTotalLookup.put(15, valueSoftFifteen());
    softTotalLookup.put(16, valueSoftSixteen());
    softTotalLookup.put(17, valueSoftSeventeen());
    softTotalLookup.put(18, valueSoftEighteen());
    softTotalLookup.put(19, valueSoftNineteen());
    softTotalLookup.put(20, valueSoftTwenty());
    softTotalLookup.put(21, valueSoftTwenty());
  }


  private Map<Card, Action> valueBelowNine() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.HIT);
    value.put(Card.FOUR, Action.HIT);
    value.put(Card.FIVE, Action.HIT);
    value.put(Card.SIX, Action.HIT);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueNine() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.DOUBLE);
    value.put(Card.FOUR, Action.DOUBLE);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueTen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.DOUBLE);
    value.put(Card.THREE, Action.DOUBLE);
    value.put(Card.FOUR, Action.DOUBLE);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.DOUBLE);
    value.put(Card.EIGHT, Action.DOUBLE);
    value.put(Card.NINE, Action.DOUBLE);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueEleven() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.DOUBLE);
    value.put(Card.THREE, Action.DOUBLE);
    value.put(Card.FOUR, Action.DOUBLE);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.DOUBLE);
    value.put(Card.EIGHT, Action.DOUBLE);
    value.put(Card.NINE, Action.DOUBLE);
    value.put(Card.TEN, Action.DOUBLE);
    value.put(Card.JACK, Action.DOUBLE);
    value.put(Card.QUEEN, Action.DOUBLE);
    value.put(Card.KING, Action.DOUBLE);
    value.put(Card.ACE, Action.DOUBLE);
    return value;
  }

  private Map<Card, Action> valueTwelve() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.HIT);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueThirteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueFourteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueFifteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSixteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSeventeenOrHigher() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.STAND);
    value.put(Card.EIGHT, Action.STAND);
    value.put(Card.NINE, Action.STAND);
    value.put(Card.TEN, Action.STAND);
    value.put(Card.JACK, Action.STAND);
    value.put(Card.QUEEN, Action.STAND);
    value.put(Card.KING, Action.STAND);
    value.put(Card.ACE, Action.STAND);
    return value;
  }

  private Map<Card, Action> valueSoftThirteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.HIT);
    value.put(Card.FOUR, Action.HIT);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSoftFourteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.HIT);
    value.put(Card.FOUR, Action.HIT);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSoftFifteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.HIT);
    value.put(Card.FOUR, Action.DOUBLE);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSoftSixteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.HIT);
    value.put(Card.FOUR, Action.DOUBLE);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSoftSeventeen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.HIT);
    value.put(Card.THREE, Action.DOUBLE);
    value.put(Card.FOUR, Action.DOUBLE);
    value.put(Card.FIVE, Action.DOUBLE);
    value.put(Card.SIX, Action.DOUBLE);
    value.put(Card.SEVEN, Action.HIT);
    value.put(Card.EIGHT, Action.HIT);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSoftEighteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.DOUBLE_OTHERWISE_STAND);
    value.put(Card.THREE, Action.DOUBLE_OTHERWISE_STAND);
    value.put(Card.FOUR, Action.DOUBLE_OTHERWISE_STAND);
    value.put(Card.FIVE, Action.DOUBLE_OTHERWISE_STAND);
    value.put(Card.SIX, Action.DOUBLE_OTHERWISE_STAND);
    value.put(Card.SEVEN, Action.STAND);
    value.put(Card.EIGHT, Action.STAND);
    value.put(Card.NINE, Action.HIT);
    value.put(Card.TEN, Action.HIT);
    value.put(Card.JACK, Action.HIT);
    value.put(Card.QUEEN, Action.HIT);
    value.put(Card.KING, Action.HIT);
    value.put(Card.ACE, Action.HIT);
    return value;
  }

  private Map<Card, Action> valueSoftNineteen() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.DOUBLE_OTHERWISE_STAND);
    value.put(Card.SEVEN, Action.STAND);
    value.put(Card.EIGHT, Action.STAND);
    value.put(Card.NINE, Action.STAND);
    value.put(Card.TEN, Action.STAND);
    value.put(Card.JACK, Action.STAND);
    value.put(Card.QUEEN, Action.STAND);
    value.put(Card.KING, Action.STAND);
    value.put(Card.ACE, Action.STAND);
    return value;
  }

  private Map<Card, Action> valueSoftTwenty() {
    Map<Card, Action> value = new HashMap<>();
    value.put(Card.TWO, Action.STAND);
    value.put(Card.THREE, Action.STAND);
    value.put(Card.FOUR, Action.STAND);
    value.put(Card.FIVE, Action.STAND);
    value.put(Card.SIX, Action.STAND);
    value.put(Card.SEVEN, Action.STAND);
    value.put(Card.EIGHT, Action.STAND);
    value.put(Card.NINE, Action.STAND);
    value.put(Card.TEN, Action.STAND);
    value.put(Card.JACK, Action.STAND);
    value.put(Card.QUEEN, Action.STAND);
    value.put(Card.KING, Action.STAND);
    value.put(Card.ACE, Action.STAND);
    return value;
  }

}
