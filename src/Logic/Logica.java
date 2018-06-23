/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Bodega;
import Domain.Categoria;
import Domain.Lote;
import Domain.ProductoMayorista;
import Domain.UnidadTransporte;
import Domain.Usuario;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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

    public Logica() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException {
        //Clases
        this.datos = new Datos();

        //TDA's
        this.linkedHashMapUnidadTransporte = this.datos.getLinkedHashMapUnidadTransporte();
        this.hashMapCategoria = this.datos.getHashMapCategoria();
        this.treeMapLote = this.datos.getTreeMapLote();
        this.listaUsuarios = this.datos.getListaUsuarios();
        this.grafoBodegas = this.datos.getGrafoBodegas();
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

    public boolean existeUnidadTransporte(UnidadTransporte unidadTransporte) {
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
            if (unidadTransporte.getPlaca().equals(entry.getValue().getPlaca())) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCategoria(Categoria categoria) {
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            if (categoria.getNombre().equals(entry.getValue().getNombre())) {
                return true;
            }
        }
        return false;
    }

    public boolean existeLote(Lote lote) {
        for (Map.Entry<Integer, Lote> entry : treeMapLote.entrySet()) {
            if (lote.getCodigoLote().equals(entry.getValue().getCodigoLote())) {
                return true;
            }
        }
        return false;
    }

    public boolean existeUsuario(Usuario usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (usuario.getUsuario().equals(listaUsuarios.get(i).getUsuario())) {
                return true;
            }
        }
        return false;
    }

    public int getIdBodega(String nombre) throws GraphException {
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega bodega = (Bodega) grafoBodegas.getVertex(i);
            if (bodega.getNombre().equals(nombre)) {
                return bodega.getId();
            }
        }
        return -1;
    }

    public String getNombreBodega(int id) throws GraphException {
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega bodega = (Bodega) grafoBodegas.getVertex(i);
            if (bodega.getId() == id) {
                return bodega.getNombre();
            }
        }
        return null;
    }
}
