/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Bodega;
import Domain.ProductoMayorista;
import Domain.UnidadTransporte;
import Domain.Usuario;
import Logic.Datos;

import Logic.Logica;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeison
 */
public class Modulo1 extends javax.swing.JFrame {

    //Clases
    private Datos data;
    private Logica logica;

    //TDA's
    private LinkedList<ProductoMayorista> listaProductos;
    private AdjacencyMatrixGraph grafoBodegas;
    private static LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte;

    //JList
    private DefaultListModel listModel;

    //JTable
    private DefaultTableModel tableModel;
    private int contTable;

    //ProgressBar
    private int progreso;

    //Monto y Peso
    private double precioTotal;
    private int pesoTotal;

    /**
     * Creates new form Modulo1
     */
    public Modulo1() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jProgressBar.setStringPainted(true);

        //Clases
        this.data = new Datos();
        this.logica = new Logica();

        //TDA's
        this.listaProductos = this.data.getListaProductos();
        this.grafoBodegas = this.data.getGrafoBodegas();
        this.linkedHashMapUnidadTransporte = this.data.getLinkedHashMapUnidadTransporte();

        //JList
        this.listModel = new DefaultListModel();
        this.jListProductos.setModel(listModel);
        llenarJList();

        //JTable
        this.tableModel = new DefaultTableModel();
        this.contTable = 0;
        inicializarJTable();

        //Monto y peso
        this.precioTotal = 0;
        this.pesoTotal = 0;

//        browser();
        llenarComboBoxBodegas();

    }

    private void llenarJList() {
        for (int i = 0; i < this.listaProductos.size(); i++) {
            this.listModel.addElement(this.listaProductos.get(i).getNombre());
        }
    }

    private void llenarComboBoxBodegas() throws GraphException {
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            jComboBox6.addItem(auxBodega.getNombre());
        }

    }

    private void inicializarJTable() {
        String x[][] = {};
        String columns[] = {"Producto", "Monto", "Peso"};
        tableModel = new DefaultTableModel(x, columns);
        jTableProductos.setModel(tableModel);
    }

    private void sumarPrecioYPeso() {
        if (pesoTotal < 30000) {
            for (int i = 0; i < listaProductos.size(); i++) {
                ProductoMayorista auxProducto = (ProductoMayorista) listaProductos.get(i);
                if (auxProducto.getNombre().equals(jListProductos.getSelectedValue())) {

                    pesoTotal += auxProducto.getPesoTotal();
                    precioTotal += auxProducto.getPrecioTotal();
                    break;
                }
            }
        }
        jLabelPesoTotal.setText(String.valueOf(pesoTotal));
        jLabelMontoTotal.setText(String.valueOf(precioTotal));
    }

    private void restarPrecioYPeso() {
        jLabelPesoTotal.setText(String.valueOf(pesoTotal));
        jLabelMontoTotal.setText(String.valueOf(precioTotal));
    }

    public void browser() throws GraphException {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        jPanel14.setLayout(new BorderLayout());
        jPanel14.add(view, BorderLayout.CENTER);
        String latitud = "";
        String longitud = "";
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            if (jComboBox6.getSelectedItem().equals(auxBodega.getNombre())) {
                System.out.println(jComboBox6.getSelectedItem());
                latitud = auxBodega.getLatitud();
                longitud = auxBodega.getLongitud();

            }
        }
        String url = "http://" + "maps.google.es/?q=loc:" + latitud + "%20" + longitud;
        System.out.println(url);
        browser.loadURL(url);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListProductos = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelMontoTotal = new javax.swing.JLabel();
        jLabelPesoTotal = new javax.swing.JLabel();
        jLabelCategoria = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        fondo1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        fondo2 = new javax.swing.JLabel();
        fondo3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Producto", "Monto", "Peso"
            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 490, 190));

        jScrollPane2.setViewportView(jListProductos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 310, 190));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Categoría del vehículo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, -1, -1));
        jPanel1.add(jProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 530, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Peso total:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Monto total:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, -1, -1));

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, -1, -1));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, -1, -1));

        jLabelMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabelMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 160, 20));

        jLabelPesoTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabelPesoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 160, 20));

        jLabelCategoria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 180, 20));

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 100, -1));

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel1.add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Página 1", jPanel1);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Seleccione la bodega:");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jComboBox6.setBackground(new java.awt.Color(0, 51, 51));
        jComboBox6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 30));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 710, 450));

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Confirmar");
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jButton4.setBackground(new java.awt.Color(0, 51, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 90, 30));

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel5.add(fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Página 2", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        getContentPane().add(fondo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        String elemento = jListProductos.getSelectedValue();
        this.logica.agergarProducto(elemento, tableModel, contTable, listaProductos, pesoTotal, precioTotal);
        this.jProgressBar.setMaximum(1000);
        for (int i = 0; i < listaProductos.size(); i++) {
            ProductoMayorista auxProducto = (ProductoMayorista) listaProductos.get(i);
            if (auxProducto.getNombre().equals(jListProductos.getSelectedValue())) {
                if (pesoTotal < 1000) {
                    this.progreso += auxProducto.getPesoTotal();
                    this.jProgressBar.setValue(progreso);
                } else if (pesoTotal >= 1000 && pesoTotal < 5000) {
                    this.jProgressBar.setMaximum(5000);
                    this.progreso += auxProducto.getPesoTotal();
                    this.jProgressBar.setValue(progreso);
                } else if (pesoTotal >= 5000 && pesoTotal < 10000) {
                    this.jProgressBar.setMaximum(10000);
                    this.progreso += auxProducto.getPesoTotal();
                    this.jProgressBar.setValue(progreso);
                } else if (pesoTotal >= 10000 && pesoTotal < 30000) {
                    this.jProgressBar.setMaximum(30000);
                    this.progreso += auxProducto.getPesoTotal();
                    this.jProgressBar.setValue(progreso);
                } else if (pesoTotal == 30000) {
                    JOptionPane.showMessageDialog(null, "Ha llenado el vehículo más grande al máximo");

                }

            }
        }
        sumarPrecioYPeso();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        System.out.println(tableModel.getValueAt(jTableProductos.getSelectedRow(), 2));
        if (jTableProductos.getSelectedRow() != -1) {
            if (pesoTotal > 0) {
                pesoTotal -= (Integer) tableModel.getValueAt(jTableProductos.getSelectedRow(), 2);
                precioTotal -= (Double) tableModel.getValueAt(jTableProductos.getSelectedRow(), 1);
                if(pesoTotal >= 10000 && pesoTotal < 30000){
                jProgressBar.setMaximum(30000);
                }
                if(pesoTotal >= 5000 && pesoTotal < 10000){
                jProgressBar.setMaximum(10000);
                }
                if(pesoTotal >= 1000 && pesoTotal < 5000){
                jProgressBar.setMaximum(5000);
                }
                if(pesoTotal < 1000 ){
                jProgressBar.setMaximum(1000);
                }
                this.progreso -= (Integer) tableModel.getValueAt(jTableProductos.getSelectedRow(), 2);
                this.jProgressBar.setValue(progreso);
                tableModel.removeRow(jTableProductos.getSelectedRow());
                restarPrecioYPeso();
            }
        }

//        restarPrecioYPeso();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.progreso += 10;
        this.jProgressBar.setValue(progreso);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // TODO add your handling code here:
        int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            LinkedList<Usuario> listaUsuarios = this.data.getListaUsuarios();
            System.exit(0);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        try {
            // TODO add your handling code here:
            browser();

        } catch (GraphException ex) {
            Logger.getLogger(Modulo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Modulo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Modulo1().setVisible(true);
                } catch (IOException | GraphException ex) {
                    Logger.getLogger(Modulo1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Modulo1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JLabel fondo3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelMontoTotal;
    private javax.swing.JLabel jLabelPesoTotal;
    private javax.swing.JList<String> jListProductos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableProductos;
    // End of variables declaration//GEN-END:variables
}
