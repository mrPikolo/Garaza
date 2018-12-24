package vozila;

import java.io.Serializable;

/**
 *
 * @author Goran Preradovic
 */
public class Vozilo implements Serializable{
    public String naziv;
    public String brSasije;
    public String brMotora;
    public String foto;
    public String registarskiBroj;
    public String tip;

    public Vozilo(String naziv, String brSasije, String brMotora, String foto, String registarskiBroj) {
        this.naziv = naziv;
        this.brSasije = brSasije;
        this.brMotora = brMotora;
        this.foto = foto;
        this.registarskiBroj = registarskiBroj;
    }
    
    
}
