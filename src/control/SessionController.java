package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.stage.Stage;
import model.GameMode;
import model.Player;
import model.PlayerImpl;
import model.Session;
import model.SessionImpl;
import model.GameImpl;
import model.Color;
import model.Game;
import view.GameView;
import view.SessionView;

public class SessionController {
	
	private Stage stage;
	private SessionView sessionView;
	private SessionImpl sessionModel;
	
	public SessionController(Stage stage,SessionView sessionView) {
		this.stage = stage;
		this.sessionView = sessionView;
		
		sessionView.getSinglePlayerBtn().setOnAction(e -> handleStartSinglePlayer());
//		sessionView.getMultyPlayerBtn().setOnAction(e -> handleStartMultyPlayer());
//		sessionView.getAiChallengeBtn().setOnAction(e -> handleStartAiChallenge());
	}
	
	
	
	
	private void handleStartSinglePlayer() {
		
		GameImpl gameModel = new GameImpl(GameMode.SINGLE_PLAYER,
									 SessionImpl.getInstance().getUnlockedLevel());
		
		SessionImpl.getInstance().setCurrentGame(gameModel);
		
		List<Color> allColors = new ArrayList<>(Arrays.asList(Color.values()));
		
		int level = SessionImpl.getInstance().getUnlockedLevel();
		
		GameView gameView = new GameView(gameModel);
		
		
		
		stage.getScene().setRoot(gameView.getRoot());
		stage.setWidth(1024);
		stage.setHeight(800);
		stage.setMinWidth(1024);
	    stage.setMinHeight(800);
	    stage.centerOnScreen();
	}
//	
//	private void handleStartMultyPlayer() {
//		
//		Game gameModel = new GameImpl(GameMode.MULTY_PLAYER,
//									 SessionImpl.getInstance().getUnlockedLevel());
//		
//		GameView gameView = new GameView(GameMode.MULTY_PLAYER);
//		
//		
//		
//		stage.getScene().setRoot(gameView.getRoot());
//		stage.setWidth(1200);
//		stage.setHeight(800);
//		stage.setMinWidth(1200);
//	    stage.setMinHeight(800);
//	    stage.centerOnScreen();
//	}
//	
//
//	private void handleStartAiChallenge() {
//		
//		Game gameModel = new GameImpl(GameMode.AI_CHALLENGE,
//									 SessionImpl.getInstance().getUnlockedLevel());
//		
//		GameView gameView = new GameView(GameMode.AI_CHALLENGE);
//		
//		
//		
//		stage.getScene().setRoot(gameView.getRoot());
//		stage.setWidth(1200);
//		stage.setHeight(800);
//		stage.setMinWidth(1200);
//	    stage.setMinHeight(800);
//	    stage.centerOnScreen();
//	}
	

}
