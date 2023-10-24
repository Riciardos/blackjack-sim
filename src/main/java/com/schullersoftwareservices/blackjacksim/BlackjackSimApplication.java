package com.schullersoftwareservices.blackjacksim;

import com.schullersoftwareservices.blackjacksim.model.Action;
import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Dealer;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackSimApplication {

  public static void main(String[] args) {
    Shoe shoe = new Shoe(8);
    shoe.randomShuffle();
    Dealer dealer = new Dealer(shoe);
    while (shoe.getAllCards().size() > 52) {
      dealer.playHand();
    }
  }
}
