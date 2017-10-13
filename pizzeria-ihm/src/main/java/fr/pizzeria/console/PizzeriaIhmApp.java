package fr.pizzeria.console;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class PizzeriaIhmApp extends Application {
	
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));     
        
        Scene scene = new Scene(root);
              
        stage.setScene(scene);
        
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        
        stage.setTitle("Tower of Pizza");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
