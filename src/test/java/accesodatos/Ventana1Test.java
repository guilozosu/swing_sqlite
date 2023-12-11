/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package accesodatos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

/**
 *
 * @author dieef
 */
public class Ventana1Test extends TestCase {
    
    public Ventana1Test(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testListarEmpresas() {
        
        // given una ventana con 4 empresas
        Ventana1 v1 = null;
        try {
            
            v1 = new Ventana1();
            v1.lista_empresas.clear();
            v1.limpiar_tabla_empresas();
            
            v1.lista_empresas.add(new Empresa());
            v1.lista_empresas.add(new Empresa());
            v1.lista_empresas.add(new Empresa());
            v1.lista_empresas.add(new Empresa());
            
            
        } catch (IOException | ClassNotFoundException | SAXException ex) {
            Logger.getLogger(Ventana1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // when llamo a listarEmpresas, debe rellenar el table model con las mismas filas que tengamos en lista
        v1.listar_empresas();
        
        // then el tamaño de la lista tiene que ser el mismo que la cantidad de filas
        assertEquals(v1.lista_empresas.size(),v1.jTable_empresa.getModel().getRowCount());
        
    }

    public void testBorrarEmpresa() {
        
        // given una ventana con 2 empresas
        Ventana1 v1 = null;
        try {
            
            v1 = new Ventana1();
            v1.lista_empresas.clear();
            
            v1.lista_empresas.add(new Empresa());
            v1.lista_empresas.add(new Empresa());
            
            
        } catch (IOException | ClassNotFoundException | SAXException ex) {
            Logger.getLogger(Ventana1Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // when eliminamos la primera empresa de la lista
        v1.lista_empresas.remove(0);
        
        // then el tamaño de la lista tiene que ser 1 porque teníamos 2 y hemos eliminado 1
        assertEquals(v1.lista_empresas.size(),1);
        
    }
}
