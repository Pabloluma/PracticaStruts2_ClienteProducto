package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;

public class EditarAction extends ActionSupport {
	private Cliente cliente; 
	private Pedido pedido;
	private int idCliente;
	Connection conn = null;

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
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	private Cliente getClientePorId(int idCliente) {
	    Cliente cli = null;
	    String query = "SELECT * FROM cliente WHERE idCliente = ?";
	    try (Connection conn = getConexion();
	         PreparedStatement pst = conn.prepareStatement(query)) {
	        pst.setInt(1, idCliente);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            cli = new Cliente();
	            cli.setIdCliente(rs.getInt("idCliente"));
	            cli.setTelefono(rs.getString("telefono"));
	            cli.setNombre(rs.getString("nombre"));
	            cli.setApellidos(rs.getString("apellido"));
	            cli.setGenero(rs.getInt("genero"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cli;
	}
	
	private Pedido getPedidoPorClienteId(int idCliente) {
        Pedido ped = null;
        String query = "SELECT * FROM pedido WHERE idCliente = ?";
        try (Connection conn = getConexion();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, idCliente);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ped = new Pedido();
                ped.setIdPedido(rs.getInt("idpedido"));
                ped.setHardware(rs.getString("tipoproductohardware"));
                ped.setSoftware(rs.getString("tipoproductosoftware"));
                ped.setProducto(rs.getInt("producto"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setPrecio(rs.getInt("precio"));
                ped.setComprobante(rs.getInt("comprobante"));
                ped.setObservaciones(rs.getString("observaciones"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ped;
    }

	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	
	public String editarCliente() {
		 	System.out.println("ID recibido: " + idCliente);
		    cliente = getClientePorId(idCliente);
		    pedido = getPedidoPorClienteId(idCliente);
		    if (cliente != null) {
		        System.out.println("Cliente encontrado: " + cliente.getNombre());
		        System.out.println("Pedido encontrado: " + pedido.getProducto());
		        return SUCCESS;
		    } else {
		        System.out.println("No se encontró el cliente.");
		        return ERROR;
		    }
	}
	public Pedido getPedido() {
        return pedido;
    }



}
