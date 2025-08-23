package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {
	
	 private VBox root;
	 private TextField nameField;
	 private Button loginBtn;
	 
	 
	public LoginView(){
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		root.getStyleClass().add("loginView");
		

		Label label = new Label("Enter the name of player");
		label.getStyleClass().add("titleLogin");
		
		nameField = new TextField();
		nameField.setPromptText("Player name");
		nameField.getStyleClass().add("nameField");
		
		
		loginBtn = new Button("Confirm");
		loginBtn.getStyleClass().add("btnMain");
		
		root.getChildren().addAll(label,nameField,loginBtn);
		
	 }
	
	public Parent getRoot() {
		return root;
	}
	
	   public Button getLoginBtn() {
	        return this.loginBtn;
	    }
	
	public String getPlayerName() {
		return nameField.getText();
	}
	
}
