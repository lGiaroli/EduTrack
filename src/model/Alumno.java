package model;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int nivel;      // Nuevo campo nivel
    private char seccion;    // Nuevo campo seccion

    // Constructor
    public Alumno(int id, String nombre, String apellido, int nivel, char seccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
        this.seccion = seccion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public char getSeccion() { return seccion; }
    public void setSeccion(char seccion) { this.seccion = seccion; }

    @Override
    public String toString() {
        return "Alumno: " + nombre + " " + apellido;
    }
}
