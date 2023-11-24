package com.schullersoftwareservices.blackjacksim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class BustPercentage {

  Card card;
  Integer bustCount;
  Integer notBustCount;

  public void registerBust() {
    bustCount += 1;
  }

  public void registerNotBust() {
    notBustCount += 1;
  }

}
