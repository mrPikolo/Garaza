package garaza;

import aplikacija.Evidencija;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;

import vozila.*;

/**
 *
 * @author Goran Preradovic
 */
public class Garaza implements Serializable{
    public int brPlatformi;
    public Platforma [] platforme;
    
    public Garaza(){
        brPlatformi=2;
        platforme = new Platforma[brPlatformi];
        for(int i=0;i<platforme.length;i++){
            platforme[i]=new Platforma();
        }
    }
    
    public Garaza(int brPlatformi){
        this.brPlatformi=brPlatformi;
        platforme = new Platforma[brPlatformi];
        for(int i=0;i<platforme.length;i++){
            platforme[i]=new Platforma();
        }
    }
    
    public boolean dozvoljenUlaz(){
        boolean dozvoljeno= true;
        for(int i=0;i<platforme.length;i++){
            if(platforme[i] != null){
            dozvoljeno &= platforme[i].popunjena;
            }
            else{
                System.out.println("Elementi niza platforme nisu referencirani");
            }
        }
        return !dozvoljeno;
    }
    
    public static void main(String [] args){
        Garaza garaza = new Garaza();
        System.out.println("Dozvoljen ulaz: " + garaza.dozvoljenUlaz());
        /*
        for(Platforma p: garaza.platforme){
            p.popunjena=true;
        }
        System.out.println("Nakon popunjavanja svih platformi dozvoljen ulaz u grazu: " + garaza.dozvoljenUlaz());
        */
        System.out.println("Dodavanje vozila");
        Automobil a1 = new Automobil("Audi", "1234", "4321", "slika", "123-A-123", 4);
        Automobil a2 = new Automobil("Golf", "2341", "1256", "slika", "321-G-123", 4);
        Automobil a3 = new Automobil("VW", "3214", "5321", "slika", "213-D-123", 4);
        Kombi k1 = new Kombi("Mercedes", "6578", "1256", "slika", "578-M-124", 3);
        Motocikl m1 = new Motocikl("BMW", "3456", "3456", "slika", "129-A-289");
        
        garaza.dodavanjeVozilaNaParkingMjesto(1, a1);
        garaza.dodavanjeVozilaNaParkingMjesto(1, a2);
        garaza.dodavanjeVozilaNaParkingMjesto(1, a3);
        garaza.dodavanjeVozilaNaParkingMjesto(1, k1);
        garaza.dodavanjeVozilaNaParkingMjesto(1, m1);
        
        garaza.platforme[1].ispis();
        System.out.println("Broj platformi u garazi je: " + garaza.brPlatformi);
        System.out.println("Broj slobodnih mjesta na platformi 0: " + garaza.platforme[0].brSlobodnihMjesta);
        System.out.println("Broj slobodnih mjesta na platformi 1: " + garaza.platforme[1].brSlobodnihMjesta);
    }
    
    public void dodavanjeVozilaNaParkingMjesto(int redBrojPlatforme,Vozilo vozilo){
        if(platforme[redBrojPlatforme].brSlobodnihMjesta == 0){
            platforme[redBrojPlatforme].popunjena = true;
            System.out.println("Platforma " + redBrojPlatforme + " je pounjena!");
        }
        else {
            boolean dodanoVozilo = false;
            for (int i = 2; i < 10; i++) {
                for (int j = 0; j < 8; j++) {
                    if (platforme[redBrojPlatforme].matrica[i][j].parkingMjesto && platforme[redBrojPlatforme].matrica[i][j].slobodno) {
                        
                        //System.out.println("Parking mjesto[" + i + "][" + j + "] je slobodno" );
                        platforme[redBrojPlatforme].matrica[i][j].vozilo = vozilo;
                        platforme[redBrojPlatforme].matrica[i][j].slobodno = false;
                        dodanoVozilo = true;
                        platforme[redBrojPlatforme].brSlobodnihMjesta--;                        
                    }
                    if (dodanoVozilo) {
                        return;
                    }
                }
            }    
        }
    }
    
    public void serijalizuj(){
        try (FileOutputStream fos = new FileOutputStream("./garazaFajlovi/garaza.ser");
                ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(platforme);
        } catch (Exception e) {
            Evidencija.log(Level.WARNING, Garaza.class.getName(), e);
        }
    }
    
    public void deserijalizuj() {
        try (FileInputStream fis = new FileInputStream("./garazaFajlovi/garaza.ser");
                ObjectInputStream in = new ObjectInputStream(fis)) {
            platforme = (Platforma []) in.readObject();
        } catch (Exception e) {
            Evidencija.log(Level.WARNING, Garaza.class.getName(), e);
        }
    }
}
