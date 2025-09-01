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
    
    Button 	singleBtn,
			multyBtn,
			aiBtn;
    
    VBox topBox,
    	 btnBox;
    
    GridPane stats;
    
    HBox scoreBox,
    	 attemptsBox,
    	 levelBox,
    	 timeBox;
    
    Label scoreLabel,
    	  attemptsLabel,
    	  levelLabel,
    	  timeLabel;
    
    Label scoreValue,
    	  attemptsValue,
    	  levelValue,
    	  timeValue;
    
    Label welcomeMsg;

    public SessionView() {
        root = new BorderPane();
        
        // TITLE
        Image brand = new Image(getClass().getResourceAsStream("/negativo.png"));
        ImageView brandView = new ImageView(brand); 
        StackPane brandPane = new StackPane(brandView);
        brandPane.getStyleClass().add("brand");
        brandView.setFitHeight(80); 
        brandView.setPreserveRatio(true); 

        // STATS
        stats = new GridPane();
        stats.getStyleClass().add("containerStats");
        
	        // BEST SCORE 
	        scoreBox = new HBox();
	        scoreLabel = new Label("BEST SCORE: ");
	        scoreLabel.getStyleClass().add("statsLabel");
	        scoreValue = new Label();
	        scoreValue.getStyleClass().add("statsValue");
	        scoreBox.getChildren().addAll(scoreLabel,scoreValue);
	        
	        // ATTEMPTS AVG
	        attemptsBox = new HBox();
	        attemptsLabel = new Label("AVG ATTEMPTS: ");
	        attemptsLabel.getStyleClass().add("statsLabel");
	        attemptsValue = new Label();
	        attemptsValue.getStyleClass().add("statsValue");
	        attemptsBox.getChildren().addAll(attemptsLabel,attemptsValue);
	        
	        // LEVEL UNLOCKED
	        levelBox = new HBox();
	        levelLabel = new Label("LEVEL: ");
	        levelLabel.getStyleClass().add("statsLabel");
	        levelValue = new Label();
	        levelValue.getStyleClass().add("statsValue");
	        levelBox.getChildren().addAll(levelLabel,levelValue);
        
	        // TIME AVG
	        timeBox = new HBox();
	        timeLabel = new Label("AVG Time: ");
	        timeLabel.getStyleClass().add("statsLabel");
	        timeValue = new Label();
	        timeValue.getStyleClass().add("statsValue");
	        timeBox.getChildren().addAll(timeLabel,timeValue);
	        
        // MSG
        welcomeMsg = new Label("Welcome " + SessionImpl.getInstance().getFirstPlayer().getName());
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
        multyBtn = new Button("Multy Player");
        multyBtn.getStyleClass().add("btnMain");
        aiBtn = new Button("Challenge Ai");
        aiBtn.getStyleClass().add("btnMain");

        btnBox = new VBox(singleBtn, multyBtn, aiBtn);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(30);

        root.setCenter(btnBox);
    }
    
    public Parent getRoot() {
		return root;
	}
   
    public void setController(SessionController controller) {
    	this.sessionController = controller;
    	this.bindStats(controller);
    }
    
   public void bindStats(SessionController sessionController) {
	   scoreValue.textProperty().bind(sessionController.bestScoreProperty().asString());
	   attemptsValue.textProperty().bind(sessionController.attemptsAvgProperty().asString());
	   levelValue.textProperty().bind(sessionController.levelUnlockedProperty().asString());
	   timeValue.textProperty().bind(sessionController.timeAvgProperty().asString());
   }
	
   public Button getSinglePlayerBtn() {
        return this.singleBtn;
    }
   public Button getMultyPlayerBtn() {
       return this.multyBtn;
   }
   public Button getAiChallengeBtn() {
       return this.aiBtn;
   }
}
