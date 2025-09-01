package view;

import java.util.Arrays;
import java.util.List;

import control.GameController;
import model.Color;
import model.GameImpl;
import model.SessionImpl;
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
    private GameController gameController;

    private GridPane attemptsGrid;
    
    VBox topBox;
    
    HBox statsBox,
    	 attemptsBox,
    	 timeBox;
    
    Label attemptsLabel,
    	  timeLabel;
    
    Label attemptsValue,
    	  timeValue;
 
    StackPane msgBox;
    
    Label msg;
    
    ScrollPane scrollPane;
    
    HBox colorsBox;
    
    public GameView(GameController controller) {
        root = new BorderPane();
        gameController = controller;
        
        topBox = new VBox(); // container for stats and msg
        
        // GAME STATS
        statsBox = new HBox();
        statsBox.setAlignment(Pos.CENTER);
        statsBox.getStyleClass().add("statsBox");        
	        
	        // ATTEMTPS
	        attemptsLabel = new Label("Attempts: ");
	        attemptsLabel.getStyleClass().add("statsLabel");
	        attemptsValue = new Label("");
	        attemptsValue.getStyleClass().add("statsValue");
	        attemptsValue.textProperty().bind(
	                gameController.remainingAttemptsProperty().asString()
	            );
	        
	        attemptsBox = new HBox(attemptsLabel,attemptsValue);
	        
	        
	        // TIME
	        timeLabel = new Label("Time: ");
	        timeLabel.getStyleClass().add("statsLabel");
	        timeValue = new Label("");
	        timeValue.getStyleClass().add("statsValue");
	        timeValue.textProperty().bind(
	        	    Bindings.createStringBinding(
	        	        () -> {
	        	            int second = gameController.timeProperty().get();
	        	            int hours = second / 3600;
	        	            int minutes = (second % 3600) / 60;
	        	            int seconds = second % 60;
	        	            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	        	        },
	        	        gameController.timeProperty()
	        	    )
	        	);
	        timeBox = new HBox(timeLabel,timeValue);
	     
	    statsBox.getChildren().addAll(attemptsBox,timeBox);
	    
        topBox.getChildren().add(statsBox);
        
        // MSG
        msgBox = new StackPane();
        msgBox.getStyleClass().add("msgBox");
        
        msg = new Label("Make your guess!");
        msg.getStyleClass().add("msg");
        msgBox.getChildren().add(msg);
        
        topBox.getChildren().add(msgBox);

        root.setTop(topBox);
        
        // ATTEMPTS GRID
        attemptsGrid = new GridPane();
        attemptsGrid.setHgap(10);
        attemptsGrid.setVgap(10);
        attemptsGrid.setPadding(new Insets(10));
        attemptsGrid.setAlignment(Pos.CENTER);
        attemptsGrid.getStyleClass().add("attemptsArea");
        
        for(int i=0;i<20;i++)
        	createRowAttempts(i,3);

        scrollPane = new ScrollPane(attemptsGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.getStyleClass().add("scrollPane");

        root.setCenter(scrollPane);

        // AVAILABLE COLORS
        colorsBox = new HBox();
        colorsBox.setAlignment(Pos.BOTTOM_CENTER);
        colorsBox.setSpacing(16);
        colorsBox.setPadding(new Insets(16, 16, 16, 16));
       
        List<Color> colorsAvailable = gameController.getAvailableColors();

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
    
    public Label getAttemptsLabel() {
    	return attemptsValue;
    }
    
    public Label getTimeLabel() {
    	return timeLabel;
    }

    
    public GridPane getAttemptsGrid() {
        return attemptsGrid;
    }

    public Parent getRoot() {
        return root;
    }
}
