package view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.GameMode;

public class GameView {
	
    private BorderPane root;
    
	  
    public GameView(GameMode mode){
    	root = new BorderPane();
    	
    	Label msg = new Label();
    	
    	 switch(mode) {
         case SINGLE_PLAYER:
             msg.setText("Single Player Mode");
             break;
         case MULTY_PLAYER:
             msg.setText("Multiplayer Mode");
             break;
         case AI_CHALLENGE:
             msg.setText("Challenge AI Mode");
             break;
     }
    	
    	msg.getStyleClass().add("msg");
    	
    	root.setCenter(msg);
    	
    }
	
	
	public Parent getRoot() {
		return root;
	}
}
