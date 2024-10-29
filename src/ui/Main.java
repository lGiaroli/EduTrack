package ui;

import model.Alumno;
import model.Materia;
import model.Calificacion;
import service.AlumnoService;
import service.MateriaService;
import service.CalificacionService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final AlumnoService alumnoService = new AlumnoService();
    private static final MateriaService materiaService = new MateriaService();
    private static final CalificacionService calificacionService = new CalificacionService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Menú EduTrack ===");
            System.out.println("1. Agregar Alumno");
            System.out.println("2. Agregar Materia");
            System.out.println("3. Agregar Calificación");
            System.out.println("4. Listar Alumnos");
            System.out.println("5. Listar Materias");
            System.out.println("6. Listar Calificaciones");
            System.out.println("7. Buscar Alumno por ID");
            System.out.println("8. Buscar Materia por ID");
            System.out.println("9. Actualizar Alumno");
            System.out.println("10. Actualizar Materia");
            System.out.println("11. Eliminar Alumno");
            System.out.println("12. Eliminar Materia");
            System.out.println("13. Eliminar Calificación");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1 -> agregarAlumno();
                case 2 -> agregarMateria();
                case 3 -> agregarCalificacion();
                case 4 -> listarAlumnos();
                case 5 -> listarMaterias();
                case 6 -> listarCalificaciones();
                case 7 -> buscarAlumnoPorId();
                case 8 -> buscarMateriaPorId();
                case 9 -> actualizarAlumno();
                case 10 -> actualizarMateria();
                case 11 -> eliminarAlumno();
                case 12 -> eliminarMateria();
                case 13 -> eliminarCalificacion();
                case 0 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("Gracias por usar EduTrack.");
    }

    // Métodos para cada opción del menú
    private static void agregarAlumno() {
        System.out.print("Nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido del alumno: ");
        String apellido = scanner.nextLine();
        System.out.print("Nivel (1-6): ");
        int nivel = scanner.nextInt();
        System.out.print("Sección (A o B): ");
        char seccion = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();

        Alumno alumno = new Alumno(0, nombre, apellido, nivel, seccion);
        int id = alumnoService.agregarAlumno(alumno);
        System.out.println("Alumno agregado con ID: " + id);
    }

    private static void agregarMateria() {
        System.out.print("Nombre de la materia: ");
        String nombre = scanner.nextLine();

        Materia materia = new Materia(0, nombre);
        int id = materiaService.agregarMateria(materia);
        System.out.println("Materia agregada con ID: " + id);
    }

    private static void agregarCalificacion() {
        System.out.print("ID del alumno: ");
        int alumnoId = scanner.nextInt();
        System.out.print("ID de la materia: ");
        int materiaId = scanner.nextInt();
        System.out.print("Nota: ");
        double nota = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        // Obtener el alumno y la materia
        Alumno alumno = alumnoService.obtenerAlumnoPorId(alumnoId);
        Materia materia = materiaService.obtenerMateriaPorId(materiaId);

        if (alumno == null) {
            System.out.println("Error: Alumno no encontrado con ID: " + alumnoId);
            return;
        }
        if (materia == null) {
            System.out.println("Error: Materia no encontrada con ID: " + materiaId);
            return;
        }

        // Crear el objeto Calificacion con todos los datos necesarios
        Calificacion calificacion = new Calificacion(
                0,
                alumnoId,
                materiaId,
                nota,
                new Date(),
                materia.getNombre(),
                alumno.getNombre(),
                alumno.getApellido()
        );

        calificacionService.agregarCalificacion(calificacion);
        System.out.println("Calificación agregada correctamente.");
    }

    private static void listarAlumnos() {
        List<Alumno> alumnos = alumnoService.listarAlumnos();
        System.out.println("\n=== Lista de Alumnos ===");
        for (Alumno alumno : alumnos) {
            System.out.println("ID: " + alumno.getId() + ", Nombre: " + alumno.getNombre() + ", Apellido: " + alumno.getApellido() + "Nivel del alumno: " + alumno.getNivel() + ", Sección: " + alumno.getSeccion());
        }
    }

    private static void listarMaterias() {
        List<Materia> materias = materiaService.listarMaterias();
        System.out.println("\n=== Lista de Materias ===");
        for (Materia materia : materias) {
            System.out.println("ID: " + materia.getId() + ", Nombre: " + materia.getNombre());
        }
    }

    private static void listarCalificaciones() {
        System.out.print("Ingrese el nivel (1-6): ");
        int nivel = scanner.nextInt();
        System.out.print("Ingrese la sección (A o B): ");
        char seccion = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine(); // Limpiar el buffer

        // Obtener la lista de calificaciones de tipo Calificacion
        List<Calificacion> calificaciones = calificacionService.listarCalificacionesPorNivelYSeccion(nivel, seccion);
        System.out.println("\n=== Lista de Calificaciones para Nivel " + nivel + " Sección " + seccion + " ===");

        // Iterar sobre las calificaciones y mostrarlas como texto
        for (Calificacion calificacion : calificaciones) {
            System.out.println("Alumno ID: " + calificacion.getAlumnoId() +
                    ", Materia: " + calificacion.getMateriaNombre() +
                    ", Nota: " + calificacion.getNota() +
                    ", Fecha: " + calificacion.getFecha());
        }

        if (calificaciones.isEmpty()) {
            System.out.println("No se encontraron calificaciones para el nivel y sección seleccionados.");
        }
    }


    private static void buscarAlumnoPorId() {
        System.out.print("ID del alumno: ");
        int id = scanner.nextInt();
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
        if (alumno != null) {
            System.out.println("ID: " + alumno.getId() + ", Nombre: " + alumno.getNombre() + ", Apellido: " + alumno.getApellido() + ", Nivel del alumno: " + alumno.getNivel() + ", Sección: " + alumno.getSeccion());
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static void buscarMateriaPorId() {
        System.out.print("ID de la materia: ");
        int id = scanner.nextInt();
        Materia materia = materiaService.obtenerMateriaPorId(id);
        if (materia != null) {
            System.out.println("ID: " + materia.getId() + ", Nombre: " + materia.getNombre());
        } else {
            System.out.println("Materia no encontrada.");
        }
    }

    private static void actualizarAlumno() {
        System.out.print("ID del alumno a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Nuevo nivel: ");
        int nivel = scanner.nextInt();
        System.out.print("Nueva seccion: ");
        char seccion = scanner.next().toUpperCase().charAt(0);

        Alumno alumno = new Alumno(id, nombre, apellido, nivel, seccion);
        alumnoService.actualizarAlumno(alumno);
    }

    private static void actualizarMateria() {
        System.out.print("ID de la materia a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Nuevo nombre de la materia: ");
        String nombre = scanner.nextLine();

        Materia materia = new Materia(id, nombre);
        materiaService.actualizarMateria(materia);
    }

    private static void eliminarAlumno() {
        System.out.print("ID del alumno a eliminar: ");
        int id = scanner.nextInt();
        alumnoService.eliminarAlumno(id);
    }

    private static void eliminarMateria() {
        System.out.print("ID de la materia a eliminar: ");
        int id = scanner.nextInt();
        materiaService.eliminarMateria(id);
    }

    private static void eliminarCalificacion() {
        System.out.print("ID de la calificación a eliminar: ");
        int id = scanner.nextInt();
        calificacionService.eliminarCalificacion(id);
    }
}
