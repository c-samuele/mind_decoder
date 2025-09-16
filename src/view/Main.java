package view;

import controller.LoginController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
    	
    	stage.setTitle("Mind Decoder");
    	
    	LoginView loginView = new LoginView();
    	
    	LoginController controller = new LoginController(loginView,stage);
    	
    	Scene scene = new Scene(loginView.getRoot(),600,350);

        scene.getStylesheets().add(getClass().getResource("/view/assets/style.css").toExternalForm());
    	
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
