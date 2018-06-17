/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Bodega;
import Domain.Categoria;
import Domain.Lote;
import Domain.UnidadTransporte;
import Domain.Usuario;
import TDA.BinaryTree.LinkedBinaryTree;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 *
 * @author jeison
 */
public class Data {

    private static LinkedBinaryTree arbolProductosMayoristas = new LinkedBinaryTree();
    private static LinkedList<Usuario> listaUsuarios = new LinkedList<>();
    private static AdjacencyMatrixGraph grafoBodegas = new AdjacencyMatrixGraph(100);
    private static HashMap<String, Categoria> hashMapCategoria = new HashMap<>();
    private static TreeMap<Integer, Lote> treeMapLote = new TreeMap<>();
    private static LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte = new LinkedHashMap<>();

    

    /**
     *
     * @throws IOException
     */
    public Data() throws IOException, GraphException {
        if (grafoBodegas.isEmpty()) {
            llenarBodegas();
        }
        if(linkedHashMapUnidadTransporte.isEmpty()){
            llenarUnidadesTransporte();
        }
    }

    private void fillListaUsuarios() throws FileNotFoundException, IOException {
//        String sCadena;
//        FileReader fr = new FileReader("Usuarios.txt");
//        BufferedReader bf = new BufferedReader(fr);
//        while ((sCadena = bf.readLine()) != null) {
//            Usuario usuario = new Usuario();
//            String[] aux = sCadena.split(";");
//            usuario.setId(0);
//            usuario.setNombre(aux[1]);
//            usuario.setRol(aux[2]);
//            usuario.setUsuario(aux[3]);
//            usuario.setContrasena(aux[4]);
//        }
//        bf.close();
    }

    private void llenarBodegas() throws GraphException {
        grafoBodegas.insertVerex(new Bodega(1, "Bodega 1", "123.9214", "43543", 23, "url bodega 1"));
        grafoBodegas.insertVerex(new Bodega(2, "Bodega 2", "234.678", "3542", 55, "url bodega 2"));
        grafoBodegas.insertVerex(new Bodega(3, "Bodega 3", "4262.7", "55555", 6, "url bodega 3"));
        grafoBodegas.insertVerex(new Bodega(4, "Bodega 4", "65675", "464.77", 122, "url bodega 4")); 
    }
    
    private void llenarUnidadesTransporte(){
        linkedHashMapUnidadTransporte.put(1, new UnidadTransporte(1, "123-A", "100", "url Unidad 1"));
        linkedHashMapUnidadTransporte.put(2, new UnidadTransporte(2, "456-B", "11", "url Unidad 2"));
        linkedHashMapUnidadTransporte.put(3, new UnidadTransporte(3, "789-C", "45", "url Unidad 3"));
        linkedHashMapUnidadTransporte.put(4, new UnidadTransporte(4, "0234-D", "65", "url Unidad 4"));
        linkedHashMapUnidadTransporte.put(5, new UnidadTransporte(4, "1657-E", "77", "url Unidad 5"));
    }

    public HashMap<String, Categoria> getHashMapCategoria() {
        return hashMapCategoria;
    }

    public void setHashMapCategoria(HashMap<String, Categoria> hashMapCategoria) {
        Data.hashMapCategoria = hashMapCategoria;
    }

    public TreeMap<Integer, Lote> getTreeMapLote() {
        return treeMapLote;
    }

    public void setTreeMapLote(TreeMap<Integer, Lote> treeMapLote) {
        Data.treeMapLote = treeMapLote;
    }

    public LinkedHashMap<Integer, UnidadTransporte> getLinkedHashMapUnidadTransporte() {
        return linkedHashMapUnidadTransporte;
    }

    public void setLinkedHashMapUnidadTransporte(LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte) {
        Data.linkedHashMapUnidadTransporte = linkedHashMapUnidadTransporte;
    }

    public LinkedBinaryTree getArbolProductosMayoristas() {
        return arbolProductosMayoristas;
    }

    public void setArbolProductosMayoristas(LinkedBinaryTree arbolProductosMayoristas) {
        Data.arbolProductosMayoristas = arbolProductosMayoristas;
    }

    public AdjacencyMatrixGraph getGrafoBodegas() {
        return grafoBodegas;
    }

    public void setGrafoBodegas(AdjacencyMatrixGraph grafoBodegas) {
        Data.grafoBodegas = grafoBodegas;
    }

    public LinkedList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void setListaUsuarios(LinkedList<Usuario> listaUsuarios) {
        Data.listaUsuarios = listaUsuarios;
    }

}
