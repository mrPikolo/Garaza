/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vozila;

import aplikacija.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
        
        dodajButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                
                String tipVozila = AdminController.tipVozila;
                boolean dodanoVozilo = false;
                
                switch(tipVozila){
                
                    case "Automobil": 
                        {
                            Automobil a = new Automobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, a);
                           // AdminController.listaVozila.add(a);
                            break;
                        }                       
                    case "Kombi": 
                        {
                            Kombi k = new Kombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k); 
                            break;
                        }
                    case "Motocikl": 
                        {
                            Motocikl m = new Motocikl(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText());
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, m);     
                            break;
                        }
                        
                    case "Policijski automobil": 
                        {
                            PolicijskiAutomobil a = new PolicijskiAutomobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, a); 
                            break;
                        }
                    case "Sanitetski automobil": 
                        {
                            SanitetskiAutomobil a = new SanitetskiAutomobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, a);     
                            break;
                        }
                    case "Policijski kombi": 
                        {
                            PolicijskiKombi k = new PolicijskiKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k);  
                            break;
                        }
                    case "Sanitetski kombi": 
                        {
                            SanitetskiKombi k = new SanitetskiKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k); 
                            break;
                        }
                    case "Vatrogasni kombi": 
                        {
                            VatrogasniKombi k = new VatrogasniKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k); 
                            break;
                        }
                    case "Policijski motocikl": 
                        {
                            PolicijskiMotocikl m = new PolicijskiMotocikl(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText());
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, m);   
                            break;
                        }                        
                }
                if (dodanoVozilo) {
                    //  App.garaza.serijalizuj();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Uspjesno ste se dodali novo vozilo!", ButtonType.OK);

                    alert.showAndWait();
                   // AdminController.popuniListuNaPlatformi(AdminController.trenutnaPlatforma);
                    Stage stage = (Stage) dodajButton.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }    
    
}
