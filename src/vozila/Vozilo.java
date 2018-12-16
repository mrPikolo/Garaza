package vozila;

import java.io.Serializable;

/**
 *
 * @author Goran Preradovic
 */
public class Vozilo implements Serializable{
    public String name;
    public String chassisNumber;
    public String engineNumber;
    public String image;
    public String registrationNumber;
    //public String type;

    public Vozilo(String name, String chassisNumber, String engineNumber, String image, String registrationNumber) {
        this.name = name;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
        this.image = image;
        this.registrationNumber = registrationNumber;
    }
    
    
}
