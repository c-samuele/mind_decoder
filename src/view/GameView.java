package view;

import java.util.ArrayList;
import java.util.List;
import control.GameController;
import model.Color;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GameView {

    private BorderPane root;
    private GameController gameController;

    private GridPane attemptsGrid;
    
    VBox topBox;
    
    HBox settingsBox;
    Label usernameLabel;
    Button exit;
   
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
    
    private final List<Text> colorHintsTexts;
    private final List<Text> indexHintsTexts;
    
    HBox colorsBox;
    
    public GameView(GameController controller) {
    	this.gameController = controller;
    	
        root = new BorderPane();
        
        // for hints
        colorHintsTexts = new ArrayList<>();
        indexHintsTexts = new ArrayList<>();
        
        
        topBox = new VBox(); // container for stats and msg
        
        // GAME STATS
        statsBox = new HBox();
        statsBox.setAlignment(Pos.CENTER);
        statsBox.getStyleClass().add("statsBox");    
        
        settingsBox = new HBox();
        settingsBox.setSpacing(700);
        settingsBox.setAlignment(Pos.CENTER);
        exit = new Button("✖");
        exit.getStyleClass().add("exitBtn");
        usernameLabel = new Label();
        usernameLabel.getStyleClass().add("usernameLabel");
        settingsBox.getChildren().addAll(usernameLabel,exit);
	        
	        // ATTEMTPS
	        attemptsLabel = new Label("Attempts: ");
	        attemptsLabel.getStyleClass().add("statsLabel");
	        attemptsValue = new Label();
	        attemptsValue.getStyleClass().add("statsValue");
	        
	        
	        attemptsBox = new HBox(attemptsLabel,attemptsValue);
	        attemptsBox.getStyleClass().add("boxStatsGame");
	        
	        // TIME
	        timeLabel = new Label("Time: ");
	        timeLabel.getStyleClass().add("statsLabel");
	        timeValue = new Label();
	        timeValue.getStyleClass().add("statsValue");
	        
	        timeBox = new HBox(timeLabel,timeValue);
	        timeBox.getStyleClass().add("boxStatsGame");
	        
	    statsBox.getChildren().addAll(attemptsBox,timeBox);
	    statsBox.setSpacing(600);
	    
        topBox.getChildren().addAll(settingsBox,statsBox);
        
        
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
        


        scrollPane = new ScrollPane(attemptsGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.getStyleClass().add("scrollPane");

        root.setCenter(scrollPane);

        // AVAILABLE COLORS
        colorsBox = new HBox();
        colorsBox.setAlignment(Pos.BOTTOM_CENTER);
        colorsBox.setSpacing(16);
        colorsBox.setPadding(new Insets(16));
        
        root.setBottom(colorsBox);
        
    	bindStatsGame(gameController);
    }

    public static String getFormatStopWatch(int second) {
        int hours = second / 3600;
        int minutes = (second % 3600) / 60;
        int seconds = second % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
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
   
    public void createRowAttempts(int rowIndex, int numCells) {
        for (int col = 0; col < numCells; col++) {
            Pane cell = new Pane();
            cell.getStyleClass().add("cell");

            final int currentCol = col;
            
            cell.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    Color draggedColor = Color.valueOf(db.getString());
                    gameController.colorDropped(currentCol, draggedColor);
                }
                event.setDropCompleted(true);
                event.consume();
            });

            cell.setOnDragOver(event -> {
                if (event.getGestureSource() != cell && event.getDragboard().hasString())
                    event.acceptTransferModes(TransferMode.MOVE);
                event.consume();
            });

            attemptsGrid.add(cell, col, rowIndex);
        }

        // Hints
        StackPane colorHint = new StackPane();
        colorHint.getStyleClass().add("hint");
        Text txtColor = new Text("Colors correct");
        txtColor.getStyleClass().add("hintText");
        colorHint.getChildren().add(txtColor);
        colorHintsTexts.add(txtColor);

        StackPane indexHint = new StackPane();
        indexHint.getStyleClass().add("hint");
        Text txtIndex = new Text("Positions correct");
        txtIndex.getStyleClass().add("hintText");
        indexHint.getChildren().add(txtIndex);
        indexHintsTexts.add(txtIndex);

        attemptsGrid.add(colorHint, numCells + 1, rowIndex);
        attemptsGrid.add(indexHint, numCells + 2, rowIndex);
    }

    // Refresh Available Color on drag-and-drop
    public void refreshColorsBox(List<Color> colorsAvailable) {
        colorsBox.getChildren().clear();
        for (Color c : colorsAvailable) {
            Circle circle = new Circle(18, toFXColor(c));
            circle.setOnDragDetected(event -> {
                Dragboard db = circle.startDragAndDrop(TransferMode.MOVE);
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(javafx.scene.paint.Color.TRANSPARENT);
                db.setDragView(circle.snapshot(params, null));
                ClipboardContent content = new ClipboardContent();
                content.putString(c.name());
                db.setContent(content);
                event.consume();
            });
            colorsBox.getChildren().add(circle);
        }
    }
  
    public void updateCell(int rowIndex, int colIndex, Color color) {
        Pane cell = (Pane) attemptsGrid.getChildren().stream()
            .filter(n -> GridPane.getRowIndex(n) != null && GridPane.getColumnIndex(n) != null)
            .filter(n -> GridPane.getRowIndex(n) == rowIndex && GridPane.getColumnIndex(n) == colIndex)
            .map(n -> (Pane) n)
            .findFirst().orElse(null);

        if (cell != null) {
            cell.setBackground(new Background(
                new BackgroundFill(toFXColor(color), new CornerRadii(18), Insets.EMPTY)
            ));
        }
    }

    
    public void showHints(int rowIndex, int colorCorrect, int indexCorrect) {
        if (rowIndex < colorHintsTexts.size() && rowIndex < indexHintsTexts.size()) {
            colorHintsTexts.get(rowIndex).setText("Colors correct: " + colorCorrect);
            indexHintsTexts.get(rowIndex).setText("Positions correct: " + indexCorrect);
        }
    }
    
    public void bindStatsGame(GameController controller) {
    	usernameLabel.setText(controller.getUsername());
    	attemptsValue.textProperty().bind(gameController.remainingAttemptsProperty().asString());
    	timeValue.textProperty().bind(
        	    Bindings.createStringBinding(
        	        () -> getFormatStopWatch(gameController.timeProperty().get()),
        	        gameController.timeProperty()
        	    )
        	);
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
    
    public Label getMsgLabel() {
    	return msg;
    }
    
    public void setMsgLabel(String str) {
    	msg.setText(str);
    }
    
    public Button getExitBtn() {
    	return exit;
    }
    
}
