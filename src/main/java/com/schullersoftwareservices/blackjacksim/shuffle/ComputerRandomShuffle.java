package com.schullersoftwareservices.blackjacksim.shuffle;

import com.schullersoftwareservices.blackjacksim.model.Card;
import java.util.ArrayList;
import java.util.List;

public class ComputerRandomShuffle implements ShuffleBehaviour {

  @Override
  public List<Card> shuffle(List<Card> cards) {
    List<Card> newPile = new ArrayList<>(cards.size());
    int i = (int) Math.floor(Math.random() * cards.size());
    while (0 < cards.size()) {
      newPile.add(cards.remove(i));
      i = (int) Math.floor(Math.random() * cards.size());
    }
    return newPile;
  }
}
