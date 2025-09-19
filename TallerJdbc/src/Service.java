import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Service {

    public void obtenerAlumnos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Estudiantes";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        int cont = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("ID: %d | %s %s | %s | Edad: %d | Estado civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
            cont++;
        }
        System.out.println("Total de estudiantes encontrados: " + cont);
    }

    public void insertarAlumno(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.print("Digite apellido del alumno: ");
        String apellido = sc.nextLine();
        System.out.print("Digite el correo electrónico: ");
        String correo = sc.nextLine();
        System.out.print("Digite la edad: ");
        int edad = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Digite el estado civil: ");
        String estadoCivil = sc.nextLine();

        String sql = "INSERT INTO Estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?,?,?,?,?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estadoCivil);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Registro insertado de forma correcta");
        } else {
            System.out.println("Fallo en la inserción");
        }
    }

    public void actualizarAlumno(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el ID del alumno a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite el nuevo estado civil: ");
        String estadoCivil = sc.nextLine();

        String sql = "UPDATE Estudiantes SET estado_civil=? WHERE id=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, estadoCivil);
        stm.setInt(2, id);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Registro actualizado de forma correcta");
        } else {
            System.out.println("Fallo en la actualización");
        }
    }

    public void eliminarAlumno(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el ID del alumno a eliminar: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Estudiantes WHERE id=?";
        var stm = conn.prepareStatement(sql);
        stm.setInt(1, id);

        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("Registro eliminado de forma correcta");
        } else {
            System.out.println("Fallo en la eliminación");
        }
    }

    public void obtenerAlumnoPorCorreo(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el correo a buscar: ");
        String correo = sc.nextLine();

        String sql = "SELECT * FROM Estudiantes WHERE correo=?";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, correo);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("ID: %d | %s %s | %s | Edad: %d | Estado civil: %s%n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        } else {
            System.out.println("No se encontró estudiante con ese correo.");
        }
    }
}
