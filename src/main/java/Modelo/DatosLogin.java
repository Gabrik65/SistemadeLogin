package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
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
        crearArchivoSiNoExiste();
        cargarUsuarios();
    }

    /**
     * Devuelve la lista de credenciales cargadas.
     */
    public ArrayList<String> getCredenciales() {
        return credenciales;
    }

    /**
     * Crea el archivo login.txt si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(archivo);
        try {
            if (file.createNewFile()) {
                System.out.println("Archivo creado: " + archivo);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    /**
     * Carga los pares usuario;clave desde el archivo a la lista.
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
