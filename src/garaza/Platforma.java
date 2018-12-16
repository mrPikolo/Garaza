package garaza;

import java.io.Serializable;

/**
 *
 * @author Goran Preradovic
 */
public class Platforma implements Serializable{
    
    public int brSlobodnihMjesta;
    public boolean popunjena;
    public MjestoNaPlatformi[][] matrica;
    
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
                    if((i<10) && j==3)
                        matrica[i][j].parkingMjesto=true;
                    if((i<10) && j==4)
                        matrica[i][j].parkingMjesto=true;
                }
            }            
        }
        
        brSlobodnihMjesta = 28;
        popunjena=false;
    }
    
    public static void main(String [] args){
        Platforma platforma = new Platforma();
        
        for(int i=0;i<platforma.matrica.length;i++){
            for(int j=0;j<platforma.matrica[i].length;j++){
                System.out.println("matrica["+i+"]["+j+"]: "+platforma.matrica[i][j]);
            }
        }
    }
    
}
