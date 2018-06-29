/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Bodega;
import Domain.Categoria;
import Domain.Lote;
import Domain.OrdenDistribucion;
import Domain.ProductoMayorista;
import Domain.Usuario;
import Logic.Cruds;
import Logic.Datos;
import Logic.Logica;
import TDA.BinaryTree.TreeException;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.sun.org.apache.xml.internal.serialize.HTMLdtd;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author jeison
 */
public class Modulo2 extends javax.swing.JFrame implements Serializable {

    //Clases
    private Datos data;
    private Logica logica;
    private Cruds cruds;

    //TDA's
    private TreeMap<Integer, Lote> treeMapLote;
    private HashMap<String, Categoria> hashMapCategoria;
    private LinkedList<ProductoMayorista> listaProductos;
    private LinkedList<Usuario> listaUsuarios;
    private AdjacencyMatrixGraph grafoBodegas;
    private static LinkedList<OrdenDistribucion> listaOrdenes;

    //TAble Model
    private DefaultTableModel tableModelProductosReporte;
    private DefaultTableModel tableModelBodegasReporte;
    private DefaultTableModel tableModelHistorial;
    private int contTableHistorial;
    private int contTableBodegaReporte;
    private int contTableProductoReporte;

    //----
    private int contFilasTablaHistorial;
    
    //Filtro tabla
    private TableRowSorter trs;

    /**constructor
     * 
     * @throws IOException io
     * @throws GraphException grafo
     * @throws FileNotFoundException archivo
     * @throws ClassNotFoundException clase
     * @throws TreeException arbol
     */
    public Modulo2() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException, TreeException {
        initComponents();
        this.setLocationRelativeTo(null);

        this.jXDatePickerDesdeHistorial.setDate(null);
        this.jXDatePickerHastaHistorial.setDate(null);

        //Clases
        this.data = new Datos();
        this.logica = new Logica();
        this.cruds = new Cruds();

        //TDA's
        this.treeMapLote = this.data.getTreeMapLote();
        this.listaProductos = this.data.getListaProductos();
        this.treeMapLote = this.data.getTreeMapLote();
        this.listaUsuarios = this.data.getListaUsuarios();
        this.hashMapCategoria = this.data.getHashMapCategoria();
        this.grafoBodegas = this.data.getGrafoBodegas();
        this.listaOrdenes = this.data.getListaOrdenes();

        //TAbleModel
        this.tableModelProductosReporte = new DefaultTableModel();
        this.tableModelBodegasReporte = new DefaultTableModel();
        this.tableModelHistorial = new DefaultTableModel();
        this.contTableHistorial = 0;
        this.contTableBodegaReporte = 0;
        this.contTableProductoReporte = 0;

        //Historial
        inicializarJTableHistorial();

        //Reporte
        llenarComboLotesReporte();
        inicializarJTableBodega();
        inicializarJTableProductoReporte();
        dualAxis();

//        llenarComboLotesM2P2();
//        llenarComboCategoriasM2();
//        this.logica.agregarTodosProductos(tableModelHistorial, contTable, listaProductos);
    }

    /**
     * Inicializa el JTable del historial.
     */
    private void inicializarJTableHistorial() {
        String x[][] = {};
        String columns[] = {"Bodega", "Producto", "Categoria", "Lote", "Operador"};
        tableModelHistorial = new DefaultTableModel(x, columns);
        jTableHistorial.setModel(tableModelHistorial);
    }

    /**
     * Inicializa el JTable de la bodega.
     */
    private void inicializarJTableBodega() {
        String x[][] = {};
        String columns[] = {"Bodega"};
        tableModelBodegasReporte = new DefaultTableModel(x, columns);
        jTableBodegaReporte.setModel(tableModelBodegasReporte);
    }

    /**
     * Inicializa el JTable del reporte de productos.
     */
    private void inicializarJTableProductoReporte() {
        String x[][] = {};
        String columns[] = {"Id", "Nombre", "Unidad de medida", "Valor Unidad", "Categoria", "Precio total", "Peso total"};
        tableModelProductosReporte = new DefaultTableModel(x, columns);
        jTableProductosReporte.setModel(tableModelProductosReporte);
    }

    /**
     * Llena el comboBox de lotes.
     */
    private void llenarComboLotesReporte() {
        jComboBoxIdLoteReporte.removeAllItems();
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            jComboBoxIdLoteReporte.addItem(String.valueOf(entry.getValue().getId()));

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

        fondo3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductosReporte = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableBodegaReporte = new javax.swing.JTable();
        jButtonBuscarReporte = new javax.swing.JButton();
        jComboBoxIdLoteReporte = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButtonSeleccionarBodega = new javax.swing.JButton();
        fondo1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHistorial = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtonBuscrHistorial = new javax.swing.JButton();
        jXDatePickerDesdeHistorial = new org.jdesktop.swingx.JXDatePicker();
        jXDatePickerHastaHistorial = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldFiltroCategoria = new javax.swing.JTextField();
        jTextFieldFiltroLote = new javax.swing.JTextField();
        jTextFieldFiltroOperador = new javax.swing.JTextField();
        jTextFieldFiltroProducto = new javax.swing.JTextField();
        jTextFieldFiltroBodega = new javax.swing.JTextField();
        fondo4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fondo5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableProductosReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                " Id", "Nombre", "Unidad Medida", "Valor Unidad", "Categoria", "Precio Total", "Peso Total"
            }
        ));
        jScrollPane1.setViewportView(jTableProductosReporte);
        if (jTableProductosReporte.getColumnModel().getColumnCount() > 0) {
            jTableProductosReporte.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 660, 210));

        jTableBodegaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Bodega"
            }
        ));
        jScrollPane2.setViewportView(jTableBodegaReporte);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 230, 210));

        jButtonBuscarReporte.setBackground(new java.awt.Color(0, 51, 51));
        jButtonBuscarReporte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonBuscarReporte.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBuscarReporte.setText("Buscar");
        jButtonBuscarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarReporteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBuscarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        jComboBoxIdLoteReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIdLoteReporteActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxIdLoteReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 180, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Indique el Id de lote :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jButtonSeleccionarBodega.setBackground(new java.awt.Color(0, 51, 51));
        jButtonSeleccionarBodega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonSeleccionarBodega.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSeleccionarBodega.setText("Seleccionar");
        jButtonSeleccionarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarBodegaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSeleccionarBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 110, -1));

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel1.add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 960, 540));

        jTabbedPane1.addTab("Reporte de Lotes Anulados", jPanel1);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Bodega", "Producto", "Fecha", "Title 4", "Title 5"
            }
        ));
        jScrollPane3.setViewportView(jTableHistorial);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 900, 220));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Desde:");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jButtonBuscrHistorial.setText("Buscar");
        jButtonBuscrHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscrHistorialActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonBuscrHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 70, -1));
        jPanel6.add(jXDatePickerDesdeHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 140, -1));
        jPanel6.add(jXDatePickerHastaHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 140, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Hasta:");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jTextFieldFiltroCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFiltroCategoriaKeyTyped(evt);
            }
        });
        jPanel6.add(jTextFieldFiltroCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 140, -1));

        jTextFieldFiltroLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFiltroLoteKeyTyped(evt);
            }
        });
        jPanel6.add(jTextFieldFiltroLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 140, -1));

        jTextFieldFiltroOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFiltroOperadorKeyTyped(evt);
            }
        });
        jPanel6.add(jTextFieldFiltroOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, 140, -1));

        jTextFieldFiltroProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFiltroProductoKeyTyped(evt);
            }
        });
        jPanel6.add(jTextFieldFiltroProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 140, -1));

        jTextFieldFiltroBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFiltroBodegaKeyTyped(evt);
            }
        });
        jPanel6.add(jTextFieldFiltroBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 140, -1));

        fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel6.add(fondo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Historial de productos", jPanel6);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 670, 420));

        fondo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel7.add(fondo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Detalle", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 960, 530));

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 130, -1));

        jButton2.setBackground(new java.awt.Color(0, 51, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Mantenimiento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setVisible(true);
            dispose();
        } catch (IOException | GraphException | ClassNotFoundException | TreeException ex) {
            Logger.getLogger(Modulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

//        System.out.println(jComboBox5.getSelectedItem());

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            try {
                this.cruds.guardarEnArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.exit(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonBuscarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarReporteActionPerformed

        tableModelBodegasReporte.setRowCount(0);
        tableModelProductosReporte.setRowCount(0);

        Lote lote = this.logica.getLotePorIdLote(Integer.parseInt(jComboBoxIdLoteReporte.getSelectedItem().toString()));
        HashMap<Integer, LinkedList<ProductoMayorista>> hashMap = this.logica.getMapaBodegas(lote.getId());

        if (hashMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Este lote no está en ninguna bodega");
        } else {
            for (Map.Entry<Integer, LinkedList<ProductoMayorista>> entry : hashMap.entrySet()) {
                try {
                    Bodega bodega = this.logica.getBodegaPorIdBodega(entry.getKey());
                    this.logica.agregarBodega(bodega, tableModelBodegasReporte, contTableBodegaReporte);
                } catch (GraphException ex) {
                    Logger.getLogger(Modulo2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_jButtonBuscarReporteActionPerformed

    private void jComboBoxIdLoteReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIdLoteReporteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBoxIdLoteReporteActionPerformed

    private void jButtonBuscrHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscrHistorialActionPerformed
        for (int i = 0; i < listaOrdenes.size(); i++) {
            LinkedList<ProductoMayorista> productos = listaOrdenes.get(i).getListaProductos();
            for (int j = 0; j < productos.size(); j++) {
                Lote lote = this.logica.getLotePorIdLote(productos.get(j).getIdLote());
                if (lote.getFechaEmpacado().after(jXDatePickerDesdeHistorial.getDate())
                        && lote.getFechaVecimiento().before(jXDatePickerHastaHistorial.getDate())) {
                    try {
                        this.logica.agregarTablaHistorial(listaOrdenes.get(i), productos.get(j), tableModelHistorial, contTableHistorial);
                    } catch (GraphException ex) {
                        Logger.getLogger(Modulo2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }//GEN-LAST:event_jButtonBuscrHistorialActionPerformed

    private void jButtonSeleccionarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarBodegaActionPerformed
        try {
            tableModelProductosReporte.setRowCount(0);

            Lote lote = this.logica.getLotePorIdLote(Integer.parseInt(jComboBoxIdLoteReporte.getSelectedItem().toString()));
            HashMap<Integer, LinkedList<ProductoMayorista>> hashMap = this.logica.getMapaBodegas(lote.getId());
            String bodegaSeleccionada = tableModelBodegasReporte.getValueAt(jTableBodegaReporte.getSelectedRow(), 0).toString();
            int idBodegaSeleccionada = this.logica.getIdBodega(bodegaSeleccionada);

            Date date = new Date();
            if (lote.getFechaVecimiento().before(date)) {

                for (Map.Entry<Integer, LinkedList<ProductoMayorista>> entry : hashMap.entrySet()) {

                    if (entry.getKey() == idBodegaSeleccionada) {
                        for (int i = 0; i < entry.getValue().size(); i++) {
                            this.logica.agregarProductos(entry.getValue().get(i), tableModelProductosReporte, contTableProductoReporte);
                        }
                    }
                }
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String expirationDate = dateFormat.format(lote.getFechaVecimiento());
                JOptionPane.showMessageDialog(null,
                        "La fecha de vencimiento del lote es: " + expirationDate
                        + "\nLos productos asociados a este lote actualmente se encuentran vencidos");

            } else {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String expirationDate = dateFormat.format(lote.getFechaVecimiento());
                int salida = JOptionPane.showConfirmDialog(null,
                        "La fecha de vencimiento del lote es: " + expirationDate
                        + "\nLos productos asociados a este lote actualmente no se encuentran vencidos"
                        + "\n¿Desea visualizarlos?", "Confirmar salida",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (salida == 0) {
                    for (Map.Entry<Integer, LinkedList<ProductoMayorista>> entry : hashMap.entrySet()) {

                        if (entry.getKey() == idBodegaSeleccionada) {
                            for (int i = 0; i < entry.getValue().size(); i++) {
                                this.logica.agregarProductos(entry.getValue().get(i), tableModelProductosReporte, contTableProductoReporte);
                            }
                        }
                    }
                }
            }

        } catch (GraphException ex) {
            Logger.getLogger(Modulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSeleccionarBodegaActionPerformed

    private void jTextFieldFiltroCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltroCategoriaKeyTyped
        jTextFieldFiltroCategoria.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextFieldFiltroCategoria.getText(), 2));
                //El "(?i)" es para que funcione con mayusculas y minusculas
            }

        });

        trs = new TableRowSorter(tableModelHistorial);
        jTableHistorial.setRowSorter(trs);
    }//GEN-LAST:event_jTextFieldFiltroCategoriaKeyTyped

    private void jTextFieldFiltroLoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltroLoteKeyTyped
        jTextFieldFiltroLote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextFieldFiltroLote.getText(), 3));
                //El "(?i)" es para que funcione con mayusculas y minusculas
            }

        });

        trs = new TableRowSorter(tableModelHistorial);
        jTableHistorial.setRowSorter(trs);
    }//GEN-LAST:event_jTextFieldFiltroLoteKeyTyped

    private void jTextFieldFiltroOperadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltroOperadorKeyTyped
        jTextFieldFiltroOperador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextFieldFiltroOperador.getText(), 4));
                //El "(?i)" es para que funcione con mayusculas y minusculas
            }

        });

        trs = new TableRowSorter(tableModelHistorial);
        jTableHistorial.setRowSorter(trs);
    }//GEN-LAST:event_jTextFieldFiltroOperadorKeyTyped

    private void jTextFieldFiltroProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltroProductoKeyTyped
        jTextFieldFiltroBodega.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextFieldFiltroBodega.getText(), 0));
                //El "(?i)" es para que funcione con mayusculas y minusculas
            }

        });

        trs = new TableRowSorter(tableModelHistorial);
        jTableHistorial.setRowSorter(trs);
    }//GEN-LAST:event_jTextFieldFiltroProductoKeyTyped

    private void jTextFieldFiltroBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFiltroBodegaKeyTyped
        jTextFieldFiltroProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextFieldFiltroProducto.getText(), 1));
                //El "(?i)" es para que funcione con mayusculas y minusculas
            }

        });

        trs = new TableRowSorter(tableModelHistorial);
        jTableHistorial.setRowSorter(trs);
    }//GEN-LAST:event_jTextFieldFiltroBodegaKeyTyped
//--------------------------------CHART--------------------------------------------------------------------

    public void dualAxis() throws GraphException, IOException, FileNotFoundException, ClassNotFoundException, TreeException {
        Grafico demo = new Grafico("Reporte de lotes por Bodega", jPanel2);
        demo.pack();
    }

    //----------------------------------------------------CHART------------------------------------------------
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
            java.util.logging.Logger.getLogger(Modulo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modulo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modulo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modulo2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Modulo2().setVisible(true);
                } catch (IOException | GraphException | ClassNotFoundException ex) {
                    Logger.getLogger(Modulo2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TreeException ex) {
                    Logger.getLogger(Modulo2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo3;
    private javax.swing.JLabel fondo4;
    private javax.swing.JLabel fondo5;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBuscarReporte;
    private javax.swing.JButton jButtonBuscrHistorial;
    private javax.swing.JButton jButtonSeleccionarBodega;
    private javax.swing.JComboBox<String> jComboBoxIdLoteReporte;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableBodegaReporte;
    private javax.swing.JTable jTableHistorial;
    private javax.swing.JTable jTableProductosReporte;
    private javax.swing.JTextField jTextFieldFiltroBodega;
    private javax.swing.JTextField jTextFieldFiltroCategoria;
    private javax.swing.JTextField jTextFieldFiltroLote;
    private javax.swing.JTextField jTextFieldFiltroOperador;
    private javax.swing.JTextField jTextFieldFiltroProducto;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerDesdeHistorial;
    private org.jdesktop.swingx.JXDatePicker jXDatePickerHastaHistorial;
    // End of variables declaration//GEN-END:variables
}
