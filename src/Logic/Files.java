/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Categoria;
import Domain.Lote;
import Domain.UnidadTransporte;
import Domain.Usuario;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author jeison
 */
public class Files {

    private Data data;
    private LinkedList<Usuario> listaUsuarios;
    private HashMap<String, Categoria> hashMapCategoria;
    private LinkedHashMap<Integer, UnidadTransporte> linkedHashMapTransporte;
    private AdjacencyMatrixGraph grafoBodegas;

    public Files() throws IOException, GraphException {
        data = new Data();
        this.listaUsuarios = this.data.getListaUsuarios();
        this.hashMapCategoria = this.data.getHashMapCategoria();
        this.linkedHashMapTransporte = this.data.getLinkedHashMapUnidadTransporte();
        this.grafoBodegas = this.data.getGrafoBodegas();
    }

    public void ArchivoUsuarios() throws IOException {
        String usuarios = "";
        for (int i = 0; i < listaUsuarios.size(); i++) {
            usuarios += listaUsuarios.get(i).toString() + "\r\n";
        }
        File fileUsuarios = new File("Usuarios.txt");
        BufferedWriter bwUsuarios;
        bwUsuarios = new BufferedWriter(new FileWriter(fileUsuarios));
        bwUsuarios.write(usuarios);
        bwUsuarios.close();
    }

    public void ArchivoCategoria() throws IOException {
        String categorias = "";
        for (Map.Entry<String, Categoria> entry : hashMapCategoria.entrySet()) {
            categorias += entry.getValue() + "\r\n";
        }
        File fileCategorias = new File("Categorias.txt");
        BufferedWriter bwCategorias;
        bwCategorias = new BufferedWriter(new FileWriter(fileCategorias));
        bwCategorias.write(categorias);
        bwCategorias.close();

    }

    public void ArchivoTransporte() throws IOException {
        String unidades = "";
        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapTransporte.entrySet()) {
            unidades += entry.getValue() + "\r\n";
        }
        File fileTransporte = new File("Transporte.txt");
        BufferedWriter bwTransporte;
        bwTransporte = new BufferedWriter(new FileWriter(fileTransporte));
        bwTransporte.write(unidades);
        bwTransporte.close();
    }
    
    public void ArchivoBodega() throws IOException, GraphException{
        String bodegas="";
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            bodegas+=grafoBodegas.getVertex(i).toString()+"\r\n";
        }
        File fileBodegas = new File("Bodegas.txt");
        BufferedWriter bwBodegas;
        bwBodegas = new BufferedWriter(new FileWriter(fileBodegas));
        bwBodegas.write(bodegas);
        bwBodegas.close();
    }

}
