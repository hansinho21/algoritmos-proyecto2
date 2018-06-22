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
import TDA.BinaryTree.LinkedBinaryTree;
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
public class Datos implements Serializable{

    //Clases
    private Archivos archivos = new Archivos();
    
    //TDA's
    private static LinkedBinaryTree arbolProductosMayoristas = new LinkedBinaryTree();
    private static LinkedList<Usuario> listaUsuarios = new LinkedList<>();
    private static AdjacencyMatrixGraph grafoBodegas = new AdjacencyMatrixGraph(100);
    private static HashMap<String, Categoria> hashMapCategoria = new HashMap<>();
    private static TreeMap<Integer, Lote> treeMapLote = new TreeMap<>();
    private static LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte = new LinkedHashMap<>();
    
    //lista provisional mientras el profe sube el arbol arreglado
    private static LinkedList<ProductoMayorista> listaProductos = new LinkedList<>();

    /**
     *
     * @throws IOException
     */
    public Datos() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException {
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
        if(listaProductos.isEmpty()){
            llenarProductos();
        }
    }

    private void llenarListaUsuarios() throws FileNotFoundException, IOException, ClassNotFoundException {
//        listaUsuarios.add(new Usuario(1, "jans", "Administrador", "jansino", "jansino21"));
//        listaUsuarios.add(new Usuario(2, "juam", "Operador", "panchito", "jansino21"));
//        listaUsuarios.add(new Usuario(3, "julia", "Administrador", "juli", "jansino21"));
//        listaUsuarios.add(new Usuario(4, "allana", "Operador", "ali", "jansino21"));

        this.listaUsuarios = this.archivos.leerArchivoUsuarios();
    }

    private void llenarBodegas() throws GraphException, FileNotFoundException, IOException {
        grafoBodegas.insertVertex(new Bodega(23, "Bodega Test", "9.8273563", "-83.8706482", 23, "url bodega 1"));
//        grafoBodegas.insertVertex(new Bodega(24, "Bodega sdf", "234.678", "3542", 55, "url bodega 2"));
//        grafoBodegas.insertVertex(new Bodega(35, "Bodega dgdf", "4262.7", "55555", 6, "url bodega 3"));
//        grafoBodegas.insertVertex(new Bodega(47, "Bodega jfjhgjh", "65675", "464.77", 122, "url bodega 4"));
        String sCadena;
        FileReader fr = new FileReader("Bodegas.txt");
        BufferedReader bf = new BufferedReader(fr);
        while ((sCadena = bf.readLine()) != null) {
                Bodega bodega = new Bodega();
                String[] aux = sCadena.split(";");
                bodega.setId(Integer.parseInt(aux[0]));
                bodega.setNombre(aux[1]);
                bodega.setLatitud(aux[2]);
                bodega.setLongitud(aux[3]);
                bodega.setDistanciaCentroOperaciones(Float.parseFloat(aux[4]));
                bodega.setUrlFotografia(aux[5]);
                grafoBodegas.insertVertex(bodega);
            }
            bf.close();
    }

    private void llenarCategorias() throws FileNotFoundException, IOException, ClassNotFoundException {
//        hashMapCategoria.put("Categoria 1", new Categoria(1, "Enlatados", "aaaa"));
//        hashMapCategoria.put("Categoria 2", new Categoria(2, "Legumbres", "bbbb"));
//        hashMapCategoria.put("Categoria 3", new Categoria(3, "Lacteos", "cccc"));
//        hashMapCategoria.put("Categoria 4", new Categoria(4, "Liquidos", "dddd"));
        
        this.hashMapCategoria = this.archivos.leerArchivoCategorias();
    }

    private void llenarLotes() throws IOException, FileNotFoundException, ClassNotFoundException {
//        Date date = new Date(1, 2, 3);
//        treeMapLote.put(1, new Lote(1, "A123", date, date));
//        treeMapLote.put(2, new Lote(2, "B456", date, date));
//        treeMapLote.put(3, new Lote(3, "C789", date, date));
//        treeMapLote.put(4, new Lote(4, "A433", date, date));

        this.treeMapLote = this.archivos.leerArchivoLotes();
    }

    private void llenarUnidadesTransporte() throws FileNotFoundException, IOException, ClassNotFoundException {
//        linkedHashMapUnidadTransporte.put(1, new UnidadTransporte(1, "123-A", "100", "url Unidad 1"));
//        linkedHashMapUnidadTransporte.put(2, new UnidadTransporte(2, "456-B", "11", "url Unidad 2"));
//        linkedHashMapUnidadTransporte.put(3, new UnidadTransporte(3, "789-C", "45", "url Unidad 3"));
//        linkedHashMapUnidadTransporte.put(4, new UnidadTransporte(4, "0234-D", "65", "url Unidad 4"));
//        linkedHashMapUnidadTransporte.put(5, new UnidadTransporte(4, "1657-E", "77", "url Unidad 5"));
        
        this.linkedHashMapUnidadTransporte = this.archivos.leerArchivoUnidadesTransporte();
    }
    
    private void llenarProductos() {
        listaProductos.add(new ProductoMayorista(1, "Procucto 1", "kg", 1, 22, "x", 1, 1, 213, ""));
        listaProductos.add(new ProductoMayorista(1, "Procucto 2", "kg", 1, 22, "x", 1, 1, 213, ""));
        listaProductos.add(new ProductoMayorista(1, "Procucto 3", "kg", 1, 22, "x", 1, 1, 213, ""));
        listaProductos.add(new ProductoMayorista(1, "Procucto 4", "kg", 1, 22, "x", 1, 1, 213, ""));
    }

    public LinkedList<ProductoMayorista> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<ProductoMayorista> listaProductos) {
        Datos.listaProductos = listaProductos;
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

    public LinkedBinaryTree getArbolProductosMayoristas() {
        return arbolProductosMayoristas;
    }

    public void setArbolProductosMayoristas(LinkedBinaryTree arbolProductosMayoristas) {
        Datos.arbolProductosMayoristas = arbolProductosMayoristas;
    }

    public AdjacencyMatrixGraph getGrafoBodegas() {
        return grafoBodegas;
    }

    public void setGrafoBodegas(AdjacencyMatrixGraph grafoBodegas) {
        Datos.grafoBodegas = grafoBodegas;
    }

    public LinkedList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(LinkedList<Usuario> listaUsuarios) throws IOException {
//        Datos.listaUsuarios = listaUsuarios;
        this.archivos.escribirArchivoUsuarios(listaUsuarios);
    }

}
