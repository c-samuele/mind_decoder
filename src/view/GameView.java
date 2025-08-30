package view;

import java.util.Arrays;
import java.util.List;
import model.Color;
import model.GameImpl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GameView {

    private BorderPane root;

    private GridPane attemptsGrid;
 

    public GameView(GameImpl game) {
        root = new BorderPane();
        
        int attempts = game.getRemainingAttempts();
        

        
        // GAME STATS
        HBox statsBox = new HBox();
        statsBox.setAlignment(Pos.CENTER);
        statsBox.setSpacing(20);
        statsBox.setPadding(new Insets(10));
        
        Label titleAttemptsLabel = new Label("Attempts: ");
        titleAttemptsLabel.getStyleClass().add("statsText");
        Label attemptsLabel = new Label("" + attempts);
        attemptsLabel.getStyleClass().add("statsValue");
        
        Label titleTimeLabel = new Label("Time: ");
        titleTimeLabel.getStyleClass().add("statsText");
        Label timeLabel = new Label("");
        timeLabel.getStyleClass().add("statsValue"); 
        
        timeLabel.textProperty().bind(
        	    Bindings.createStringBinding(() -> {
        	        int second = game.getStopWatch().getSecondsInt();
        	        int hours = second / 3600;
        	        int minutes = (second % 3600) / 60;
        	        int seconds = second % 60;
        	        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        	    }, game.getStopWatch().getSecondsProperty())
        	);
        
        

        statsBox.getChildren().addAll(titleAttemptsLabel,attemptsLabel,titleTimeLabel,timeLabel);
        root.setTop(statsBox);

        // MSG
        VBox centerBox = new VBox();
        centerBox.setSpacing(25);
        centerBox.setAlignment(Pos.CENTER);
        
        Label msg = new Label("Make your guess!");
        msg.getStyleClass().add("msg");
        centerBox.getChildren().add(msg);

        // ATTEMPTS GRID
        attemptsGrid = new GridPane();
        attemptsGrid.setHgap(10);
        attemptsGrid.setVgap(10);
        attemptsGrid.setPadding(new Insets(10));
        attemptsGrid.setAlignment(Pos.CENTER);
        attemptsGrid.getStyleClass().add("attemptsArea");
        
        for(int i=0;i<20;i++)
        	createRowAttempts(i,3);

        ScrollPane scrollPane = new ScrollPane(attemptsGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.getStyleClass().add("scrollPane");
        centerBox.getChildren().add(scrollPane);

        root.setCenter(centerBox);

        // AVAILABLE COLORS
        HBox colorsBox = new HBox();
        colorsBox.setAlignment(Pos.CENTER);
        colorsBox.setSpacing(16);
        colorsBox.setPadding(new Insets(16, 16, 16, 16));
        
        List<Color> colorsAvailable = game.getAvailableColors(Arrays.asList(Color.values()), game.getLevel());

        for (Color c : colorsAvailable) {
            Circle circle = new Circle(18, toFXColor(c));
            colorsBox.getChildren().add(circle);
        }

        root.setBottom(colorsBox);
    }

    // Enum Color to javaFx Color
    private javafx.scene.paint.Color toFXColor(model.Color c) {
        return switch(c) {
            case RED     -> javafx.scene.paint.Color.RED;
            case BLUE    -> javafx.scene.paint.Color.BLUE;
            case GREEN   -> javafx.scene.paint.Color.GREEN;
            case YELLOW  -> javafx.scene.paint.Color.YELLOW;
            case ORANGE  -> javafx.scene.paint.Color.ORANGE;
            case PURPLE  -> javafx.scene.paint.Color.PURPLE;
            case BROWN   -> javafx.scene.paint.Color.BROWN;
            case PINK    -> javafx.scene.paint.Color.PINK;
            case CYAN    -> javafx.scene.paint.Color.CYAN;
            case AZURE   -> javafx.scene.paint.Color.AZURE;
            case MAGENTA -> javafx.scene.paint.Color.MAGENTA;
            case LIME    -> javafx.scene.paint.Color.LIME;
        };
    }

    public void createRowAttempts(int rowIndex,int numCells) {
    	// Attempts colors
    	for(int col = 0; col <numCells; col++) {
    		Pane cell = new Pane();
    		cell.getStyleClass().add("cell");
    		this.getAttemptsGrid().add(cell, col, rowIndex);
    	}
    	// Hints msg
    	
    		StackPane colorCorrect = new StackPane();
    		colorCorrect.getStyleClass().add("hint");
    		Text labelColor = new Text("Colors correct");
    		labelColor.getStyleClass().add("hintText");
    		colorCorrect.getChildren().add(labelColor);
    		
    		StackPane indexCorrect = new StackPane();
    		indexCorrect.getStyleClass().add("hint");
    		Text labelIndex = new Text("Positions correct");
    		labelIndex.getStyleClass().add("hintText");
    		indexCorrect.getChildren().add(labelIndex);
    		
    		this.getAttemptsGrid().add(colorCorrect, numCells+1, rowIndex);
    		this.getAttemptsGrid().add(indexCorrect, numCells+2, rowIndex);
    		
    		
    	// DRAG & DROP...
    }

    
    public GridPane getAttemptsGrid() {
        return attemptsGrid;
    }

    public Parent getRoot() {
        return root;
    }
}
