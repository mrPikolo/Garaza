package aplikacija;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goran Preradovic
 */
public class Evidencija {
    
    public static Handler handler;
    {
        try {
            handler = new FileHandler("./garazaFajlovi/error.log");
        } catch (Exception ex) {
            Logger.getLogger(Evidencija.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void log(Level level,String name,Exception ex) {
        Logger logger = Logger.getLogger(name);
        logger.addHandler(handler);
        logger.log(level,ex.fillInStackTrace().toString());
    }
}
