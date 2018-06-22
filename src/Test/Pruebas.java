/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Domain.Bodega;
import Domain.Lote;
import Domain.UnidadTransporte;
import Domain.Usuario;
import Logic.Archivos;
import Logic.Cruds;
import Logic.Datos;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeison
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, GraphException {
        // TODO code application logic here
//        Cruds cruds = new Cruds();
//        Data data = new Data();
//        AdjacencyMatrixGraph m = new AdjacencyMatrixGraph(3);
//        Bodega a = new Bodega(1, "b1", "324243", "343242", 0, "assdads");
//        Bodega b = new Bodega(2, "b2", "324243", "343242", 0, "assdads");
//        Bodega c = new Bodega(3, "b3", "324243", "343242", 0, "assdads");
//        m.insertVertex(a);
//        m.insertVertex(b);
//        m.insertVertex(c);
//        boolean x = m.existVertex(a);
//        System.out.println(x);
//        m.deleteVertex(a);
//        System.out.println(x);
 
            
//        LinkedList<Usuario> lista= new LinkedList<>();
//        Usuario u = new Usuario();
//        u.setContrasena("dgdfg");
//        u.setId(2);
//        u.setNombre("adasda");
//        u.setRol("asdsad");
//        u.setUsuario("adasda");
//        Usuario v = new Usuario();
//        v.setContrasena("dgdfg");
//        v.setId(4);
//        v.setNombre("adasda");
//        v.setRol("asdsad");
//        v.setUsuario("adasda");
////        cruds.AgregarUsuario(v);
//        cruds.AgregarUsuario(u);
//        cruds.eliminarUsuario(u);



//        TreeMap<Integer, Lote> datos = data.getTreeMapLote();
//        Lote lote = new Lote(1, "A", new Date(0, 0, 0), new Date(1, 1, 1));
//        Lote lote2 = new Lote(2, "B", new Date(0, 0, 0), new Date(1, 1, 1));
//        cruds.agregarLote(lote);
//        cruds.agregarLote(lote2);
//        cruds.agregarLote(new Lote(3, "C", new Date(0, 0, 0), new Date(1, 1, 1)));
//        cruds.agregarLote(new Lote(4, "D", new Date(0, 0, 0), new Date(1, 1, 1)));
//
//
//        for (Map.Entry<Integer, Lote> entry : datos.entrySet()) {
//            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString() + "\n");
//        }
//        
//        cruds.eliminarLote(lote);
//
//        
//        for (Map.Entry<Integer, Lote> entry : datos.entrySet()) {
//            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
//        }
//        System.out.println("\n");
//        
//        Lote auxLote = new Lote(2, "BBBBB", new Date(0, 0, 0), new Date(1, 1, 1));
//        
//        datos.replace(2, auxLote);
//        
//        for (Map.Entry<Integer, Lote> entry : datos.entrySet()) {
//            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue().toString());
//        }
        
    }

}
