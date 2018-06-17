/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Bodega;
import Domain.Categoria;
import Domain.Lote;
import Domain.UnidadTransporte;
import Domain.Usuario;
import Logic.CRUDS;
import Logic.Data;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeison
 */
public class Mantenimiento extends javax.swing.JFrame {

    //instancias
    private Data data;
    private CRUDS cruds;

    //tda's
    private LinkedList<Usuario> listaUsuarios;
    private AdjacencyMatrixGraph grafoBodegas;
    private HashMap<String, Categoria> hashMapCategoria;
    private TreeMap<Integer, Lote> treeMapLote;
    private LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte;

    private TextAutoCompleter textAutoCompleterUsuario;
    private TextAutoCompleter textAutoCompleterBodega;
    private TextAutoCompleter textAutoCompleterCategoria;
    private TextAutoCompleter textAutoCompleterLote;
    private TextAutoCompleter textAutoCompleterUnidadTransorte;
    private TextAutoCompleter textAutoCompleterProducto;

    /**
     * Creates new form Mantenimiento
     */
    public Mantenimiento() throws IOException, GraphException {
        initComponents();
        this.setLocationRelativeTo(null);

        this.jLabelIdBodega2.setVisible(false);
        this.jLabelIdUnidadTransporte2.setVisible(false);

        //instancias
        this.data = new Data();
        this.cruds = new CRUDS();

        //tda's
        this.listaUsuarios = this.data.getListaUsuarios();
        this.grafoBodegas = this.data.getGrafoBodegas();
        this.hashMapCategoria = this.data.getHashMapCategoria();
        this.treeMapLote = this.data.getTreeMapLote();
        this.linkedHashMapUnidadTransporte = this.data.getLinkedHashMapUnidadTransporte();

        //autocopleter
        this.textAutoCompleterBodega = new TextAutoCompleter(jTextFieldNombreBodega);
        this.textAutoCompleterUnidadTransorte = new TextAutoCompleter(jTextFieldPlacaUnidadTransporte);

        //metodos
        llenarAutocompleterBodegas();
        llenarAutocompleterUnidadesTransporte();
    }

    private void limpiarInformacionBodega() {
        jLabelIdBodega.setText("");
        jTextFieldLatitudBodega.setText("");
        jTextFieldLongitudBodega.setText("");
        jTextFieldDistanciaBodega.setText("");
        jTextFieldUrlBodega.setText("");
        jTextFieldNombreBodega.setText("");
        
        this.jLabelIdBodega2.setVisible(false);
    }
    
    private void limpiarInformacionUnidadTransporte(){
        jLabelIdUnidadTransporte.setText("");
        jTextFieldPlacaUnidadTransporte.setText("");
        jTextFieldCapacidadUnidadTransporte.setText("");
        jTextFieldUrlUnidadTransporte.setText("");
        
        this.jLabelIdUnidadTransporte2.setVisible(false);
    }

    private boolean validarInformacionBodega() {
        if (jTextFieldLatitudBodega.getText().equals("")
                || jTextFieldLongitudBodega.getText().equals("")
                || jTextFieldDistanciaBodega.getText().equals("")
                || jTextFieldUrlBodega.getText().equals("")
                || jTextFieldNombreBodega.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarInformacionUnidadTransporte() {
        if (jTextFieldPlacaUnidadTransporte.getText().equals("")
                || jTextFieldCapacidadUnidadTransporte.getText().equals("")
                || jTextFieldUrlUnidadTransporte.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * LLena el AutoCompleter con las Bodegas.
     */
    private void llenarAutocompleterBodegas() throws GraphException {
        textAutoCompleterBodega.removeAllItems();
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            System.out.println(grafoBodegas.getVertex(i).toString());
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            textAutoCompleterBodega.addItem(auxBodega.getNombre());
        }
    }
    
    /**
     *
     * LLena el AutoCompleter con las unidades de transporte.
     */
    private void llenarAutocompleterUnidadesTransporte() throws GraphException {
        textAutoCompleterUnidadTransorte.removeAllItems();
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
            textAutoCompleterUnidadTransorte.addItem(entry.getValue().getPlaca());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNombreUsuario = new javax.swing.JTextField();
        jComboBoxRolUsuario = new javax.swing.JComboBox<>();
        jTextFieldUserUsuario = new javax.swing.JTextField();
        jPasswordFieldPasswordUsuario = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        fondo1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelIdBodega2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldNombreBodega = new javax.swing.JTextField();
        jTextFieldLatitudBodega = new javax.swing.JTextField();
        jTextFieldLongitudBodega = new javax.swing.JTextField();
        jTextFieldDistanciaBodega = new javax.swing.JTextField();
        jLabelIdBodega = new javax.swing.JLabel();
        jButtonAgregarBodega = new javax.swing.JButton();
        jButtonEditarBodega = new javax.swing.JButton();
        jButtonEliminarBodega = new javax.swing.JButton();
        jButtonLimpiarBodega = new javax.swing.JButton();
        jTextFieldUrlBodega = new javax.swing.JTextField();
        fondo2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        fondo6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        fondo5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabelIdUnidadTransporte2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelIdUnidadTransporte = new javax.swing.JLabel();
        jTextFieldPlacaUnidadTransporte = new javax.swing.JTextField();
        jTextFieldCapacidadUnidadTransporte = new javax.swing.JTextField();
        jTextFieldUrlUnidadTransporte = new javax.swing.JTextField();
        jButtonAgregarUnidadTransporte = new javax.swing.JButton();
        jButtonEditarUnidadTransporte = new javax.swing.JButton();
        jButtonEliminarUnidadTransporte = new javax.swing.JButton();
        jButtonLimpiarUnidadTransporte = new javax.swing.JButton();
        fondo4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        fondo7 = new javax.swing.JLabel();
        fondo3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Rol:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Usuario:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        jTextFieldNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jTextFieldNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 220, 30));

        jComboBoxRolUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxRolUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operador", "Administrador" }));
        jPanel1.add(jComboBoxRolUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 220, 30));

        jTextFieldUserUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jTextFieldUserUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 220, 30));

        jPasswordFieldPasswordUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jPasswordFieldPasswordUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 220, 30));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, -1, -1));

        jButton2.setText("Eliminar");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, -1));

        Modificar.setText("Modificar");
        jPanel1.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, -1, -1));

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel1.add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Usuario", jPanel1);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIdBodega2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdBodega2.setText("ID:");
        jPanel5.add(jLabelIdBodega2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Nombre:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Latitud:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Distancia:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Longitud:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("URL fotografía:");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, -1, -1));

        jTextFieldNombreBodega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldNombreBodega.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreBodegaFocusGained(evt);
            }
        });
        jPanel5.add(jTextFieldNombreBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 150, 30));

        jTextFieldLatitudBodega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel5.add(jTextFieldLatitudBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 150, 30));

        jTextFieldLongitudBodega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel5.add(jTextFieldLongitudBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 150, 30));

        jTextFieldDistanciaBodega.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel5.add(jTextFieldDistanciaBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 150, 30));
        jPanel5.add(jLabelIdBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 140, 30));

        jButtonAgregarBodega.setText("Agregar");
        jButtonAgregarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarBodegaActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonAgregarBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, -1, -1));

        jButtonEditarBodega.setText("Editar");
        jButtonEditarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarBodegaActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonEditarBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, -1, -1));

        jButtonEliminarBodega.setText("Eliminar");
        jButtonEliminarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarBodegaActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonEliminarBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, -1, -1));

        jButtonLimpiarBodega.setText("Limpiar");
        jButtonLimpiarBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarBodegaActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonLimpiarBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, -1, -1));
        jPanel5.add(jTextFieldUrlBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 130, -1));

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel5.add(fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Bodega", jPanel5);

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel8.add(fondo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Categoria", jPanel8);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel7.add(fondo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Lote", jPanel7);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIdUnidadTransporte2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdUnidadTransporte2.setText("ID:");
        jPanel6.add(jLabelIdUnidadTransporte2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Placa:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Capacidad:");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("URL fotografia:");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));
        jPanel6.add(jLabelIdUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 100, 30));

        jTextFieldPlacaUnidadTransporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldPlacaUnidadTransporte.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPlacaUnidadTransporteFocusGained(evt);
            }
        });
        jPanel6.add(jTextFieldPlacaUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 120, 30));

        jTextFieldCapacidadUnidadTransporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel6.add(jTextFieldCapacidadUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 120, 30));
        jPanel6.add(jTextFieldUrlUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 110, 30));

        jButtonAgregarUnidadTransporte.setText("Agregar");
        jButtonAgregarUnidadTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarUnidadTransporteActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonAgregarUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, -1));

        jButtonEditarUnidadTransporte.setText("Editar");
        jButtonEditarUnidadTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarUnidadTransporteActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonEditarUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, -1, -1));

        jButtonEliminarUnidadTransporte.setText("Eliminar");
        jButtonEliminarUnidadTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarUnidadTransporteActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonEliminarUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, -1, -1));

        jButtonLimpiarUnidadTransporte.setText("Limpiar");
        jButtonLimpiarUnidadTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarUnidadTransporteActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonLimpiarUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel6.add(fondo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Unidad de Transporte", jPanel6);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel9.add(fondo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Producto", jPanel9);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        getContentPane().add(fondo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * metodo para agregar un usuario a la lista
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Usuario usuario = new Usuario();
        //agregar id autoincremental
        usuario.setId(2);
        usuario.setNombre(jTextFieldNombreUsuario.getText());
        usuario.setRol((String) jComboBoxRolUsuario.getSelectedItem());
        usuario.setUsuario(jTextFieldUserUsuario.getText());
        usuario.setContrasena(jPasswordFieldPasswordUsuario.getText());
        cruds.AgregarUsuario(usuario);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldNombreBodegaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreBodegaFocusGained
        try {
            for (int i = 0; i < grafoBodegas.getSize(); i++) {
                Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
                if (jTextFieldNombreBodega.getText().equals(auxBodega.getNombre())) {
                    jLabelIdBodega.setText(String.valueOf(auxBodega.getId()));
                    jTextFieldLatitudBodega.setText(auxBodega.getLatitud());
                    jTextFieldLongitudBodega.setText(auxBodega.getLongitud());
                    jTextFieldDistanciaBodega.setText(String.valueOf(auxBodega.getDistanciaCentroOperaciones()));
                    jTextFieldUrlBodega.setText(auxBodega.getUrlFotografia());
                    
                    jLabelIdBodega.setVisible(true);
                }
            }
        } catch (GraphException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldNombreBodegaFocusGained

    private void jButtonAgregarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarBodegaActionPerformed
        if (validarInformacionBodega() == true) {
            try {
                Bodega auxBodega = new Bodega();
                auxBodega.setId(grafoBodegas.getSize() + 1);
                auxBodega.setNombre(jTextFieldNombreBodega.getText());
                auxBodega.setLatitud(jTextFieldLatitudBodega.getText());
                auxBodega.setLongitud(jTextFieldLongitudBodega.getText());
                auxBodega.setDistanciaCentroOperaciones(Float.parseFloat(jTextFieldDistanciaBodega.getText()));
                auxBodega.setUrlFotografia(jTextFieldUrlBodega.getText());

                cruds.agregarBodega(auxBodega);

                limpiarInformacionBodega();
                llenarAutocompleterBodegas();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAgregarBodegaActionPerformed

    private void jButtonLimpiarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarBodegaActionPerformed
        limpiarInformacionBodega();
    }//GEN-LAST:event_jButtonLimpiarBodegaActionPerformed

    private void jButtonEditarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarBodegaActionPerformed
        if (validarInformacionBodega() == true) {
            try {
                Bodega auxBodega = new Bodega();
                auxBodega.setId(Integer.parseInt(jLabelIdBodega.getText()));
                auxBodega.setNombre(jTextFieldNombreBodega.getText());
                auxBodega.setLatitud(jTextFieldLatitudBodega.getText());
                auxBodega.setLongitud(jTextFieldLongitudBodega.getText());
                auxBodega.setDistanciaCentroOperaciones(Float.parseFloat(jTextFieldDistanciaBodega.getText()));
                auxBodega.setUrlFotografia(jTextFieldUrlBodega.getText());

                cruds.editarBodega(auxBodega);

                limpiarInformacionBodega();
                llenarAutocompleterBodegas();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditarBodegaActionPerformed

    private void jButtonEliminarBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarBodegaActionPerformed
        if (validarInformacionBodega() == true) {
            try {
                Bodega auxBodega = new Bodega();
                auxBodega.setId(Integer.parseInt(jLabelIdBodega.getText()));
                auxBodega.setNombre(jTextFieldNombreBodega.getText());
                auxBodega.setLatitud(jTextFieldLatitudBodega.getText());
                auxBodega.setLongitud(jTextFieldLongitudBodega.getText());
                auxBodega.setDistanciaCentroOperaciones(Float.parseFloat(jTextFieldDistanciaBodega.getText()));
                auxBodega.setUrlFotografia(jTextFieldUrlBodega.getText());

                cruds.eliminarBodega(auxBodega);

                limpiarInformacionBodega();
                llenarAutocompleterBodegas();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarBodegaActionPerformed

    private void jTextFieldPlacaUnidadTransporteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPlacaUnidadTransporteFocusGained
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
            if(jTextFieldPlacaUnidadTransporte.getText().equals(entry.getValue().getPlaca())){
                jLabelIdUnidadTransporte.setText(String.valueOf(entry.getValue().getId()));
                jTextFieldCapacidadUnidadTransporte.setText(entry.getValue().getCapacidad());
                jTextFieldUrlUnidadTransporte.setText(entry.getValue().getUrlFotografia());
                
                jLabelIdUnidadTransporte2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldPlacaUnidadTransporteFocusGained

    private void jButtonAgregarUnidadTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarUnidadTransporteActionPerformed
        if (validarInformacionUnidadTransporte() == true) {
            try {
                UnidadTransporte auxUnidad = new UnidadTransporte();
                auxUnidad.setId(linkedHashMapUnidadTransporte.size()+1);
                auxUnidad.setCapacidad(jTextFieldCapacidadUnidadTransporte.getText());
                auxUnidad.setPlaca(jTextFieldPlacaUnidadTransporte.getText());
                auxUnidad.setUrlFotografia(jTextFieldUrlUnidadTransporte.getText());
                
                cruds.agregarUnidadTransporte(auxUnidad);
                
                limpiarInformacionUnidadTransporte();
                llenarAutocompleterUnidadesTransporte();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAgregarUnidadTransporteActionPerformed

    private void jButtonLimpiarUnidadTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarUnidadTransporteActionPerformed
        limpiarInformacionUnidadTransporte();
    }//GEN-LAST:event_jButtonLimpiarUnidadTransporteActionPerformed

    private void jButtonEditarUnidadTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarUnidadTransporteActionPerformed
        if (validarInformacionUnidadTransporte() == true) {
            try {
                UnidadTransporte auxUnidad = new UnidadTransporte();
                auxUnidad.setId(Integer.parseInt(jLabelIdUnidadTransporte.getText()));
                auxUnidad.setCapacidad(jTextFieldCapacidadUnidadTransporte.getText());
                auxUnidad.setPlaca(jTextFieldPlacaUnidadTransporte.getText());
                auxUnidad.setUrlFotografia(jTextFieldUrlUnidadTransporte.getText());
                
                cruds.editarUnidadTransporte(auxUnidad);
                
                limpiarInformacionUnidadTransporte();
                llenarAutocompleterUnidadesTransporte();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditarUnidadTransporteActionPerformed

    private void jButtonEliminarUnidadTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarUnidadTransporteActionPerformed
        if (validarInformacionUnidadTransporte() == true) {
            try {
                UnidadTransporte auxUnidad = new UnidadTransporte();
                auxUnidad.setId(Integer.parseInt(jLabelIdUnidadTransporte.getText()));
                auxUnidad.setCapacidad(jTextFieldCapacidadUnidadTransporte.getText());
                auxUnidad.setPlaca(jTextFieldPlacaUnidadTransporte.getText());
                auxUnidad.setUrlFotografia(jTextFieldUrlUnidadTransporte.getText());
                
                cruds.eliminarUnidadTransporte(auxUnidad);
                
                limpiarInformacionUnidadTransporte();
                llenarAutocompleterUnidadesTransporte();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarUnidadTransporteActionPerformed

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
            java.util.logging.Logger.getLogger(Mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Mantenimiento().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                } catch (GraphException ex) {
                    Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Modificar;
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JLabel fondo3;
    private javax.swing.JLabel fondo4;
    private javax.swing.JLabel fondo5;
    private javax.swing.JLabel fondo6;
    private javax.swing.JLabel fondo7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAgregarBodega;
    private javax.swing.JButton jButtonAgregarUnidadTransporte;
    private javax.swing.JButton jButtonEditarBodega;
    private javax.swing.JButton jButtonEditarUnidadTransporte;
    private javax.swing.JButton jButtonEliminarBodega;
    private javax.swing.JButton jButtonEliminarUnidadTransporte;
    private javax.swing.JButton jButtonLimpiarBodega;
    private javax.swing.JButton jButtonLimpiarUnidadTransporte;
    private javax.swing.JComboBox<String> jComboBoxRolUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIdBodega;
    private javax.swing.JLabel jLabelIdBodega2;
    private javax.swing.JLabel jLabelIdUnidadTransporte;
    private javax.swing.JLabel jLabelIdUnidadTransporte2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordFieldPasswordUsuario;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldCapacidadUnidadTransporte;
    private javax.swing.JTextField jTextFieldDistanciaBodega;
    private javax.swing.JTextField jTextFieldLatitudBodega;
    private javax.swing.JTextField jTextFieldLongitudBodega;
    private javax.swing.JTextField jTextFieldNombreBodega;
    private javax.swing.JTextField jTextFieldNombreUsuario;
    private javax.swing.JTextField jTextFieldPlacaUnidadTransporte;
    private javax.swing.JTextField jTextFieldUrlBodega;
    private javax.swing.JTextField jTextFieldUrlUnidadTransporte;
    private javax.swing.JTextField jTextFieldUserUsuario;
    // End of variables declaration//GEN-END:variables
}
