public class Usuario {
    private String nombre;
    private int documento;
    private boolean estudiante;

    public Usuario(String nombre, int documento, boolean estudiante) {
        this.nombre = nombre;
        this.documento = documento;
        this.estudiante = estudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public boolean isEstudiante() {
        return estudiante;
    }
}

