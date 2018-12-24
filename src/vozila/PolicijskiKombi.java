package vozila;

/**
 *
 * @author Goran Preradovic 
 */
public class PolicijskiKombi extends Kombi implements PolicijskoVozilo{
    
    public boolean upaljenaRotacija;
    
    public PolicijskiKombi(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj, int nosivost) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj, nosivost);
        tip="Policijski kombi";
        upaljenaRotacija=false;
    }
    
    
    
}
