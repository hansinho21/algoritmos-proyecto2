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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 *
 * @author jeison
 */
public class Datos implements Serializable {

    //Clases
    private Archivos archivos = new Archivos();

    //TDA's
    private static LinkedBinaryTree arbolProductosMayoristas = new LinkedBinaryTree();
    private static LinkedList<Usuario> listaUsuarios = new LinkedList<>();
    private static AdjacencyMatrixGraph grafoBodegas = new AdjacencyMatrixGraph(100);
    private static HashMap<String, Categoria> hashMapCategoria = new HashMap<>();
    private static TreeMap<Integer, Lote> treeMapLote = new TreeMap<>();
    private static LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte = new LinkedHashMap<>();
    private static LinkedList<OrdenDistribucion> listaOrdenes = new LinkedList<>();

    //lista provisional mientras el profe sube el arbol arreglado
    private static LinkedList<ProductoMayorista> listaProductos = new LinkedList<>();

    /**
     * constructor
     * @throws IOException io
     * @throws GraphException grafo
     * @throws FileNotFoundException archivo
     * @throws ClassNotFoundException clase
     * @throws TreeException arbol
     */
    public Datos() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException, TreeException {
        if (grafoBodegas.isEmpty()) {
            llenarBodegas();
        }
        if (hashMapCategoria.isEmpty()) {
            llenarCategorias();
        }
        if (treeMapLote.isEmpty()) {
            llenarLotes();
        }
        if (linkedHashMapUnidadTransporte.isEmpty()) {
            llenarUnidadesTransporte();
        }
        if (listaUsuarios.isEmpty()) {
            llenarListaUsuarios();
        }
        if (listaProductos.isEmpty()) {
            llenarProductos();
        }
        if (listaOrdenes.isEmpty()) {
            llenarOrdenes();
        }
    }

    /**
     * llenar la lista de usuarios
     * @throws FileNotFoundException archivo
     * @throws IOException io
     * @throws ClassNotFoundException clase 
     */
    private void llenarListaUsuarios() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.listaUsuarios = this.archivos.leerArchivoUsuarios();
    }

    /**
     * llenar el grafo bodegas
     * @throws GraphException grafo
     * @throws FileNotFoundException archivo
     * @throws IOException io
     * @throws ClassNotFoundException clase 
     */
    private void llenarBodegas() throws GraphException, FileNotFoundException, IOException, ClassNotFoundException {
        this.grafoBodegas = this.archivos.leerArchivoBodegas();
    }

    /**
     * llenar tda categorias
     * @throws FileNotFoundException archivo
     * @throws IOException io
     * @throws ClassNotFoundException clase 
     */
    private void llenarCategorias() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.hashMapCategoria = this.archivos.leerArchivoCategorias();
    }

    /**
     * llenar  tda lotes
     * @throws IOException io
     * @throws FileNotFoundException archivo
     * @throws ClassNotFoundException clase
     */
    private void llenarLotes() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.treeMapLote = this.archivos.leerArchivoLotes();
    }

    /**
     * llenar tda unidades transporte
     * @throws FileNotFoundException archivo
     * @throws IOException io
     * @throws ClassNotFoundException clase 
     */
    private void llenarUnidadesTransporte() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.linkedHashMapUnidadTransporte = this.archivos.leerArchivoUnidadesTransporte();
    }

    /**
     * llenar tda pro
     * @throws IOException io
     * @throws FileNotFoundException file
     * @throws ClassNotFoundException clase
     * @throws TreeException arbol
     */
    private void llenarProductos() throws IOException, FileNotFoundException, ClassNotFoundException, TreeException {
        this.listaProductos = this.archivos.leerArchivoProductos();
    }

    /**
     *  llenar tda ordenes
     * @throws FileNotFoundException archivo
     * @throws IOException io
     * @throws ClassNotFoundException clase 
     */
    private void llenarOrdenes() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.listaOrdenes = this.archivos.leerArchivoOrdenes();
    }

    /**
     * geters y seters
     * @return LinkedList
     */
    public LinkedList<ProductoMayorista> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<ProductoMayorista> listaProductos) throws IOException {
//        Datos.listaProductos = listaProductos;
        this.archivos.escribirArchivoProductos(listaProductos);
    }

    public HashMap<String, Categoria> getHashMapCategoria() {
        return hashMapCategoria;
    }

    public void setHashMapCategoria(HashMap<String, Categoria> hashMapCategoria) throws IOException {
//        Datos.hashMapCategoria = hashMapCategoria;
        this.archivos.escribirArchivoCategorias(hashMapCategoria);
    }

    public TreeMap<Integer, Lote> getTreeMapLote() {
        return treeMapLote;
    }

    public void setTreeMapLote(TreeMap<Integer, Lote> treeMapLote) throws IOException {
//        Datos.treeMapLote = treeMapLote;
        this.archivos.escribirArchivoLotes(treeMapLote);
    }

    public LinkedHashMap<Integer, UnidadTransporte> getLinkedHashMapUnidadTransporte() {
        return linkedHashMapUnidadTransporte;
    }

    public void setLinkedHashMapUnidadTransporte(LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte) throws IOException {
//        Datos.linkedHashMapUnidadTransporte = linkedHashMapUnidadTransporte;
        this.archivos.escribirArchivoUnidadesTransporte(linkedHashMapUnidadTransporte);
    }

    public AdjacencyMatrixGraph getGrafoBodegas() {
        return grafoBodegas;
    }

    public void setGrafoBodegas(AdjacencyMatrixGraph grafoBodegas) throws IOException, FileNotFoundException, GraphException {
        this.archivos.escribirArchivoBodegas(grafoBodegas);
    }

    public LinkedList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(LinkedList<Usuario> listaUsuarios) throws IOException {
//        Datos.listaUsuarios = listaUsuarios;
        this.archivos.escribirArchivoUsuarios(listaUsuarios);
    }

    public LinkedList<OrdenDistribucion> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(LinkedList<OrdenDistribucion> listaOrdenes) throws IOException{
        this.archivos.escribirArchivoOrdenes(listaOrdenes);
    }

}
