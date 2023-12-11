package accesodatos;

import java.io.Serializable;

public class Empleado implements Serializable {
    
    private static int id_siguiente = 1;
    private int fk_id_departamento;
    private int id_empleado;
    private String nombre;
    private String apellidos;
    private String fecha_nacimiento;
    private String email;
    private String telefono;

    public Empleado() {
        this.id_empleado = id_siguiente;
        id_siguiente++;
    }

    public Empleado(boolean xml, int fk_id_departamento, int id_empleado, String nombre, String apellidos, String fecha_nacimiento, String email, String telefono) {
        this.fk_id_departamento = fk_id_departamento;
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.telefono = telefono;
        id_siguiente = id_empleado+1;
    }
    
    public Empleado(int fk_id_departamento, String nombre, String apellidos, String fecha_nacimiento, String email, String telefono) {
        this.fk_id_departamento = fk_id_departamento;
        this.id_empleado = id_siguiente;
        id_siguiente++;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.telefono = telefono;
    }

    public int getFk_id_departamento() {
        return fk_id_departamento;
    }

    public void setFk_id_departamento(int fk_id_departamento) {
        this.fk_id_departamento = fk_id_departamento;
    }
    
    public static int getId_siguiente() {
        return id_siguiente;
    }

    public static void setId_siguiente(int id_siguiente) {
        Empleado.id_siguiente = id_siguiente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
