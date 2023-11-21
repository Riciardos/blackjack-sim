package com.schullersoftwareservices.blackjacksim.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hand {

  List<Card> cards;
  Integer softTotal;
  Integer hardTotal;

  public boolean isBust() {
    return hardTotal > 21;
  }

}
