package control;

import view.*;

import java.util.Optional;

import javafx.stage.Stage;
import model.*;
import view.SessionView;

public class LoginController {
	
	private LoginView view;
	private Stage stage;
	
	
	public LoginController(LoginView view,Stage stage){
		this.view = view;
		this.stage = stage;
		view.getLoginBtn().setOnAction(e -> handleStart());
	}

	private void handleStart() {
		String playerName = view.getPlayerName();
		Player player = new PlayerImpl(playerName);
		
		SessionImpl session = SessionImpl.getInstance(player,Optional.empty());
		
		SessionView sessionView = new SessionView(session.getFirstPlayer().getName(),
												  SessionImpl.getInstance().getBestScore(),
												  SessionImpl.getInstance().getAttemptsAvg(),
												  SessionImpl.getInstance().getTimeAvg(),
												  SessionImpl.getInstance().getUnlockedLevel());
		
		stage.getScene().setRoot(sessionView.getRoot());
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setMinWidth(1200);
	    stage.setMinHeight(800);
	    stage.centerOnScreen();
	    
	    new SessionController(stage, sessionView);
	}
}
