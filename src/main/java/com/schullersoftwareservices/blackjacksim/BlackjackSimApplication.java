package com.schullersoftwareservices.blackjacksim;

import com.schullersoftwareservices.blackjacksim.model.Action;
import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Dealer;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import com.schullersoftwareservices.blackjacksim.service.ShoeRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BlackjackSimApplication {

  public static void main(String[] args) {

    ShoeRunner shoeRunner = new ShoeRunner();
    shoeRunner.analyseMultipleShoes(10000);
  }


}
