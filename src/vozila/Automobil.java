package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class Automobil extends Vozilo{
    
    int brVrata;
    
    public Automobil(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj, int brVrata) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj);
        this.brVrata = brVrata;   
        tip="Automobil";
    }
    
}
