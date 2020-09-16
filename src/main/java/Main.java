import javafx.application.Application;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Collatz Conjecture Visualization");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Iteration");
        yAxis.setLabel("Value of number in cycle");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Collatz Conjecture Visualization");

        XYChart.Series series = new XYChart.Series();
        series.setName("Value");

        ArrayList<Double> collatzData = collatz(50);

        lineChart.getData().add(new XYChart.Series<>(makeData(collatzData)));

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static ArrayList<Double> collatz(double num) {
        ArrayList<Double> proc = new ArrayList<>();
        proc.add(num);

        while (true) {
            if (num == 1) {
                break;
            }
            if (num % 2 == 1) {
                num = (num * 3) + 1;
            } else {
                num /= 2;
            }
            proc.add(num);
//            System.out.println(num);
        }
        return proc;
    }

    // wip label atop each point
    private static Node dataLabel(ObjectExpression<Number> val) {
        Label label = new Label();
        label.textProperty().bind(val.asString("%,.1f"));

        Pane pane = new Pane(label);
        pane.setShape(new Rectangle(6, 6));
        pane.setScaleShape(false);

        label.translateYProperty().bind(label.heightProperty().divide(-0.7));
        return pane;
    }

    private static ObservableList<XYChart.Data<Number, Number>> makeData(ArrayList<Double> collatz) {
        ObservableList<XYChart.Data<Number, Number>> list = FXCollections.observableArrayList();

        for (int i = 0; i < collatz.size(); i++) {
            XYChart.Data<Number, Number> data = new XYChart.Data<>(i, collatz.get(i));
            data.setNode(dataLabel(data.YValueProperty()));
            list.add(data);
        }
        return list;
    }
}