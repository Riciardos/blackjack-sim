package com.schullersoftwareservices.blackjacksim.shuffle;

import com.schullersoftwareservices.blackjacksim.model.Card;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CasinoShuffle implements ShuffleBehaviour {

  public List<Card> shuffle(List<Card> cards) {
    return sloppyFaroShuffle(cards);
  }

  public String name() {
    return "CasinoShuffle";
  }

  private List<Card> sloppyFaroShuffle(List<Card> cards) {
    List<Card> newPile = new ArrayList<>(cards.size());

    // split in 2
    List<Card> leftHalf = cards.subList(0, cards.size() / 2);
    List<Card> rightHalf = cards.subList(cards.size() / 2, cards.size());

    Iterator<Card> rightIterator = rightHalf.iterator();
    Iterator<Card> leftIterator = leftHalf.iterator();
    int switchCounter = 0;

    while (leftIterator.hasNext() || rightIterator.hasNext()) {

      // add rest of pile if the other one is empty
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
      // alternate adding 0-4 cards from either pile
      double numOfCardsToBeAdded = Math.floor(Math.random() * 5);
      log.debug("Num of cards to be added: {}", numOfCardsToBeAdded);
      int counter = 0;
      if (switchCounter % 2 == 0) {
        while (leftIterator.hasNext() && counter < numOfCardsToBeAdded) {
          newPile.add(leftIterator.next());
          counter++;
        }
      } else {
        while (rightIterator.hasNext() && counter < numOfCardsToBeAdded) {
          newPile.add(rightIterator.next());
          counter++;
        }
      }
      switchCounter++;
    }
    return newPile;
  }

}
