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
import java.util.Date;
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
    }

    private void llenarListaUsuarios() throws FileNotFoundException, IOException {
//        listaUsuarios.add(new Usuario(1, "jans", "Administrador", "jansino", "jansino21"));
//        listaUsuarios.add(new Usuario(2, "juam", "Operador", "panchito", "jansino21"));
//        listaUsuarios.add(new Usuario(3, "julia", "Administrador", "juli", "jansino21"));
//        listaUsuarios.add(new Usuario(4, "allana", "Operador", "ali", "jansino21"));
        String sCadena;
        FileReader fr = new FileReader("Usuarios.txt");
        BufferedReader bf = new BufferedReader(fr);
        while ((sCadena = bf.readLine()) != null) {
            Usuario usuario = new Usuario();
            String[] aux = sCadena.split(";");
            usuario.setId(Integer.parseInt(aux[0]));
            usuario.setNombre(aux[1]);
            usuario.setRol(aux[2]);
            usuario.setUsuario(aux[3]);
            usuario.setContrasena(aux[4]);
            listaUsuarios.add(usuario);
        }
        bf.close();
    }

    private void llenarBodegas() throws GraphException, FileNotFoundException, IOException {
//        grafoBodegas.insertVertex(new Bodega(1, "Bodega 1", "123.9214", "43543", 23, "url bodega 1"));
//        grafoBodegas.insertVertex(new Bodega(2, "Bodega 2", "234.678", "3542", 55, "url bodega 2"));
//        grafoBodegas.insertVertex(new Bodega(3, "Bodega 3", "4262.7", "55555", 6, "url bodega 3"));
//        grafoBodegas.insertVertex(new Bodega(4, "Bodega 4", "65675", "464.77", 122, "url bodega 4"));
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

    private void llenarCategorias() throws FileNotFoundException, IOException {
//        hashMapCategoria.put("Categoria 1", new Categoria(1, "Enlatados", "aaaa"));
//        hashMapCategoria.put("Categoria 2", new Categoria(2, "Legumbres", "bbbb"));
//        hashMapCategoria.put("Categoria 3", new Categoria(3, "Lacteos", "cccc"));
//        hashMapCategoria.put("Categoria 4", new Categoria(4, "Liquidos", "dddd"));
        String sCadena;
        FileReader fr = new FileReader("Categorias.txt");
        BufferedReader bf = new BufferedReader(fr);
        while ((sCadena = bf.readLine()) != null) {
                Categoria categoria = new Categoria();
                String[] aux = sCadena.split(";");
                categoria.setId(Integer.parseInt(aux[0]));
                categoria.setNombre(aux[1]);
                categoria.setDescripcion(aux[2]);
                hashMapCategoria.put(aux[1], categoria);
            }
            bf.close();
        
    }

    private void llenarLotes() {
        Date date = new Date(1, 2, 3);
        treeMapLote.put(1, new Lote(1, "A123", date, date));
        treeMapLote.put(2, new Lote(2, "B456", date, date));
        treeMapLote.put(3, new Lote(3, "C789", date, date));
        treeMapLote.put(4, new Lote(4, "A433", date, date));
    }

    private void llenarUnidadesTransporte() throws FileNotFoundException, IOException {
//        linkedHashMapUnidadTransporte.put(1, new UnidadTransporte(1, "123-A", "100", "url Unidad 1"));
//        linkedHashMapUnidadTransporte.put(2, new UnidadTransporte(2, "456-B", "11", "url Unidad 2"));
//        linkedHashMapUnidadTransporte.put(3, new UnidadTransporte(3, "789-C", "45", "url Unidad 3"));
//        linkedHashMapUnidadTransporte.put(4, new UnidadTransporte(4, "0234-D", "65", "url Unidad 4"));
//        linkedHashMapUnidadTransporte.put(5, new UnidadTransporte(4, "1657-E", "77", "url Unidad 5"));
        String sCadena;
        FileReader fr = new FileReader("Transporte.txt");
        BufferedReader bf = new BufferedReader(fr);
        while ((sCadena = bf.readLine()) != null) {
                UnidadTransporte transporte = new UnidadTransporte();
                String[] aux = sCadena.split(";");
                
                transporte.setId(Integer.parseInt(aux[0]));
                transporte.setPlaca(aux[1]);
                transporte.setCapacidad(aux[2]);
                transporte.setUrlFotografia(aux[3]);
                linkedHashMapUnidadTransporte.put(Integer.parseInt(aux[0]), transporte);
            }
            bf.close();
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
