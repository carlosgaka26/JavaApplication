/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javasystemapplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.event.CellEditorListener;
/**
 *
 * @author CarlosGalvan
 */
public class AltaProducto extends javax.swing.JFrame {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar, btnCancelar;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private AlmacenDAO almacenDAO = new AlmacenDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Creates new form AltaProducto
     */
    public AltaProducto() {
         setTitle("Alta de Productos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 🔹 Crear el modelo de la tabla
        String[] columnas = {"Cliente", "Almacén", "Producto", "Unidad de Medida", "Lote", "Presentación", "Observaciones"};
        modeloTabla = new DefaultTableModel(null, columnas);

        // 🔹 Crear la tabla con el modelo
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // 🔹 Panel para la tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Botones para agregar y guardar productos
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        JButton btnAgregarFila = new JButton("Agregar Fila");

        panelBotones.add(btnAgregarFila);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        // 🔹 Panel principal
        setLayout(new BorderLayout());
        add(panelTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // 🔹 Aplicar estilos
        aplicarEstilos();

        // 🎯 Eventos de botones
        btnAgregarFila.addActionListener(e -> agregarFila());
//        btnGuardar.addActionListener(e -> guardarProductos());
        btnCancelar.addActionListener(e -> {
            dispose();
            new PantallaProductos().setVisible(true);
        });

        // 🔹 Establecer editores de celdas para las columnas "Cliente", "Almacén" y "Producto"
        tablaProductos.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JComboBox<>(cargarClientes())));
        tablaProductos.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<>(cargarAlmacenes())));
        tablaProductos.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<>(cargarProductos())));

        // 🔹 Detectar clic en la columna de "Observaciones"
        tablaProductos.getColumnModel().getColumn(6).setCellEditor(new TableCellEditor() {
            JTextArea textArea = new JTextArea(5, 20);

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                // Cuando se hace clic en la celda, mostrar un cuadro de texto más grande para las observaciones
                String observaciones = (String) value;
                textArea.setText(observaciones != null ? observaciones : "");
                textArea.setCaretPosition(textArea.getText().length());
                return new JScrollPane(textArea);
            }

            @Override
            public Object getCellEditorValue() {
                return textArea.getText(); // Devolver el texto escrito
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) {
                return true;
            }

            @Override
            public boolean shouldSelectCell(EventObject anEvent) {
                return true;
            }

            @Override
            public boolean stopCellEditing() {
                return true;
            }

            @Override
            public void cancelCellEditing() {
                textArea.setText(""); // Cancelar la edición si es necesario
            }

             @Override
             public void addCellEditorListener(CellEditorListener l) {
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }

             @Override
             public void removeCellEditorListener(CellEditorListener l) {
                 throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             }
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
            java.util.logging.Logger.getLogger(AltaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AltaProducto().setVisible(true);
            }
        });
    }

    private void aplicarEstilos() {
        Color azul = new Color(0, 102, 204);
        Color rojo = new Color(204, 0, 0);
        Color blanco = Color.WHITE;

        btnGuardar.setBackground(azul);
        btnGuardar.setForeground(blanco);
        btnCancelar.setBackground(rojo);
        btnCancelar.setForeground(blanco);
    }

    private String[] cargarProductos() {
        List<String[]> listaProductos = productoDAO.obtenerProductos();
        List<String> nombresProductos = new ArrayList<>();

        for (String[] producto : listaProductos) {
            nombresProductos.add(producto[0]); // nombre_producto
        }
        return nombresProductos.toArray(new String[0]);
    }

    private String[] cargarAlmacenes() {
        List<String[]> listaAlmacenes = almacenDAO.obtenerAlmacenes();
        List<String> nombresAlmacenes = new ArrayList<>();

        for (String[] almacen : listaAlmacenes) {
            nombresAlmacenes.add(almacen[1]); // nombre_almacen
        }
        return nombresAlmacenes.toArray(new String[0]);
    }

    private String[] cargarClientes() {
        List<String[]> listaClientes = clienteDAO.obtenerClientes();
        List<String> nombresClientes = new ArrayList<>();

        for (String[] cliente : listaClientes) {
            nombresClientes.add(cliente[1]); // nombre_cliente
        }
        return nombresClientes.toArray(new String[0]);
    }

    private void agregarFila() {
        modeloTabla.addRow(new Object[]{"", "", "", "", "", "", ""});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
