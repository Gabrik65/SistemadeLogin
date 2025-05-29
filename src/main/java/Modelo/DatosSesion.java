package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Maneja las tareas personales de un usuario autenticado.
 */
public class DatosSesion {
    private final String nombreArchivo;

    public DatosSesion(String usuario) {
        this.nombreArchivo = usuario + "_todo.txt";
        crearArchivoSiNoExiste();
    }

    /**
     * Crea el archivo de tareas si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File archivo = new File(nombreArchivo);
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo de tareas creado: " + nombreArchivo);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo de tareas: " + e.getMessage());
        }
    }

    /**
     * Escribe una nueva tarea al final del archivo.
     *
     * @param tarea Texto de la tarea.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean escribirTarea(String tarea) {
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(tarea + System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir tarea: " + e.getMessage());
            return false;
        }
    }


    public void mostrarTareas() {
        File archivo = new File(nombreArchivo);

        try (Scanner scanner = new Scanner(archivo)) {
            System.out.println("Tareas del usuario:");
            int numero = 1;
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(numero + ". " + linea);
                numero++;
            }
            if (numero == 1) {
                System.out.println("(Sin tareas registradas)");
            }
        } catch (IOException e) {
            System.err.println("Error al leer tareas: " + e.getMessage());
        }
    }
}