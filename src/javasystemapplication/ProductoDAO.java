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

    // 🔹 Obtener productos como una lista de String[]
    public List<String[]> obtenerProductos() {
        List<String[]> productos = new ArrayList<>();
        String sql = "SELECT nombre_producto, clase_producto FROM productos";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre_producto");
                String clase = rs.getString("clase_producto");

                productos.add(new String[]{nombre, clase});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

// 🔹 Editar producto y actualizar en la base de datos
    public boolean editarProducto(String nombreOriginal, String nuevoNombre, String nuevaClase, String conversion) {
        String sql = "UPDATE productos SET nombre_producto = ?, clase_producto = ?, conversion = ? WHERE nombre_producto = ?";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevaClase);
            ps.setString(3, conversion);
            ps.setString(4, nombreOriginal);

            return ps.executeUpdate() > 0;
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

// 🔹 Buscar producto en la base de datos
    public List<String[]> buscarProducto(String criterio) {
        List<String[]> listaProductos = new ArrayList<>();
        String sql = "SELECT nombre_producto, clase_producto, unidad_medida, conversion FROM productos WHERE nombre_producto LIKE ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre_producto");
                String clase = rs.getString("clase_producto");
                String unidadMedida = rs.getString("unidad_medida");
                String conversion = rs.getString("conversion");

                // 🔥 Crear un array de strings para agregar a la tabla
                listaProductos.add(new String[]{nombre, clase, unidadMedida, conversion});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    // 🔹 Agregar producto a la base de datos con conversión
    public boolean agregarProducto(String nombre, String clase, String unidadMedida, String conversion) {
        String sql = "INSERT INTO productos (nombre_producto, clase_producto, unidad_medida, conversion) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, clase);
            ps.setString(3, unidadMedida);
            ps.setString(4, conversion);

            return ps.executeUpdate() > 0; // Retorna true si se insertó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Obtener todos los productos cuando el campo de búsqueda está vacío
    public List<String[]> obtenerTodosLosProductos() {
        List<String[]> listaProductos = new ArrayList<>();
        String sql = "SELECT nombre_producto, clase_producto, unidad_medida, conversion FROM productos";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre_producto");
                String clase = rs.getString("clase_producto");
                String unidadMedida = rs.getString("unidad_medida");
                String conversion = rs.getString("conversion");

                listaProductos.add(new String[]{nombre, clase, unidadMedida, conversion});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
    }
// 🔹 Obtener datos del producto por nombre

    public String[] obtenerProductoPorNombre(String nombreProducto) {
        String sql = "SELECT nombre_producto, clase_producto, unidad_medida, conversion FROM productos WHERE nombre_producto = ?";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre_producto");
                String clase = rs.getString("clase_producto");
                String unidadMedida = rs.getString("unidad_medida");
                String conversion = rs.getString("conversion");
                return new String[]{nombre, clase, unidadMedida, conversion};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtener el factor desde la conversión
     */
    private int obtenerFactorDesdeConversion(String conversion) {
        try {
            String[] partes = conversion.split(" ");
            return Integer.parseInt(partes[0]); // El primer valor es el factor
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
