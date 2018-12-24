package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class VatrogasniKombi extends Kombi{
    
    public boolean upaljenaRotacija;
    
    public VatrogasniKombi(String naziv, String brSasije, String brMotora, 
            String foto, String registarskiBroj, int nosivost) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj, nosivost);
        tip="Vatrogasni kombi";
        upaljenaRotacija=false;
    }
    
}
