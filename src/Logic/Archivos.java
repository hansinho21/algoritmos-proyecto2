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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author hvill
 */
public class Archivos implements Serializable {

    public Archivos() throws IOException, FileNotFoundException, GraphException {
        File archivoUsuario = new File("Usuarios.txt");
        File archivoCategoria = new File("Categorias.txt");
        File archivoLote = new File("Lotes.txt");
        File archivoUnidadTransporte = new File("UnidadesTransporte.txt");
        File archivoBodega = new File("Bodegas.txt");
        File archivoProducto = new File("Productos.txt");
        File archivoOrden = new File("Ordenes.txt");

        if (!archivoUsuario.exists()) {
            escribirArchivoUsuarios(new LinkedList<>());
        }
        if (!archivoCategoria.exists()) {
            escribirArchivoCategorias(new HashMap<>());
        }
        if (!archivoLote.exists()) {
            escribirArchivoLotes(new TreeMap<>());
        }
        if (!archivoUnidadTransporte.exists()) {
            escribirArchivoUnidadesTransporte(new LinkedHashMap<>());
        }
        if (!archivoBodega.exists()) {
            escribirArchivoBodegas(new AdjacencyMatrixGraph(100));
        }
        if(!archivoProducto.exists()){
            escribirArchivoProductos(new LinkedList<>());
        }
        if(!archivoOrden.exists()){
            escribirArchivoOrdenes(new LinkedList<>());
        }
    }

    //Archivo Usuarios---------------------------------------------------
    public LinkedList leerArchivoUsuarios() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("Usuarios.txt"));
        LinkedList linkedList = (LinkedList) objectIS.readObject();
        return linkedList;
    }

    public void escribirArchivoUsuarios(LinkedList<Usuario> linkedList) throws FileNotFoundException, IOException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("Usuarios.txt"));
        objectOS.writeObject(linkedList);
        objectOS.close();
    }

    //Archivo Categorias---------------------------------------------------
    public HashMap leerArchivoCategorias() throws FileNotFoundException, IOException, ClassNotFoundException {

        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("Categorias.txt"));
        LinkedList<Categoria> linkedList = (LinkedList) objectIS.readObject();

        //Paso lo del linkedlist a un hashmap
        HashMap<String, Categoria> hashMap = new HashMap<>();
        for (int i = 0; i < linkedList.size(); i++) {
            hashMap.put(linkedList.get(i).getNombre(), linkedList.get(i));
        }

        return hashMap;
    }

    public void escribirArchivoCategorias(HashMap<String, Categoria> hashMap) throws FileNotFoundException, IOException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("Categorias.txt"));

        //Paso lo del hashmap a un linkedlist
        LinkedList<Categoria> linkedList = new LinkedList<>();
        for (Map.Entry<String, Categoria> entry : hashMap.entrySet()) {
            linkedList.add(entry.getValue());

        }

        objectOS.writeObject(linkedList);
        objectOS.close();
    }

    //Archivo Lote---------------------------------------------------
    public TreeMap leerArchivoLotes() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("Lotes.txt"));
        LinkedList<Lote> linkedList = (LinkedList) objectIS.readObject();

        //Paso lo del linkedlist a un TreeMap
        TreeMap<Integer, Lote> treeMap = new TreeMap<>();
        for (int i = 0; i < linkedList.size(); i++) {
            treeMap.put(linkedList.get(i).getId(), linkedList.get(i));
        }

        return treeMap;
    }

    public void escribirArchivoLotes(TreeMap<Integer, Lote> treeMap) throws FileNotFoundException, IOException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("Lotes.txt"));

        //Paso lo del TreeMap a un linkedlist
        LinkedList<Lote> linkedList = new LinkedList<>();
        for (Map.Entry<Integer, Lote> entry : treeMap.entrySet()) {
            linkedList.add(entry.getValue());

        }

        objectOS.writeObject(linkedList);
        objectOS.close();
    }

    //Archivo Lote---------------------------------------------------
    public LinkedHashMap leerArchivoUnidadesTransporte() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("UnidadesTransporte.txt"));
        LinkedList<UnidadTransporte> linkedList = (LinkedList) objectIS.readObject();

        //Paso lo del linkedlist a un LinkedHashMap
        LinkedHashMap<Integer, UnidadTransporte> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedHashMap.put(linkedList.get(i).getId(), linkedList.get(i));
        }

        return linkedHashMap;
    }

    public void escribirArchivoUnidadesTransporte(LinkedHashMap<Integer, UnidadTransporte> treeMap) throws FileNotFoundException, IOException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("UnidadesTransporte.txt"));

        //Paso lo del LinkedHashMap a un linkedlist
        LinkedList<UnidadTransporte> linkedList = new LinkedList<>();
        for (Map.Entry<Integer, UnidadTransporte> entry : treeMap.entrySet()) {
            linkedList.add(entry.getValue());

        }

        objectOS.writeObject(linkedList);
        objectOS.close();
    }

    //Archivo Bodegas---------------------------------------------------
    public AdjacencyMatrixGraph leerArchivoBodegas() throws FileNotFoundException, IOException, ClassNotFoundException, GraphException {
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("Bodegas.txt"));
        LinkedList linkedList = (LinkedList) objectIS.readObject();

        AdjacencyMatrixGraph grafo = new AdjacencyMatrixGraph(100);
        for (int i = 0; i < linkedList.size(); i++) {
            grafo.insertVertex(linkedList.get(i));
        }

        return grafo;
    }

    public void escribirArchivoBodegas(AdjacencyMatrixGraph grafo) throws FileNotFoundException, IOException, GraphException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("Bodegas.txt"));

        LinkedList<Bodega> linkedList = new LinkedList();
        if (grafo.isEmpty() == false) {
            for (int i = 0; i < grafo.getSize(); i++) {
                linkedList.add((Bodega) grafo.getVertex(i));
            }
        }

        objectOS.writeObject(linkedList);
        objectOS.close();
    }
    
    //Archivo Productos---------------------------------------------------
    public LinkedBinaryTree leerArchivoProductos() throws FileNotFoundException, IOException, ClassNotFoundException, TreeException {
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("Productos.txt"));
        LinkedList linkedList = (LinkedList) objectIS.readObject();
        
        LinkedBinaryTree binaryTree = new LinkedBinaryTree();
        for (int i = 0; i < linkedList.size(); i++) {
            binaryTree.insert(linkedList.get(i));
        }
                
        return binaryTree;
    }

    public void escribirArchivoProductos(LinkedList<ProductoMayorista> linkedList) throws FileNotFoundException, IOException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("Productos.txt"));
        objectOS.writeObject(linkedList);
        objectOS.close();
    }
    
    //Archivo Usuarios---------------------------------------------------
    public LinkedList leerArchivoOrdenes() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("Ordenes.txt"));
        LinkedList linkedList = (LinkedList) objectIS.readObject();
        return linkedList;
    }

    public void escribirArchivoOrdenes(LinkedList<OrdenDistribucion> linkedList) throws FileNotFoundException, IOException {
        ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream("Ordenes.txt"));
        objectOS.writeObject(linkedList);
        objectOS.close();
    }

}
