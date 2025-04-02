package javasystemapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar el inventario
 *
 * @author CarlosGalvan
 */
public class InventarioDAO {

    // 🔹 Agregar producto al inventario
    public boolean agregarProducto(Inventario inventario) {
        String sql = "INSERT INTO inventario (cliente_nombre, almacen_nombre, producto_nombre, unidad_medida, cantidad, presentacion_producto, lote_producto, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inventario.getClienteNombre());
            ps.setString(2, inventario.getAlmacenNombre());
            ps.setString(3, inventario.getProductoNombre());
            ps.setString(4, inventario.getUnidadMedida());
            ps.setInt(5, inventario.getCantidad());
            ps.setString(6, inventario.getPresentacionProducto());
            ps.setString(7, inventario.getLoteProducto());
            ps.setString(8, inventario.getObservaciones());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Obtener todos los productos del inventario (SIN id_producto)
    public List<Inventario> obtenerInventario() {
        List<Inventario> inventarioLista = new ArrayList<>();
        String sql = "SELECT cliente_nombre, almacen_nombre, producto_nombre, unidad_medida, cantidad, presentacion_producto, lote_producto, observaciones FROM inventario";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventario inventario = new Inventario(
                        rs.getString("cliente_nombre"),
                        rs.getString("almacen_nombre"),
                        rs.getString("producto_nombre"),
                        rs.getString("unidad_medida"),
                        rs.getInt("cantidad"),
                        rs.getString("presentacion_producto"),
                        rs.getString("lote_producto"),
                        rs.getString("observaciones")
                );
                inventarioLista.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarioLista;
    }

    // 🔹 Actualizar producto en el inventario
    public boolean actualizarProducto(Inventario inventario) {
        String sql = "UPDATE inventario SET cliente_nombre = ?, almacen_nombre = ?, producto_nombre = ?, unidad_medida = ?, cantidad = ?, presentacion_producto = ?, observaciones = ? "
                + "WHERE lote_producto = ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inventario.getClienteNombre());
            ps.setString(2, inventario.getAlmacenNombre());
            ps.setString(3, inventario.getProductoNombre());
            ps.setString(4, inventario.getUnidadMedida());
            ps.setInt(5, inventario.getCantidad());
            ps.setString(6, inventario.getPresentacionProducto());
            ps.setString(7, inventario.getObservaciones());
            ps.setString(8, inventario.getLoteProducto());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Eliminar producto del inventario por lote_producto
    public boolean eliminarProducto(String loteProducto) {
        String sql = "DELETE FROM inventario WHERE lote_producto = ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, loteProducto);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Buscar producto por lote
    public Inventario buscarProductoPorLote(String loteProducto) {
        String sql = "SELECT cliente_nombre, almacen_nombre, producto_nombre, unidad_medida, cantidad, presentacion_producto, lote_producto, observaciones "
                + "FROM inventario WHERE lote_producto = ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, loteProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Inventario(
                        rs.getString("cliente_nombre"),
                        rs.getString("almacen_nombre"),
                        rs.getString("producto_nombre"),
                        rs.getString("unidad_medida"),
                        rs.getInt("cantidad"),
                        rs.getString("presentacion_producto"),
                        rs.getString("lote_producto"),
                        rs.getString("observaciones")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean loteExiste(String lote) {
        String sql = "SELECT COUNT(*) FROM inventario WHERE lote_producto = ?";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lote);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // El lote ya existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // El lote no existe
    }

    public boolean agregarProductos(List<Inventario> listaInventario) {
        String sql = "INSERT INTO inventario (cliente_nombre, almacen_nombre, producto_nombre, unidad_medida, cantidad, presentacion_producto, lote_producto, observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            for (Inventario inv : listaInventario) {
                ps.setString(1, inv.getClienteNombre());
                ps.setString(2, inv.getAlmacenNombre());
                ps.setString(3, inv.getProductoNombre());
                ps.setString(4, inv.getUnidadMedida());
                ps.setInt(5, inv.getCantidad());
                ps.setString(6, inv.getPresentacionProducto());
                ps.setString(7, inv.getLoteProducto());
                ps.setString(8, inv.getObservaciones());
                ps.addBatch(); // Agregar al lote
            }

            int[] resultados = ps.executeBatch();
            return resultados.length == listaInventario.size();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarTotales(String nombreAlmacen, String nombreCliente, String nombreProducto, String presentacion, int cantidad) {
        String sqlVerificar = "SELECT total FROM totales WHERE nombre_almacen = ? AND nombre_cliente = ? AND nombre_producto = ? AND presentacion_producto = ?";
        String sqlInsertar = "INSERT INTO totales (nombre_almacen, nombre_cliente, nombre_producto, presentacion_producto, total) VALUES (?, ?, ?, ?, ?)";
        String sqlActualizar = "UPDATE totales SET total = total + ? WHERE nombre_almacen = ? AND nombre_cliente = ? AND nombre_producto = ? AND presentacion_producto = ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement stmtVerificar = con.prepareStatement(sqlVerificar); PreparedStatement stmtInsertar = con.prepareStatement(sqlInsertar); PreparedStatement stmtActualizar = con.prepareStatement(sqlActualizar)) {

            // Verificar si el producto ya existe en la tabla totales
            stmtVerificar.setString(1, nombreAlmacen);
            stmtVerificar.setString(2, nombreCliente);
            stmtVerificar.setString(3, nombreProducto);
            stmtVerificar.setString(4, presentacion);
            ResultSet rs = stmtVerificar.executeQuery();

            if (rs.next()) {
                // Producto ya existe, actualizar total
                stmtActualizar.setInt(1, cantidad);
                stmtActualizar.setString(2, nombreAlmacen);
                stmtActualizar.setString(3, nombreCliente);
                stmtActualizar.setString(4, nombreProducto);
                stmtActualizar.setString(5, presentacion);
                stmtActualizar.executeUpdate();
            } else {
                // Producto no existe, insertarlo
                stmtInsertar.setString(1, nombreAlmacen);
                stmtInsertar.setString(2, nombreCliente);
                stmtInsertar.setString(3, nombreProducto);
                stmtInsertar.setString(4, presentacion);
                stmtInsertar.setInt(5, cantidad);
                stmtInsertar.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
