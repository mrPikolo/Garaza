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
    
    private static int brFoto = 0;
    private String putanjaDoFoto = "./garazaFajlovi/foto/";

    public Vozilo(String naziv, String brSasije, String brMotora, String foto, String registarskiBroj) {
        brFoto++;
        this.naziv = naziv;
        this.brSasije = brSasije;
        this.brMotora = brMotora;
        this.foto = foto;
        this.registarskiBroj = registarskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getBrSasije() {
        return brSasije;
    }

    public void setBrSasije(String brSasije) {
        this.brSasije = brSasije;
    }

    public String getBrMotora() {
        return brMotora;
    }

    public void setBrMotora(String brMotora) {
        this.brMotora = brMotora;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public static int getBrFoto() {
        return brFoto;
    }

    public static void setBrFoto(int brFoto) {
        Vozilo.brFoto = brFoto;
    }

    public String getPutanjaDoFoto() {
        return putanjaDoFoto;
    }

    public void setPutanjaDoFoto(String putanjaDoFoto) {
        this.putanjaDoFoto = putanjaDoFoto;
    }

    
    
    public String toString(){
        return tip + " " + registarskiBroj;
    }
}
