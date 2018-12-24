package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class Kombi extends Vozilo{
    
    int nosivost;

    public Kombi(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj,int nosivost) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj);
        this.nosivost = nosivost;
        tip="Kombi";
    }
    
    
}
