/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Bodega;
import Domain.Categoria;
import Domain.Lote;
import Domain.OrdenDistribucion;
import Domain.ProductoMayorista;
import Domain.UnidadTransporte;
import Domain.Usuario;
import TDA.BinaryTree.TreeException;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hvill
 */
public class Logica implements Serializable {

    //Clases
    private Datos datos;

    //TDA's
    private static LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte;
    private static HashMap<String, Categoria> hashMapCategoria;
    private static TreeMap<Integer, Lote> treeMapLote;
    private static LinkedList<Usuario> listaUsuarios;
    private static AdjacencyMatrixGraph grafoBodegas;
    private static LinkedList<ProductoMayorista> listaProductos;
    private static LinkedList<OrdenDistribucion> listaOrdenes = new LinkedList<>();

    public Logica() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException, TreeException {
        //Clases
        this.datos = new Datos();

        //TDA's
        this.linkedHashMapUnidadTransporte = this.datos.getLinkedHashMapUnidadTransporte();
        this.hashMapCategoria = this.datos.getHashMapCategoria();
        this.treeMapLote = this.datos.getTreeMapLote();
        this.listaUsuarios = this.datos.getListaUsuarios();
        this.grafoBodegas = this.datos.getGrafoBodegas();
        this.listaProductos = this.datos.getListaProductos();
        this.listaOrdenes = this.datos.getListaOrdenes();
    }

    /**
     * Valida si la tecla precionada es un número o no.
     *
     * @param event KeyEvent
     * @return Retorna true si la tecla presionada es un número y un false si no
     * lo es.
     */
    public boolean esNumero(java.awt.event.KeyEvent event) {
        char charType = event.getKeyChar();
        if (Character.isDigit(charType)) {
            return true;
        }
        return false;
    }

    /**
     * Agrega una bodega a la tabla de la clase Modulo2
     * @param bodega Bodega
     * @param tableModel tabla
     * @param contTable  contador
     * @throws GraphException grafo
     */
    public void agregarBodega(Bodega bodega, DefaultTableModel tableModel, int contTable) throws GraphException {
        tableModel.insertRow(contTable, new Object[]{});
        tableModel.setValueAt(bodega.getNombre(), contTable, 0);
    }

    /**
     * Agrega un producto a la tabla de la clase Modulo2
     *
     * @param producto Producto
     * @param tableModel tabla
     * @param contTable contador
     * @throws GraphException grafo
     */
    public void agregarProductos(ProductoMayorista producto, DefaultTableModel tableModel, int contTable) throws GraphException {
        tableModel.insertRow(contTable, new Object[]{});
        tableModel.setValueAt(producto.getId(), contTable, 0);
        tableModel.setValueAt(producto.getNombre(), contTable, 1);
        tableModel.setValueAt(producto.getUnidadMedidas(), contTable, 2);
        tableModel.setValueAt(producto.getValorUnidad(), contTable, 3);
        tableModel.setValueAt(producto.getIdCategoria(), contTable, 4);
        tableModel.setValueAt(producto.getPrecioTotal(), contTable, 5);
        tableModel.setValueAt(producto.getPesoTotal(), contTable, 6);
    }

    /**
     * Agrega un producto a la tabla de la clase Modulo1
     *
     * @param nombreProducto String
     * @param tableModel tabla
     * @param contTable contador
     * @param listaProductos lsta
     * @param peso int
     * @param precio double
     */
    public void agergarProducto(String nombreProducto, DefaultTableModel tableModel, int contTable, LinkedList listaProductos, int peso, double precio) {
        for (int i = 0; i < listaProductos.size(); i++) {
            ProductoMayorista auxProducto = (ProductoMayorista) listaProductos.get(i);
            if (auxProducto.getNombre().equals(nombreProducto)) {
                tableModel.insertRow(contTable, new Object[]{});
                tableModel.setValueAt(auxProducto.getNombre(), contTable, 0);
                tableModel.setValueAt(auxProducto.getPrecioTotal(), contTable, 1);
                tableModel.setValueAt(auxProducto.getPesoTotal(), contTable, 2);

                peso += auxProducto.getPesoTotal();
                precio += auxProducto.getPrecioTotal();
                break;
            }
        }
    }

    /**
     * Valida si existe una unidad de transporte
     *
     * @param unidadTransporte Unidad
     * @return boolean
     */
    public boolean existeUnidadTransporte(UnidadTransporte unidadTransporte) {
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
            if (unidadTransporte.getPlaca().equals(entry.getValue().getPlaca())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si esxiste una categoria
     *
     * @param categoria categoria
     * @return boolean
     */
    public boolean existeCategoria(Categoria categoria) {
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            if (categoria.getNombre().equals(entry.getValue().getNombre())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si existe un lote
     *
     * @param lote lote
     * @return boolean
     */
    public boolean existeLote(Lote lote) {
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            if (lote.getCodigoLote().equals(entry.getValue().getCodigoLote())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si existe un usuario
     *
     * @param usuario String
     * @return boolean
     */
    public boolean existeUsuario(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (usuario.getUsuario().equals(listaUsuarios.get(i).getUsuario())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el id de una bodega mediante el nombre de la bodega
     *
     * @param nombre String
     * @return int
     * @throws GraphException grafo
     */
    public int getIdBodega(String nombre) throws GraphException {
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega bodega = (Bodega) grafoBodegas.getVertex(i);
            if (bodega.getNombre().equals(nombre)) {
                return bodega.getId();
            }
        }
        return -1;
    }

    /**
     * Obtiene el nombre de una bodega mediante el id de la bodega
     *
     * @param id int
     * @return String
     * @throws GraphException grafo
     */
    public String getNombreBodega(int id) throws GraphException {
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega bodega = (Bodega) grafoBodegas.getVertex(i);
            if (bodega.getId() == id) {
                return bodega.getNombre();
            }
        }
        return null;
    }

    /**
     * Obtiene el id de un operador mediante el nombre del operador
     *
     * @param nombre String
     * @return String
     */
    public int getIdOperador(String nombre) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (nombre.equals(listaUsuarios.get(i).getNombre()) && listaUsuarios.get(i).getRol().equals("Operador")) {
                return listaUsuarios.get(i).getId();
            }
        }
        return -1;
    }

    /**
     * Obtiene el nombre de un operador mediante el id del operador
     *
     * @param id int
     * @return String
     */
    public String getNombreOperador(int id) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (id == listaUsuarios.get(i).getId()) {
                return listaUsuarios.get(i).getNombre();
            }
        }
        return null;
    }

    /**
     * Obtiene un objeto tipo Producto Mayorista mediante el nombre del producto
     *
     * @param nombre String
     * @return Producto
     */
    public ProductoMayorista getProducto(String nombre) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getNombre().equals(nombre)) {
                return listaProductos.get(i);
            }
        }
        return null;
    }

    /**
     * Obtiene una lista de productos filtrandolos por el id de categoria y el
     * id de lote
     *
     * @param idCategoria int
     * @param idLote int
     * @return LinkedList
     */
    public LinkedList<ProductoMayorista> getListaProductosPorCategoriaYLote(int idCategoria, int idLote) {
        LinkedList<ProductoMayorista> linkedList = new LinkedList<>();
        for (int i = 0; i < this.listaProductos.size(); i++) {
            ProductoMayorista producto = this.listaProductos.get(i);
            if (producto.getIdLote() == idLote && producto.getIdCategoria() == idCategoria) {
                linkedList.add(producto);
            }
        }
        return linkedList;
    }

    /**
     * Obtiene una lista de Productos Mayoristas que están asociados al id lote
     * pasado como parametro
     *
     * @param idLote int
     * @return LinkedList
     */
    public LinkedList<ProductoMayorista> getListaProductosPorLote(int idLote) {
        LinkedList<ProductoMayorista> linkedList = new LinkedList<>();
        for (int i = 0; i < this.listaProductos.size(); i++) {
            ProductoMayorista producto = this.listaProductos.get(i);
            if (producto.getIdLote() == idLote) {
                linkedList.add(producto);
            }
        }
        return linkedList;
    }

    /**
     * Obtiene un objeto tipo Bodega mediante el id de una bodega
     *
     * @param idBodega int
     * @return int id
     * @throws GraphException grafo
     */
    public Bodega getBodegaPorIdBodega(int idBodega) throws GraphException {
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega bodega = (Bodega) grafoBodegas.getVertex(i);
            if (bodega.getId() == idBodega) {
                return bodega;
            }
        }
        return null;
    }

    /**
     * Obtiene un objeto tipo Lote mediante el id
     *
     * @param idLote int
     * @return int id
     */
    public Lote getLotePorIdLote(int idLote) {
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            if (entry.getValue().getId() == idLote) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Obtiene un objeto tipo categoria mediante el nombre
     *
     * @param nombre String
     * @return Categoria
     */
    public Categoria getCategoria(String nombre) {
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            if (entry.getValue().getNombre().equals(nombre)) {
                return entry.getValue();
            }
        }
        return null;
    }


    /**
     * agragar un producto
     * @param orden Orden distribucion
     * @param producto lista
     * @param tableModel tabla
     * @param contTable int
     * @throws GraphException grafo
     */
    public void agregarTablaHistorial(OrdenDistribucion orden, ProductoMayorista producto, DefaultTableModel tableModel, int contTable) throws GraphException {
        tableModel.insertRow(contTable, new Object[]{});
        tableModel.setValueAt(getNombreBodega(orden.getIdBodegaDestino()), contTable, 0);
        tableModel.setValueAt(producto.getNombre(), contTable, 1);
        tableModel.setValueAt(getCategoria(producto.getIdCategoria()), contTable, 2);
        tableModel.setValueAt(getLotePorIdLote(producto.getIdLote()).getCodigoLote(), contTable, 3);
        tableModel.setValueAt(getNombreOperador(orden.getIdOperador()), contTable, 4);
        contTable++;
    }

    /**
     * Carga un hashmap con una bodega y la lista de productos que estén asociados al id de lote pasado como parametro
     * @param idLote idLote
     * @return Retorna un hashMap con la información.
     */
    public HashMap getMapaBodegas(int idLote) {
        HashMap<Integer, LinkedList<ProductoMayorista>> hashMap = new HashMap<>();
        boolean existe = false;
        for (int i = 0; i < listaOrdenes.size(); i++) {
            LinkedList<ProductoMayorista> linkedList = new LinkedList<>();
            LinkedList<ProductoMayorista> auxListaProductos = listaOrdenes.get(i).getListaProductos();
            for (int j = 0; j < auxListaProductos.size(); j++) {
                if (auxListaProductos.get(j).getIdLote() == idLote) {
                    linkedList.add(auxListaProductos.get(j));
                    existe = true;
                }
            }
            if (existe == true) {
                hashMap.put(listaOrdenes.get(i).getIdBodegaDestino(), linkedList);
            }
            existe = false;
        }
        return hashMap;
    }

    /**
     * Retorna el nombre de una categoria segun su id
     * @param idCategoria
     * @return Retorna el nombre de la categoria
     */
    public String getCategoria(int idCategoria) {
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            if (entry.getValue().getId() == idCategoria) {
                return entry.getValue().getNombre();
            }
        }
        return null;
    }

}
