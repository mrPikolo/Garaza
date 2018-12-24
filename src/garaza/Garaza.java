package garaza;

import java.io.Serializable;

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
}
