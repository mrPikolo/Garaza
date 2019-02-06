/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vozila;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import korisnici.administrator.AdminController;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class VoziloController implements Initializable {

    @FXML
    private TextField brojSasijeTextField;

    @FXML
    private Label brojVrataIliNosivostLabela;

    @FXML
    private Button izmjeniButton;

    @FXML
    private Button dodajButton;

    @FXML
    private Button dodajFotografijuButton;

    @FXML
    private TextField brojVrataIliNosivostTextField;

    @FXML
    private TextField registarskiBrojTextField;

    @FXML
    private HBox brojVrataIliNosivostHBox;

    @FXML
    private TextField brojMotoraTextField;

    @FXML
    private TextField nazivTextField;

    @FXML
    private CheckBox upaljenaRotacijaCheckBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {                   

        if ((AdminController.tipVozila.equals("Motocikl")) || AdminController.tipVozila.contains("motocikl")) {
            brojVrataIliNosivostHBox.setVisible(false);
            if(AdminController.tipVozila.equals("Motocikl"))
                    upaljenaRotacijaCheckBox.setVisible(false);
        }
        
        if (AdminController.tipVozila.contains("automobil") || AdminController.tipVozila.contains("Automobil")) {
            brojVrataIliNosivostLabela.setText("Broj vrata:");
            if(AdminController.tipVozila.equals("Automobil"))
                    upaljenaRotacijaCheckBox.setVisible(false);
        }
        
        if (AdminController.tipVozila.contains("kombi") || AdminController.tipVozila.contains("Kombi")) {
            brojVrataIliNosivostLabela.setText("Nosivost:");
            if(AdminController.tipVozila.equals("Kombi"))
                    upaljenaRotacijaCheckBox.setVisible(false);
        }

        if ("Dodaj vozilo".equals(AdminController.dodajIliIzmjeniVozilo)) {
            izmjeniButton.setVisible(false);
        } else {
            dodajButton.setVisible(false);
        }
    }    
    
}
