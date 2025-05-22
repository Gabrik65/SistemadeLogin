package org.ejemplo;

import java.util.ArrayList;

/**
 * Clase responsable de cargar las credenciales desde un archivo.
 */
public class DatosLogin {
    public ArrayList<String> credenciales = new ArrayList<>();

    /**
     * Constructor que inicializa las credenciales desde el archivo.
     */
    public DatosLogin() {
        cargarUsuarios();
    }

    /**
     * Lee el archivo login.txt y agrega las líneas válidas a la lista de credenciales.
     */
    private void cargarUsuarios() {
        // TODO: Abrir archivo login.txt
        // TODO: Leer línea por línea y agregar solo las que contienen ";"
        // TODO: Ignorar líneas vacías o mal formateadas
    }
}