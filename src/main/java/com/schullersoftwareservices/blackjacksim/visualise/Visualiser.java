package com.schullersoftwareservices.blackjacksim.visualise;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

  public void visualise() {

    visualeGraph(dataSet.getTopTrueCounts(), -8, 8 , "Top True Count");
    visualeGraph(dataSet.getCuttingCardTrueCounts(), -5, 5 , "Cutting Card True Count");


  }

  private void visualeGraph(List<Integer> yValues, Integer xMin, Integer xMax, String title) {
    Frequency frequency = new Frequency();
    yValues.forEach((count) -> frequency.addValue(count));
    CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
        .title(title)
        .xAxisTitle(title)
        .yAxisTitle("Frequency")
        .build();

    chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
    chart.getStyler().setAvailableSpaceFill(0.99);
    chart.getStyler().setOverlapped(true);

    Map<Integer, Long> distributionMap = new LinkedHashMap<>();
    for (int i = xMin ; i< xMax ; i++) {
      distributionMap.put(i, frequency.getCount(i));
    }

    List<Long> yData = new ArrayList();
    yData.addAll(distributionMap.values());
    List<Object> xData = List.of(distributionMap.keySet().toArray());
    chart.addSeries(title, xData, yData);

    new SwingWrapper<>(chart).displayChart();
  }


}