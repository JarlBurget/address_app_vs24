package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class YearStatisticsController {
    @FXML
    private PieChart pieChart;

    @FXML
    private void initialize() {
        pieChart.setLegendVisible(false);
        pieChart.setLabelsVisible(true);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(180);
    }

    /**
     * Sets the persons to show the statistics for.
     *
     * @param persons
     */
    public void setPersonData(List<Person> persons) {
        HashMap<String, Integer> yearCounter = new HashMap<String, Integer>();
        for (Person p : persons) {
            int year = p.getBirthday().getYear();
            if (yearCounter.containsKey(String.valueOf(year))) {
                yearCounter.put(String.valueOf(year), yearCounter.get(String.valueOf(year)) + 1);
            } else  {
                yearCounter.put(String.valueOf(year), 1);
            }
        }

        pieChart.getData().clear();

        for (String year: yearCounter.keySet()) {
            int cnt =  yearCounter.get(year);
            PieChart.Data d = new PieChart.Data(year + " (" + cnt + " persons)", cnt);
            pieChart.getData().add(d);
        }
    }
}