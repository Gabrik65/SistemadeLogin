package datacontroller;

/**
 * Clase encargada de verificar las credenciales del usuario.
 */
public class Login {

    /**
     * Verifica si existe una línea con el formato exacto "usuario; clave".
     *
     * @param usuario nombre de usuario ingresado
     * @param clave contraseña ingresada
     * @param datos objeto DatosLogin que contiene la lista de credenciales
     * @return true si las credenciales son válidas, false en caso contrario
     */
    public boolean autenticar(String usuario, String clave, DatosLogin datos) {
        // TODO: Crear String intento = usuario + ";" + clave
        // TODO: Recorrer datos.credenciales y comparar con intento
        String intento = usuario + ";" + clave;
        for (String credencial : datos.credenciales) {
            if (credencial.equals(intento)) {
                return true;
            }
        }
        return false;
    }
}