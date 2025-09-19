import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/escuela?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static String userName = "root";
    static String password = "Pass_123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                int opcion = 0;
                Scanner sc = new Scanner(System.in);
                Service service = new Service();

                while (opcion != 6) {
                    System.out.println("\n===== MENÚ PRINCIPAL =====");
                    System.out.println("1. Insertar estudiante");
                    System.out.println("2. Actualizar estudiante");
                    System.out.println("3. Eliminar estudiante");
                    System.out.println("4. Consultar todos los estudiantes");
                    System.out.println("5. Consultar estudiante por email");
                    System.out.println("6. Salir del programa");
                    System.out.print("Seleccione opción: ");
                    opcion = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    switch (opcion) {
                        case 1 -> service.insertarAlumno(conn);
                        case 2 -> service.actualizarAlumno(conn);
                        case 3 -> service.eliminarAlumno(conn);
                        case 4 -> service.obtenerAlumnos(conn);
                        case 5 -> {
                            System.out.print("Digite el correo a buscar: ");
                            
                            service.obtenerAlumnoPorCorreo(conn);
                        }
                        case 6 -> System.out.println("👋 Saliendo del sistema...");
                        default -> System.out.println("⚠️ Opción inválida, intente de nuevo.");
                    }
                }
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
