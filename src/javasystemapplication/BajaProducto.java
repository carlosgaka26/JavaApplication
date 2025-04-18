/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javasystemapplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author CarlosGalvan
 */
public class BajaProducto extends javax.swing.JFrame {

    private JComboBox<String> comboCliente, comboProducto, comboLote;
    private JTextField txtCantidad;
    private JButton btnAgregar, btnRegistrar;
    private JTable tablaSalidas, tablaTotales;
    private DefaultTableModel modeloSalidas, modeloTotales;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private InventarioDAO inventarioDAO = new InventarioDAO();

    /**
     * Creates new form BajaProducto
     */
    public BajaProducto() {
        setTitle("Baja de Productos");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelArriba = new JPanel(new BorderLayout());
        JPanel panelFormulario = new JPanel(new FlowLayout());

        comboCliente = new JComboBox<>();
        comboProducto = new JComboBox<>();
        comboLote = new JComboBox<>();
        txtCantidad = new JTextField(5);
        btnAgregar = new JButton("Agregar a salida");
        btnRegistrar = new JButton("Registrar Salida");

        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(comboCliente);
        panelFormulario.add(new JLabel("Producto:"));
        panelFormulario.add(comboProducto);
        panelFormulario.add(new JLabel("Lote:"));
        panelFormulario.add(comboLote);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(txtCantidad);
        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnRegistrar);

        modeloSalidas = new DefaultTableModel(new String[]{"Cliente", "Producto", "Lote", "Cantidad"}, 0);
        tablaSalidas = new JTable(modeloSalidas);

        panelArriba.add(panelFormulario, BorderLayout.NORTH);
        panelArriba.add(new JScrollPane(tablaSalidas), BorderLayout.CENTER);

        // Panel inferior
        JPanel panelAbajo = new JPanel(new BorderLayout());
        modeloTotales = new DefaultTableModel(new String[]{"Almacén", "Cliente", "Producto", "Presentación", "Total"}, 0);
        tablaTotales = new JTable(modeloTotales);
        panelAbajo.add(new JLabel("Totales actuales:"), BorderLayout.NORTH);
        panelAbajo.add(new JScrollPane(tablaTotales), BorderLayout.CENTER);

        // Dividir en dos secciones
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelArriba, panelAbajo);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        cargarClientes();
        cargarTotales();

        comboCliente.addActionListener(e -> cargarProductosPorCliente());
        comboProducto.addActionListener(e -> cargarLotesPorProducto());

        btnAgregar.addActionListener(e -> agregarASalida());
        btnRegistrar.addActionListener(e -> registrarSalida());

        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BajaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BajaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BajaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BajaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BajaProducto().setVisible(true);
            }
        });
    }

    private void cargarClientes() {
        comboCliente.removeAllItems();
        for (String nombre : clienteDAO.obtenerNombresClientes()) {
            comboCliente.addItem(nombre);
        }
    }

    private void cargarProductosPorCliente() {
        comboProducto.removeAllItems();
        String cliente = (String) comboCliente.getSelectedItem();
        if (cliente != null) {
            for (String nombreProd : productoDAO.obtenerProductosPorCliente(cliente)) {
                comboProducto.addItem(nombreProd);
            }
        }
    }

    private void cargarLotesPorProducto() {
        comboLote.removeAllItems();
        String cliente = (String) comboCliente.getSelectedItem();
        String producto = (String) comboProducto.getSelectedItem();
        if (cliente != null && producto != null) {
            for (String lote : productoDAO.obtenerLotesPorClienteYProducto(cliente, producto)) {
                comboLote.addItem(lote);
            }
        }
    }

    private void agregarASalida() {
        String cliente = (String) comboCliente.getSelectedItem();
        String producto = (String) comboProducto.getSelectedItem();
        String lote = (String) comboLote.getSelectedItem();
        String cantidadStr = txtCantidad.getText();

        if (cliente == null || producto == null || lote == null || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) throw new NumberFormatException();

            modeloSalidas.addRow(new Object[]{cliente, producto, lote, cantidad});
            txtCantidad.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.");
        }
    }

    private void registrarSalida() {
        int filas = modeloSalidas.getRowCount();
        if (filas == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos en la tabla de salida.");
            return;
        }

        boolean exito = true;

        for (int i = 0; i < filas; i++) {
            String cliente = (String) modeloSalidas.getValueAt(i, 0);
            String producto = (String) modeloSalidas.getValueAt(i, 1);
            String lote = (String) modeloSalidas.getValueAt(i, 2);
            int cantidad = Integer.parseInt(modeloSalidas.getValueAt(i, 3).toString());

            Inventario inv = inventarioDAO.buscarProductoPorLote(lote);
            if (inv == null || inv.getCantidad() < cantidad) {
                JOptionPane.showMessageDialog(this, "Cantidad insuficiente para el lote: " + lote);
                exito = false;
                continue;
            }

            int nuevaCantidad = inv.getCantidad() - cantidad;
            inv.setCantidad(nuevaCantidad);
            inventarioDAO.actualizarProducto(inv);
            inventarioDAO.actualizarTotales(inv.getAlmacenNombre(), cliente, producto, inv.getPresentacionProducto(), -cantidad);
        }

        if (exito) {
            JOptionPane.showMessageDialog(this, "Salidas registradas correctamente.");
            modeloSalidas.setRowCount(0);
            cargarTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrieron errores. Revisa las salidas.");
        }
    }

    private void cargarTotales() {
        modeloTotales.setRowCount(0);
        for (String[] fila : inventarioDAO.obtenerTotales()) {
            modeloTotales.addRow(fila);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
