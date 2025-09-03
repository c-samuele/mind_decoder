package control;

import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Color;
import model.GameImpl;
import model.GameState;
import model.Hint;
import model.SessionImpl;
import view.GameView;
import model.Code;
import model.CodeImpl;

public class GameController {
	
	private Stage stage;

    private GameImpl gameModel;
    private GameView gameView;
    private SessionController sessionController;
    
    private Timeline timeline;

    private final IntegerProperty time;
    private final IntegerProperty remainingAttempts;
    
    public GameController(GameImpl gameModel,Stage stage,SessionController sessionController) {
    	this.gameModel = gameModel;
        this.sessionController = sessionController;
        this.stage = stage;
        
        time = new SimpleIntegerProperty(gameModel.getTime());
        remainingAttempts = new SimpleIntegerProperty(gameModel.getRemainingAttempts());
        
        startStopWatch();
        
        this.gameView = new GameView(this);
        
        // DEBUG SECRET CODE 
        System.out.print("{SECRET CODE:"+SessionImpl.getInstance().getCurrentGame().getSecretCode().getColor()+"}");
        
        // Mostra subito la prima riga vuota
        gameView.createRowAttempts(gameModel.getCurrentAttemptRow(), gameModel.getCurrentAttempt().length);

        // Mostra subito i colori disponibili
        refreshColors();
        
        // Handler for exit to session
        gameView.getExitBtn().setOnAction(e -> exitToSession());
    }
    
    public void exitToSession() {
        stopTimer();
        sessionController.updateStats();
        stage.getScene().setRoot(sessionController.getSessionView().getRoot());
    }
 
    
 // Notifica della view quando un colore viene droppato
    public void colorDropped(int colIndex, Color draggedColor) {
        // aggiorna il modello
        gameModel.setColorCurrentAttempt(colIndex, draggedColor);
        gameModel.removeAvailableColor(draggedColor);

        // aggiorna la view
        int currentRow = gameModel.getCurrentAttemptRow();
        gameView.updateCell(currentRow, colIndex, draggedColor);
        gameView.refreshColorsBox(gameModel.getAvailableColors());

        // se il tentativo è completo
        if (gameModel.isCurrentAttemptFull()) {
            submitAttempt();
            List<Hint> hints = gameModel.getHints();
            Hint lastHint = hints.get(hints.size() - 1);
            gameView.showHints(gameModel.getCurrentAttemptRow() - 1, 
                    		   lastHint.getColorCorrect(), 
                    		   lastHint.getIndexCorrect());

            gameModel.resetCurrentAttempt();
            gameModel.resetAvailableColors();
            
            GameState state = gameModel.getState();
            if(state == GameState.PLAYING) {
            	createNewAttemptRow();
            	gameView.refreshColorsBox(gameModel.getAvailableColors());
            }
            else if(state == GameState.WIN) {
            	gameView.setMsgLabel("is Win!!");
            	SessionImpl.getInstance().unlockNextLevel();
            	stopTimer();
            }
            else {
            	gameView.setMsgLabel("Game Over!!");
            	stopTimer();
            }
        }
    }

    // Crea nuova riga vuota nella view
    private void createNewAttemptRow() {
        int currentRow = gameModel.getCurrentAttemptRow();
        gameView.createRowAttempts(currentRow, gameModel.getCurrentAttempt().length);
    }

    // Aggiorna la barra dei colori disponibili
    private void refreshColors() {
        gameView.refreshColorsBox(gameModel.getAvailableColors());
    }

    public void submitAttempt() {
        Code code = new CodeImpl(Arrays.asList(gameModel.getCurrentAttempt()));
        try {
            gameModel.makeAttempt(code);
            remainingAttempts.setValue(gameModel.getRemainingAttempts());
            
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public IntegerProperty timeProperty() { 
    	return time; 
    }
    
    public IntegerProperty remainingAttemptsProperty() { 
    	return remainingAttempts; 
    }
    
    public void startStopWatch() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        	int newValue = gameModel.getTime() + 1;
            gameModel.setTime(newValue);
            time.set(newValue);
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
    
    public String getUsername() {
    	return SessionImpl.getInstance().getFirstPlayer().getName();
    }
    
    public Parent getGameRoot() {
    	return gameView.getRoot();
    }
    
}