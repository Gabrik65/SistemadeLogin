package Modelo;

/**
 * Subclase de Usuario que representa un perfil con información adicional.
 */
public class Perfil extends Usuario {
    // Atributos adicionales
    private String correo;
    private String fechaCreacion;

    /**
     * Constructor que inicializa los atributos heredados y los nuevos.
     *
     * @param nombre nombre de usuario
     * @param clave  clave del usuario
     * @param correo correo electrónico del usuario
     * @param fechaCreacion fecha de creación del perfil (por ejemplo, en formato YYYY-MM-DD)
     */
    public Perfil(String nombre, String clave, String correo, String fechaCreacion) {
        super(nombre, clave);
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "nombre='" + getNombre() + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
