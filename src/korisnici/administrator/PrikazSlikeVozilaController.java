/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnici.administrator;

import aplikacija.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static korisnici.administrator.AdminController.fotoVozila;


public class PrikazSlikeVozilaController implements Initializable {

    @FXML
    private ImageView slikaImageView;

    @FXML
    private Button okButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("slika:  "+ AdminController.fotoVozila);
        FileInputStream  f=null;
        try {
            f = new FileInputStream (AdminController.fotoVozila);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrikazSlikeVozilaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(f);
        slikaImageView.setImage(image);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });
    }    
    
}
