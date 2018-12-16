package garaza;

import java.io.Serializable;

/**
 *
 * @author Goran Preradovic
 */
public class Garaza implements Serializable{
    public int brPlatformi;
    public static Platforma [] platforme;
    
    {
        platforme = new Platforma[brPlatformi];
    }
    
    public Garaza(){
        brPlatformi=2;
        for(Platforma p: platforme){
            p=new Platforma();
        }
    }
    
    public boolean dozvoljenUlazUGrazu(){
        boolean dozvoljeno= true;
        for(Platforma p:platforme){
            dozvoljeno &= p.popunjena;
        }
        return dozvoljeno;
    }
}
