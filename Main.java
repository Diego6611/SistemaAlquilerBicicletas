import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaAlquiler sistema = new SistemaAlquiler();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Agregar bicicleta");
            System.out.println("3. Registrar alquiler");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Mostrar historiales");
            System.out.println("6. Listar bicicletas");
            System.out.println("7. Mostrar usuarios registrados");
            System.out.println("8. Mostrar bicicletas disponibles");
            System.out.println("9. Mostrar bicicletas alquiladas con su usuario");
            System.out.println("10. Mostrar bicicletas alquiladas por un usuario");
            System.out.println("11. Mostrar usuarios que han alquilado bicicletas");
            System.out.println("12. Recargar bicicleta eléctrica por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Documento: ");
                        int documento = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Tipo de usuario:");
                        System.out.println("1. Estudiante");
                        System.out.println("2. Empleado");
                        System.out.print("Opción: ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        boolean esEstudiante = (tipo == 1);
                        Usuario usuario = new Usuario(nombre, documento, esEstudiante);
                        sistema.agregarUsuario(usuario);
                        System.out.println("Usuario agregado.");
                        break;

                    case 2:
                        System.out.print("¿Disponible? (true/false): ");
                        if (!scanner.hasNextBoolean()) {
                            System.out.println("Entrada inválida.");
                            scanner.nextLine();
                            break;
                        }
                        boolean disponible = scanner.nextBoolean();

                        System.out.print("¿Es eléctrica? (true/false): ");
                        if (!scanner.hasNextBoolean()) {
                            System.out.println("Entrada inválida.");
                            scanner.nextLine();
                            break;
                        }
                        boolean electrica = scanner.nextBoolean();
                        scanner.nextLine();

                        sistema.agregarBicicleta(disponible, electrica);
                        System.out.println("Bicicleta agregada.");
                        break;

                    case 3:
                        System.out.print("Documento del usuario: ");
                        int docAlquiler = scanner.nextInt();
                        System.out.print("ID de bicicleta: ");
                        int idAlquiler = scanner.nextInt();
                        scanner.nextLine();
                        Usuario uAlquiler = sistema.buscarUsuarioPorDocumento(docAlquiler);
                        Bicicleta bAlquiler = sistema.buscarBicicletaPorId(idAlquiler);
                        if (uAlquiler != null && bAlquiler != null && bAlquiler.isDisponible()) {
                            if (bAlquiler.isElectrica() && bAlquiler.getCarga() == 0) {
                                System.out.println("La bicicleta eléctrica no tiene carga. No se puede alquilar.");
                            } else {
                                sistema.registrarAlquiler(uAlquiler, bAlquiler, new java.util.Date());
                                System.out.println("Alquiler registrado.");
                            }
                        } else {
                            System.out.println("Datos inválidos o bicicleta no disponible.");
                        }
                        break;

                    case 4:
                        System.out.print("ID de bicicleta a devolver: ");
                        int idDevolver = scanner.nextInt();
                        scanner.nextLine();
                        Bicicleta bDevolver = sistema.buscarBicicletaPorId(idDevolver);
                        if (bDevolver != null) {
                            sistema.registrarDevolucion(bDevolver, new java.util.Date());
                            System.out.println("Devolución registrada.");
                        } else {
                            System.out.println("Bicicleta no encontrada.");
                        }
                        break;

                    case 5:
                        sistema.mostrarHistoriales();
                        break;

                    case 6:
                        for (Bicicleta b : sistema.getBicicletas()) {
                            System.out.println("ID: " + b.getId() +
                                               ", Tipo: " + (b.isElectrica() ? "Eléctrica" : "Convencional") +
                                               ", Disponible: " + b.isDisponible() +
                                               (b.isElectrica() ? ", Carga: " + b.getCarga() + "%" : ""));
                        }
                        break;

                    case 7:
                        ArrayList<Usuario> listaUsuarios = sistema.getUsuarios();
                        if (listaUsuarios.isEmpty()) {
                            System.out.println("No hay usuarios registrados.");
                        } else {
                            for (Usuario u : listaUsuarios) {
                                System.out.println("Nombre: " + u.getNombre() +
                                                   ", Documento: " + u.getDocumento() +
                                                   ", Tipo: " + (u.isEstudiante() ? "Estudiante" : "Empleado"));
                            }
                        }
                        break;

                    case 8:
                        ArrayList<Bicicleta> disponibles = sistema.getBicicletasDisponibles();
                        if (disponibles.isEmpty()) {
                            System.out.println("No hay bicicletas disponibles.");
                        } else {
                            for (Bicicleta b : disponibles) {
                                System.out.println("ID: " + b.getId() +
                                                   ", Tipo: " + (b.isElectrica() ? "Eléctrica" : "Convencional") +
                                                   ", Carga: " + (b.isElectrica() ? b.getCarga() + "%" : "N/A"));
                            }
                        }
                        break;

                    case 9:
                        sistema.mostrarBicicletasAlquiladas();
                        break;
                    case 10:
                        System.out.print("Ingrese el documento del usuario: ");
                        int docConsulta = scanner.nextInt();
                        scanner.nextLine();
                        sistema.mostrarBicicletasPorUsuario(docConsulta);
                        break;

                    case 11:
                        sistema.mostrarUsuariosConAlquileres();
                        break;

                    case 12:
                        System.out.print("Ingrese el ID de la bicicleta eléctrica: ");
                        int idRecarga = scanner.nextInt();
                        scanner.nextLine();
                        sistema.recargarBicicleta(idRecarga);
                        break;

                    case 0:
                        continuar = false;
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
