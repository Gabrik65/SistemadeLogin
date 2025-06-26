package Modelo;

/**
 * Representa una tarea individual del usuario.
 */
public class Tarea {
    private String descripcion;
    private Prioridad priority;
    private boolean completada;

    /**
     * Constructor que inicializa la descripción de la tarea.
     *
     * @param descripcion contenido de la tarea
     */
    public Tarea(String descripcion,Prioridad priority, boolean completada) {
        this.descripcion = descripcion;
        this.priority = priority;
        this.completada = completada;
    }

    public enum Prioridad {
        ALTO,
        MEDIO,
        BAJO;

        public static Prioridad fromString(String input) {
            for (Prioridad p : values()) {
                if (p.name().equalsIgnoreCase(input)) {
                    return p;
                }
            }
            return null;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPriority() {
        return priority.name().toLowerCase();
    }

    public boolean getStatus() {
        return completada;
    }

    public void setStatus(boolean completada) {
        this.completada = completada;
    }

    public String estaCompletada() {
        return completada ? "Completada" : "Incompleta";
    }

    public boolean isComplete() {
        return completada;
    }

    @Override
    public String toString() {
        return String.format("Tarea: %s | Prioridad: %s | Estado: %s",
                descripcion,
                priority.name().toLowerCase(),
                estaCompletada());
    }

}
