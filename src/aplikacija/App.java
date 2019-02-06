/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacija;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import korisnici.administrator.*;

/**
 *
 * @author Goran
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            String resurs = "/korisnici/administrator/admin.fxml";
            Pane myPane = (Pane) FXMLLoader.load(getClass().getResource(resurs));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            stage.setTitle("Administrator");
            stage.show();
    } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
