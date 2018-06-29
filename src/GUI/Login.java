/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Categoria;
import Domain.Usuario;
import Logic.Datos;
import TDA.BinaryTree.TreeException;
import TDA.Graph.GraphException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jeison
 */
public class Login extends javax.swing.JFrame {

    //Clases
    private Datos datos;

    //TDA's
    private LinkedList<Usuario> listaUsuarios;

    /**
     * constructor de la clase
     * @throws IOException io
     * @throws GraphException grafo
     * @throws FileNotFoundException archivo
     * @throws ClassNotFoundException clase
     * @throws TreeException arbol
     */
    public Login() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException, TreeException {
        initComponents();
        this.setLocationRelativeTo(null);

        //Clases
        this.datos = new Datos();

        //TDA's
        this.listaUsuarios = this.datos.getListaUsuarios();
        
        for (int i = 0; i < listaUsuarios.size(); i++) {
            System.out.println(listaUsuarios.get(i).toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextFieldNombreUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        LoginUser = new javax.swing.JLabel();
        LoginPassword = new javax.swing.JLabel();
        jPasswordFieldContraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jButtonSalirLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldNombreUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jTextFieldNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 170, -1));

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, -1, -1));

        LoginUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LoginUser.setForeground(new java.awt.Color(255, 255, 255));
        LoginUser.setText("User:");
        getContentPane().add(LoginUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        LoginPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LoginPassword.setForeground(new java.awt.Color(255, 255, 255));
        LoginPassword.setText("Password:");
        getContentPane().add(LoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jPasswordFieldContraseña.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jPasswordFieldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1211811764.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 260, 270));

        jButtonSalirLogin.setBackground(new java.awt.Color(0, 51, 51));
        jButtonSalirLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonSalirLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalirLogin.setText("Salir");
        jButtonSalirLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalirLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * boton salir
     * @param evt action event
     */
    private void jButtonSalirLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirLoginActionPerformed
        // TODO add your handling code here:
        int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {

            System.exit(0);
        }

    }//GEN-LAST:event_jButtonSalirLoginActionPerformed

    /**
     * boton ingresar
     * @param evt action event
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (jTextFieldNombreUsuario.getText().equals(listaUsuarios.get(i).getUsuario())
                    && jPasswordFieldContraseña.getText().equalsIgnoreCase(listaUsuarios.get(i).getContrasena())) {
                if (listaUsuarios.get(i).getRol().equals("Operador")) {
                    try {
                        Modulo1 panel = new Modulo1();
                        panel.setVisible(true);
                        dispose();
                    } catch (IOException | GraphException | ClassNotFoundException | TreeException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (listaUsuarios.get(i).getRol().equalsIgnoreCase("Administrador")) {
                    try {
                        Modulo2 panel = new Modulo2();
                        panel.setVisible(true);
                        dispose();
                    } catch (IOException | GraphException | ClassNotFoundException | TreeException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (IOException | GraphException | ClassNotFoundException | TreeException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LoginPassword;
    private javax.swing.JLabel LoginUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSalirLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordFieldContraseña;
    private javax.swing.JTextField jTextFieldNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
