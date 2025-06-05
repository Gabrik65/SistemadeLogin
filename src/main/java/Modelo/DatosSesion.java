package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Maneja las tareas personales de un usuario autenticado.
 */
public class DatosSesion {
    private final String archivo;
    protected final ArrayList<Tarea> tareas = new ArrayList<>();

    /**
     * Constructor que carga las tareas desde el archivo del usuario.
     *
     * @param usuario nombre del usuario
     */
    public DatosSesion(String usuario) {
        this.archivo = usuario + "_todo.txt";
        crearArchivoSiNoExiste();
        cargarTareas();
    }

    /**
     * Crea el archivo si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(archivo);
        try {
            if (file.createNewFile()) {
                System.out.println("Archivo de tareas creado: " + archivo);
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    /**
     * Carga las tareas desde el archivo a la lista.
     */
    private void cargarTareas() {
        File file = new File(archivo);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    tareas.add(new Tarea(linea));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo de tareas: " + e.getMessage());
        }
    }

    /**
     * Agrega una nueva tarea y la guarda en el archivo.
     *
     * @param descripcion texto de la tarea
     */
    public void agregarTarea(String descripcion) {
        Tarea nuevaTarea = new Tarea(descripcion);
        tareas.add(nuevaTarea);
        guardarTareaEnArchivo(nuevaTarea);
    }

    /**
     * Escribe una tarea individual en el archivo (modo append).
     */
    private void guardarTareaEnArchivo(Tarea tarea) {
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(tarea.getDescripcion() + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al guardar tarea: " + e.getMessage());
        }
    }


    /**
     * Elimina una tarea registrada para el usuario.
     */
    public void eliminarTarea(int num) {
        tareas.remove(num);
    }


    /**
     * Devuelve la lista de tareas.
     *
     * @return lista de tareas
     */
    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    /**
     * Muestra todas las tareas por consola.
     */
    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("(Sin tareas registradas)");
        } else {
            System.out.println("Tareas del usuario:");
            int i = 1;
            for (Tarea tarea : tareas) {
                System.out.println(i++ + ". " + tarea.getDescripcion());
            }
        }
    }
}
