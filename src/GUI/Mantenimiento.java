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
import Logic.Cruds;
import Logic.Data;
import Logic.Files;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.ComponentOrientation;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jeison
 */
public class Mantenimiento extends javax.swing.JFrame {

    //instancias
    private Data data;
    private Cruds cruds;

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
        this.jLabelIdCategoria2.setVisible(false);
        this.jLabelIdLote2.setVisible(false);
        this.jLabelIdUnidadTransporte2.setVisible(false);

        //instancias
        this.data = new Data();
        this.cruds = new Cruds();

        //tda's
        this.listaUsuarios = this.data.getListaUsuarios();
        this.grafoBodegas = this.data.getGrafoBodegas();
        this.hashMapCategoria = this.data.getHashMapCategoria();
        this.treeMapLote = this.data.getTreeMapLote();
        this.linkedHashMapUnidadTransporte = this.data.getLinkedHashMapUnidadTransporte();

        //autocopleter
        this.textAutoCompleterUsuario = new TextAutoCompleter(jTextFieldNombreUsuario);
        this.textAutoCompleterBodega = new TextAutoCompleter(jTextFieldNombreBodega);
        this.textAutoCompleterCategoria = new TextAutoCompleter(jTextFieldNombreCategoria);
        this.textAutoCompleterLote = new TextAutoCompleter(jTextFieldCodigoLote);
        this.textAutoCompleterUnidadTransorte = new TextAutoCompleter(jTextFieldPlacaUnidadTransporte);

        //llenar autocompleters
        llenarAutocompleterBodegas();
        llenarAutocompleterCategoria();
        llenarAutocompleterUnidadesTransporte();
        llenarAutocompleterLote();
        llenarAutocompleterUsuarios();
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
    
    private void limpiarInformacionCategoria(){
        jLabelIdCategoria.setText("");
        jTextFieldNombreCategoria.setText("");
        jTextFieldDescripcionCategoria.setText("");
        
        jLabelIdCategoria2.setVisible(false);
    }
    
    private void limpiarInformacionUsuario(){
        jLabelIdUsuario.setText("");
        jTextFieldNombreUsuario.setText("");
        jTextFieldUserUsuario.setText("");
        jPasswordFieldPasswordUsuario.setText("");
    }
    
    private void limpiarInformacionLote(){
        jLabelIdLote.setText("");
        jTextFieldCodigoLote.setText("");
        jDateChooser1.setDateFormatString("");
        jDateChooser2.setDateFormatString("");
        jLabelIdLote2.setVisible(false);
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
    
    private boolean validarInformacionCategoria(){
        if (jTextFieldNombreCategoria.getText().equals("")
                || jTextFieldDescripcionCategoria.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean validarInformacionLote() {
        if (jTextFieldCodigoLote.getText().equals("")
                || jDateChooser1.getDate().equals("")
                || jDateChooser2.getDate().equals("")) {
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
    
    private void llenarAutocompleterUsuarios(){
        textAutoCompleterUsuario.removeAllItems();
        for (int i = 0; i < listaUsuarios.size(); i++) {        
            System.out.println(listaUsuarios.get(i).toString());
            Usuario usuario= listaUsuarios.get(i);
            textAutoCompleterUsuario.addItem(usuario.getNombre());
        }
    }
    
    /**
     *
     * LLena el AutoCompleter con las categorias.
     */
    private void llenarAutocompleterCategoria() throws GraphException {
        textAutoCompleterCategoria.removeAllItems();
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
            textAutoCompleterCategoria.addItem(entry.getValue().getNombre());
        }
    }
    
    /**
     *
     * LLena el AutoCompleter con los lotes.
     */
    private void llenarAutocompleterLote() throws GraphException {
        textAutoCompleterLote.removeAllItems();
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
            textAutoCompleterLote.addItem(entry.getValue().getCodigoLote());
            textAutoCompleterLote.addItem(entry.getValue().getFechaEmpacado());
            textAutoCompleterLote.addItem(entry.getValue().getFechaVecimiento());
            
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
        jLabelIdUsuario = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButtonLimpiarUsuario = new javax.swing.JButton();
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
        jLabel20 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        fondo2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabelIdCategoria2 = new javax.swing.JLabel();
        jLabelIdCategoria = new javax.swing.JLabel();
        jTextFieldDescripcionCategoria = new javax.swing.JTextField();
        jTextFieldNombreCategoria = new javax.swing.JTextField();
        jButtonAgregarCategoria = new javax.swing.JButton();
        jButtonEditarCategoria = new javax.swing.JButton();
        jButtonEliminarCategoria = new javax.swing.JButton();
        jButtonLimpiarCategoria = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        fondo6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabelIdLote2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelIdLote = new javax.swing.JLabel();
        jTextFieldCodigoLote = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonAgregarLote = new javax.swing.JButton();
        jButtonEditarLote = new javax.swing.JButton();
        jButtonEliminarLote = new javax.swing.JButton();
        jButtonLimpiarLote = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
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
        jLabel21 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        fondo4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabelIdProducto = new javax.swing.JLabel();
        jLabelNombreProducto = new javax.swing.JLabel();
        jLabelValorUnidad = new javax.swing.JLabel();
        jLabelPesoProducto = new javax.swing.JLabel();
        jLabelDescripcionProducto = new javax.swing.JLabel();
        jLabelIdLoteProducto = new javax.swing.JLabel();
        jLabelIdCategoriaProducto = new javax.swing.JLabel();
        jLabelPrecioProducto = new javax.swing.JLabel();
        jLabelUrlFotoProducto = new javax.swing.JLabel();
        jLabelUnidadMedidasProducto = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        fondo7 = new javax.swing.JLabel();
        fondo3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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
        jTextFieldNombreUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreUsuarioFocusGained(evt);
            }
        });
        jTextFieldNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreUsuarioActionPerformed(evt);
            }
        });
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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, -1, -1));

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        jLabelIdUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelIdUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabelIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 80, 30));

        jButton3.setBackground(new java.awt.Color(0, 51, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 20, 90, 30));

        jButtonLimpiarUsuario.setText("Limpiar");
        jButtonLimpiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLimpiarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, -1, -1));

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel1.add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

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
        jTextFieldNombreBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreBodegaActionPerformed(evt);
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

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("FileChooser en el url");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 210, 30));

        jButton4.setBackground(new java.awt.Color(0, 51, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 20, 90, 30));

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel5.add(fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Bodega", jPanel5);

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Descripción:");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Nombre:");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jLabelIdCategoria2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdCategoria2.setText("ID:");
        jPanel8.add(jLabelIdCategoria2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));
        jPanel8.add(jLabelIdCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 170, 30));
        jPanel8.add(jTextFieldDescripcionCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 140, 30));

        jTextFieldNombreCategoria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreCategoriaFocusGained(evt);
            }
        });
        jPanel8.add(jTextFieldNombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 140, 30));

        jButtonAgregarCategoria.setText("Agregar");
        jButtonAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarCategoriaActionPerformed(evt);
            }
        });
        jPanel8.add(jButtonAgregarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, -1, -1));

        jButtonEditarCategoria.setText("Editar");
        jButtonEditarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarCategoriaActionPerformed(evt);
            }
        });
        jPanel8.add(jButtonEditarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, -1, -1));

        jButtonEliminarCategoria.setText("Eliminar");
        jButtonEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCategoriaActionPerformed(evt);
            }
        });
        jPanel8.add(jButtonEliminarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, -1, -1));

        jButtonLimpiarCategoria.setText("Limpiar");
        jButtonLimpiarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarCategoriaActionPerformed(evt);
            }
        });
        jPanel8.add(jButtonLimpiarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, -1, -1));

        jButton5.setBackground(new java.awt.Color(0, 51, 51));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 20, 90, 30));

        fondo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel8.add(fondo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Categoria", jPanel8);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIdLote2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdLote2.setText("ID:");
        jPanel7.add(jLabelIdLote2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Código:");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Fecha de empacado:");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Fecha de vencimiento:");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, -1));
        jPanel7.add(jLabelIdLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 110, 30));

        jTextFieldCodigoLote.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoLoteFocusGained(evt);
            }
        });
        jPanel7.add(jTextFieldCodigoLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 180, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Hay que hacer bien los date");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 260, 40));

        jButtonAgregarLote.setText("Agregar");
        jButtonAgregarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarLoteActionPerformed(evt);
            }
        });
        jPanel7.add(jButtonAgregarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, -1, -1));

        jButtonEditarLote.setText("Editar");
        jButtonEditarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarLoteActionPerformed(evt);
            }
        });
        jPanel7.add(jButtonEditarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, -1));

        jButtonEliminarLote.setText("Eliminar");
        jButtonEliminarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarLoteActionPerformed(evt);
            }
        });
        jPanel7.add(jButtonEliminarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        jButtonLimpiarLote.setText("Limpiar");
        jButtonLimpiarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarLoteActionPerformed(evt);
            }
        });
        jPanel7.add(jButtonLimpiarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, -1, -1));

        jButton6.setBackground(new java.awt.Color(0, 51, 51));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Salir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 20, 90, 30));
        jPanel7.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 180, -1));
        jPanel7.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 180, -1));

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
        jPanel6.add(jTextFieldPlacaUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 300, 30));

        jTextFieldCapacidadUnidadTransporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel6.add(jTextFieldCapacidadUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 300, 30));
        jPanel6.add(jTextFieldUrlUnidadTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 300, 30));

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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("FileChooser en el url");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 210, 30));

        jButton7.setBackground(new java.awt.Color(0, 51, 51));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Salir");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 20, 90, 30));

        fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel6.add(fondo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Unidad de Transporte", jPanel6);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setBackground(new java.awt.Color(0, 51, 51));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Salir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 90, 30));

        jLabelIdProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelIdProducto.setText("Id");
        jPanel9.add(jLabelIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabelNombreProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNombreProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelNombreProducto.setText("Nombre");
        jPanel9.add(jLabelNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabelValorUnidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelValorUnidad.setForeground(new java.awt.Color(0, 0, 0));
        jLabelValorUnidad.setText("Valor Unidad");
        jPanel9.add(jLabelValorUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));

        jLabelPesoProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelPesoProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPesoProducto.setText("Peso Total");
        jPanel9.add(jLabelPesoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

        jLabelDescripcionProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelDescripcionProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelDescripcionProducto.setText("Descripcion");
        jPanel9.add(jLabelDescripcionProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        jLabelIdLoteProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdLoteProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelIdLoteProducto.setText("Id Lote");
        jPanel9.add(jLabelIdLoteProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        jLabelIdCategoriaProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdCategoriaProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelIdCategoriaProducto.setText("Id Categoria");
        jPanel9.add(jLabelIdCategoriaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        jLabelPrecioProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelPrecioProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelPrecioProducto.setText("Precio total");
        jPanel9.add(jLabelPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1));

        jLabelUrlFotoProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelUrlFotoProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelUrlFotoProducto.setText("URL fotografia");
        jPanel9.add(jLabelUrlFotoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabelUnidadMedidasProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelUnidadMedidasProducto.setForeground(new java.awt.Color(0, 0, 0));
        jLabelUnidadMedidasProducto.setText("Unidad Medidas");
        jPanel9.add(jLabelUnidadMedidasProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jButton9.setBackground(new java.awt.Color(0, 51, 51));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Eliminar");
        jPanel9.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, -1, -1));

        jButton10.setBackground(new java.awt.Color(0, 51, 51));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Agregar");
        jPanel9.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jButton11.setBackground(new java.awt.Color(0, 51, 51));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Limpiar");
        jPanel9.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

        jButton12.setBackground(new java.awt.Color(0, 51, 51));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Editar");
        jPanel9.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, -1, -1));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 160, 30));

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 160, 30));

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 160, 30));

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 160, 30));

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 160, 30));

        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 160, 30));

        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(0, 0, 0));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 160, 30));

        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(0, 0, 0));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 160, 30));

        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, 160, 30));

        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 160, 30));

        fondo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel9.add(fondo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

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
        usuario.setId(listaUsuarios.size()+1);
        usuario.setNombre(jTextFieldNombreUsuario.getText());
        usuario.setRol((String) jComboBoxRolUsuario.getSelectedItem());
        usuario.setUsuario(jTextFieldUserUsuario.getText());
        usuario.setContrasena(jPasswordFieldPasswordUsuario.getText());
        cruds.AgregarUsuario(usuario);
        llenarAutocompleterUsuarios();
        
        JOptionPane.showMessageDialog(null, "Agregado correctamente!!");
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

    private void jTextFieldNombreCategoriaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreCategoriaFocusGained
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            if(jTextFieldNombreCategoria.getText().equals(entry.getValue().getNombre())){
                jLabelIdCategoria.setText(String.valueOf(entry.getValue().getId()));
                jTextFieldNombreCategoria.setText(entry.getValue().getNombre());
                jTextFieldDescripcionCategoria.setText(entry.getValue().getDescripcion());
                
                jLabelIdCategoria2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldNombreCategoriaFocusGained

    private void jButtonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarCategoriaActionPerformed
        if (validarInformacionCategoria()== true) {
            try {
                Categoria auxCategoria = new Categoria();
                auxCategoria.setId(hashMapCategoria.size()+1);
                auxCategoria.setNombre(jTextFieldNombreCategoria.getText());
                auxCategoria.setDescripcion(jTextFieldDescripcionCategoria.getText());
                
                cruds.agregarCategoria(auxCategoria);
                
                limpiarInformacionCategoria();
                llenarAutocompleterCategoria();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAgregarCategoriaActionPerformed

    private void jButtonEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarCategoriaActionPerformed
        if (validarInformacionCategoria()== true) {
            try {
                Categoria auxCategoria = new Categoria();
                auxCategoria.setId(Integer.parseInt(jLabelIdCategoria.getText()));
                auxCategoria.setNombre(jTextFieldNombreCategoria.getText());
                auxCategoria.setDescripcion(jTextFieldDescripcionCategoria.getText());
                
                cruds.editarCategoria(auxCategoria);
                
                limpiarInformacionCategoria();
                llenarAutocompleterCategoria();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditarCategoriaActionPerformed

    private void jButtonEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarCategoriaActionPerformed
        if (validarInformacionCategoria()== true) {
            try {
                Categoria auxCategoria = new Categoria();
                auxCategoria.setId(Integer.parseInt(jLabelIdCategoria.getText()));
                auxCategoria.setNombre(jTextFieldNombreCategoria.getText());
                auxCategoria.setDescripcion(jTextFieldDescripcionCategoria.getText());
                
                cruds.eliminarCategoria(auxCategoria);
                
                limpiarInformacionCategoria();
                llenarAutocompleterCategoria();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarCategoriaActionPerformed

    private void jButtonLimpiarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarCategoriaActionPerformed
        limpiarInformacionCategoria();
    }//GEN-LAST:event_jButtonLimpiarCategoriaActionPerformed

    private void jTextFieldCodigoLoteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoLoteFocusGained
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            if(jTextFieldCodigoLote.getText().equals(entry.getValue().getCodigoLote())){
                jLabelIdLote.setText(String.valueOf(entry.getValue().getId()));
                jDateChooser1.setDate(entry.getValue().getFechaEmpacado());
                jDateChooser2.setDate(entry.getValue().getFechaVecimiento());                
                jLabelIdLote2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldCodigoLoteFocusGained

    private void jButtonAgregarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarLoteActionPerformed
        if(validarInformacionLote() == true){
            try {
                Lote auxLote = new Lote();
                Date date = new Date(2, 2, 2);
                auxLote.setId(treeMapLote.size()+1);
                auxLote.setCodigoLote(jTextFieldCodigoLote.getText());
                auxLote.setFechaEmpacado(date);
                auxLote.setFechaVecimiento(date);
                
                cruds.agregarLote(auxLote);
                
                limpiarInformacionLote();
                llenarAutocompleterLote();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAgregarLoteActionPerformed

    private void jButtonEditarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarLoteActionPerformed
        if(validarInformacionLote() == true){
            try {
                Lote auxLote = new Lote();
                Date date = new Date(2, 2, 2);
                auxLote.setId(Integer.parseInt(jLabelIdLote.getText()));
                auxLote.setCodigoLote(jTextFieldCodigoLote.getText());
                auxLote.setFechaEmpacado(date);
                auxLote.setFechaVecimiento(date);
                
                cruds.editarLote(auxLote);
                
                limpiarInformacionLote();
                llenarAutocompleterLote();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEditarLoteActionPerformed

    private void jButtonEliminarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarLoteActionPerformed
        if(validarInformacionLote() == true){
            try {
                Lote auxLote = new Lote();
                Date date = new Date(2, 2, 2);
                auxLote.setId(Integer.parseInt(jLabelIdLote.getText()));
                auxLote.setCodigoLote(jTextFieldCodigoLote.getText());
                auxLote.setFechaEmpacado(date);
                auxLote.setFechaVecimiento(date);
                
                cruds.eliminarLote(auxLote);
                
                limpiarInformacionLote();
                llenarAutocompleterLote();
            } catch (GraphException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarLoteActionPerformed

    private void jButtonLimpiarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarLoteActionPerformed
        limpiarInformacionLote();
    }//GEN-LAST:event_jButtonLimpiarLoteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:  
        Usuario usuario = new Usuario();
        //agregar id autoincremental
        usuario.setId(Integer.parseInt(jLabelIdUsuario.getText()));
        usuario.setNombre(jTextFieldNombreUsuario.getText());
        usuario.setRol((String) jComboBoxRolUsuario.getSelectedItem());
        usuario.setUsuario(jTextFieldUserUsuario.getText());
        usuario.setContrasena(jPasswordFieldPasswordUsuario.getText());
        cruds.eliminarUsuario(usuario);
        limpiarInformacionUsuario();
        llenarAutocompleterUsuarios();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldNombreUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreUsuarioFocusGained
        // TODO add your handling code here:
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if(jTextFieldNombreUsuario.getText().equalsIgnoreCase(
                    listaUsuarios.get(i).getNombre())){
            jLabelIdUsuario.setText(String.valueOf(listaUsuarios.get(i).getId()));
            jComboBoxRolUsuario.setSelectedItem(listaUsuarios.get(i).getRol());
            jTextFieldUserUsuario.setText(listaUsuarios.get(i).getUsuario());
            jPasswordFieldPasswordUsuario.setText(listaUsuarios.get(i).getContrasena());
            }
        }
        
    }//GEN-LAST:event_jTextFieldNombreUsuarioFocusGained

    private void jTextFieldNombreBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreBodegaActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        Usuario usuario = new Usuario();
        //agregar id autoincremental
        usuario.setId(Integer.parseInt(jLabelIdUsuario.getText()));
        usuario.setNombre(jTextFieldNombreUsuario.getText());
        usuario.setRol((String) jComboBoxRolUsuario.getSelectedItem());
        usuario.setUsuario(jTextFieldUserUsuario.getText());
        usuario.setContrasena(jPasswordFieldPasswordUsuario.getText());
        cruds.editarUsuario(usuario);
        limpiarInformacionUsuario();
        llenarAutocompleterUsuarios();
        
    }//GEN-LAST:event_ModificarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            Data d= new Data();
            LinkedList<Usuario> listaUsuarios = d.getListaUsuarios();
            Files f = new Files();
            f.ArchivoUsuarios();
            f.ArchivoTransporte();
            f.ArchivoCategoria();
            f.ArchivoTransporte();
            f.ArchivoBodega();
            System.exit(0);
        }
            System.exit(0);
        } catch (IOException | GraphException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            Data d= new Data();
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            Data d= new Data();
            LinkedList<Usuario> listaUsuarios = d.getListaUsuarios();
            Files f = new Files();
            f.ArchivoBodega();
            f.ArchivoUsuarios();
            f.ArchivoCategoria();
            f.ArchivoTransporte();
            System.exit(0);
        }
            System.exit(0);
        } catch (IOException | GraphException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            Data d= new Data();
            LinkedList<Usuario> listaUsuarios = d.getListaUsuarios();
            Files f = new Files();
            f.ArchivoBodega();
            f.ArchivoUsuarios();
            f.ArchivoCategoria();
            f.ArchivoTransporte();
            System.exit(0);
        }
            System.exit(0);
        } catch (IOException | GraphException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            Data d= new Data();
            LinkedList<Usuario> listaUsuarios = d.getListaUsuarios();
            Files f = new Files();
            f.ArchivoBodega();
            f.ArchivoUsuarios();
            f.ArchivoCategoria();
            f.ArchivoTransporte();
            System.exit(0);
        }
            System.exit(0);
        } catch (IOException | GraphException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            Data d= new Data();
            LinkedList<Usuario> listaUsuarios = d.getListaUsuarios();
            Files f = new Files();
            f.ArchivoBodega();
            f.ArchivoUsuarios();
            f.ArchivoCategoria();
            f.ArchivoTransporte();
            System.exit(0);
        }
            System.exit(0);
        } catch (IOException | GraphException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButtonLimpiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarUsuarioActionPerformed
        // TODO add your handling code here:
        limpiarInformacionUsuario();
    }//GEN-LAST:event_jButtonLimpiarUsuarioActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextFieldNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreUsuarioActionPerformed

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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAgregarBodega;
    private javax.swing.JButton jButtonAgregarCategoria;
    private javax.swing.JButton jButtonAgregarLote;
    private javax.swing.JButton jButtonAgregarUnidadTransporte;
    private javax.swing.JButton jButtonEditarBodega;
    private javax.swing.JButton jButtonEditarCategoria;
    private javax.swing.JButton jButtonEditarLote;
    private javax.swing.JButton jButtonEditarUnidadTransporte;
    private javax.swing.JButton jButtonEliminarBodega;
    private javax.swing.JButton jButtonEliminarCategoria;
    private javax.swing.JButton jButtonEliminarLote;
    private javax.swing.JButton jButtonEliminarUnidadTransporte;
    private javax.swing.JButton jButtonLimpiarBodega;
    private javax.swing.JButton jButtonLimpiarCategoria;
    private javax.swing.JButton jButtonLimpiarLote;
    private javax.swing.JButton jButtonLimpiarUnidadTransporte;
    private javax.swing.JButton jButtonLimpiarUsuario;
    private javax.swing.JComboBox<String> jComboBoxRolUsuario;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDescripcionProducto;
    private javax.swing.JLabel jLabelIdBodega;
    private javax.swing.JLabel jLabelIdBodega2;
    private javax.swing.JLabel jLabelIdCategoria;
    private javax.swing.JLabel jLabelIdCategoria2;
    private javax.swing.JLabel jLabelIdCategoriaProducto;
    private javax.swing.JLabel jLabelIdLote;
    private javax.swing.JLabel jLabelIdLote2;
    private javax.swing.JLabel jLabelIdLoteProducto;
    private javax.swing.JLabel jLabelIdProducto;
    private javax.swing.JLabel jLabelIdUnidadTransporte;
    private javax.swing.JLabel jLabelIdUnidadTransporte2;
    private javax.swing.JLabel jLabelIdUsuario;
    private javax.swing.JLabel jLabelNombreProducto;
    private javax.swing.JLabel jLabelPesoProducto;
    private javax.swing.JLabel jLabelPrecioProducto;
    private javax.swing.JLabel jLabelUnidadMedidasProducto;
    private javax.swing.JLabel jLabelUrlFotoProducto;
    private javax.swing.JLabel jLabelValorUnidad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordFieldPasswordUsuario;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldCapacidadUnidadTransporte;
    private javax.swing.JTextField jTextFieldCodigoLote;
    private javax.swing.JTextField jTextFieldDescripcionCategoria;
    private javax.swing.JTextField jTextFieldDistanciaBodega;
    private javax.swing.JTextField jTextFieldLatitudBodega;
    private javax.swing.JTextField jTextFieldLongitudBodega;
    private javax.swing.JTextField jTextFieldNombreBodega;
    private javax.swing.JTextField jTextFieldNombreCategoria;
    private javax.swing.JTextField jTextFieldNombreUsuario;
    private javax.swing.JTextField jTextFieldPlacaUnidadTransporte;
    private javax.swing.JTextField jTextFieldUrlBodega;
    private javax.swing.JTextField jTextFieldUrlUnidadTransporte;
    private javax.swing.JTextField jTextFieldUserUsuario;
    // End of variables declaration//GEN-END:variables
}
