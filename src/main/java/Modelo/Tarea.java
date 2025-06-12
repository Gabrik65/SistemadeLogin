package Modelo;

/**
 * Representa una tarea individual del usuario.
 */
public class Tarea {
    private String descripcion;
    private Boolean completada;

    /**
     * Constructor que inicializa la descripción de la tarea.
     *
     * @param descripcion contenido de la tarea
     */
    public Tarea(String descripcion, Boolean completada) {
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getStatus() {
        return completada;
    }

    public void setStatus(Boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
