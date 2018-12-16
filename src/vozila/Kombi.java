package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class Kombi extends Vozilo{
    
    int loadCapacity;

    public Kombi(String name, String chassisNumber, String engineNumber,
            String image, String registrationNumber,int loadCapacity) {
        
        super(name, chassisNumber, engineNumber, image, registrationNumber);
        this.loadCapacity = loadCapacity;
    }
    
    
}
