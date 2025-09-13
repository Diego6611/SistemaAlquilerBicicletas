import java.util.Date;

public class Historial {
    private Usuario usuario;
    private Bicicleta bicicleta;
    private Date fechaAlquiler;
    private Date fechaDevolucion;

    public Historial(Usuario usuario, Bicicleta bicicleta, Date fechaAlquiler) {
        this.usuario = usuario;
        this.bicicleta = bicicleta;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String mostrarResumen() {
        return "Usuario: " + usuario.getNombre() +
               ", Bicicleta ID: " + bicicleta.getId() +
               ", Alquiler: " + fechaAlquiler +
               ", Devolución: " + (fechaDevolucion != null ? fechaDevolucion : "Pendiente");
    }
}
