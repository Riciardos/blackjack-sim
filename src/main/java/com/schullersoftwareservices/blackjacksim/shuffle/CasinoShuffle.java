package com.schullersoftwareservices.blackjacksim.shuffle;

import com.schullersoftwareservices.blackjacksim.model.Card;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CasinoShuffle implements ShuffleBehaviour {

  public List<Card> shuffle(List<Card> cards) {
    return sloppyFaroShuffle(cards);
  }

  private List<Card> sloppyFaroShuffle(List<Card> cards) {
    List<Card> newPile = new ArrayList<>(cards.size());

    // split in 2
    List<Card> leftHalf = cards.subList(0, cards.size() / 2);
    List<Card> rightHalf = cards.subList(cards.size()/ 2 , cards.size());

    Iterator<Card> rightIterator = rightHalf.iterator();
    Iterator<Card> leftIterator = leftHalf.iterator();
    int switchCounter = 0;

    while (leftIterator.hasNext() || rightIterator.hasNext()) {
      if (!leftIterator.hasNext()) {
        while (rightIterator.hasNext()) {
          newPile.add(rightIterator.next());
        }
        break;
      }
      if (!rightIterator.hasNext()) {
        while (leftIterator.hasNext()) {
          newPile.add(leftIterator.next());
        }
        break;
      }
      if (switchCounter % 2 == 0) {
        for (int i = 0 ; i < 4 ; i++){
          newPile.add(leftIterator.next());
        }
      } else {
        for (int i = 0 ; i < 4 ; i++){
          newPile.add(rightIterator.next());
        }
      }
      switchCounter ++;
    }
    return newPile;
  }

}
