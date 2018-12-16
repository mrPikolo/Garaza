package garaza;

import java.io.Serializable;
import vozila.Vozilo;

/**
 *
 * @author Goran Preradovic
 */
public class MjestoNaPlatformi implements Serializable{
    
    // slobodno podrazumjeva da li postoji vozilo na tom mjestu
    public boolean slobodno;
    public boolean parkingMjesto;
    public Vozilo vozilo;
    
    public MjestoNaPlatformi(){
        slobodno=true;
        parkingMjesto=false;
        vozilo=null;
    }
    
    public String toString(){
        return " slobodno="+slobodno+"; parkingMjesto="+parkingMjesto+"; vozilo="+vozilo;
    }
    
}
