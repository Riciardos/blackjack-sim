package com.schullersoftwareservices.blackjacksim.shuffle;

import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Deck;
import java.util.List;

public interface ShuffleBehaviour {

  List<Card> shuffle(List<Card> cards);

}
