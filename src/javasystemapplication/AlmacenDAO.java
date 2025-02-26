/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasystemapplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author CarlosGalvan
 */
public class AlmacenDAO {
        public boolean agregarAlmacen(String nombreAlmacen, String nombreCliente) {
        String sql = "INSERT INTO almacenes (nombre_almacen, nombre_cliente) VALUES (?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreAlmacen);
            ps.setString(2, nombreCliente);
            return ps.executeUpdate() > 0; // Retorna true si se insertó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para editar un almacén
    public boolean editarAlmacen(int id, String nuevoNombre, String nuevoCliente) {
        String sql = "UPDATE almacenes SET nombre_almacen = ?, nombre_cliente = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoCliente);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0; // Retorna true si se actualizó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un almacén
    public boolean eliminarAlmacen(int id) {
        String sql = "DELETE FROM almacenes WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0; // Retorna true si se eliminó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar almacenes por nombre
    public List<String[]> buscarAlmacen(String nombre) {
        String sql = "SELECT * FROM almacenes WHERE nombre_almacen LIKE ?";
        List<String[]> resultados = new ArrayList<>();

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] fila = {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre_almacen"),
                    rs.getString("nombre_cliente")
                };
                resultados.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultados;
    }
    
    public List<String[]> buscarAlmacen2(String filtro) {
    List<String[]> lista = new ArrayList<>();
    String sql = "SELECT id, nombre_almacen, nombre_cliente FROM almacenes WHERE LOWER(nombre_almacen) LIKE LOWER(?)";

    try (Connection con = ConexionBD.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, "%" + filtro + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new String[]{
                rs.getString("id"),
                rs.getString("nombre_almacen"),
                rs.getString("nombre_cliente")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}
}
