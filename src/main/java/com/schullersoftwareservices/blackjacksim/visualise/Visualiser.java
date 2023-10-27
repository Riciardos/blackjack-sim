package com.schullersoftwareservices.blackjacksim.visualise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.Frequency;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;


@Slf4j
@Data
public class Visualiser {


  private DataSet dataSet;
  public Visualiser() {

  }

  public void visualise() {

    Frequency frequency = new Frequency();
    dataSet.getTopTrueCounts().forEach((topCount) -> frequency.addValue(topCount));
    CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
        .title("Top True Count distribution")
        .xAxisTitle("Top true count")
        .yAxisTitle("Frequency")
        .build();

    chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
    chart.getStyler().setAvailableSpaceFill(0.99);
    chart.getStyler().setOverlapped(true);

    Map<Integer, Long> distributionMap = new HashMap<>();
    for (int i =0 ; i< 15 ; i++) {
      distributionMap.put(i, frequency.getCount(i));
    }

    List<Long> yData = new ArrayList();
    yData.addAll(distributionMap.values());
    List<Object> xData = List.of(distributionMap.keySet().toArray());
    chart.addSeries("Top True Count", xData, yData);

    new SwingWrapper<>(chart).displayChart();

  }


}
