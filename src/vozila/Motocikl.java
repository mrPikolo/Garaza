package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class Motocikl extends Vozilo{
    
    public Motocikl(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj);
        tip="Motocikl";
    }
    
}
