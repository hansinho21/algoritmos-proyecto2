/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Categoria;
import Domain.Lote;
import Domain.ProductoMayorista;
import Domain.UnidadTransporte;
import Domain.Usuario;
import TDA.Graph.GraphException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class Logica {

    //Clases
    private Datos datos;

    //TDA's
    private static LinkedHashMap<Integer, UnidadTransporte> linkedHashMapUnidadTransporte;
    private static HashMap<String, Categoria> hashMapCategoria;
    private static TreeMap<Integer, Lote> treeMapLote;
    private static LinkedList<Usuario> listaUsuarios;

    public Logica() throws IOException, GraphException, FileNotFoundException, ClassNotFoundException {
        //Clases
        this.datos = new Datos();

        //TDA's
        this.linkedHashMapUnidadTransporte = this.datos.getLinkedHashMapUnidadTransporte();
        this.hashMapCategoria = this.datos.getHashMapCategoria();
        this.treeMapLote = this.datos.getTreeMapLote();
        this.listaUsuarios = this.datos.getListaUsuarios();
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
    
    public boolean existeUsuario(Usuario usuario){
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if(usuario.getUsuario().equals(listaUsuarios.get(i).getUsuario())){
                return true;
            }
        }
        return false;
    }

//    public UnidadTransporte getSiguienteCapacidadTransporte(UnidadTransporte unidadTransporte){
//        UnidadTransporte auxTransporte = new UnidadTransporte();
//        UnidadTransporte salida = new UnidadTransporte();
//        int cont1 = 0;
//        int cont2 = 0;
//        for (Map.Entry<Integer, UnidadTransporte> entry : linkedHashMapUnidadTransporte.entrySet()) {
//            auxTransporte = entry.getValue();
//            for (Map.Entry<Integer, UnidadTransporte> entry1 : linkedHashMapUnidadTransporte.entrySet()) {
//                
//            }
//        }
//    }
}
