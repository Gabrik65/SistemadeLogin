package Modelo;

/**
 * Clase que representa a un usuario del sistema.
 */
public class Usuario {
    private final String nombre;
    private final String clave;

    /**
     * Constructor que inicializa los atributos del usuario.
     *
     * @param nombre nombre del usuario
     * @param clave clave del usuario
     */
    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }
}
