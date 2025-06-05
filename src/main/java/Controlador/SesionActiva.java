package Controlador;

import Modelo.DatosSesion;
import Modelo.GestorUsuarios;

import java.util.Scanner;

/**
 * Representa la sesión de un usuario logueado.
 */
public class SesionActiva {
    private final String usuario;
    private final Scanner scanner = new Scanner(System.in);
    private final DatosSesion datosSesion;

    public SesionActiva(String usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario);
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
        System.out.println("\n--- Menú de Usuario (" + usuario + ") ---");
        System.out.println("1. Ver tareas");
        System.out.println("2. Escribir nueva tarea");

        if (esAdmin()) {
            System.out.println("3. Registrar nuevo usuario");
            System.out.println("4. Salir");
        } else {
            System.out.println("3. Salir");
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
                if (esAdmin()) {
                    registrarUsuario();
                } else {
                    return true; // salir
                }
                break;
            case "4":
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
        System.out.println("Programa cerrando.");
        System.exit(1);
    }

    private boolean esAdmin() {
        return "admin".equalsIgnoreCase(usuario);
    }

    /**
     * Solicita al usuario una tarea y la guarda.
     */
    private void escribirTarea() {
        System.out.print("Ingrese la nueva tarea: ");
        String tarea = scanner.nextLine();
        boolean exito = datosSesion.escribirTarea(tarea);
        if (exito) {
            System.out.println("Tarea guardada con éxito.");
        } else {
            System.out.println("No se pudo guardar la tarea.");
        }
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
}
