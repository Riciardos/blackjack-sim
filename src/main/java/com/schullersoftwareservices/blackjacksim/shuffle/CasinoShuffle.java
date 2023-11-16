package com.schullersoftwareservices.blackjacksim.shuffle;

import com.schullersoftwareservices.blackjacksim.model.Card;
import java.util.ArrayList;
import java.util.List;

public class CasinoShuffle implements ShuffleBehaviour {

  public List<Card> shuffle(List<Card> cards) {
    List<Card> newPile = new ArrayList<>(cards.size());

    while (cards.size() > 3) {
      List<Card> subPile = new ArrayList<>(cards.subList(0, 3));
      cards.subList(0,3).clear();
      newPile.addAll(subPile);
    }
    newPile.addAll(cards);

    return newPile;
  }

}
