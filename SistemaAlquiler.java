import java.util.ArrayList;
import java.util.Date;

public class SistemaAlquiler {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Bicicleta> bicicletas;
    private ArrayList<Historial> historiales;
    private int contadorBicicletas = 1;

    public SistemaAlquiler() {
        usuarios = new ArrayList<>();
        bicicletas = new ArrayList<>();
        historiales = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarBicicleta(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    public void agregarBicicleta(boolean disponible, boolean electrica) {
        Bicicleta bicicleta = new Bicicleta(contadorBicicletas, disponible, electrica);
        bicicletas.add(bicicleta);
        contadorBicicletas++;
    }

    public void registrarAlquiler(Usuario usuario, Bicicleta bicicleta, Date fechaAlquiler) {
        if (bicicleta.isElectrica() && bicicleta.getCarga() == 0) {
            System.out.println("La bicicleta eléctrica no tiene carga. No se puede alquilar.");
            return;
        }

        Historial historial = new Historial(usuario, bicicleta, fechaAlquiler);
        historiales.add(historial);

        if (bicicleta.isElectrica()) {
            bicicleta.setCarga(bicicleta.getCarga() - 25);
        }

        bicicleta.setDisponible(false);
    }

    public void registrarDevolucion(Bicicleta bicicleta, Date fechaDevolucion) {
        for (Historial historial : historiales) {
            if (historial.getBicicleta().getId() == bicicleta.getId() && historial.getFechaDevolucion() == null) {
                historial.setFechaDevolucion(fechaDevolucion);
                bicicleta.setDisponible(true);
                break;
            }
        }
    }

    public void mostrarHistoriales() {
        for (Historial historial : historiales) {
            System.out.println(historial.mostrarResumen());
            System.out.println("---------------------------");
        }
    }

    public void mostrarBicicletasAlquiladas() {
        boolean hayAlquileres = false;
        for (Historial h : historiales) {
            if (h.getFechaDevolucion() == null) {
                Usuario u = h.getUsuario();
                Bicicleta b = h.getBicicleta();
                System.out.println("Bicicleta ID: " + b.getId() +
                                   " está alquilada por " + u.getNombre() +
                                   " (Documento: " + u.getDocumento() + ")");
                hayAlquileres = true;
            }
        }
        if (!hayAlquileres) {
            System.out.println("No hay bicicletas alquiladas actualmente.");
        }
    }

    public void mostrarBicicletasPorUsuario(int documento) {
        boolean encontrado = false;
        for (Historial h : historiales) {
            if (h.getUsuario().getDocumento() == documento) {
                Bicicleta b = h.getBicicleta();
                System.out.println("Bicicleta ID: " + b.getId() +
                                   ", Tipo: " + (b.isElectrica() ? "Eléctrica" : "Convencional") +
                                   ", Fecha alquiler: " + h.getFechaAlquiler() +
                                   ", Fecha devolución: " + (h.getFechaDevolucion() != null ? h.getFechaDevolucion() : "Pendiente"));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Ese usuario no ha alquilado ninguna bicicleta.");
        }
    }

    public void mostrarUsuariosConAlquileres() {
        ArrayList<Integer> documentosMostrados = new ArrayList<>();
        for (Historial h : historiales) {
            Usuario u = h.getUsuario();
            if (!documentosMostrados.contains(u.getDocumento())) {
                System.out.println("Nombre: " + u.getNombre() +
                                   ", Documento: " + u.getDocumento() +
                                   ", Tipo: " + (u.isEstudiante() ? "Estudiante" : "Empleado"));
                documentosMostrados.add(u.getDocumento());
            }
        }
        if (documentosMostrados.isEmpty()) {
            System.out.println("Ningún usuario ha alquilado bicicletas.");
        }
    }

    public void recargarBicicleta(int id) {
        Bicicleta b = buscarBicicletaPorId(id);
        if (b != null && b.isElectrica()) {
            b.setCarga(100);
            System.out.println("Bicicleta ID " + id + " recargada al 100%.");
        } else if (b != null) {
            System.out.println("La bicicleta no es eléctrica.");
        } else {
            System.out.println("Bicicleta no encontrada.");
        }
    }

    public Usuario buscarUsuarioPorDocumento(int documento) {
        for (Usuario u : usuarios) {
            if (u.getDocumento() == documento) {
                return u;
            }
        }
        return null;
    }

    public Bicicleta buscarBicicletaPorId(int id) {
        for (Bicicleta b : bicicletas) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public ArrayList<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Bicicleta> getBicicletasDisponibles() {
        ArrayList<Bicicleta> disponibles = new ArrayList<>();
        for (Bicicleta b : bicicletas) {
            if (b.isDisponible()) {
                disponibles.add(b);
            }
        }
        return disponibles;
    }
}
