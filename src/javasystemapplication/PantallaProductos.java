/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javasystemapplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author CarlosGalvan
 */
public class PantallaProductos extends javax.swing.JFrame {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnAlta, btnBaja, btnBuscar, btnEditar, btnEliminar, btnInventario, btnRegresar;
    private ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Creates new form PantallaProductos
     */
    public PantallaProductos() {
        setTitle("Gestión de Productos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Modelo de tabla con nuevas columnas
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Producto");
        modeloTabla.addColumn("Clase");

        // 🔹 Tabla con scroll
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // 🔹 Panel inferior (botones de acción)
        JPanel panelInferior = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar Producto");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnInventario = new JButton("Inventario");
        btnRegresar = new JButton("Regresar");

        panelInferior.add(btnAgregar);
        panelInferior.add(btnBuscar);
        panelInferior.add(btnEditar);
        panelInferior.add(btnEliminar);
        panelInferior.add(btnRegresar);

        // 🔹 Panel superior (Alta y Baja)
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAlta = new JButton("Alta");
        btnBaja = new JButton("Baja");
        panelSuperior.add(btnAlta);
        panelSuperior.add(btnBaja);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // 🔹 Aplicar estilos
        aplicarEstilos();

        // 🔹 Cargar datos en la tabla
        cargarProductos();

        // 🔹 Eventos de los botones
        btnAgregar.addActionListener(e -> {
            dispose();
            new AgregarProducto().setVisible(true);
        });

        btnBuscar.addActionListener(e -> {
            dispose();
            new BuscarProducto().setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int filaSeleccionada = tablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {
                String nombreProducto = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                dispose();
                new EditarProducto(nombreProducto).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {
                String nombreProducto = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Seguro que deseas eliminar el producto '" + nombreProducto + "'?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    boolean eliminado = productoDAO.eliminarProducto(nombreProducto);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Producto eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        modeloTabla.removeRow(filaSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        btnAlta.addActionListener(e -> {
            dispose();
            new AltaProducto().setVisible(true);
        });
        
        btnRegresar.addActionListener(e -> {
            dispose();
            new MenuPrincipal().setVisible(true);
        });
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
            java.util.logging.Logger.getLogger(PantallaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaProductos().setVisible(true);
            }
        });
    }

// 🔹 Aplicar estilos a los botones
    private void aplicarEstilos() {
        Color azul = new Color(0, 102, 204);
        Color rojo = new Color(204, 0, 0);
        Color gris = Color.GRAY;
        Color blanco = Color.WHITE;

        btnAlta.setBackground(azul);
        btnAlta.setForeground(blanco);
        btnBaja.setBackground(rojo);
        btnBaja.setForeground(blanco);
        btnAgregar.setBackground(azul);
        btnAgregar.setForeground(blanco);
        btnBuscar.setBackground(azul);
        btnBuscar.setForeground(blanco);
        btnEditar.setBackground(azul);
        btnEditar.setForeground(blanco);
        btnEliminar.setBackground(rojo);
        btnEliminar.setForeground(blanco);
        btnInventario.setBackground(azul);
        btnInventario.setForeground(blanco);
        btnRegresar.setBackground(gris);
        btnRegresar.setForeground(blanco);
    }

    // 🔹 Método para cargar los productos en la tabla
    private void cargarProductos() {
        List<String[]> productos = productoDAO.obtenerProductos();
        modeloTabla.setRowCount(0); // Limpiar tabla antes de cargar

        for (String[] producto : productos) {
            modeloTabla.addRow(producto);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
