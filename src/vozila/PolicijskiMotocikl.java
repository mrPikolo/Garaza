package vozila;

/**
 *
 * @author Goran Preradovic
 */
public class PolicijskiMotocikl extends Motocikl implements PolicijskoVozilo {
    
    public boolean upaljenaRotacija;
    
    public PolicijskiMotocikl(String naziv, String brSasije, String brMotora,
            String foto, String registarskiBroj) {
        
        super(naziv, brSasije, brMotora, foto, registarskiBroj);
        tip="Policijski motocikl";
        upaljenaRotacija=true;
    }
    
}
