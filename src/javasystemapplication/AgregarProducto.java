/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javasystemapplication;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author CarlosGalvan
 */
public class AgregarProducto extends javax.swing.JFrame {

    private JTextField txtNombre, txtClase, txtCantidad;
    private JComboBox<String> cmbUnidadMedida, cmbTipoCantidad;
    private JButton btnGuardar, btnCancelar;
    private ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Creates new form AgregarProducto
     */
    public AgregarProducto() {
               setTitle("Agregar Nuevo Producto");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Panel principal
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // 🔹 Campos principales
        JLabel lblNombre = new JLabel("Nombre del Producto:");
        txtNombre = new JTextField();

        JLabel lblClase = new JLabel("Clase:");
        txtClase = new JTextField();

        // 🔹 Añadir campos principales
        panelPrincipal.add(lblNombre);
        panelPrincipal.add(txtNombre);
        panelPrincipal.add(lblClase);
        panelPrincipal.add(txtClase);

        add(panelPrincipal, BorderLayout.NORTH);

        // 🔹 Sección de Conversión
        JPanel panelConversion = new JPanel(new GridLayout(3, 2, 10, 10));
        panelConversion.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204)),
                "Conversión", 0, 0, new Font("Arial", Font.BOLD, 12),
                new Color(0, 102, 204))
        );

        // 🔹 Campos de Conversión
        JLabel lblCantidad = new JLabel("Factor:");
        txtCantidad = new JTextField();

        JLabel lblTipoCantidad = new JLabel("Tipo de Cantidad:");
        String[] tiposCantidad = {"Piezas", "Litros", "Kilos", "Metros", "Gramos", "Unidades"};
        cmbTipoCantidad = new JComboBox<>(tiposCantidad);

        JLabel lblUnidadMedida = new JLabel("Equivale a:");
        String[] unidades = {"Caja", "Paquete", "Bolsa", "Bote", "Tarima", "Rollo"};
        cmbUnidadMedida = new JComboBox<>(unidades);

        // 🔹 Añadir campos de Conversión
        panelConversion.add(lblCantidad);
        panelConversion.add(txtCantidad);
        panelConversion.add(lblTipoCantidad);
        panelConversion.add(cmbTipoCantidad);
        panelConversion.add(lblUnidadMedida);
        panelConversion.add(cmbUnidadMedida);

        add(panelConversion, BorderLayout.CENTER);

        // 🔹 Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // 🔹 Aplicar estilos
        aplicarEstilos();

        // 🎯 Evento para guardar producto
        btnGuardar.addActionListener(e -> guardarProducto());

        // 🎯 Evento para cancelar
        btnCancelar.addActionListener(e -> {
            dispose();
            new PantallaProductos().setVisible(true);
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
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AgregarProducto().setVisible(true);
        });
    }

    private void guardarProducto() {
        String nombre = txtNombre.getText().trim();
        String clase = txtClase.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();
        String tipoCantidad = (String) cmbTipoCantidad.getSelectedItem();
        String unidadMedida = (String) cmbUnidadMedida.getSelectedItem();

        // Validar campos obligatorios
        if (nombre.isEmpty() || clase.isEmpty() || cantidadStr.isEmpty() || tipoCantidad == null || unidadMedida == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que la cantidad sea un número válido
        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El factor debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 🔥 Formato de conversión -> "12 piezas = 1 caja" o "5 litros = 1 bote"
        String conversion = cantidad + " " + tipoCantidad.toLowerCase() + " = 1 " + unidadMedida.toLowerCase();

        // Confirmar antes de guardar
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Deseas guardar el producto '" + nombre + "' con la conversión: " + conversion + "?",
                "Confirmar Guardado", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean guardado = productoDAO.agregarProducto(nombre, clase, unidadMedida, conversion);

            if (guardado) {
                JOptionPane.showMessageDialog(this, "Producto agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cerrar la ventana después de guardar
                new PantallaProductos().setVisible(true); // Regresar a la pantalla de productos
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Aplica los estilos a los botones y componentes
     */
    private void aplicarEstilos() {
        Color azul = new Color(0, 102, 204);
        Color rojo = new Color(204, 0, 0);
        Color blanco = Color.WHITE;

        btnGuardar.setBackground(azul);
        btnGuardar.setForeground(blanco);
        btnCancelar.setBackground(rojo);
        btnCancelar.setForeground(blanco);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
