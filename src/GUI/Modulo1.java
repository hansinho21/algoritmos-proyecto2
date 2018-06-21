/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Bodega;
import Domain.ProductoMayorista;
import Domain.Usuario;
import Logic.Data;
import Logic.Files;
import Logic.Logica;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.LinkedList;
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
    private Data data;
    private Logica logica;

    //TDA's
    private LinkedList<ProductoMayorista> listaProductos;
    private AdjacencyMatrixGraph grafoBodegas;

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
    public Modulo1() throws IOException, GraphException {
        initComponents();
        this.setLocationRelativeTo(null);

        //Clases
        this.data = new Data();
        this.logica = new Logica();

        //TDA's
        this.listaProductos = this.data.getListaProductos();
        this.grafoBodegas = this.data.getGrafoBodegas();

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
        browser();//tengo que corregir
    }

    private void inicializarJTable() {
        String x[][] = {};
        String columns[] = {"Producto", "Monto", "Peso"};
        tableModel = new DefaultTableModel(x, columns);
        jTableProductos.setModel(tableModel);
    }

    private void calcularPrecioYPeso() {
        for (int i = 0; i < listaProductos.size(); i++) {
            ProductoMayorista auxProducto = (ProductoMayorista) listaProductos.get(i);
            if (auxProducto.getNombre().equals(jListProductos.getSelectedValue())) {

                pesoTotal += auxProducto.getPesoTotal();
                precioTotal += auxProducto.getPrecioTotal();
                break;
            }
        }
        jLabelPesoTotal.setText(String.valueOf(pesoTotal));
        jLabelMontoTotal.setText(String.valueOf(precioTotal));
    }

    public void browser() throws GraphException {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        jPanel14.setLayout(new BorderLayout());
        jPanel14.add(view, BorderLayout.CENTER);
        String latitud = "9.7873748";
        String longitud = "-83.849056";
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            System.out.println(jComboBox6.getSelectedItem());
            if (jComboBox6.getSelectedItem().equals(auxBodega.getNombre())) {
                latitud = auxBodega.getLatitud();
                longitud = auxBodega.getLongitud();
            }
        }
       
        String url = "http://maps.google.es/?q=loc:" + latitud + "%20" + longitud;
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 30, 490, 190));

        jScrollPane2.setViewportView(jListProductos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 310, 190));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Categoría del vehículo:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, -1, -1));
        jPanel1.add(jProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 530, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Peso total:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Monto total:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, -1, -1));

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, -1, -1));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, -1, -1));

        jLabelMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabelMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 160, 20));

        jLabelPesoTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabelPesoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 160, 20));

        jLabelCategoria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jLabelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 180, 20));

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
        calcularPrecioYPeso();
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        if (jTableProductos.getSelectedRow() != -1) {
            tableModel.removeRow(jTableProductos.getSelectedRow());
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.progreso += 10;
        this.jProgressBar.setValue(progreso);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                    "Realmente desea salir de la apilcación?", "Confirmar salida",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (salida == 0) {
                Data d = new Data();
                LinkedList<Usuario> listaUsuarios = d.getListaUsuarios();
                Files f = new Files();
                f.ArchivoUsuarios();
                f.ArchivoCategoria();
                f.ArchivoBodega();
                System.exit(0);
            }
            System.exit(0);
        } catch (IOException | GraphException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

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
