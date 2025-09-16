package controller;

import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Color;
import model.Cpu;
import model.CpuImpl;
import model.GameImpl;
import model.GameMode;
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
    
    private Cpu cpu;
    private int round;
    
    private GameMode gameMode;
    
    
    public GameController(GameImpl gameModel,Stage stage,SessionController sessionController) {
    	this.gameModel = gameModel;
        this.sessionController = sessionController;
        this.stage = stage;
        this.round = 1;
        this.gameMode = gameModel.getMode();
        
        time = new SimpleIntegerProperty(gameModel.getTime());
        remainingAttempts = new SimpleIntegerProperty(gameModel.getRemainingAttempts());
        
        
        // CPU
        if(gameMode == GameMode.AI_CHALLENGE){
        	cpu = new CpuImpl(gameModel.numberOfColors(),gameModel.getAvailableColors());
        	cpu.initMatrix();
        	cpu.printMatrix();
        }
        
        
        
        startStopWatch();
        
        this.gameView = new GameView(this);
        
        // Debug: Print a SecretCode.
        System.out.print("\nSECRET CODE:"+SessionImpl.getInstance().getCurrentGame().getSecretCode().getColor() + "\n");
        
        // show first attempt row
        gameView.createRowAttempts(gameModel.getCurrentAttemptRow(), gameModel.getCurrentAttempt().length);

        // show colors available
        refreshColors();
        
        // Handler for exit to session
        gameView.getExitBtn().setOnAction(e -> exitToSession());
    }
    
    public void exitToSession() {
        stopTimer();
        sessionController.updateStats();
        stage.getScene().setRoot(sessionController.getSessionView().getRoot());
    }
 
    
    
    public void colorDropped(int colIndex, Color draggedColor) {
        // Update model
        gameModel.setColorCurrentAttempt(colIndex, draggedColor);
        gameModel.removeAvailableColor(draggedColor);

        // Update view
        int currentRow = gameModel.getCurrentAttemptRow();
        gameView.updateCell(currentRow, colIndex, draggedColor);
        gameView.refreshColorsBox(gameModel.getAvailableColors());
        
        if (gameModel.isCurrentAttemptFull()) {
        	
            submitAttempt(); // Make attempt
            
            Hint lastHint = gameModel.getHints().getLast(); 
            
            gameView.showHints(gameModel.getCurrentAttemptRow(), // Show hints 
         		   lastHint.getColorCorrect(), 
         		   lastHint.getIndexCorrect());
            
            gameModel.nextAttemptRow();			// Increment number of row
      
            round++;							// Increment round
            
            gameModel.resetCurrentAttempt();	// Reset attempt
            gameModel.resetAvailableColors();	// Reset Available Colors
            
            GameState state = gameModel.getState();
            
            if(state == GameState.PLAYING) {
            	createNewAttemptRow();
            	gameView.refreshColorsBox(gameModel.getAvailableColors());
            }
            else if(state == GameState.WIN) {
            	gameView.setMsgLabel(SessionImpl.getInstance().getFirstPlayer().getName()+" Win!!");
            	
            	if(gameMode == GameMode.SINGLE_PLAYER)
            		SessionImpl.getInstance().unlockNextLevel();
            	
            	stopTimer();
            }
            else {
            	gameView.setMsgLabel("Game Over!!");
            	stopTimer();
            }
            
            if(gameMode == GameMode.AI_CHALLENGE)
            	nextRound();
           
        }
    }

    // Create new attempt row
    private void createNewAttemptRow() {
        int currentRow = gameModel.getCurrentAttemptRow();
        gameView.createRowAttempts(currentRow, gameModel.getCurrentAttempt().length);
    }

    // Update view available colors
    private void refreshColors() {
        gameView.refreshColorsBox(gameModel.getAvailableColors());
    }

    public void submitAttempt() {
        Code code = new CodeImpl(Arrays.asList(gameModel.getCurrentAttempt()));
        try {
            gameModel.makeAttempt(code);	
            remainingAttempts.setValue(gameModel.getRemainingAttempts()); // Update attemptsProperty for bind of attempts remaining 
            
            if(gameMode == GameMode.AI_CHALLENGE)
            	cpu.addAttempt(code, gameModel.getHints().getLast());
            
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
    
    
    // Ai Challenge Round 
    public void nextRound() {
    	if (gameModel.getState() != GameState.PLAYING) 
    		return;
    	
    	if (round % 2 != 0) { // turn of player
            return;
    	} else { // turn of cpu
    		Code cpuAttempt;
    		
    		if(gameModel.getCurrentAttemptRow() < 18) 
    			cpuAttempt = cpu.makeUniqueRandomAttempt();
    		else 
    			cpuAttempt = cpu.chooseAttempt();
    		
    			System.out.println("CPU ATTEMPT:"+cpuAttempt.getColor());
    			
    			gameModel.makeAttempt(cpuAttempt);
    			
    			int cpuRow = gameModel.getCurrentAttemptRow();
    			
    			for (int i = 0; i < cpuAttempt.size(); i++) 
    			    gameView.updateCell(cpuRow, i, cpuAttempt.getColorByIndex(i));
    			
    			
    			cpu.addAttempt(cpuAttempt, gameModel.getHints().getLast());
    			
    			cpu.printMatrix();
    			
    			Hint lastHint = gameModel.getHints().getLast();
    			
		        gameView.showHints(gameModel.getCurrentAttemptRow(), 
		                           lastHint.getColorCorrect(), 
		                           lastHint.getIndexCorrect());
		        
		        if(gameModel.getState() == GameState.WIN) {
		        	gameView.setMsgLabel("Computer Win");
	            	stopTimer();
	            	return;
		        }
		        	
		        
		        gameModel.nextAttemptRow();
		        
    		    createNewAttemptRow();
    		}
    	round++;
    }
    
}