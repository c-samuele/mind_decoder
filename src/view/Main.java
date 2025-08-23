package view;

import javafx.application.Application;
import control.LoginController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
    	
    	LoginView login = new LoginView();
    	LoginController controller = new LoginController(login,stage);
    	Scene scene = new Scene(login.getRoot(),600,350);
    	stage.setTitle("Mind Decoder");

        scene.getStylesheets().add(getClass().getResource("/view/assets/style.css").toExternalForm());
    	
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
