package Controlador;

import Modelo.DatosLogin;
import Modelo.Usuario;

/**
 * Clase encargada de la lógica de autenticación.
 */
public class Login {

    /**
     * Verifica si las credenciales son válidas.
     *
     * @param usuario nombre ingresado
     * @param clave contraseña ingresada
     * @param datos instancia de DatosLogin
     * @return Usuario autenticado si es válido, null si no
     */
    public Boolean autenticar(String usuario, String clave, DatosLogin datos) {
        for (Usuario u : datos.getUsuarios()) {
            if (u.getNombre().equals(usuario) && u.getClave().equals(clave)) {
                return true;
            }
        }
        return false;
    }
}
