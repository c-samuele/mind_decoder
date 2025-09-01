package control;

import view.*;

import java.util.Optional;

import javafx.stage.Stage;
import model.*;
import view.SessionView;

public class LoginController {
	
	private Stage stage;
	private LoginView loginView;
	private SessionController sessionController;
	private SessionView sessionView;
	
	
	public LoginController(LoginView view,Stage stage){
		this.loginView = view;
		this.stage = stage;
		view.getLoginBtn().setOnAction(e -> handleStart());
	}

	private void handleStart() {
		String playerName = loginView.getPlayerName();
		Player player = new PlayerImpl(playerName);
		
		// singleton
		SessionImpl singletonSession = SessionImpl.getInstance(player,Optional.empty());
		
		sessionView = new SessionView();
		sessionController = new SessionController(stage);
		sessionView.setController(sessionController);
	
		// BTN SINGLE
		sessionView.getSinglePlayerBtn().setOnAction(e -> sessionController.handleStartSinglePlayer());
		// BTN MULTY
		// BTN CHALLENGE 
		
		stage.getScene().setRoot(sessionView.getRoot());
		stage.setWidth(1024);
		stage.setHeight(800);
		stage.setMinWidth(1024);
	    stage.setMinHeight(800);
	    stage.centerOnScreen();
	    
	   
	}
}
