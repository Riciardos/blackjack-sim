package com.schullersoftwareservices.blackjacksim;

import com.schullersoftwareservices.blackjacksim.model.Action;
import com.schullersoftwareservices.blackjacksim.model.Card;
import com.schullersoftwareservices.blackjacksim.model.Dealer;
import com.schullersoftwareservices.blackjacksim.model.Shoe;
import com.schullersoftwareservices.blackjacksim.service.ShoeRunner;
import com.schullersoftwareservices.blackjacksim.shuffle.CasinoShuffle;
import com.schullersoftwareservices.blackjacksim.shuffle.ComputerRandomShuffle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BlackjackSimApplication {

  public static void main(String[] args) {

    ShoeRunner shoeRunner = new ShoeRunner(8, new ComputerRandomShuffle(), 4);
    shoeRunner.analyseMultipleShoes(100000);
  }


}
