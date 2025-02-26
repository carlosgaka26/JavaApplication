/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javasystemapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author CarlosGalvan
 */
public class InicioSesion extends javax.swing.JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIniciarSesion;
    private JButton btnCancelar;

    /**
     * Creates new form InicioSesion
     */
    public InicioSesion() {
        setTitle("Inicio de Sesión");
        setSize(400, 450);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Quita la barra de título para un diseño más moderno

        // Panel principal con fondo degradado
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 0, 128), getWidth(), getHeight(), new Color(255, 0, 0));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);

        // Título del formulario
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(120, 30, 200, 30);
        panel.add(lblTitulo);

        // Campo Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setBounds(50, 100, 100, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 130, 300, 30);
        txtUsuario.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtUsuario);

        // Campo Contraseña
        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setForeground(Color.WHITE);
        lblContraseña.setFont(new Font("Arial", Font.PLAIN, 16));
        lblContraseña.setBounds(50, 180, 100, 25);
        panel.add(lblContraseña);

        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(50, 210, 300, 30);
        txtContraseña.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(txtContraseña);

        // Botón Iniciar Sesión
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(50, 270, 300, 40);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setBackground(new Color(0, 0, 255));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setBorder(null);
        panel.add(btnIniciarSesion);

        // Efecto Hover para el botón Iniciar Sesión
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarSesion.setBackground(new Color(0, 0, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarSesion.setBackground(new Color(0, 0, 255));
            }
        });

        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 330, 300, 40);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(255, 0, 0));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(null);
        panel.add(btnCancelar);

        // Efecto Hover para el botón Cancelar
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(200, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(255, 0, 0));
            }
        });

        // Acción del botón Iniciar Sesión
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());
                if (usuario.equals("admin") && contraseña.equals("1234")) {
                    dispose();
                    new MenuPrincipal().setVisible(true);
                    // Aquí iría la lógica para abrir el sistema
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });

        add(panel);
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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
