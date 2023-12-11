package accesodatos;

import java.io.Serializable;

public class Departamento implements Serializable {
    
    private static int id_siguiente = 1;
    private int fk_id_empresa;
    private int id_departamento;
    private String nombre;
    private String descripcion;
    private String horario;
    private String numero_oficina;
    private String email;

    public Departamento() {
        this.id_departamento = id_siguiente;
        id_siguiente++;
    }
    
    public Departamento(boolean xml, int fk_id_empresa, int id_departamento, String nombre, String descripcion, String horario, String numero_oficina, String email) {
        this.fk_id_empresa = fk_id_empresa;
        this.id_departamento = id_departamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.numero_oficina = numero_oficina;
        this.email = email;
        id_siguiente = id_departamento+1;
    }
    
    public Departamento(int fk_id_empresa, String nombre, String descripcion, String horario, String numero_oficina, String email) {
        this.fk_id_empresa = fk_id_empresa;
        this.id_departamento = id_siguiente;
        id_siguiente++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.numero_oficina = numero_oficina;
        this.email = email;
    }

    public int getFk_id_empresa() {
        return fk_id_empresa;
    }

    public void setFk_id_empresa(int fk_id_empresa) {
        this.fk_id_empresa = fk_id_empresa;
    }
    
    public static int getId_siguiente() {
        return id_siguiente;
    }

    public static void setId_siguiente(int id_siguiente) {
        Departamento.id_siguiente = id_siguiente;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNumero_oficina() {
        return numero_oficina;
    }

    public void setNumero_oficina(String numero_oficina) {
        this.numero_oficina = numero_oficina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

