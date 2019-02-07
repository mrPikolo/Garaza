package garaza;

import aplikacija.Evidencija;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;

import vozila.*;

/**
 *
 * @author Goran Preradovic
 */
public class Garaza implements Serializable{
    
    public static ArrayList<Platforma> listaPlatformi=new ArrayList<>();
    
    public Garaza(){
        listaPlatformi.add(new Platforma());
        listaPlatformi.add(new Platforma());
    }
    
    public boolean dozvoljenUlaz(){
        boolean dozvoljeno= true;
        for(Platforma p:listaPlatformi){
            if(p != null){
            dozvoljeno &= p.popunjena;
            }
            else{
                System.out.println("Elementi niza platforme nisu referencirani");
            }
        }
        return !dozvoljeno;
    }
    
    public boolean dodajPlatformu(){
        boolean dodanaPlatforma;
        int brPlatformiPrije = listaPlatformi.size();
        listaPlatformi.add(new Platforma());
        if(listaPlatformi.size()>brPlatformiPrije)
            dodanaPlatforma = true;
        else
            dodanaPlatforma = false;
        return dodanaPlatforma;
    }
    
    public boolean dodavanjeVozilaNaParkingMjesto(int redBrojPlatforme,Vozilo vozilo){
        boolean dodanoVozilo = false;
        if(listaPlatformi.get(redBrojPlatforme-1).brSlobodnihMjesta <= 0 ){
            listaPlatformi.get(redBrojPlatforme-1).popunjena = true;
            System.out.println("Platforma " + redBrojPlatforme + " je pounjena!");
            dodanoVozilo = false;
        }
        else {
            //boolean dodanoVozilo = false;
            for (int i = 2; i < 10; i++) {
                for (int j = 0; j < 8; j++) {
                    if (listaPlatformi.get(redBrojPlatforme-1).matrica[i][j].parkingMjesto && listaPlatformi.get(redBrojPlatforme-1).matrica[i][j].slobodno) {
                        listaPlatformi.get(redBrojPlatforme-1).matrica[i][j].vozilo = vozilo;
                        listaPlatformi.get(redBrojPlatforme-1).matrica[i][j].slobodno = false;
                        dodanoVozilo = true;
                        listaPlatformi.get(redBrojPlatforme-1).brSlobodnihMjesta--;                        
                    }
                    if(dodanoVozilo)
                        break;
                }
                if(dodanoVozilo){
                    listaPlatformi.get(redBrojPlatforme-1).listaVozilaNaPlatformi.add(vozilo);
                        break;
                }
            }    
        }
        return dodanoVozilo;
    }
    
    public void serijalizuj(){
        try (FileOutputStream fos = new FileOutputStream("./garazaFajlovi/garaza.ser");
                ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(listaPlatformi);
        } catch (Exception e) {
            Evidencija.log(Level.WARNING, Garaza.class.getName(), e);
        }
    }
    
    public void deserijalizuj() {
        try (FileInputStream fis = new FileInputStream("./garazaFajlovi/garaza.ser");
                ObjectInputStream in = new ObjectInputStream(fis)) {
            listaPlatformi = (ArrayList<Platforma>) in.readObject();
        } catch (Exception e) {
            Evidencija.log(Level.WARNING, Garaza.class.getName(), e);
        }
    }    
}
