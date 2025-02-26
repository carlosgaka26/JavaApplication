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
public class PantallaAlmacen extends javax.swing.JFrame {

    private JTable tablaAlmacenes;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnBuscar, btnEditar, btnEliminar, btnRegresar;
    private AlmacenDAO almacenDAO;

    /**
     * Creates new form PantallaAlmacen
     */
    public PantallaAlmacen() {
 setTitle("Gestión de Almacenes");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        almacenDAO = new AlmacenDAO();

        // 🔹 Modelo de tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre Almacén");
        modeloTabla.addColumn("Cliente");

        // 🔹 Tabla con scroll
        tablaAlmacenes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlmacenes);
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnRegresar = new JButton("Regresar");

        // 🔹 Estilos de botones (COLORES CONSISTENTES)
        Color azul = new Color(0, 102, 204);  // Azul para agregar, buscar y editar
        Color rojo = new Color(204, 0, 0);    // Rojo para eliminar
        Color gris = Color.GRAY;              // Gris para regresar
        Color blanco = Color.WHITE;           // Texto en blanco

        btnAgregar.setBackground(azul);
        btnAgregar.setForeground(blanco);
        btnBuscar.setBackground(azul);
        btnBuscar.setForeground(blanco);
        btnEditar.setBackground(azul);
        btnEditar.setForeground(blanco);
        btnEliminar.setBackground(rojo);
        btnEliminar.setForeground(blanco);
        btnRegresar.setBackground(gris);
        btnRegresar.setForeground(blanco);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRegresar);
        add(panelBotones, BorderLayout.SOUTH);

        // 🔹 Cargar almacenes en la tabla
        cargarAlmacenes();

        // 🔹 Eventos de botones
        btnRegresar.addActionListener(e -> {
            dispose();
            new MenuPrincipal().setVisible(true);
        });

        btnAgregar.addActionListener(e -> {
            dispose();
            new AgregarAlmacen().setVisible(true);
        });

        btnBuscar.addActionListener(e -> {
            dispose();
            new BuscarAlmacen().setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int filaSeleccionada = tablaAlmacenes.getSelectedRow();
            if (filaSeleccionada != -1) {
                int id = Integer.parseInt((String) modeloTabla.getValueAt(filaSeleccionada, 0));
                String nombreAlmacen = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                String cliente = (String) modeloTabla.getValueAt(filaSeleccionada, 2);

                new EditarAlmacen(id, nombreAlmacen, cliente, this).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un almacén para editar.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaAlmacenes.getSelectedRow();
            if (filaSeleccionada != -1) {
                int id = Integer.parseInt((String) modeloTabla.getValueAt(filaSeleccionada, 0));
                String nombreAlmacen = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar el almacén: " + nombreAlmacen + "?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    if (new AlmacenDAO().eliminarAlmacen(id)) {
                        JOptionPane.showMessageDialog(null, "Almacén eliminado correctamente.");
                        cargarAlmacenes();  // Actualizar tabla después de eliminar
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el almacén.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un almacén para eliminar.");
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
// </editor-fold>                        

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
            java.util.logging.Logger.getLogger(PantallaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaAlmacen().setVisible(true);
            }
        });
    }

    public void cargarAlmacenes() {
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos
        List<String[]> almacenes = almacenDAO.buscarAlmacen("");
        for (String[] almacen : almacenes) {
            modeloTabla.addRow(almacen);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
