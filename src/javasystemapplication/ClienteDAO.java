/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasystemapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // 1️⃣ CREAR: Agregar un nuevo cliente
    public boolean agregarCliente(String nombreCliente, String domicilio, String rfc) {
        String sql = "INSERT INTO clientes (nombre_cliente, domicilio, RFC) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreCliente);
            ps.setString(2, domicilio);
            ps.setString(3, rfc);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se insertó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ LEER: Obtener todos los clientes de la base de datos
    public List<String[]> obtenerClientes() {
        List<String[]> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection con = ConexionBD.obtenerConexion(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nombreCliente = rs.getString("nombre_cliente");
                String domicilio = rs.getString("domicilio");
                String rfc = rs.getString("RFC");

                clientes.add(new String[]{id, nombreCliente, domicilio, rfc});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // 3️⃣ ACTUALIZAR: Editar un cliente existente
    public boolean editarCliente(String id, String nuevoNombre, String nuevoDomicilio, String nuevoRFC) {
        String sql = "UPDATE clientes SET nombre_cliente = ?, domicilio = ?, RFC = ? WHERE id = ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoDomicilio);
            ps.setString(3, nuevoRFC);
            ps.setString(4, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se actualizó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ ELIMINAR: Eliminar un cliente por su ID
    public boolean eliminarCliente(String id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se eliminó correctamente

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ BUSCAR: Buscar clientes por nombre (sin importar mayúsculas o minúsculas)
    public List<String[]> buscarClientes(String filtro) {
        List<String[]> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE LOWER(nombre_cliente) LIKE LOWER(?)";

        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + filtro + "%"); // Buscar clientes que contengan el texto

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String nombreCliente = rs.getString("nombre_cliente");
                String domicilio = rs.getString("domicilio");
                String rfc = rs.getString("RFC");

                clientes.add(new String[]{id, nombreCliente, domicilio, rfc});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Obtener solo los nombres de los clientes
    public List<String> obtenerNombresClientes() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre_cliente FROM clientes";

        try (Connection con = ConexionBD.obtenerConexion(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                nombres.add(rs.getString("nombre_cliente"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombres;
    }
    
    
}
