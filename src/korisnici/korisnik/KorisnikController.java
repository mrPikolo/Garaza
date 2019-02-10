/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korisnici.korisnik;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Goran
 */
public class KorisnikController implements Initializable {

    
    @FXML
    private Label minBrVozilaLabel;
    
    public int minBrVozila;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void setMin(String s){
        minBrVozilaLabel.setText(s);
        minBrVozila=Integer.valueOf(s);
    }
    
}
