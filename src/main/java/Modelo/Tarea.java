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
    public Tarea(String descripcion, boolean completada) {
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

    public void setStatus(boolean completada) {
        this.completada = completada;
    }

    public String estaCompletada(boolean completa) {
        if (completa){
            return "Completada";
        }else {
            return "Incompleta";
        }
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
