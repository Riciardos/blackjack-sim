package com.schullersoftwareservices.blackjacksim.visualise;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSet {

  private List<Integer> topCounts;
  private List<Integer> lowCounts;
  private List<Integer> topTrueCounts;
  private List<Integer> lowTrueCounts;
  private List<Integer> cuttingCardCounts;
  private List<Integer> cuttingCardTrueCounts;
  private String shuffleBehaviourName;


}
