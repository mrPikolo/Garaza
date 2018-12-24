package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class PolicijskiAutomobil extends Automobil implements PolicijskoVozilo{
    
    public boolean upaljenaRotacija;
    
    public PolicijskiAutomobil(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj, int brVrata) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj, brVrata);
        tip="Policijski automobil";
        upaljenaRotacija= false;
    }
    
}
