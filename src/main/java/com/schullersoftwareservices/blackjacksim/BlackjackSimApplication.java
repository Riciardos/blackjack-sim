package com.schullersoftwareservices.blackjacksim;

import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackSimApplication {

  public static void main(String[] args) {
    Shoe shoe = new Shoe(8);
    for (Card card: shoe.getAllCards()) {
      System.out.println(card);
    }
  }

}
