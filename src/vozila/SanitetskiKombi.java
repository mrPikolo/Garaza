package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class SanitetskiKombi extends Kombi{
    
    public boolean upaljenaRotacija;
    
    public SanitetskiKombi(String naziv, String brSasije, String brMotora, 
            String foto, String registarskiBroj, int nosivost) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj, nosivost); 
        tip="Sanitetski kombi";
        upaljenaRotacija=false;
    }
    
}
