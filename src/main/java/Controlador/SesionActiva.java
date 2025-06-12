package Controlador;

import Modelo.DatosSesion;
import Modelo.GestorUsuarios;
import Modelo.Usuario;

import java.util.Objects;
import java.util.Scanner;

/**
 * Representa la sesión de un usuario logueado.
 */
public class SesionActiva {
    private final Usuario usuario;
    private final Scanner scanner = new Scanner(System.in);
    private final DatosSesion datosSesion;

    public SesionActiva(Usuario usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario.getNombre());
    }

    public void menuSesion() {
        boolean salir = false;

        while (!salir) {
            imprimirOpciones();
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            salir = procesarOpcion(opcion);
        }
        cerrarPrograma();
    }

    private void imprimirOpciones() {
        System.out.println("\n--- Menú de Usuario (" + usuario.getNombre() + ") ---");
        System.out.println("1. Ver tareas");
        System.out.println("2. Escribir nueva tarea");
        System.out.println("3. Eliminar una tarea");

        if (esAdmin()) {
            System.out.println("4. Registrar nuevo usuario");
            System.out.println("5. Salir");
        } else {
            System.out.println("4. Salir");
        }
    }

    private boolean procesarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                datosSesion.mostrarTareas();
                break;
            case "2":
                escribirTarea();
                break;
            case "3":
                escribirTarea();
                break;
            case "4":
                if (esAdmin()) {
                    registrarUsuario();
                } else {
                    return true; // salir
                }
                break;
            case "5":
                if (esAdmin()) {
                    return true; // salir
                }
                // else fall through
            default:
                System.out.println("Opción no válida.");
                break;
        }
        return false;
    }

    private void cerrarPrograma() {
        System.out.println("Sesión cerrada.");
    }

    private boolean esAdmin() {
        return "admin".equals(usuario.getNombre());
    }

    /**
     * Solicita al usuario una tarea y la guarda.
     */
    private void escribirTarea() {
        System.out.print("Ingrese la nueva tarea: ");
        String tarea = scanner.nextLine();
        System.out.print("Tarea completada: (Y/N)");
        String completed = scanner.nextLine();
        do {
            System.out.print("Opcion invalida");
            System.out.print("¿Tarea completada? (Y/N): ");
            completed = scanner.nextLine().trim();
        } while (!completed.equalsIgnoreCase("Y") && !completed.equalsIgnoreCase("N"));
        boolean completada = Objects.equals(completed, "Y");
        datosSesion.agregarTarea(tarea, completada);
    }

    /**
     * Da listado de tareas y deja eliminar.
     */
    private void eliminarTarea() {
        datosSesion.mostrarTareas();
        System.out.print("Ingrese num de la tarea: ");
        datosSesion.eliminarTarea(stringToint(scanner.nextLine()));
        // aun no checkea si es que el num es mas grande que la lista
    }
    /**
     * Solo para admin: registra un nuevo usuario.
     */
    private void registrarUsuario() {
        System.out.print("Ingrese nuevo nombre de usuario: ");
        String nuevoUsuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String clave = scanner.nextLine();

        GestorUsuarios gestor = new GestorUsuarios();
        boolean registrado = gestor.registrar(nuevoUsuario, clave);

        if (registrado) {
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("No se pudo registrar el usuario (puede que ya exista).");
        }
    }



    private static int stringToint(String number){
        int num = 0;

        if(isInt(number)){
            num = Integer.parseInt(number);
        }else {
            System.out.println("Argumento Invalido");
        }

        return num;
    }

    private static boolean isInt(String number){
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
