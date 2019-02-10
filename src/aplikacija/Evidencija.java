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
    static final Logger LOGGER = Logger.getLogger("Logger");
    static {
            try {
                handler = new FileHandler("/garazaFajlovi/error.log");
                LOGGER.addHandler(handler);
            } catch (Exception ex) {}
    }
    
    public static void setErrorLog(Exception ex) {
        StackTraceElement elements[] = ex.getStackTrace();
	for (StackTraceElement element:elements) 
            LOGGER.log(Level.WARNING, element.toString());
    }
}
