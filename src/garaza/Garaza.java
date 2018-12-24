package garaza;

import aplikacija.Evidencija;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;

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
        
        for(Platforma p: garaza.platforme){
            p.popunjena=true;
        }
        System.out.println("Nakon popunjavanja svih platformi dozvoljen ulaz u grazu: " + garaza.dozvoljenUlaz());
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
