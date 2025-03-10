package javasystemapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Agregar producto
    public boolean agregarProducto(String nombreProducto) {
        String sql = "INSERT INTO productos (nombre_producto) VALUES (?)";

        try (Connection conn = ConexionBD.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreProducto);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
            return false;
        }
    }

    // Obtener lista de productos
    public List<String> obtenerProductos() {
        List<String> productos = new ArrayList<>();
        String sql = "SELECT nombre_producto FROM productos";

        try (Connection conn = ConexionBD.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productos.add(rs.getString("nombre_producto"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }

        return productos;
    }

    // Editar producto
    public static boolean editarProducto(String nombreOriginal, String nuevoNombre) {
        try (Connection conn = ConexionBD.obtenerConexion()) {
            String query = "UPDATE productos SET nombre_producto = ? WHERE nombre_producto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nombreOriginal);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar producto
    public static boolean eliminarProducto(String nombreProducto) {
        try (Connection conn = ConexionBD.obtenerConexion()) {
            String query = "DELETE FROM productos WHERE nombre_producto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreProducto);

            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar producto por nombre
    public List<String> buscarProducto(String nombreProducto) {
        List<String> productos = new ArrayList<>();
        String sql = "SELECT nombre_producto FROM productos WHERE nombre_producto LIKE ?";

        try (Connection conn = ConexionBD.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombreProducto + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(rs.getString("nombre_producto"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return productos;
    }

    // Método para buscar productos por nombre
    public static List<String> buscarProductos(String nombre) {
        List<String> productos = new ArrayList<>();
        try (Connection conn = ConexionBD.obtenerConexion()) {
            String query = "SELECT nombre_producto FROM productos WHERE nombre_producto LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(rs.getString("nombre_producto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
