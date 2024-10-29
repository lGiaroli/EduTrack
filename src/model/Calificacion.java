package model;

import java.util.Date;

public class Calificacion {

    private int id;
    private int alumnoId;
    private int materiaId;
    private double nota;
    private Date fecha;
    private String materiaNombre;
    private String nombreAlumno;
    private String apellidoAlumno;


    // Constructor completo con todos los campos
    public Calificacion(int id, int alumnoId, int materiaId, double nota, Date fecha,
                        String materiaNombre, String nombreAlumno, String apellidoAlumno) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.materiaId = materiaId;
        this.nota = nota;
        this.fecha = fecha;
        this.materiaNombre = materiaNombre;
        this.nombreAlumno = nombreAlumno;
        this.apellidoAlumno = apellidoAlumno;
    }

    // Getters y Setters
    public int getId() { return id; }
    public int getAlumnoId() { return alumnoId; }
    public int getMateriaId() { return materiaId; }
    public double getNota() { return nota; }
    public Date getFecha() { return fecha; }
    public String getMateriaNombre() { return materiaNombre; }
    public String getNombreAlumno() { return nombreAlumno; }
    public String getApellidoAlumno() { return apellidoAlumno; }
}
