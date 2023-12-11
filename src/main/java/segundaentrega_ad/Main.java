
package accesodatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import org.xml.sax.SAXException;


public class Main {
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException{
        Ventana1 v;
        
        v = new Ventana1();
        
        v.setVisible(true);
    }
}
