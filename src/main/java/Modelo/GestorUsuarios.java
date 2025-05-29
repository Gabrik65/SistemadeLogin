package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Registra nuevos usuarios en login.txt.
 */
public class GestorUsuarios {
    private final String archivo = "login.txt";

    public GestorUsuarios() {
        crearArchivoSiNoExiste();
    }

    /**
     * Crea el archivo de login si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(archivo);
        try {
            if (file.createNewFile()) {
                System.out.println("Archivo de usuarios creado.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo de usuarios: " + e.getMessage());
        }
    }

    /**
     * Registra un nuevo usuario con su contraseña.
     *
     * @param usuario Nombre del usuario.
     * @param clave Contraseña del usuario.
     * @return true si se registró correctamente, false si ya existe o hubo error.
     */
    public boolean registrar(String usuario, String clave) {
        if (usuarioExiste(usuario)) {
            return false;
        }

        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(System.lineSeparator() + usuario + ";" + clave);
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si un usuario ya está registrado en el archivo.
     */
    private boolean usuarioExiste(String usuario) {
        File file = new File(archivo);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.startsWith(usuario + ";")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al verificar usuario existente: " + e.getMessage());
        }
        return false;
    }
}
