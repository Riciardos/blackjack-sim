package com.schullersoftwareservices.blackjacksim.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Card {
  ACE(11, 1, -1),
  TWO(2, 2, 1),
  THREE(3, 3, 1),
  FOUR(4, 4, 1),
  FIVE(5, 5, 1),
  SIX(6, 6, 1),
  SEVEN(7, 7, 0),
  EIGHT(8, 8, 0),
  NINE(9, 9, 0),
  TEN(10, 10, -1),
  JACK(10, 10, -1),
  QUEEN(10, 10, -1),
  KING(10, 10, -1);

  private Integer softValue;
  private Integer hardValue;
  private Integer countValue;
}
