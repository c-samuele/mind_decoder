package view;

import control.SessionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.SessionImpl;

public class SessionView {

    private BorderPane root;
    
    private SessionController sessionController;
    
    // Button for the main menu
    Button 	singleBtn,
			aiBtn;
    
    // Box for the scene
    VBox topBox,
    	 btnBox;
    
    // Grid for the sessionStats
    GridPane stats;
    
    // Box for the label and value
    HBox scoreBox,
    	 attemptsBox,
    	 levelBox,
    	 timeBox;
    
    // Text label
    Label scoreLabel,
    	  attemptsLabel,
    	  levelLabel,
    	  timeLabel;
    
    // Value label
    Label scoreValue,
    	  attemptsValue,
    	  levelValue,
    	  timeValue;
    
    // Start message with user
    Label welcomeMsg;

    public SessionView(SessionController controller) {
    	this.sessionController = controller;
    	
        root = new BorderPane();
        
        // Brand image
        Image brand = new Image(getClass().getResourceAsStream("/negativo.png"));
        ImageView brandView = new ImageView(brand); 
        StackPane brandPane = new StackPane(brandView);
        brandPane.getStyleClass().add("brand");
        brandView.setFitHeight(80); 
        brandView.setPreserveRatio(true); 

        // Session stats
        stats = new GridPane();
        stats.getStyleClass().add("containerStats");
        
	        // Best Score
	        scoreBox = new HBox();
	        scoreLabel = new Label("BEST SCORE: ");
	        scoreLabel.getStyleClass().add("statsLabel");
	        scoreValue = new Label();
	        scoreValue.getStyleClass().add("statsValue");
	        scoreBox.getChildren().addAll(scoreLabel,scoreValue);
	        
	        // Attempts avg
	        attemptsBox = new HBox();
	        attemptsLabel = new Label("AVG ATTEMPTS: ");
	        attemptsLabel.getStyleClass().add("statsLabel");
	        attemptsValue = new Label();
	        attemptsValue.getStyleClass().add("statsValue");
	        attemptsBox.getChildren().addAll(attemptsLabel,attemptsValue);
	        
	        // Current level
	        levelBox = new HBox();
	        levelLabel = new Label("LEVEL: ");
	        levelLabel.getStyleClass().add("statsLabel");
	        levelValue = new Label();
	        levelValue.getStyleClass().add("statsValue");
	        levelBox.getChildren().addAll(levelLabel,levelValue);
        
	        // Time avg
	        timeBox = new HBox();
	        timeLabel = new Label("AVG Time: ");
	        timeLabel.getStyleClass().add("statsLabel");
	        timeValue = new Label();
	        timeValue.getStyleClass().add("statsValue");
	        timeBox.getChildren().addAll(timeLabel,timeValue);  
	        
        // MSG
        welcomeMsg = new Label();
        welcomeMsg.getStyleClass().add("username");
        
        // ADD BOX TO STATS
        stats.add(scoreBox,0,0);
        stats.add(attemptsBox,1,0);
        stats.add(timeBox,2,0);
        stats.add(levelBox,3,0);
        stats.setAlignment(Pos.CENTER);
        stats.setPadding(new Insets(10,10,10,10));
        stats.setHgap(40);
        
        // TOP BOX ALL ITEM
        topBox = new VBox(brandPane, stats,welcomeMsg);
        topBox.setAlignment(Pos.CENTER);
        root.setTop(topBox);

        // BUTTONS
        singleBtn = new Button("Single Player");
        singleBtn.getStyleClass().add("btnMain");
        aiBtn = new Button("Challenge Ai");
        aiBtn.getStyleClass().add("btnMain");

        btnBox = new VBox(singleBtn, aiBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(30);
        
        // Bind to session stats
        bindStats();
        
        root.setCenter(btnBox);
    }
    
    public Parent getRoot() {
		return root;
	}
    
   public void bindStats() {
	   scoreValue.textProperty().bind(sessionController.bestScoreProperty().asString());
	   attemptsValue.textProperty().bind(sessionController.attemptsAvgProperty().asString());
	   levelValue.textProperty().bind(sessionController.levelUnlockedProperty().asString());
	   timeValue.textProperty().bind(sessionController.timeAvgProperty().asString());
   }
   
   public Label getMsg() {
	   return this.welcomeMsg;
   }
	
   public Button getSinglePlayerBtn() {
        return this.singleBtn;
    }
   public Button getAiChallengeBtn() {
       return this.aiBtn;
   }
   
   
   
}
