package view;

import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGui extends Application {
    @Override
    public void start(Stage stage) {
    	// CONTENT
    	BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 1200, 800);
        
        // TITLE
        Label title = new Label("Mind Decoder");
        title.getStyleClass().add("title");
        
        // STATS
        GridPane stats = new GridPane();
        
        Label score = new Label("BEST SCORE: 500");
        score.getStyleClass().add("statsLabel");
        Label attemptsAvg = new Label("AVG ATTEMPTS: 6");
        attemptsAvg.getStyleClass().add("statsLabel");
        Label timeAvg = new Label("AVG TIME: 12m 36s");
        timeAvg.getStyleClass().add("statsLabel");
        Label level = new Label("CURRENT LEVEL: 0");
        level.getStyleClass().add("statsLabel");
        
        stats.add(score,0, 0);
        stats.add(attemptsAvg,1, 0);
        stats.add(timeAvg,2, 0);
        stats.add(level,3, 0);
        stats.setAlignment(Pos.CENTER);
        stats.setPadding(new Insets(10,10,10,10));
        stats.setHgap(40);
        
        VBox statsBox = new VBox(title,stats);
        statsBox.setAlignment(Pos.CENTER);
        statsBox.setSpacing(10);
        pane.setTop(statsBox);
        
        scene.getStylesheets().add(getClass().getResource("/view/assets/style.css").toExternalForm());
        stage.setTitle("Mind Decoder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
