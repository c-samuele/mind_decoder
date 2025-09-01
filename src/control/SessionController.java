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


public class SessionController {
	
	private Stage stage;
	
	private SessionImpl sessionModel;

	private final IntegerProperty bestScore;
	private final IntegerProperty attemptsAvg;
	private final IntegerProperty levelUnlocked;
	private final DoubleProperty timeAvg;
	
	
	
	public SessionController(Stage stage) {
		this.stage = stage;
		this.sessionModel = SessionImpl.getInstance();
		
		// Stats for sessionView
		bestScore = new SimpleIntegerProperty(sessionModel.getBestScore());
		attemptsAvg = new SimpleIntegerProperty(sessionModel.getAttemptsAvg());
		levelUnlocked = new SimpleIntegerProperty(sessionModel.getUnlockedLevel());
		timeAvg = new SimpleDoubleProperty(sessionModel.getTimeAvg());
		
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
	
	public void updateStats() {
        bestScore.set(sessionModel.getBestScore());
        attemptsAvg.set(sessionModel.getAttemptsAvg());
        levelUnlocked.set(sessionModel.getUnlockedLevel());
        timeAvg.set(sessionModel.getTimeAvg());
    }
	
	
	public void handleStartSinglePlayer() {
        GameImpl gameModel = new GameImpl(GameMode.SINGLE_PLAYER, sessionModel.getUnlockedLevel());
        sessionModel.setCurrentGame(gameModel);
        
        GameController gameController = new GameController(gameModel);
        
        gameController.startStopWatch();
        
        GameView gameView = new GameView(gameController);
        
        stage.getScene().setRoot(gameView.getRoot());
        stage.setWidth(1024);
        stage.setHeight(800);
        stage.setMinWidth(1024);
        stage.setMinHeight(800);
        stage.centerOnScreen();
    }
	

}
