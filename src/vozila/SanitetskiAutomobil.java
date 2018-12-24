package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class SanitetskiAutomobil extends Automobil{
    
    public boolean upaljenaRotacija;
    
    public SanitetskiAutomobil(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj, int brVrata) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj, brVrata);
        tip="Sanitetski automobil";
        upaljenaRotacija=false;
    }
    
}
