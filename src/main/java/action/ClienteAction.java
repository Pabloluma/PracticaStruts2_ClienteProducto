package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;

public class ClienteAction extends ActionSupport {
    private ArrayList<Cliente> clientes; // Asumiendo que tienes una clase Cliente

    // Método para obtener la conexión a la base de datos
    private Connection getConexion() {
        String url = "jdbc:mysql://localhost:3306/tiendaproductos?serverTimezone=UTC";
        String usuario = "root";
        String contrasenya = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, contrasenya);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para cargar los clientes
    public String cargarClientes() {
        clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Connection conn = getConexion();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellido"));
                cliente.setGenero(rs.getInt("genero"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

  
}
