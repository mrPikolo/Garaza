/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnici.administrator;

import aplikacija.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class AdminController implements Initializable {
    
    public static String tipVozila="";
    
    public static String dodajIliIzmjeniVozilo="";

     @FXML
    private Button obrisiVoziloButton;

    @FXML
    private TableColumn<?, ?> registarskiBrojVozilaTableColumn;

    @FXML
    private Button dodajVoziloButton;

    @FXML
    private TableView<?> tabelaVozlaTableView;

    @FXML
    private ComboBox<?> tipVozilaComboBox;

    @FXML
    private ChoiceBox<?> izborPlatformeChoiceBox;

    @FXML
    private TableColumn<?, ?> tipVozilaTableColumn;

    @FXML
    private TableColumn<?, ?> nazivVozilaTableColumn;

    @FXML
    private Button izmjeniVoziloButton;

    @FXML
    private TableColumn<?, ?> brojSasijeVozilaTableColumn;

    @FXML
    private TableColumn<?, ?> brojMotoraVozilaTableColumn;

    @FXML
    private Button pokreniKorisnikaButton;

    @FXML
    private Button dodajPlatformuButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        
        ObservableList listaVozila = FXCollections.observableArrayList("Automobil","Policijski automobil","Sanitetski automobil",
                "Kombi","Sanitetski kombi","Policijski kombi","Vatrogasni kombi",
                "Motocikl","Policijski motocikl");
        tipVozilaComboBox.setItems(listaVozila); 
        tipVozilaComboBox.getSelectionModel().selectFirst();
        
        dodajVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tipVozila=tipVozilaComboBox.getValue().toString();
                dodajIliIzmjeniVozilo="Dodaj vozilo";
                otvoriVoziloGUI();                
            }
        });
        
        izmjeniVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tipVozila=tipVozilaComboBox.getValue().toString();
                dodajIliIzmjeniVozilo="Izmjeni vozilo";
                otvoriVoziloGUI();                
            }
        });
    }    
    
    private void otvoriVoziloGUI() {
        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            String resurs = "/vozila/vozilo.fxml";
            Pane myPane = (Pane) FXMLLoader.load(getClass().getResource(resurs));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            stage.setTitle("Vozilo");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
