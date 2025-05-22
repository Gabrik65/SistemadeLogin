package org.ejemplo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatosLogin {
    public ArrayList<String> credenciales = new ArrayList<>();

    // Constructor para pruebas que no cargan archivo
    public DatosLogin() {
        // lista vacía por defecto
    }

    public DatosLogin(String nombreArchivo) throws IOException {
        cargarUsuarios(nombreArchivo);
    }

    private void cargarUsuarios(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")) {
                    credenciales.add(linea);
                }
            }
        }
    }
}
