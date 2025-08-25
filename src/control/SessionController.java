package control;

import java.util.Optional;

import javafx.stage.Stage;
import model.Player;
import model.PlayerImpl;
import model.Session;
import model.SessionImpl;
import view.SessionView;

public class SessionController {
	
	private Stage stage;
	private SessionView sessionView;
	private SessionImpl sessionModel;
	
	public SessionController(Stage stage,SessionView sessionView) {
		this.stage = stage;
		this.sessionView = sessionView;
		
		sessionView.getSinglePlayerBtn().setOnAction(e -> handleStart());
	}
	
	private void handleStart() {
		
		
		stage.getScene().setRoot(sessionView.getRoot());
		stage.setWidth(1200);
		stage.setHeight(800);
		stage.setMinWidth(1200);
	    stage.setMinHeight(800);
	    stage.centerOnScreen();
	}

}
