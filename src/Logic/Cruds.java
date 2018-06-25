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
import TDA.BinaryTree.LinkedBinaryTree;
import TDA.BinaryTree.TreeException;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jeison
 */
public class Cruds implements Serializable{

    private Datos data;
    private LinkedList<Usuario> listaUsuarios;
    private AdjacencyMatrixGraph grafoBodegas;
    private HashMap<String, Categoria> hashMapCategoria;
    private TreeMap<Integer, Lote> treeMapLote;
    private LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte;
    private LinkedList<OrdenDistribucion> listaOrdenes;
    
    private LinkedList<ProductoMayorista> listaProductos;

    public Cruds() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException, TreeException {
        this.data = new Datos();
        this.listaUsuarios = this.data.getListaUsuarios();
        this.grafoBodegas = this.data.getGrafoBodegas();
        this.hashMapCategoria = this.data.getHashMapCategoria();
        this.treeMapLote = this.data.getTreeMapLote();
        this.linkedHashMapUnidadTransporte = this.data.getLinkedHashMapUnidadTransporte();
        this.listaProductos = this.data.getListaProductos();
        this.listaOrdenes = this.data.getListaOrdenes();
    }

    //CRUDs Categorías ----------------------------------------------------------------------------------------
    public void agregarCategoria(Categoria categoria) throws IOException {
        if (hashMapCategoria.isEmpty()) {
            hashMapCategoria.put(categoria.getNombre(), categoria);
            this.data.setHashMapCategoria(hashMapCategoria);
        } else {
            if (hashMapCategoria.containsKey(categoria.getNombre()) == true) {
                JOptionPane.showMessageDialog(null, "La clave ya existe");
            } else if (hashMapCategoria.containsValue(categoria) == true) {
                JOptionPane.showMessageDialog(null, "La categoria ya existe");
            } else {
                hashMapCategoria.put(categoria.getNombre(), categoria);
                this.data.setHashMapCategoria(hashMapCategoria);
            }

        }
    }

    public void editarCategoria(Categoria categoria) throws IOException {
        hashMapCategoria.replace(categoria.getNombre(), categoria);
        this.data.setHashMapCategoria(hashMapCategoria);
    }

    public void eliminarCategoria(Categoria categoria) throws IOException {
        if (hashMapCategoria.containsKey(categoria.getNombre()) == false) {
            JOptionPane.showMessageDialog(null, "La categoria no existe");
        } else {
            hashMapCategoria.remove(categoria.getNombre());
            this.data.setHashMapCategoria(hashMapCategoria);
        }
    }

    //CRUDs Lotes ---------------------------------------------------------------------------------------
    public void agregarLote(Lote lote) throws IOException {
        if (treeMapLote.isEmpty()) {
            treeMapLote.put(lote.getId(), lote);
            this.data.setTreeMapLote(treeMapLote);
            JOptionPane.showMessageDialog(null, "Lote agregado");
        } else {
            if (treeMapLote.containsKey(lote.getId()) == true) {
                JOptionPane.showMessageDialog(null, "La clave ya existe");
            } else if (treeMapLote.containsValue(lote) == true) {
                JOptionPane.showMessageDialog(null, "El lote ya existe");
            } else {
                treeMapLote.put(lote.getId(), lote);
                this.data.setTreeMapLote(treeMapLote);
                JOptionPane.showMessageDialog(null, "Lote agregado");
            }

        }
    }

    public void editarLote(Lote lote) throws IOException {
        treeMapLote.replace(lote.getId(), lote);
        this.data.setTreeMapLote(treeMapLote);
    }

    public void eliminarLote(Lote lote) throws IOException {
        if (treeMapLote.containsKey(lote.getId()) == false) {
            JOptionPane.showMessageDialog(null, "El lote no existe");
        } else {
            treeMapLote.remove(lote.getId());
            this.data.setTreeMapLote(treeMapLote);
        }
    }

    //CRUDs Unidades de transporte ----------------------------------------------------------------------------------------
    public void agregarUnidadTransporte(UnidadTransporte unidadTransporte) throws IOException {
        if (linkedHashMapUnidadTransporte.isEmpty()) {
            linkedHashMapUnidadTransporte.put(unidadTransporte.getId(), unidadTransporte);
            this.data.setLinkedHashMapUnidadTransporte(linkedHashMapUnidadTransporte);
        } else {
            if (linkedHashMapUnidadTransporte.containsKey(unidadTransporte.getId()) == true) {
                JOptionPane.showMessageDialog(null, "La clave ya existe");
            } else if (linkedHashMapUnidadTransporte.containsValue(unidadTransporte) == true) {
                JOptionPane.showMessageDialog(null, "La unidad de transporte ya existe");
            } else {
                linkedHashMapUnidadTransporte.put(unidadTransporte.getId(), unidadTransporte);
                this.data.setLinkedHashMapUnidadTransporte(linkedHashMapUnidadTransporte);
            }

        }
    }

    public void editarUnidadTransporte(UnidadTransporte unidadTransporte) throws IOException {
        linkedHashMapUnidadTransporte.replace(unidadTransporte.getId(), unidadTransporte);
        this.data.setLinkedHashMapUnidadTransporte(linkedHashMapUnidadTransporte);
    }

    public void eliminarUnidadTransporte(UnidadTransporte unidadTransporte) throws IOException {
        if (linkedHashMapUnidadTransporte.containsKey(unidadTransporte.getId()) == false) {
            JOptionPane.showMessageDialog(null, "La unidad de transporte no existe");
        } else {
            linkedHashMapUnidadTransporte.remove(unidadTransporte.getId());
            this.data.setLinkedHashMapUnidadTransporte(linkedHashMapUnidadTransporte);
        }
    }

    //CRUDs Productos ----------------------------------------------------------------------------------------
    public void agregarProductoMayorista(ProductoMayorista productoMayorista) {

    }

    public void editarProductoMayorista(ProductoMayorista productoMayorista) {

    }

    public void eliminarProductoMayorista(ProductoMayorista productoMayorista) {

    }

    //CRUDs Bodegas ----------------------------------------------------------------------------------------
    public void agregarBodega(Bodega bodega) throws GraphException, IOException {
        boolean exist = false;
        if (grafoBodegas.isEmpty()) {
            grafoBodegas.insertVertex(bodega);
            this.data.setGrafoBodegas(grafoBodegas);
        } else {
            for (int i = 0; i < grafoBodegas.getSize(); i++) {
                Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
                if (auxBodega.getId() == bodega.getId()) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The user already exists");
                }
            }//for
            if (exist == false) {
                grafoBodegas.insertVertex(bodega);
                this.data.setGrafoBodegas(grafoBodegas);
            }
        }
    }

    public void editarBodega(Bodega bodega) throws GraphException, IOException {
        boolean exist = false;
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            if (auxBodega.getId() == bodega.getId()) {
                exist = true;
                grafoBodegas.setVertex(i, bodega);
                this.data.setGrafoBodegas(grafoBodegas);
            }
        }
        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }

    public void eliminarBodega(Bodega bodega) throws GraphException, IOException {
        boolean exist = false;
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            if (auxBodega.getNombre().equals(bodega.getNombre())) {
                System.out.println(auxBodega.getNombre() + "<-------->" + bodega.getNombre());
                exist = true;
                grafoBodegas.deleteVertex(auxBodega);
                this.data.setGrafoBodegas(grafoBodegas);
            }
        }
        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }

    //CRUDs Usuario ----------------------------------------------------------------------------------------
    public void agregarUsuario(Usuario usuario) throws IOException {
        boolean exist = false;
        if (listaUsuarios.isEmpty()) {
            listaUsuarios.add(usuario);
            this.data.setListaUsuarios(listaUsuarios);
            exist = true;
        } else {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).getId() == (usuario.getId())) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The user already exists");
                }
            }
        }
        if (exist == false) {
            listaUsuarios.add(usuario);
            this.data.setListaUsuarios(listaUsuarios);
        }
        System.out.println(listaUsuarios.size());
        for (int i = 0; i < listaUsuarios.size(); i++) {
            System.out.println(listaUsuarios.get(i).toString());
        }
    }

    public void eliminarUsuario(Usuario usuario) throws IOException {
        boolean exist = false;
        System.out.println(listaUsuarios.size() + "Inicio");
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == (usuario.getId())) {
                exist = true;
                listaUsuarios.remove(i);
                this.data.setListaUsuarios(listaUsuarios);
                JOptionPane.showMessageDialog(null, "user removed succesfull");
            }
        }

        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }
    

    public void editarUsuario(Usuario usuario) throws IOException {
        boolean exist = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == (usuario.getId())) {
                exist = true;
                listaUsuarios.set(i, usuario);
                data.setListaUsuarios(listaUsuarios);
                JOptionPane.showMessageDialog(null, "correctly modified user");
            }
        }
       if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }
    
    //CRUDs Producto ----------------------------------------------------------------------------------------
    public void agregarProducto(ProductoMayorista producto) throws IOException {
        boolean exist = false;
        if (listaProductos.isEmpty()) {
            listaProductos.add(producto);
            this.data.setListaProductos(listaProductos);
            exist = true;
        } else {
            for (int i = 0; i < listaProductos.size(); i++) {
                if (listaProductos.get(i).getId() == (producto.getId())) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The user already exists");
                }
            }
        }
        if (exist == false) {
            listaProductos.add(producto);
            this.data.setListaProductos(listaProductos);
        }
        System.out.println(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println(listaProductos.get(i).toString());
        }
    }

    public void eliminarProducto(ProductoMayorista producto) throws IOException {
        boolean exist = false;
        System.out.println(listaProductos.size() + "Inicio");
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == (producto.getId())) {
                exist = true;
                listaProductos.remove(i);
                this.data.setListaProductos(listaProductos);
                JOptionPane.showMessageDialog(null, "user removed succesfull");
            }
        }

        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }
    

    public void editarProducto(ProductoMayorista producto) throws IOException {
        boolean exist = false;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == (producto.getId())) {
                exist = true;
                listaProductos.set(i, producto);
                data.setListaProductos(listaProductos);
                JOptionPane.showMessageDialog(null, "correctly modified user");
            }
        }
       if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }
    
    //CRUDs Orden ----------------------------------------------------------------------------------------
    public void agregarOrden(OrdenDistribucion orden) throws IOException{
        boolean exist = false;
        if (listaOrdenes.isEmpty()) {
            listaOrdenes.add(orden);
            this.data.setListaOrdenes(listaOrdenes);
            exist = true;
        } else {
            for (int i = 0; i < listaOrdenes.size(); i++) {
                if (listaOrdenes.get(i).getId() == (orden.getId())) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The user already exists");
                }
            }
        }
        if (exist == false) {
            listaOrdenes.add(orden);
            this.data.setListaOrdenes(listaOrdenes);
        }
    }

    public void eliminarOrden(OrdenDistribucion orden) throws IOException{
        boolean exist = false;
        System.out.println(listaOrdenes.size() + "Inicio");
        for (int i = 0; i < listaOrdenes.size(); i++) {
            if (listaOrdenes.get(i).getId() == (orden.getId())) {
                exist = true;
                listaOrdenes.remove(i);
                this.data.setListaOrdenes(listaOrdenes);
                JOptionPane.showMessageDialog(null, "user removed succesfull");
            }
        }

        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }
    

    public void editarOrden(OrdenDistribucion orden) throws IOException {
        boolean exist = false;
        for (int i = 0; i < listaOrdenes.size(); i++) {
            if (listaOrdenes.get(i).getId() == (orden.getId())) {
                exist = true;
                listaOrdenes.set(i, orden);
                data.setListaOrdenes(listaOrdenes);
                JOptionPane.showMessageDialog(null, "correctly modified user");
            }
        }
       if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }

}
