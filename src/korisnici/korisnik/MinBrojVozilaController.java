/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnici.korisnik;

import aplikacija.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class MinBrojVozilaController implements Initializable {

    @FXML
    private Button dodajButton;

    @FXML
    private TextField minBrVozilaTextField;

    @FXML
    void pokreniKorisnickiGUI(ActionEvent event) {
        otvoriKorisnikGUI();
        //System.out.println("min: " + minBrVozilaTextField.getText());
        Stage stage = (Stage) dodajButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String s = minBrVozilaTextField.getText();
        
    }

    private void otvoriKorisnikGUI() {
        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            String resurs = "/korisnici/korisnik/korisnik.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resurs));
            Parent root = loader.load();
            
            KorisnikController controller = loader.<KorisnikController>getController();
            controller.setMin(minBrVozilaTextField.getText());
            //Scene myScene = new Scene(myPane);
            Scene myScene = new Scene(root);
            stage.setScene(myScene);
            stage.setTitle("Korisnik");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
