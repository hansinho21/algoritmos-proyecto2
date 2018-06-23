/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Bodega;
import Domain.Categoria;
import Domain.Lote;
import Domain.ProductoMayorista;
import Domain.UnidadTransporte;
import Domain.Usuario;
import Logic.Cruds;
import Logic.Datos;
import Logic.Logica;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.ComponentOrientation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jeison
 */
public class Mantenimiento extends javax.swing.JFrame implements Serializable {

    //instancias
    private Datos data;
    private Cruds cruds;
    private Logica logica;

    //tda's
    private LinkedList<Usuario> listaUsuarios;
    private AdjacencyMatrixGraph grafoBodegas;
    private HashMap<String, Categoria> hashMapCategoria;
    private TreeMap<Integer, Lote> treeMapLote;
    private LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte;
    private LinkedList<ProductoMayorista> listaProducto;

    private TextAutoCompleter textAutoCompleterUsuario;
    private TextAutoCompleter textAutoCompleterBodega;
    private TextAutoCompleter textAutoCompleterCategoria;
    private TextAutoCompleter textAutoCompleterLote;
    private TextAutoCompleter textAutoCompleterUnidadTransorte;
    private TextAutoCompleter textAutoCompleterProducto;
    

    /**
     * Creates new form Mantenimiento
     */
    public Mantenimiento() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);

        this.jLabelIdUsuario2.setVisible(false);
        this.jLabelIdBodega2.setVisible(false);
        this.jLabelIdCategoria2.setVisible(false);
        this.jLabelIdLote2.setVisible(false);
        this.jLabelIdUnidadTransporte2.setVisible(false);
        this.jLabelIdProducto2.setVisible(false);

        //instancias
        this.data = new Datos();
        this.cruds = new Cruds();
        this.logica = new Logica();

        //tda's
        this.listaUsuarios = this.data.getListaUsuarios();
        this.grafoBodegas = this.data.getGrafoBodegas();
        this.hashMapCategoria = this.data.getHashMapCategoria();
        this.treeMapLote = this.data.getTreeMapLote();
        this.linkedHashMapUnidadTransporte = this.data.getLinkedHashMapUnidadTransporte();
        this.listaProducto = this.data.getListaProductos();

        //autocopleter
        this.textAutoCompleterUsuario = new TextAutoCompleter(jTextFieldUserUsuario);
        this.textAutoCompleterBodega = new TextAutoCompleter(jTextFieldNombreBodega);
        this.textAutoCompleterCategoria = new TextAutoCompleter(jTextFieldNombreCategoria);
        this.textAutoCompleterLote = new TextAutoCompleter(jTextFieldCodigoLote);
        this.textAutoCompleterUnidadTransorte = new TextAutoCompleter(jTextFieldPlacaUnidadTransporte);
        this.textAutoCompleterProducto = new TextAutoCompleter(jTextFieldNombreProducto);

        //llenar autocompleters
        llenarAutocompleterBodegas();
        llenarAutocompleterCategoria();
        llenarAutocompleterUnidadesTransporte();
        llenarAutocompleterLote();
        llenarAutocompleterUsuarios();
        llenarAutocompleterProductos();
        
        //ComboBox
        llenarComboBoxCategoria();
        llenarComboBoxLote();
    }

    private void limpiarInformacionUsuario() {
        jLabelIdUsuario.setText("");
        jTextFieldNombreUsuario.setText("");
        jTextFieldUserUsuario.setText("");
        jPasswordFieldPasswordUsuario.setText("");

        this.jLabelIdUsuario2.setVisible(false);
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

    private void limpiarInformacionCategoria() {
        jLabelIdCategoria.setText("");
        jTextFieldNombreCategoria.setText("");
        jTextFieldDescripcionCategoria.setText("");

        jLabelIdCategoria2.setVisible(false);
    }

    private void limpiarInformacionLote() {
        jLabelIdLote.setText("");
        jTextFieldCodigoLote.setText("");
        jDateChooser1.setDateFormatString("");
        jDateChooser2.setDateFormatString("");
        jLabelIdLote2.setVisible(false);
    }

    private void limpiarInformacionUnidadTransporte() {
        jLabelIdUnidadTransporte.setText("");
        jTextFieldPlacaUnidadTransporte.setText("");
        jTextFieldCapacidadUnidadTransporte.setText("");
        jTextFieldUrlUnidadTransporte.setText("");

        this.jLabelIdUnidadTransporte2.setVisible(false);
    }
    
    private void limpiarInformacionProducto(){
        jLabelIdProducto.setText("");
        jTextFieldNombreProducto.setText("");
        jTextFieldDescripcionProducto.setText("");
        jTextFieldMedidasProducto.setText("");
        jTextFieldUrlProducto.setText("");
        jTextFieldPesoProducto.setText("");
        jTextFieldValorProducto.setText("");
        jTextFieldPrecioProducto.setText("");
        
        this.jLabelIdProducto2.setVisible(false);
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

    private boolean validarInformacionCategoria() {
        if (jTextFieldNombreCategoria.getText().equals("")
                || jTextFieldDescripcionCategoria.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validarInformacionLote() {
        if (jTextFieldCodigoLote.getText().equals("")
                || jDateChooser1.toString().equals("")
                || jDateChooser2.toString().equals("")) {
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
        System.out.println("----BODEGAS----");
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            System.out.println(grafoBodegas.getVertex(i).toString());
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            textAutoCompleterBodega.addItem(auxBodega.getNombre());
        }
    }

    private void llenarAutocompleterUsuarios() {
        System.out.println("----USUARIOS----");
        textAutoCompleterUsuario.removeAllItems();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            System.out.println(listaUsuarios.get(i).toString());
            Usuario usuario = listaUsuarios.get(i);
            textAutoCompleterUsuario.addItem(usuario.getUsuario());
        }
    }

    /**
     *
     * LLena el AutoCompleter con las categorias.
     */
    private void llenarAutocompleterCategoria() throws GraphException {
        System.out.println("----CATEGORIAS----");
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
        System.out.println("----LOTES----");
        textAutoCompleterLote.removeAllItems();
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
            textAutoCompleterLote.addItem(entry.getValue().getCodigoLote());

        }
    }

    /**
     *
     * LLena el AutoCompleter con las unidades de transporte.
     */
    private void llenarAutocompleterUnidadesTransporte() throws GraphException {
        System.out.println("----TRANSPORTES----");
        textAutoCompleterUnidadTransorte.removeAllItems();
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
            textAutoCompleterUnidadTransorte.addItem(entry.getValue().getPlaca());
        }
    }
    
    private void llenarAutocompleterProductos() {
        System.out.println("----PRODUCTOS----");
        textAutoCompleterProducto.removeAllItems();
        for (int i = 0; i < listaProducto.size(); i++) {
            System.out.println(listaProducto.get(i).toString());
            textAutoCompleterProducto.addItem(listaProducto.get(i).getNombre());
        }
    }
    
    private void llenarComboBoxCategoria(){
        jComboBoxIdCategoriaProducto.removeAllItems();
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            jComboBoxIdCategoriaProducto.addItem(String.valueOf(entry.getValue().getId()));
            
        }
    }
    
    private void llenarComboBoxLote(){
        jComboBoxIdLoteProducto.removeAllItems();
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            jComboBoxIdLoteProducto.addItem(String.valueOf(entry.getValue().getId()));
            
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
        jLabelIdUsuario2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelIdUsuario = new javax.swing.JLabel();
        jTextFieldNombreUsuario = new javax.swing.JTextField();
        jComboBoxRolUsuario = new javax.swing.JComboBox<>();
        jTextFieldUserUsuario = new javax.swing.JTextField();
        jPasswordFieldPasswordUsuario = new javax.swing.JPasswordField();
        jButtonAgregarUsuario = new javax.swing.JButton();
        jButtonEditarUsuario = new javax.swing.JButton();
        jButtonModificarUsuario = new javax.swing.JButton();
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
        jButtonBuscarImagenBodega = new javax.swing.JButton();
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
        jButtonBuscarImagenBodega1 = new javax.swing.JButton();
        fondo4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabelIdProducto2 = new javax.swing.JLabel();
        jLabelNombreProducto = new javax.swing.JLabel();
        jLabelValorUnidad = new javax.swing.JLabel();
        jLabelPesoProducto = new javax.swing.JLabel();
        jLabelDescripcionProducto = new javax.swing.JLabel();
        jLabelIdLoteProducto = new javax.swing.JLabel();
        jLabelIdCategoriaProducto = new javax.swing.JLabel();
        jLabelPrecioProducto = new javax.swing.JLabel();
        jLabelUrlFotoProducto = new javax.swing.JLabel();
        jLabelUnidadMedidasProducto = new javax.swing.JLabel();
        jButtonAgregarProducto = new javax.swing.JButton();
        jButtonEditarProducto = new javax.swing.JButton();
        jButtonEliminarProducto = new javax.swing.JButton();
        jButtonLimpiarProducto = new javax.swing.JButton();
        jLabelIdProducto = new javax.swing.JLabel();
        jTextFieldNombreProducto = new javax.swing.JTextField();
        jTextFieldDescripcionProducto = new javax.swing.JTextField();
        jTextFieldMedidasProducto = new javax.swing.JTextField();
        jTextFieldUrlProducto = new javax.swing.JTextField();
        jTextFieldPesoProducto = new javax.swing.JTextField();
        jTextFieldValorProducto = new javax.swing.JTextField();
        jTextFieldPrecioProducto = new javax.swing.JTextField();
        jButtonBuscarImagenBodega2 = new javax.swing.JButton();
        jComboBoxIdCategoriaProducto = new javax.swing.JComboBox<>();
        jComboBoxIdLoteProducto = new javax.swing.JComboBox<>();
        fondo7 = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();
        fondo3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIdUsuario2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdUsuario2.setText("ID:");
        jPanel1.add(jLabelIdUsuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

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

        jLabelIdUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabelIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 80, 30));

        jTextFieldNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jTextFieldNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 220, 30));

        jComboBoxRolUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxRolUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operador", "Administrador" }));
        jPanel1.add(jComboBoxRolUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 220, 30));

        jTextFieldUserUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldUserUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldUserUsuarioFocusGained(evt);
            }
        });
        jTextFieldUserUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldUserUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 220, 30));

        jPasswordFieldPasswordUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jPasswordFieldPasswordUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 220, 30));

        jButtonAgregarUsuario.setText("Agregar");
        jButtonAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAgregarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, -1, -1));

        jButtonEditarUsuario.setText("Eliminar");
        jButtonEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEditarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, -1, -1));

        jButtonModificarUsuario.setText("Modificar");
        jButtonModificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonModificarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        jButtonLimpiarUsuario.setText("Limpiar");
        jButtonLimpiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLimpiarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, -1, -1));

        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel1.add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 560));

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
        jTextFieldDistanciaBodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDistanciaBodegaKeyTyped(evt);
            }
        });
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
        jPanel5.add(jTextFieldUrlBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 150, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("FileChooser en el url");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 210, 30));

        jButtonBuscarImagenBodega.setText("Buscar Imagen");
        jButtonBuscarImagenBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarImagenBodegaActionPerformed(evt);
            }
        });
        jPanel5.add(jButtonBuscarImagenBodega, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 120, 30));

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel5.add(fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 560));

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

        fondo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel8.add(fondo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 560));

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
        jTextFieldCapacidadUnidadTransporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCapacidadUnidadTransporteKeyTyped(evt);
            }
        });
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

        jButtonBuscarImagenBodega1.setText("Buscar Imagen");
        jButtonBuscarImagenBodega1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarImagenBodega1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonBuscarImagenBodega1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 120, 30));

        fondo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel6.add(fondo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 510));

        jTabbedPane1.addTab("Unidad de Transporte", jPanel6);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIdProducto2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdProducto2.setText("Id");
        jPanel9.add(jLabelIdProducto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabelNombreProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNombreProducto.setText("Nombre");
        jPanel9.add(jLabelNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabelValorUnidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelValorUnidad.setText("Valor Unidad");
        jPanel9.add(jLabelValorUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));

        jLabelPesoProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelPesoProducto.setText("Peso Total");
        jPanel9.add(jLabelPesoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

        jLabelDescripcionProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelDescripcionProducto.setText("Descripcion");
        jPanel9.add(jLabelDescripcionProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        jLabelIdLoteProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdLoteProducto.setText("Id Lote");
        jPanel9.add(jLabelIdLoteProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        jLabelIdCategoriaProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdCategoriaProducto.setText("Id Categoria");
        jPanel9.add(jLabelIdCategoriaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        jLabelPrecioProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelPrecioProducto.setText("Precio total");
        jPanel9.add(jLabelPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1));

        jLabelUrlFotoProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelUrlFotoProducto.setText("URL fotografia");
        jPanel9.add(jLabelUrlFotoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabelUnidadMedidasProducto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelUnidadMedidasProducto.setText("Unidad Medidas");
        jPanel9.add(jLabelUnidadMedidasProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jButtonAgregarProducto.setBackground(new java.awt.Color(0, 51, 51));
        jButtonAgregarProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAgregarProducto.setText("Agregar");
        jButtonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarProductoActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jButtonEditarProducto.setBackground(new java.awt.Color(0, 51, 51));
        jButtonEditarProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonEditarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditarProducto.setText("Editar");
        jButtonEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarProductoActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonEditarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, -1, -1));

        jButtonEliminarProducto.setBackground(new java.awt.Color(0, 51, 51));
        jButtonEliminarProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonEliminarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarProducto.setText("Eliminar");
        jButtonEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarProductoActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, -1, -1));

        jButtonLimpiarProducto.setBackground(new java.awt.Color(0, 51, 51));
        jButtonLimpiarProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonLimpiarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLimpiarProducto.setText("Limpiar");
        jButtonLimpiarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarProductoActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonLimpiarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));
        jPanel9.add(jLabelIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 160, 30));

        jTextFieldNombreProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldNombreProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreProductoFocusGained(evt);
            }
        });
        jPanel9.add(jTextFieldNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 160, 30));

        jTextFieldDescripcionProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldDescripcionProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescripcionProductoActionPerformed(evt);
            }
        });
        jPanel9.add(jTextFieldDescripcionProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 160, 30));

        jTextFieldMedidasProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel9.add(jTextFieldMedidasProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 160, 30));

        jTextFieldUrlProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel9.add(jTextFieldUrlProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 160, 30));

        jTextFieldPesoProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldPesoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesoProductoActionPerformed(evt);
            }
        });
        jTextFieldPesoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPesoProductoKeyTyped(evt);
            }
        });
        jPanel9.add(jTextFieldPesoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 160, 30));

        jTextFieldValorProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldValorProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorProductoActionPerformed(evt);
            }
        });
        jTextFieldValorProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorProductoKeyTyped(evt);
            }
        });
        jPanel9.add(jTextFieldValorProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, 160, 30));

        jTextFieldPrecioProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldPrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioProductoKeyTyped(evt);
            }
        });
        jPanel9.add(jTextFieldPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 260, 160, 30));

        jButtonBuscarImagenBodega2.setText("Buscar Imagen");
        jButtonBuscarImagenBodega2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarImagenBodega2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonBuscarImagenBodega2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 120, 30));

        jPanel9.add(jComboBoxIdCategoriaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 70, 30));

        jPanel9.add(jComboBoxIdLoteProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 70, 30));

        fondo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        jPanel9.add(fondo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

        jTabbedPane1.addTab("Producto", jPanel9);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 960, 540));

        jButtonSalir.setBackground(new java.awt.Color(0, 51, 51));
        jButtonSalir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 100, 30));

        fondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo.png"))); // NOI18N
        getContentPane().add(fondo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * metodo para agregar un usuario a la lista
     *
     * @param evt
     */
    private void jButtonAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarUsuarioActionPerformed
        try {
            // TODO add your handling code here:
            Usuario usuario = new Usuario();
            int id = 0;
            if (listaUsuarios.isEmpty()) {
                id = 1;
            } else {
                id = listaUsuarios.get(listaUsuarios.size() - 1).getId() + 1;
            }
            usuario.setId(id);
            usuario.setNombre(jTextFieldNombreUsuario.getText());
            usuario.setRol((String) jComboBoxRolUsuario.getSelectedItem());
            usuario.setUsuario(jTextFieldUserUsuario.getText());
            usuario.setContrasena(jPasswordFieldPasswordUsuario.getText());

            if (this.logica.existeUsuario(usuario) == true) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario ya existe");
            } else {
                cruds.agregarUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Agregado correctamente!!");
            }

            llenarAutocompleterUsuarios();
            limpiarInformacionUsuario();

        } catch (IOException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAgregarUsuarioActionPerformed

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
                int id = 0;
                if (linkedHashMapUnidadTransporte.isEmpty()) {
                    id = 1;
                } else {
                    id = linkedHashMapUnidadTransporte.get(linkedHashMapUnidadTransporte.size() - 1).getId() + 1;
                }
                auxBodega.setId(id);
                auxBodega.setNombre(jTextFieldNombreBodega.getText());
                auxBodega.setLatitud(jTextFieldLatitudBodega.getText());
                auxBodega.setLongitud(jTextFieldLongitudBodega.getText());
                auxBodega.setDistanciaCentroOperaciones(Float.parseFloat(jTextFieldDistanciaBodega.getText()));
                auxBodega.setUrlFotografia(jTextFieldUrlBodega.getText());

                cruds.agregarBodega(auxBodega);

                limpiarInformacionBodega();
                llenarAutocompleterBodegas();
            } catch (GraphException | IOException ex) {
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
            } catch (GraphException | IOException ex) {
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
            } catch (GraphException | IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarBodegaActionPerformed

    private void jTextFieldPlacaUnidadTransporteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPlacaUnidadTransporteFocusGained
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
            if (jTextFieldPlacaUnidadTransporte.getText().equals(entry.getValue().getPlaca())) {
                jLabelIdUnidadTransporte.setText(String.valueOf(entry.getValue().getId()));
                jTextFieldCapacidadUnidadTransporte.setText(String.valueOf(entry.getValue().getCapacidad()));
                jTextFieldUrlUnidadTransporte.setText(entry.getValue().getUrlFotografia());

                jLabelIdUnidadTransporte2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldPlacaUnidadTransporteFocusGained

    private void jButtonAgregarUnidadTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarUnidadTransporteActionPerformed
        if (validarInformacionUnidadTransporte() == true) {
            try {
                UnidadTransporte auxUnidad = new UnidadTransporte();
                int id = 0;
                if (linkedHashMapUnidadTransporte.isEmpty()) {
                    id = 1;
                } else {
                    for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
                        id = entry.getValue().getId() + 1;
                    }
                }
                auxUnidad.setId(id);
                auxUnidad.setCapacidad(Integer.parseInt(jTextFieldCapacidadUnidadTransporte.getText()));
                auxUnidad.setPlaca(jTextFieldPlacaUnidadTransporte.getText());
                auxUnidad.setUrlFotografia(jTextFieldUrlUnidadTransporte.getText());

                if (this.logica.existeUnidadTransporte(auxUnidad) == true) {
                    JOptionPane.showMessageDialog(null, "Esta categoria ya existe");
                } else {
                    cruds.agregarUnidadTransporte(auxUnidad);
                }

                limpiarInformacionUnidadTransporte();
                llenarAutocompleterUnidadesTransporte();
            } catch (GraphException | IOException ex) {
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
                auxUnidad.setCapacidad(Integer.parseInt(jTextFieldCapacidadUnidadTransporte.getText()));
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
                auxUnidad.setCapacidad(Integer.parseInt(jTextFieldCapacidadUnidadTransporte.getText()));
                auxUnidad.setPlaca(jTextFieldPlacaUnidadTransporte.getText());
                auxUnidad.setUrlFotografia(jTextFieldUrlUnidadTransporte.getText());

                cruds.eliminarUnidadTransporte(auxUnidad);

                limpiarInformacionUnidadTransporte();
                llenarAutocompleterUnidadesTransporte();
            } catch (GraphException | IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarUnidadTransporteActionPerformed

    private void jTextFieldNombreCategoriaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreCategoriaFocusGained
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            if (jTextFieldNombreCategoria.getText().equals(entry.getValue().getNombre())) {
                jLabelIdCategoria.setText(String.valueOf(entry.getValue().getId()));
                jTextFieldNombreCategoria.setText(entry.getValue().getNombre());
                jTextFieldDescripcionCategoria.setText(entry.getValue().getDescripcion());

                jLabelIdCategoria2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldNombreCategoriaFocusGained

    private void jButtonAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarCategoriaActionPerformed
        if (validarInformacionCategoria() == true) {
            try {
                Categoria auxCategoria = new Categoria();
                int id = 0;
                if (hashMapCategoria.isEmpty()) {
                    id = 1;
                } else {
                    for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
                        id = entry.getValue().getId() + 1;
                    }
                }
                auxCategoria.setId(id);
                auxCategoria.setNombre(jTextFieldNombreCategoria.getText());
                auxCategoria.setDescripcion(jTextFieldDescripcionCategoria.getText());

                if (this.logica.existeCategoria(auxCategoria) == true) {
                    JOptionPane.showMessageDialog(null, "Esta categoria ya existe");
                } else {
                    cruds.agregarCategoria(auxCategoria);
                }

                limpiarInformacionCategoria();
                llenarAutocompleterCategoria();
                llenarComboBoxCategoria();
            } catch (GraphException | IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAgregarCategoriaActionPerformed

    private void jButtonEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarCategoriaActionPerformed
        if (validarInformacionCategoria() == true) {

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
        if (validarInformacionCategoria() == true) {
            try {
                Categoria auxCategoria = new Categoria();
                auxCategoria.setId(Integer.parseInt(jLabelIdCategoria.getText()));
                auxCategoria.setNombre(jTextFieldNombreCategoria.getText());
                auxCategoria.setDescripcion(jTextFieldDescripcionCategoria.getText());

                cruds.eliminarCategoria(auxCategoria);

                limpiarInformacionCategoria();
                llenarAutocompleterCategoria();
                llenarComboBoxCategoria();
            } catch (GraphException | IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarCategoriaActionPerformed

    private void jButtonLimpiarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarCategoriaActionPerformed
        limpiarInformacionCategoria();
    }//GEN-LAST:event_jButtonLimpiarCategoriaActionPerformed

    private void jTextFieldCodigoLoteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoLoteFocusGained
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            if (jTextFieldCodigoLote.getText().equals(entry.getValue().getCodigoLote())) {
                jLabelIdLote.setText(String.valueOf(entry.getValue().getId()));
                jDateChooser1.setDate(entry.getValue().getFechaEmpacado());
                jDateChooser2.setDate(entry.getValue().getFechaVecimiento());
                jLabelIdLote2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldCodigoLoteFocusGained

    private void jButtonAgregarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarLoteActionPerformed
        if (validarInformacionLote() == true) {
            try {
                Lote auxLote = new Lote();
                int id = 0;
                if (treeMapLote.isEmpty()) {
                    id = 1;
                } else {
                    for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
                        id = entry.getValue().getId() + 1;
                    }
                }
                auxLote.setId(id);
                auxLote.setCodigoLote(jTextFieldCodigoLote.getText());
                auxLote.setFechaEmpacado(jDateChooser1.getDate());
                auxLote.setFechaVecimiento(jDateChooser2.getDate());

                if (this.logica.existeLote(auxLote) == true) {
                    JOptionPane.showMessageDialog(null, "Este lote ya existe");
                } else {
                    cruds.agregarLote(auxLote);
                }

                limpiarInformacionLote();
                llenarAutocompleterLote();
                llenarComboBoxLote();
            } catch (GraphException | IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonAgregarLoteActionPerformed

    private void jButtonEditarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarLoteActionPerformed
        if (validarInformacionLote() == true) {
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
        if (validarInformacionLote() == true) {
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
                llenarComboBoxLote();
            } catch (GraphException | IOException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonEliminarLoteActionPerformed

    private void jButtonLimpiarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarLoteActionPerformed
        limpiarInformacionLote();
    }//GEN-LAST:event_jButtonLimpiarLoteActionPerformed

    private void jButtonEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarUsuarioActionPerformed
        try {
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
        } catch (IOException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonEditarUsuarioActionPerformed

    private void jTextFieldNombreBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreBodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreBodegaActionPerformed

    private void jButtonModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarUsuarioActionPerformed
        try {
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
        } catch (IOException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonModificarUsuarioActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        int salida = JOptionPane.showConfirmDialog(null,
                "Realmente desea salir de la apilcación?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (salida == 0) {
            LinkedList<Usuario> listaUsuarios = this.data.getListaUsuarios();

            System.exit(0);
        }

    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonLimpiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarUsuarioActionPerformed
        // TODO add your handling code here:
        limpiarInformacionUsuario();
    }//GEN-LAST:event_jButtonLimpiarUsuarioActionPerformed

    private void jTextFieldDescripcionProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescripcionProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDescripcionProductoActionPerformed

    private void jTextFieldPesoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPesoProductoActionPerformed

    private void jTextFieldValorProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorProductoActionPerformed

    private void jButtonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarProductoActionPerformed
        try {
            // TODO add your handling code here:
            ProductoMayorista productoMayorista = new ProductoMayorista();
            int id = 0;
            if (listaProducto.isEmpty()) {
                id = 1;
            } else {
                id = listaProducto.get(listaProducto.size() - 1).getId() + 1;
            }
            
            productoMayorista.setId(id);
            productoMayorista.setNombre(jTextFieldNombreProducto.getText());
            productoMayorista.setDescripcion(jTextFieldDescripcionProducto.getText());
            productoMayorista.setUnidadMedidas(jTextFieldMedidasProducto.getText());
            productoMayorista.setUrlFotografia(jTextFieldUrlProducto.getText());
            productoMayorista.setIdCategoria(Integer.parseInt(jComboBoxIdCategoriaProducto.getSelectedItem().toString()));
            productoMayorista.setIdLote(Integer.parseInt(jComboBoxIdLoteProducto.getSelectedItem().toString()));
            productoMayorista.setPesoTotal(Integer.parseInt(jTextFieldPesoProducto.getText()));
            productoMayorista.setPrecioTotal(Double.parseDouble(jTextFieldPrecioProducto.getText()));
            productoMayorista.setValorUnidad(Integer.parseInt(jTextFieldValorProducto.getText()));

            this.cruds.agregarProducto(productoMayorista);
            
            llenarAutocompleterProductos();
            limpiarInformacionProducto();

        } catch (IOException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAgregarProductoActionPerformed

    private void jTextFieldCapacidadUnidadTransporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCapacidadUnidadTransporteKeyTyped
        if (!this.logica.esNumero(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldCapacidadUnidadTransporteKeyTyped

    private void jTextFieldDistanciaBodegaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDistanciaBodegaKeyTyped
        if (!this.logica.esNumero(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDistanciaBodegaKeyTyped

    private void jButtonBuscarImagenBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarImagenBodegaActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
        fileChooser.setFileFilter(fileNameExtensionFilter);
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            String path = file.toString();
            Path origin = Paths.get(path);
            Path destiny = Paths.get(origin.getFileName().toString());
            File imagen = new File(destiny.toString());
            if (!imagen.exists()) {
                try {
                    Files.copy(origin, destiny, StandardCopyOption.COPY_ATTRIBUTES);
                } catch (IOException ex) {
                    Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            jTextFieldUrlBodega.setText(destiny.toString());
        }

    }//GEN-LAST:event_jButtonBuscarImagenBodegaActionPerformed

    private void jButtonBuscarImagenBodega1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarImagenBodega1ActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
        fileChooser.setFileFilter(fileNameExtensionFilter);
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            String path = file.toString();
            Path origin = Paths.get(path);
            Path destiny = Paths.get(origin.getFileName().toString());
            File imagen = new File(destiny.toString());
            if (!imagen.exists()) {
                try {
                    Files.copy(origin, destiny, StandardCopyOption.COPY_ATTRIBUTES);
                } catch (IOException ex) {
                    Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            jTextFieldUrlUnidadTransporte.setText(destiny.toString());
        }
    }//GEN-LAST:event_jButtonBuscarImagenBodega1ActionPerformed

    private void jButtonBuscarImagenBodega2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarImagenBodega2ActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
        fileChooser.setFileFilter(fileNameExtensionFilter);
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            String path = file.toString();
            Path origin = Paths.get(path);
            Path destiny = Paths.get(origin.getFileName().toString());
            File imagen = new File(destiny.toString());
            if (!imagen.exists()) {
                try {
                    Files.copy(origin, destiny, StandardCopyOption.COPY_ATTRIBUTES);
                } catch (IOException ex) {
                    Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            jTextFieldUrlProducto.setText(destiny.toString());
        }
    }//GEN-LAST:event_jButtonBuscarImagenBodega2ActionPerformed

    private void jTextFieldUserUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUserUsuarioActionPerformed

    private void jTextFieldUserUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldUserUsuarioFocusGained
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (jTextFieldUserUsuario.getText().equalsIgnoreCase(
                    listaUsuarios.get(i).getUsuario())) {
                jLabelIdUsuario.setText(String.valueOf(listaUsuarios.get(i).getId()));
                jComboBoxRolUsuario.setSelectedItem(listaUsuarios.get(i).getRol());
                jTextFieldNombreUsuario.setText(listaUsuarios.get(i).getNombre());
                jPasswordFieldPasswordUsuario.setText(listaUsuarios.get(i).getContrasena());

                this.jLabelIdUsuario2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldUserUsuarioFocusGained

    private void jTextFieldPesoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesoProductoKeyTyped
        if (!this.logica.esNumero(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPesoProductoKeyTyped

    private void jTextFieldPrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioProductoKeyTyped
        if (!this.logica.esNumero(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPrecioProductoKeyTyped

    private void jTextFieldValorProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorProductoKeyTyped
        if (!this.logica.esNumero(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldValorProductoKeyTyped

    private void jButtonEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarProductoActionPerformed
        try {
            // TODO add your handling code here:
            ProductoMayorista productoMayorista = new ProductoMayorista();
            
            productoMayorista.setId(Integer.parseInt(jLabelIdProducto.getText()));
            productoMayorista.setNombre(jTextFieldNombreProducto.getText());
            productoMayorista.setDescripcion(jTextFieldDescripcionProducto.getText());
            productoMayorista.setUnidadMedidas(jTextFieldMedidasProducto.getText());
            productoMayorista.setUrlFotografia(jTextFieldUrlProducto.getText());
            productoMayorista.setIdCategoria(Integer.parseInt(jComboBoxIdCategoriaProducto.getSelectedItem().toString()));
            productoMayorista.setIdLote(Integer.parseInt(jComboBoxIdLoteProducto.getSelectedItem().toString()));
            productoMayorista.setPesoTotal(Integer.parseInt(jTextFieldPesoProducto.getText()));
            productoMayorista.setPrecioTotal(Double.parseDouble(jTextFieldPrecioProducto.getText()));
            productoMayorista.setValorUnidad(Integer.parseInt(jTextFieldValorProducto.getText()));

            this.cruds.editarProducto(productoMayorista);
            
            llenarAutocompleterProductos();
            limpiarInformacionProducto();

        } catch (IOException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEditarProductoActionPerformed

    private void jButtonEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarProductoActionPerformed
        try {
            // TODO add your handling code here:
            ProductoMayorista productoMayorista = new ProductoMayorista();
            
            productoMayorista.setId(Integer.parseInt(jLabelIdProducto.getText()));
            productoMayorista.setNombre(jTextFieldNombreProducto.getText());
            productoMayorista.setDescripcion(jTextFieldDescripcionProducto.getText());
            productoMayorista.setUnidadMedidas(jTextFieldMedidasProducto.getText());
            productoMayorista.setUrlFotografia(jTextFieldUrlProducto.getText());
            productoMayorista.setIdCategoria(Integer.parseInt(jComboBoxIdCategoriaProducto.getSelectedItem().toString()));
            productoMayorista.setIdLote(Integer.parseInt(jComboBoxIdLoteProducto.getSelectedItem().toString()));
            productoMayorista.setPesoTotal(Integer.parseInt(jTextFieldPesoProducto.getText()));
            productoMayorista.setPrecioTotal(Double.parseDouble(jTextFieldPrecioProducto.getText()));
            productoMayorista.setValorUnidad(Integer.parseInt(jTextFieldValorProducto.getText()));

            this.cruds.eliminarProducto(productoMayorista);
            
            llenarAutocompleterProductos();
            limpiarInformacionProducto();

        } catch (IOException ex) {
            Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEliminarProductoActionPerformed

    private void jButtonLimpiarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarProductoActionPerformed
        limpiarInformacionProducto();
    }//GEN-LAST:event_jButtonLimpiarProductoActionPerformed

    private void jTextFieldNombreProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreProductoFocusGained
        for (int i = 0; i < listaProducto.size(); i++) {
            if (jTextFieldNombreProducto.getText().equals(listaProducto.get(i).getNombre())) {
                ProductoMayorista productoMayorista = listaProducto.get(i);
                jLabelIdProducto.setText(String.valueOf(productoMayorista.getId()));
                jTextFieldDescripcionProducto.setText(productoMayorista.getDescripcion());
                jTextFieldMedidasProducto.setText(productoMayorista.getUnidadMedidas());
                jTextFieldUrlProducto.setText(productoMayorista.getUrlFotografia());
                jComboBoxIdCategoriaProducto.setSelectedItem(productoMayorista.getIdCategoria());
                jComboBoxIdLoteProducto.setSelectedItem(productoMayorista.getIdLote());
                jTextFieldPesoProducto.setText(String.valueOf(productoMayorista.getPesoTotal()));
                jTextFieldPrecioProducto.setText(String.valueOf(productoMayorista.getPrecioTotal()));
                jTextFieldValorProducto.setText(String.valueOf(productoMayorista.getValorUnidad()));

                this.jLabelIdProducto2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextFieldNombreProductoFocusGained

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
                } catch (IOException | GraphException | ClassNotFoundException ex) {
                    Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JLabel fondo3;
    private javax.swing.JLabel fondo4;
    private javax.swing.JLabel fondo5;
    private javax.swing.JLabel fondo6;
    private javax.swing.JLabel fondo7;
    private javax.swing.JButton jButtonAgregarBodega;
    private javax.swing.JButton jButtonAgregarCategoria;
    private javax.swing.JButton jButtonAgregarLote;
    private javax.swing.JButton jButtonAgregarProducto;
    private javax.swing.JButton jButtonAgregarUnidadTransporte;
    private javax.swing.JButton jButtonAgregarUsuario;
    private javax.swing.JButton jButtonBuscarImagenBodega;
    private javax.swing.JButton jButtonBuscarImagenBodega1;
    private javax.swing.JButton jButtonBuscarImagenBodega2;
    private javax.swing.JButton jButtonEditarBodega;
    private javax.swing.JButton jButtonEditarCategoria;
    private javax.swing.JButton jButtonEditarLote;
    private javax.swing.JButton jButtonEditarProducto;
    private javax.swing.JButton jButtonEditarUnidadTransporte;
    private javax.swing.JButton jButtonEditarUsuario;
    private javax.swing.JButton jButtonEliminarBodega;
    private javax.swing.JButton jButtonEliminarCategoria;
    private javax.swing.JButton jButtonEliminarLote;
    private javax.swing.JButton jButtonEliminarProducto;
    private javax.swing.JButton jButtonEliminarUnidadTransporte;
    private javax.swing.JButton jButtonLimpiarBodega;
    private javax.swing.JButton jButtonLimpiarCategoria;
    private javax.swing.JButton jButtonLimpiarLote;
    private javax.swing.JButton jButtonLimpiarProducto;
    private javax.swing.JButton jButtonLimpiarUnidadTransporte;
    private javax.swing.JButton jButtonLimpiarUsuario;
    private javax.swing.JButton jButtonModificarUsuario;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxIdCategoriaProducto;
    private javax.swing.JComboBox<String> jComboBoxIdLoteProducto;
    private javax.swing.JComboBox<String> jComboBoxRolUsuario;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JLabel jLabelIdProducto2;
    private javax.swing.JLabel jLabelIdUnidadTransporte;
    private javax.swing.JLabel jLabelIdUnidadTransporte2;
    private javax.swing.JLabel jLabelIdUsuario;
    private javax.swing.JLabel jLabelIdUsuario2;
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
    private javax.swing.JTextField jTextFieldCapacidadUnidadTransporte;
    private javax.swing.JTextField jTextFieldCodigoLote;
    private javax.swing.JTextField jTextFieldDescripcionCategoria;
    private javax.swing.JTextField jTextFieldDescripcionProducto;
    private javax.swing.JTextField jTextFieldDistanciaBodega;
    private javax.swing.JTextField jTextFieldLatitudBodega;
    private javax.swing.JTextField jTextFieldLongitudBodega;
    private javax.swing.JTextField jTextFieldMedidasProducto;
    private javax.swing.JTextField jTextFieldNombreBodega;
    private javax.swing.JTextField jTextFieldNombreCategoria;
    private javax.swing.JTextField jTextFieldNombreProducto;
    private javax.swing.JTextField jTextFieldNombreUsuario;
    private javax.swing.JTextField jTextFieldPesoProducto;
    private javax.swing.JTextField jTextFieldPlacaUnidadTransporte;
    private javax.swing.JTextField jTextFieldPrecioProducto;
    private javax.swing.JTextField jTextFieldUrlBodega;
    private javax.swing.JTextField jTextFieldUrlProducto;
    private javax.swing.JTextField jTextFieldUrlUnidadTransporte;
    private javax.swing.JTextField jTextFieldUserUsuario;
    private javax.swing.JTextField jTextFieldValorProducto;
    // End of variables declaration//GEN-END:variables
}
