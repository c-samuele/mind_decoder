package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.GameStats;
import model.SessionImpl;

public class SessionView {

    private BorderPane root;
    
    Button 	singleBtn,
			multyBtn,
			aiBtn;

    public SessionView(SessionImpl session) {
        root = new BorderPane();
        
        // TITLE
        Image brand = new Image(getClass().getResourceAsStream("/negativo.png"));
        
        ImageView brandView = new ImageView(brand); 
        brandView.setFitHeight(80); 
        brandView.setPreserveRatio(true); 

        // STATS
        GridPane stats = new GridPane();
        stats.getStyleClass().add("containerStats");

        Label score = new Label("BEST SCORE: " + session.getBestScore());
        score.getStyleClass().add("statsLabel");
        Label attemptsAvg = new Label("AVG ATTEMPTS: "+ session.getAttemptsAvg());
        attemptsAvg.getStyleClass().add("statsLabel");
        Label timeAvg = new Label("AVG TIME: " + session.getTimeAvg());
        timeAvg.getStyleClass().add("statsLabel");
        Label level = new Label("CURRENT LEVEL: " + session.getUnlockedLevel());
        level.getStyleClass().add("statsLabel");
        
        Label welcomeMsg = new Label("Welcome " + session.getFirstPlayer().getName());
        welcomeMsg.getStyleClass().add("username");

        stats.add(score,0,0);
        stats.add(attemptsAvg,1,0);
        stats.add(timeAvg,2,0);
        stats.add(level,3,0);
        stats.setAlignment(Pos.CENTER);
        stats.setPadding(new Insets(10,10,10,10));
        stats.setHgap(40);

        VBox statsBox = new VBox(brandView, stats,welcomeMsg);
        statsBox.setAlignment(Pos.CENTER);
        root.setTop(statsBox);

        // BUTTONS
        singleBtn = new Button("Single Player");
        singleBtn.getStyleClass().add("btnMain");
        multyBtn = new Button("Multy Player");
        multyBtn.getStyleClass().add("btnMain");
        aiBtn = new Button("Challenge Ai");
        aiBtn.getStyleClass().add("btnMain");

        VBox boxBtn = new VBox(singleBtn, multyBtn, aiBtn);
        boxBtn.setAlignment(Pos.CENTER);
        boxBtn.setSpacing(30);

        root.setCenter(boxBtn);
    }
    
    public Parent getRoot() {
		return root;
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
