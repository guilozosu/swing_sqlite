package accesodatos;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable {
    
    private static int id_siguiente = 1;
    private int id_empresa;
    private String nif_empresa;
    private String nombre_empresa;
    private String direccion_empresa;
    private String telefono_empresa;
    private String email_empresa;

    public Empresa() {
        this.id_empresa = id_siguiente;
        id_siguiente++;
    }
    
    public Empresa(boolean xml, int id_empresa_xml, String nif_empresa, String nombre_empresa, String direccion_empresa, String telefono_empresa, String email_empresa) {
        this.id_empresa = id_empresa_xml;
        this.nif_empresa = nif_empresa;
        this.nombre_empresa = nombre_empresa;
        this.direccion_empresa = direccion_empresa;
        this.telefono_empresa = telefono_empresa;
        this.email_empresa = email_empresa;
        id_siguiente = id_empresa+1;
    }
    
    public Empresa(int id_empresa, String nif_empresa, String nombre_empresa, String direccion_empresa, String telefono_empresa, String email_empresa) {
        this.id_empresa = id_siguiente;
        id_siguiente++;
        this.nif_empresa = nif_empresa;
        this.nombre_empresa = nombre_empresa;
        this.direccion_empresa = direccion_empresa;
        this.telefono_empresa = telefono_empresa;
        this.email_empresa = email_empresa;
    }

    public static int getId_siguiente() {
        return id_siguiente;
    }

    public static void setId_siguiente(int id_siguiente) {
        Empresa.id_siguiente = id_siguiente;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNif_empresa() {
        return nif_empresa;
    }

    public void setNif_empresa(String nif_empresa) {
        this.nif_empresa = nif_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getDireccion_empresa() {
        return direccion_empresa;
    }

    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }

    public String getTelefono_empresa() {
        return telefono_empresa;
    }

    public void setTelefono_empresa(String telefono_empresa) {
        this.telefono_empresa = telefono_empresa;
    }

    public String getEmail_empresa() {
        return email_empresa;
    }

    public void setEmail_empresa(String email_empresa) {
        this.email_empresa = email_empresa;
    }

}