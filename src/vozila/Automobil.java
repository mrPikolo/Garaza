package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class Automobil extends Vozilo{
    
    int doorNumber;
    
    public Automobil(String name, String chassisNumber, String engineNumber,
            String image, String registrationNumber, int doorNumber) {
        
        super(name, chassisNumber, engineNumber, image, registrationNumber);
        this.doorNumber = doorNumber;        
    }
    
}
