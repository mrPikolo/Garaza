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
import garaza.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import static korisnici.administrator.AdminController.trenutnaPlatforma;

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
    
    public static Vozilo izmjenjenoVozilo;
    
    public static String putanjaFotoVozila = ".\\src\\garazaFajlovi\\foto";
    public static int fotoID=0;
    public String fileFoto ="";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dodajFotografijuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser choose=new FileChooser();
                File putanjaOdabraneSlike=choose.showOpenDialog((Stage) dodajButton.getScene().getWindow());
                fotoID++;
                fileFoto=putanjaFotoVozila + putanjaOdabraneSlike.getAbsolutePath().substring(putanjaOdabraneSlike.getAbsolutePath().lastIndexOf('\\'));
                File novaPutanja = new File(fileFoto);
                
                try {
                    //novaPutanja.createNewFile();
                    Files.copy(putanjaOdabraneSlike.toPath(),novaPutanja.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(VoziloController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });

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
        
        if (AdminController.dodajIliIzmjeniVozilo.equals("Izmjeni vozilo")) {
            Vozilo v = Garaza.listaPlatformi.get(AdminController.trenutnaPlatforma - 1).zamjenaVozila;
            // prikazi podatke vozila za zamjenu
            nazivTextField.setText(v.naziv);
            brojMotoraTextField.setText(v.brMotora);
            brojSasijeTextField.setText(v.brSasije);
            registarskiBrojTextField.setText(v.registarskiBroj);
            
            if (v.tip.contains("ski") || v.tip.contains("sni")) {
                upaljenaRotacijaCheckBox.selectedProperty().set(true);
            }

            if (v.tip.contains("kombi") || v.tip.contains("Kombi")) {
                Kombi k = (Kombi) v;
                brojVrataIliNosivostTextField.setText(String.valueOf(k.nosivost));
            }
            
            if (v.tip.contains("automobil") || v.tip.contains("Automobil")) {
                Automobil a = (Automobil) v;
                brojVrataIliNosivostTextField.setText(String.valueOf(a.brVrata));
            }
           
        }    

        if ("Dodaj vozilo".equals(AdminController.dodajIliIzmjeniVozilo)) {
            izmjeniButton.setVisible(false);
        } else {
            dodajButton.setVisible(false);
        }
        
        izmjeniButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String tipVozila = Garaza.listaPlatformi.get(AdminController.trenutnaPlatforma - 1).zamjenaVozila.tip;
                boolean izmjenjeno = false;
                
                switch (tipVozila) {

                    case "Automobil": {
                        Automobil a = new Automobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, a);
                        if(izmjenjeno)
                            izmjenjenoVozilo=a;
                        break;
                    }
                    case "Kombi": {
                        Kombi k = new Kombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, k);
                        if(izmjenjeno)
                            izmjenjenoVozilo=k;
                        break;
                    }
                    case "Motocikl": {
                        Motocikl m = new Motocikl(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText());
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, m);
                        if(izmjenjeno)
                            izmjenjenoVozilo=m;
                        break;
                    }

                    case "Policijski automobil": {
                        PolicijskiAutomobil a = new PolicijskiAutomobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, a);
                        if(izmjenjeno)
                            izmjenjenoVozilo=a;
                        break;
                    }
                    case "Sanitetski automobil": {
                        SanitetskiAutomobil a = new SanitetskiAutomobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, a);
                        if(izmjenjeno)
                            izmjenjenoVozilo=a;
                        break;
                    }
                    case "Policijski kombi": {
                        PolicijskiKombi k = new PolicijskiKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), "foto", registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, k);
                        if(izmjenjeno)
                            izmjenjenoVozilo=k;
                        break;
                    }
                    case "Sanitetski kombi": {
                        SanitetskiKombi k = new SanitetskiKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, k);
                        if(izmjenjeno)
                            izmjenjenoVozilo=k;
                        break;
                    }
                    case "Vatrogasni kombi": {
                        VatrogasniKombi k = new VatrogasniKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, k);
                        if(izmjenjeno)
                            izmjenjenoVozilo=k;
                        break;
                    }
                    case "Policijski motocikl": {
                        PolicijskiMotocikl m = new PolicijskiMotocikl(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText());
                        izmjenjeno = App.garaza.izmjeniVoziloNaPlatformi(AdminController.trenutnaPlatforma, m);
                        if(izmjenjeno)
                            izmjenjenoVozilo=m;
                        break;
                    }
                }
                AdminController.izmjenjenoVozilo=izmjenjenoVozilo;
                if(izmjenjeno){
                    //ako je izmjenjeno ponovo učitaj podatke u tabelu platforme
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Uspjesno ste se izmjenili vozilo!", ButtonType.OK);

                    alert.showAndWait();
                    Stage stage = (Stage) dodajButton.getScene().getWindow();
                    stage.close();
                    ;
                }
            }
        });
        
        dodajButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                
                String tipVozila = AdminController.tipVozila;
                boolean dodanoVozilo = false;
                
                switch(tipVozila){
                
                    case "Automobil": 
                        {
                            Automobil a = new Automobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, a);
                           // AdminController.listaVozila.add(a);
                            break;
                        }                       
                    case "Kombi": 
                        {
                            Kombi k = new Kombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k); 
                            break;
                        }
                    case "Motocikl": 
                        {
                            Motocikl m = new Motocikl(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText());
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, m);     
                            break;
                        }
                        
                    case "Policijski automobil": 
                        {
                            PolicijskiAutomobil a = new PolicijskiAutomobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, a); 
                            break;
                        }
                    case "Sanitetski automobil": 
                        {
                            SanitetskiAutomobil a = new SanitetskiAutomobil(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, a);     
                            break;
                        }
                    case "Policijski kombi": 
                        {
                            PolicijskiKombi k = new PolicijskiKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k);  
                            break;
                        }
                    case "Sanitetski kombi": 
                        {
                            SanitetskiKombi k = new SanitetskiKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k); 
                            break;
                        }
                    case "Vatrogasni kombi": 
                        {
                            VatrogasniKombi k = new VatrogasniKombi(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText(), Integer.valueOf(brojVrataIliNosivostTextField.getText()));
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, k); 
                            break;
                        }
                    case "Policijski motocikl": 
                        {
                            PolicijskiMotocikl m = new PolicijskiMotocikl(nazivTextField.getText(), brojSasijeTextField.getText(), brojMotoraTextField.getText(), fileFoto, registarskiBrojTextField.getText());
                            dodanoVozilo = App.garaza.dodavanjeVozilaNaParkingMjesto(AdminController.trenutnaPlatforma, m);   
                            break;
                        }                        
                }
                if (dodanoVozilo) {
                    //  App.garaza.serijalizuj();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Uspjesno ste se dodali novo vozilo!", ButtonType.OK);

                    alert.showAndWait();
                    Stage stage = (Stage) dodajButton.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }    
    
}
