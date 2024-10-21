package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import action.Cliente;
import action.Pedido;

public class ActualizarAction extends ActionSupport {
	private Cliente cliente;
	private Pedido pedido;
	Connection conn;

	// Getters y setters para cliente y pedido
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

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

	public boolean actualizarCliente() {
		PreparedStatement ps = null;
		conn = getConexion();

		try {
			if (cliente.getTelefono().trim().length() != 0 && cliente.getNombre().trim().length() != 0 && cliente.getApellidos().trim().length() != 0 && cliente.getGenero() != -1) {
				ps = conn.prepareStatement(
						"UPDATE CLIENTE SET TELEFONO = ?, NOMBRE = ?, APELLIDO = ?, GENERO = ? WHERE idCliente = ?");
				ps.setString(1, cliente.getTelefono());
				ps.setString(2, cliente.getNombre());
				ps.setString(3, cliente.getApellidos());
				ps.setInt(4, cliente.getGenero());
				ps.setInt(5, cliente.getIdCliente());
				ps.executeUpdate();
//				Devuelve el número de filas que se han actualizado
				int rowsUpdated = ps.executeUpdate();
//				Devuelve true o false, en caso de que se haya actualizado alguna fila será mayor que 0 y devolverá true sino devolverá false
				return rowsUpdated > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public String actualizarTodo() {
		if (actualizarCliente() != false) {
			return SUCCESS;
		}else {
			return ERROR;	
		}
	}

}
