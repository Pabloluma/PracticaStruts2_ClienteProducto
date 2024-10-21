package action;

public class Pedido {
	private int idPedido;
    private boolean hard;
    private boolean soft;
    private int producto;
    private int cantidad;
    private int precio;
    private int comprobante;
    private String observaciones;
    private String hardware ;
    private String software ;

    // Getters y Setters

    public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public boolean isHard() {
        return hard;
    }

    public void setHard(boolean hard) {
        this.hard = hard;
    }

    public boolean isSoft() {
        return soft;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
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
    
    public String getHardware() {
    	return hardware;
    }
    
    public void setHardware(String hardware) {
    	this.hardware = hardware;
    }
    
    public String getSoftware() {
    	return software;
    }
    
    public void setSoftware(String software) {
    	this.software = software;
    }
    
}