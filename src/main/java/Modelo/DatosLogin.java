package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestiona el archivo login.txt.
 */
public class DatosLogin {
    private final String archivo = "login.txt";
    private final ArrayList<String> credenciales = new ArrayList<>();

    /**
     * Constructor que inicializa el archivo y carga las credenciales.
     */
    public DatosLogin() {
        asegurarArchivo();
        cargarUsuarios();
    }

    /**
     * Devuelve la lista de credenciales cargadas.
     */
    public ArrayList<String> getCredenciales() {
        return credenciales;
    }

    public void asegurarArchivo() {
        File file = new File(archivo);
        if(file.exists()) {
            System.out.println("Registro de usuarios no encontrado");
            crearArchivoLogin();
        }
    }

    /**
     * Crea el archivo login.txt si no existe.
     */
    private void crearArchivoLogin() {
        File file = new File(archivo);
        try {
            if (file.createNewFile()) {
                escribirCredencialesPorDefecto(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el archivo: " + archivo, e);
        }
    }

    private void escribirCredencialesPorDefecto(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("admin;1234\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo: " + archivo, e);
        }
    }


        /**
     * Carga los pares usuario - clave desde el archivo a la lista.
     */
    private void cargarUsuarios() {
        File file = new File(archivo);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.contains(";")) {
                    credenciales.add(linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + archivo);
        }
    }
}
