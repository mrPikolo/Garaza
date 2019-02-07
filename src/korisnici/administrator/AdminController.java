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
import garaza.*;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;
import vozila.Automobil;
import vozila.Vozilo;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class AdminController implements Initializable {
    
    public static String tipVozila="";
    
    public static String dodajIliIzmjeniVozilo="";
    
    public static int trenutnaPlatforma = 0;
    
    public static ObservableList listaVozila = FXCollections.observableArrayList();

     @FXML
    private Button obrisiVoziloButton;

    @FXML
    private TableColumn<Vozilo, String> registarskiBrojVozilaTableColumn;

    @FXML
    private Button dodajVoziloButton;

    @FXML
    private TableView<Vozilo> tabelaVozlaTableView;

    @FXML
    private ComboBox<?> tipVozilaComboBox;

    @FXML
    private ComboBox<?> izborPlatformeComboBox;

    @FXML
    private TableColumn<Vozilo, String> tipVozilaTableColumn;

    @FXML
    private TableColumn<Vozilo, String> nazivVozilaTableColumn;

    @FXML
    private Button izmjeniVoziloButton;

    @FXML
    private TableColumn<Vozilo, String> brojSasijeVozilaTableColumn;

    @FXML
    private TableColumn<Vozilo, String> brojMotoraVozilaTableColumn;

    @FXML
    private Button pokreniKorisnikaButton;

    @FXML
    private Button dodajPlatformuButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        popuniListuPlatformi();
        
        ObservableList listaVozila = FXCollections.observableArrayList("Automobil","Policijski automobil","Sanitetski automobil",
                "Kombi","Sanitetski kombi","Policijski kombi","Vatrogasni kombi",
                "Motocikl","Policijski motocikl");
        tipVozilaComboBox.setItems(listaVozila); 
        tipVozilaComboBox.getSelectionModel().selectFirst();
        
        tipVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("tip"));
        nazivVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("naziv"));
        brojSasijeVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("brSasije"));
        brojMotoraVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("brMotora"));
        registarskiBrojVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("registarskiBroj"));
        
        //popuniti tabelu sa listom vozila sa platforme
       
        tabelaVozlaTableView.setItems(vozilaNaPlatformi(trenutnaPlatforma));
        
        dodajVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tipVozila=tipVozilaComboBox.getValue().toString();
                dodajIliIzmjeniVozilo="Dodaj vozilo";
                redniBrojOdabranePlatforme();
                otvoriVoziloGUI(); 
                System.out.println("TEST");    
                tabelaVozlaTableView.setItems(vozilaNaPlatformi(trenutnaPlatforma));

            }
        });
        
        izmjeniVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tipVozila=tipVozilaComboBox.getValue().toString();
                dodajIliIzmjeniVozilo="Izmjeni vozilo";
                redniBrojOdabranePlatforme();
                otvoriVoziloGUI();                
            }
        });
        
        dodajPlatformuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (App.garaza.dodajPlatformu()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Uspjesno ste se dodali novu platformu!", ButtonType.OK);
                    alert.showAndWait();
                }
                popuniListuPlatformi();
            }
        });
    }  
    
    public ObservableList listaAutomobilaNaPlatformi() {
        Platforma p = Garaza.listaPlatformi.get(trenutnaPlatforma);
        for(Vozilo v: (p.listaVozilaNaPlatformi))
            listaVozila.add(v);
        return listaVozila;
    }
    
    public static void popuniListuNaPlatformi(int brojPlatforme){
        System.out.println("Broj platforme: " + (brojPlatforme-1));
        Platforma p = Garaza.listaPlatformi.get(brojPlatforme-1);
        
        for(Vozilo v: (p.listaVozilaNaPlatformi))
            listaVozila.add(v);
       // System.out.println("iz popuniListuNaPlatformi(int brojPlatforme) je lista: " + listaVozila);
    }
    
    private void popuniListuPlatformi(){
        ObservableList listaRednihBrojevaPlatformi = FXCollections.observableArrayList();
        for(int i=0;i<Garaza.listaPlatformi.size();i++)
            listaRednihBrojevaPlatformi.add(i+1);
        izborPlatformeComboBox.setItems(listaRednihBrojevaPlatformi);
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
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void redniBrojOdabranePlatforme(){      
        trenutnaPlatforma = Integer.valueOf(izborPlatformeComboBox.getValue().toString()); 
    }
    
    public static ObservableList<Vozilo> vozilaNaPlatformi(int brojPlatforme)
    {       
        int broj = brojPlatforme==0?0:brojPlatforme-1;
        
        Platforma trenutanPlatforma=Garaza.listaPlatformi.get(broj);
       // System.out.println("::"+trenutanPlatforma.listaVozilaNaPlatformi.size()+"br"+broj);
        ObservableList<Vozilo> vozila=FXCollections.observableArrayList(trenutanPlatforma.listaVozilaNaPlatformi);
        return vozila;
    }
    
}
