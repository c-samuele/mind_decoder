package controller;

import view.*;
import model.*;
import javafx.stage.Stage;

public class LoginController {
	
	private Stage stage;
	private LoginView loginView;
	private SessionController sessionController;
	
	public LoginController(LoginView view,Stage stage){
		this.loginView = view;
		this.stage = stage;
		
		view.getLoginBtn().setOnAction(e -> handleStart());
	}

	// handler for start the session
	private void handleStart() {
		// Creation player
		String playerName = loginView.getPlayerName();
		Player player = new PlayerImpl(playerName);
		
		// Creation singleton
		SessionImpl.getInstance(player);
		
		// Creation session controller (inside creation session view)
		sessionController = new SessionController(stage);
	
		// Set stage
		stage.getScene().setRoot(sessionController.getSessionView().getRoot());
		stage.setWidth(1024);
		stage.setHeight(800);
		stage.setMinWidth(1024);
	    stage.setMinHeight(400);
	    stage.centerOnScreen();
	    
	   
	}
}
