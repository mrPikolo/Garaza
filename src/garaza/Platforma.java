package garaza;

import java.io.Serializable;
import java.util.ArrayList;
import vozila.*;

/**
 *
 * @author Goran Preradovic
 */
public class Platforma implements Serializable{
    
    public int brSlobodnihMjesta;
    public boolean popunjena;
    public MjestoNaPlatformi[][] matrica;
    public ArrayList<Vozilo> listaVozilaNaPlatformi = new ArrayList<>();
    public Vozilo zamjenaVozila;
    
    public Platforma(){
        
        matrica = new MjestoNaPlatformi[10][8];
        for(int i=0;i<matrica.length;i++){
            for(int j=0;j<matrica[i].length;j++){
                matrica[i][j]=new MjestoNaPlatformi();
                
                if(i>1){
                    if(j==0)
                    matrica[i][j].parkingMjesto=true;
                    if(j==7)
                        matrica[i][j].parkingMjesto=true;
                    if(i<10 && j==3)
                        matrica[i][j].parkingMjesto=true;
                    if(i<10 && j==4)
                        matrica[i][j].parkingMjesto=true;
                }
            }            
        }
        
        brSlobodnihMjesta = 28;
        popunjena=false;
    }  
    
    public boolean izmjenaNaVozilu(Vozilo izmjenjenoVozilo){
        boolean izmjenjeno = false;
        int idVozilaZaIzmjenu = listaVozilaNaPlatformi.indexOf(zamjenaVozila);        
        Vozilo voziloZaIzmjenu = listaVozilaNaPlatformi.get(idVozilaZaIzmjenu);
        
        if (izmjenjenoVozilo != null) {
            zamjenaVozila.naziv = izmjenjenoVozilo.naziv;
            zamjenaVozila.brMotora = izmjenjenoVozilo.brMotora;
            zamjenaVozila.brSasije = izmjenjenoVozilo.brSasije;
            zamjenaVozila.foto = izmjenjenoVozilo.foto;
            zamjenaVozila.registarskiBroj = izmjenjenoVozilo.registarskiBroj;
            
            izmjenjeno=true; 
        }
        
        return izmjenjeno;
    }
    
    public void dodajVoziloUListuPlatforme(Vozilo v){
        listaVozilaNaPlatformi.add(v);
    }
    
    public ArrayList<Vozilo> getListaVozilaNaPlatformi() {
        return listaVozilaNaPlatformi;
    }
    
    public void ispis(){
        for(int i=0;i<matrica.length;i++){
            for(int j=0;j<matrica[i].length;j++){
                System.out.println("matrica["+i+"]["+j+"]: "+matrica[i][j]);
            }
        }
    }
    
}
