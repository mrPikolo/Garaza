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
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    
    public static Vozilo izmjenjenoVozilo;
    
    public static ObservableList listaVozila = FXCollections.observableArrayList();
    
    public static ObservableList listaRednihBrojevaPlatformi = FXCollections.observableArrayList();

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
    
    @FXML
    private Button prikaziFotoButton;
    
    public static String fotoVozila;
    
    @FXML
    void promjenaPlatforme(ActionEvent event) {
        // azurira redni broj odabrane platforme
       redniBrojOdabranePlatforme(); 
        //tabelaVozlaTableView.setItems(vozilaNaPlatformi(trenutnaPlatforma));
        popuniTabelu();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        trenutnaPlatforma = 0;
        //izborPlatformeComboBox.setValue(String.valueOf(trenutnaPlatforma));
        popuniListuPlatformi();

        
        ObservableList listaVozila = FXCollections.observableArrayList("Automobil","Policijski automobil","Sanitetski automobil",
                "Kombi","Sanitetski kombi","Policijski kombi","Vatrogasni kombi",
                "Motocikl","Policijski motocikl");
        tipVozilaComboBox.setItems(listaVozila); 
        tipVozilaComboBox.getSelectionModel().selectFirst();
        
        popuniTabelu();
        
        prikaziFotoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fotoVozila = tabelaVozlaTableView.getSelectionModel().getSelectedItem().foto;
                try {
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    String resurs = "/korisnici/administrator/prikazSlikeVozila.fxml";
                    Pane myPane = (Pane) FXMLLoader.load(getClass().getResource(resurs));
                    Scene myScene = new Scene(myPane);
                    stage.setScene(myScene);
                    stage.setTitle("Slika vozila");
                    stage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        obrisiVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                redniBrojOdabranePlatforme();
                Vozilo v = tabelaVozlaTableView.getSelectionModel().getSelectedItem();
                listaVozilaNaTrenutnojPlatformi(trenutnaPlatforma).remove(v);
                redniBrojOdabranePlatforme();
                tabelaVozlaTableView.setItems(vozilaNaPlatformi(trenutnaPlatforma));
                //System.out.println("Vozila na platformi " + trenutnaPlatforma + " poslije brisanja su: " + vozilaNaPlatformi(trenutnaPlatforma) );
            }
        });
        
        dodajVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(izborPlatformeComboBox.getValue()==null){
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "Niste odabrali platformu!", ButtonType.OK);

                    alert.showAndWait();
                    //Stage stage = (Stage) dodajVoziloButton.getScene().getWindow();
                    //stage.close();
                    }
                else{
                tipVozila=tipVozilaComboBox.getValue().toString();
                dodajIliIzmjeniVozilo="Dodaj vozilo";
                redniBrojOdabranePlatforme();
                otvoriVoziloGUI();    
                tabelaVozlaTableView.setItems(vozilaNaPlatformi(trenutnaPlatforma));
                }
            }
        });
        
        izmjeniVoziloButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Garaza.listaPlatformi.get(trenutnaPlatforma - 1).zamjenaVozila = tabelaVozlaTableView.getSelectionModel().getSelectedItem();
                
                tipVozila=tipVozilaComboBox.getValue().toString();
                dodajIliIzmjeniVozilo="Izmjeni vozilo";
                redniBrojOdabranePlatforme();
                otvoriVoziloGUI();  
                popuniTabelu();
            }
        });
        
        dodajPlatformuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (App.garaza.dodajPlatformu()) {
                //dodajPlatformu();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Uspjesno ste se dodali novu platformu!", ButtonType.OK);
                    alert.showAndWait();
                }
                //dodajPlatformuUComoboBox();
                popuniListuPlatformi();
            }
        });
        
        pokreniKorisnikaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.garaza.serijalizuj();
                //App.garaza.deserijalizuj();
                //popuniListuPlatformi();
                minBrojVozilaGUI();
                Stage stage = (Stage) pokreniKorisnikaButton.getScene().getWindow();
                stage.close();
            }
        });
    } 
    
    public void popuniTabelu(){
        tipVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("tip"));
        nazivVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("naziv"));
        brojSasijeVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("brSasije"));
        brojMotoraVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("brMotora"));
        registarskiBrojVozilaTableColumn.setCellValueFactory(new PropertyValueFactory<Vozilo, String>("registarskiBroj"));
        
        //popuniti tabelu sa listom vozila sa platforme
       
        tabelaVozlaTableView.setItems(vozilaNaPlatformi(trenutnaPlatforma));
    }
    
    public ObservableList listaAutomobilaNaPlatformi() {
        Platforma p = Garaza.listaPlatformi.get(trenutnaPlatforma);
        for(Vozilo v: (p.listaVozilaNaPlatformi))
            listaVozila.add(v);
        return listaVozila;
    }
    
    public ObservableList listaAutomobilaNaPlatformi(int indeksPlatforme) {
        Platforma p = Garaza.listaPlatformi.get(indeksPlatforme-1);
        for(Vozilo v: (p.listaVozilaNaPlatformi))
            listaVozila.add(v);
        return listaVozila;
    }
    
   public ObservableList izmjenjenAutomobilaNaPlatformi(int indeksPlatforme,Vozilo izmjenjnoVozilo) {
        Platforma p = Garaza.listaPlatformi.get(indeksPlatforme-1);
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
    
    private void dodajPlatformuUComoboBox(){
        listaRednihBrojevaPlatformi.add(Garaza.listaPlatformi.size()+1);
        izborPlatformeComboBox.setItems(listaRednihBrojevaPlatformi);
    }
    
    private void popuniListuPlatformi(){
        
       // System.out.println("Garaza.listaPlatformi.size() : " + Garaza.listaPlatformi.size());
       // System.out.println("listaRednihBrojevaPlatformi.size() : " + listaRednihBrojevaPlatformi.size());
        while(Garaza.listaPlatformi.size()!= listaRednihBrojevaPlatformi.size())
            listaRednihBrojevaPlatformi.add(listaRednihBrojevaPlatformi.size()+1);
        System.out.println("listaRednihBrojevaPlatformi.size() nakon dodavanja: " + listaRednihBrojevaPlatformi.size());
        izborPlatformeComboBox.setItems(listaRednihBrojevaPlatformi);
    }
    
    private void minBrojVozilaGUI() {
        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            String resurs = "/korisnici/korisnik/minBrojVozila.fxml";
            Pane myPane = (Pane) FXMLLoader.load(getClass().getResource(resurs));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        System.out.println("trenutnaPlatforma = " + trenutnaPlatforma );
        
        System.out.println("izborPlatformeComboBox.getValue().toString() = " + izborPlatformeComboBox.getValue().toString());
        trenutnaPlatforma = Integer.valueOf(izborPlatformeComboBox.getValue().toString()); 
    }
    
    private ArrayList listaVozilaNaTrenutnojPlatformi(int brojPlatforme){
        int broj = brojPlatforme==0?0:brojPlatforme-1;        
        Platforma trenutanPlatforma=Garaza.listaPlatformi.get(broj);
        return trenutanPlatforma.listaVozilaNaPlatformi;
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
