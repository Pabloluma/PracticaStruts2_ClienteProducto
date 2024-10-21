package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;

public class PedidoAction extends ActionSupport {
    String telefono;
    String nombre;
    String apellidos;
    int genero;
    boolean hard;
    boolean soft;
    int producto;
    String cantidad;
    String precio;
    int comprobante;
    String observaciones;
    String tipoProdH = "";
    String tipoProdS = "";
    Connection conn = null;
    ArrayList<String> nombres = new ArrayList<>();

    // Abre la conexión con la base de datos
    public Connection getConexion() {
        String url = "jdbc:mysql://localhost:3306/tiendaproductos?serverTimezone=UTC";
        String usuario = "root";
        String contrasenya = "root";

        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection nuevaConexion = DriverManager.getConnection(url, usuario, contrasenya);
                if (nuevaConexion != null) {
                    System.out.println("He conectado!!");
                    conn = nuevaConexion;
                    return conn;
                } else {
                    System.out.println("No hay conexión");
                    return null;
                }
            }
            return conn;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Se comprueba si existe o no el nombre para insertarlo o actualizarlo
    public ArrayList<String> comprobar() {
        Statement st = null;
        conn = getConexion();
        try {
            st = conn.createStatement();
            String query = "SELECT nombre FROM cliente";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                nombres.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nombres;
    }

    // Obtener el id del cliente
    public int numeroCliente() {
        int numero = 0;
        PreparedStatement pst = null;
        conn = getConexion();
        try {
            pst = conn.prepareStatement("SELECT idCliente FROM cliente where nombre = ?");
            pst.setString(1, getNombre());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numero = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numero;
    }

    // Actualiza el contenido del cliente
    public void actualizar() {
        PreparedStatement ps = null;
        conn = getConexion();

        try {
            ps = conn.prepareStatement("UPDATE CLIENTE SET TELEFONO = ?, NOMBRE = ?, APELLIDO = ?, GENERO = ? WHERE NOMBRE = ?");
            ps.setString(1, getTelefono());
            ps.setString(2, getNombre());
            ps.setString(3, getApellidos());
            ps.setInt(4, getGenero());
            ps.setString(5, getNombre());
            ps.executeUpdate();
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
    }

    // Actualiza los datos de los pedidos
    public void actualizarpedido(int idCli) {
        PreparedStatement ps = null;
        conn = getConexion();

        tipoProdH = getHard() ? "Si" : "No";
        tipoProdS = getSoft() ? "Si" : "No";
        try {
            ps = conn.prepareStatement("UPDATE PEDIDO SET TIPOPRODUCTOHARDWARE = ?, TIPOPRODUCTOSOFTWARE = ?, PRODUCTO = ?, CANTIDAD = ?, PRECIO = ?, COMPROBANTE = ?, OBSERVACIONES = ? WHERE idCliente = ?");
            ps.setString(1, tipoProdH);
            ps.setString(2, tipoProdS);
            ps.setInt(3, getProducto());
            ps.setString(4, getCantidad());
            ps.setDouble(5, Double.parseDouble(getPrecio()));
            ps.setInt(6, getComprobante());
            ps.setString(7, getObservaciones());
            ps.setInt(8, idCli);
            ps.executeUpdate();
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
    }

    // Inserta los datos en la tabla cliente si es nuevo
    public void insertar() {
        conn = getConexion();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO cliente(telefono, nombre, apellido, genero) VALUES(?,?,?,?)");
            ps.setString(1, getTelefono());
            ps.setString(2, getNombre());
            ps.setString(3, getApellidos());
            ps.setInt(4, getGenero());
            ps.executeUpdate();
            System.out.println("Se ha insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Inserta los datos en la tabla pedido 
    public void insertarpedido(int numero) {
        conn = getConexion();
        PreparedStatement ps2 = null;
        tipoProdH = getHard() ? "Si" : "No";
        tipoProdS = getSoft() ? "Si" : "No";
        try {
            ps2 = conn.prepareStatement("INSERT INTO pedido(tipoproductohardware, tipoproductosoftware, producto, cantidad, precio, comprobante, observaciones, idCliente) VALUES(?,?,?,?,?,?,?,?)");
            ps2.setString(1, tipoProdH);
            ps2.setString(2, tipoProdS);
            ps2.setInt(3, getProducto());
            ps2.setString(4, getCantidad());
            ps2.setDouble(5, Double.parseDouble(getPrecio()));
            ps2.setInt(6, getComprobante());
            ps2.setString(7, getObservaciones());
            ps2.setInt(8, numero);
            ps2.executeUpdate();
            System.out.println("Se ha insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps2 != null) {
                    ps2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Dice si se ha introducido un número entero o una letra
    public boolean esEntero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    // Dice si se ha introducido un número decimal o una letra
    public boolean esDecimal(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    // Ejecuta la aplicación
    public String execute() {
        setTelefono(telefono);
        setNombre(nombre);
        setApellidos(apellidos);
        setGenero(genero);
        setHard(hard);
        setSoft(soft);
        setProducto(producto);
        setCantidad(cantidad);
        setPrecio(precio);
        setComprobante(comprobante);
        setObservaciones(observaciones);

        if (getNombre().length() == 0) {
            return ERROR;
        } else {
            if (comprobar().contains(getNombre())) {
                if (esEntero(getTelefono()) && esEntero(getCantidad()) && esDecimal(getPrecio())) {
                    actualizar();
                    actualizarpedido(numeroCliente());
                    return SUCCESS;
                } else {
                    return ERROR;
                }
            } else {
                if (esEntero(getTelefono()) && esEntero(getCantidad()) && esDecimal(getPrecio())) {
                    insertar();
                    insertarpedido(numeroCliente());
                    return SUCCESS;
                } else {
                    return ERROR;
                }
            }
        }
    }

    public boolean getHard() {
        return hard;
    }

    public void setHard(boolean hard) {
        this.hard = hard;
    }

    public boolean getSoft() {
        return soft;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getComprobante() {
        return comprobante;
    }

    public void setComprobante(int comprobante) {
        this.comprobante = comprobante;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
