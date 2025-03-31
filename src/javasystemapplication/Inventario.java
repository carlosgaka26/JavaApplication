package javasystemapplication;

/**
 * Modelo para Inventario
 */
public class Inventario {

    private int idProducto;
    private String clienteNombre;
    private String almacenNombre;
    private String productoNombre;
    private String unidadMedida;
    private int cantidad;
    private String presentacionProducto;
    private String loteProducto;
    private String observaciones;

    // 🔹 Constructor para obtener productos SIN id_producto
    public Inventario(String clienteNombre, String almacenNombre, String productoNombre, String unidadMedida, int cantidad, String presentacionProducto, String loteProducto, String observaciones) {
        this.clienteNombre = clienteNombre;
        this.almacenNombre = almacenNombre;
        this.productoNombre = productoNombre;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
        this.presentacionProducto = presentacionProducto;
        this.loteProducto = loteProducto;
        this.observaciones = observaciones;
    }

    // 🔹 Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getAlmacenNombre() {
        return almacenNombre;
    }

    public void setAlmacenNombre(String almacenNombre) {
        this.almacenNombre = almacenNombre;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPresentacionProducto() {
        return presentacionProducto;
    }

    public void setPresentacionProducto(String presentacionProducto) {
        this.presentacionProducto = presentacionProducto;
    }

    public String getLoteProducto() {
        return loteProducto;
    }

    public void setLoteProducto(String loteProducto) {
        this.loteProducto = loteProducto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
