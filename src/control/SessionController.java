package control;

import java.util.Optional;
import javafx.stage.Stage;
import model.GameMode;
import model.Player;
import model.PlayerImpl;
import model.Session;
import model.SessionImpl;
import model.GameImpl;
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
	}
	
	private void handleStartSinglePlayer() {
		
		Game gameModel = new GameImpl(GameMode.SINGLE_PLAYER,
									 SessionImpl.getInstance().getUnlockedLevel());
		
		GameView gameView = new GameView(GameMode.SINGLE_PLAYER);
		
		
		
		stage.getScene().setRoot(gameView.getRoot());
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setMinWidth(1200);
	    stage.setMinHeight(800);
	    stage.centerOnScreen();
	}

}
