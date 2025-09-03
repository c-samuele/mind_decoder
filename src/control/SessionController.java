package control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import model.GameMode;
import model.SessionImpl;
import model.GameImpl;
import view.GameView;
import view.SessionView;

public class SessionController {
	
	private Stage stage;
	
	private SessionImpl sessionModel;
	private SessionView sessionView;

	// Statistics values
	private final IntegerProperty bestScore;
	private final IntegerProperty attemptsAvg;
	private final IntegerProperty levelUnlocked;
	private final DoubleProperty timeAvg;
	
	
	public SessionController(Stage stage) {
		this.stage = stage;
		this.sessionModel = SessionImpl.getInstance();
		
		// Statistics values for sessionView 
		bestScore = new SimpleIntegerProperty(sessionModel.getBestScore());
		attemptsAvg = new SimpleIntegerProperty(sessionModel.getAttemptsAvg());
		levelUnlocked = new SimpleIntegerProperty(sessionModel.getUnlockedLevel());
		timeAvg = new SimpleDoubleProperty(sessionModel.getTimeAvg());
		
		// Creation sessionView
		sessionView = new SessionView(this);
		
		// Set welcome message
		sessionView.getMsg().setText("Welcome " + SessionImpl.getInstance().getFirstPlayer().getName());
		
		// Button for single player
		sessionView.getSinglePlayerBtn().setOnAction(e -> handleStartSinglePlayer());
		
		// Button for challenge ai
		
	}
	
	// Getter for Bind
	public IntegerProperty bestScoreProperty() {
		return bestScore;
	}
	public IntegerProperty attemptsAvgProperty() {
		return attemptsAvg;
	}
	public IntegerProperty levelUnlockedProperty() {
		return levelUnlocked;
	}
	public DoubleProperty timeAvgProperty() {
		return timeAvg;
	}
	
	// Statistics update
	public void updateStats() {
        bestScore.set(sessionModel.getBestScore());
        attemptsAvg.set(sessionModel.getAttemptsAvg());
        levelUnlocked.set(sessionModel.getUnlockedLevel());
        timeAvg.set(sessionModel.getTimeAvg());
    }
	
	// Handler for single player mode
	public void handleStartSinglePlayer() {
		// Creation gameMolde
		GameImpl gameModel = new GameImpl(GameMode.SINGLE_PLAYER, sessionModel.getUnlockedLevel());
		// Set current game
		sessionModel.setCurrentGame(gameModel);
		GameController gameController = new GameController(gameModel,stage,this);

		stage.getScene().setRoot(gameController.getGameRoot());
    }
	
	public SessionView getSessionView() {
	    return sessionView;
	}
	

}
