/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.ProductoMayorista;
import Domain.UnidadTransporte;
import TDA.Graph.GraphException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
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

    public Logica() throws IOException, GraphException {
        //Clases
        this.datos = new Datos();
        
        //TDA's
        this.linkedHashMapUnidadTransporte = this.datos.getLinkedHashMapUnidadTransporte();
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
