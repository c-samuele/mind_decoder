package control;

import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import model.Color;
import model.GameImpl;

public class GameController {

    private GameImpl gameModel;
    
    private Timeline timeline;

    private final IntegerProperty time;
    private final IntegerProperty remainingAttempts;
    
    public GameController(GameImpl game) {
        this.gameModel = game;
        time = new SimpleIntegerProperty(game.getTime());
        remainingAttempts = new SimpleIntegerProperty(game.getRemainingAttempts());
        
    }
    
    public List<Color> getAvailableColors() {
    	return gameModel.getAvailableColors(Arrays.asList(Color.values()), gameModel.getLevel());
    }
    
    public IntegerProperty timeProperty() { 
    	return time; 
    }
    
    public IntegerProperty remainingAttemptsProperty() { 
    	return remainingAttempts; 
    }
    
    public void startStopWatch() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            gameModel.setTime(gameModel.getTime() + 1);
            time.set(gameModel.getTime());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void stopTimer() {
        if (timeline != null) timeline.stop();
    }

    public int getTime() {
        return gameModel.getTime();
    }
}