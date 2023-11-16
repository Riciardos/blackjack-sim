package com.schullersoftwareservices.blackjacksim.shuffle;

import com.schullersoftwareservices.blackjacksim.model.Card;
import java.util.List;

public interface ShuffleBehaviour {

  List<Card> shuffle(List<Card> cards);

  String name();

}
