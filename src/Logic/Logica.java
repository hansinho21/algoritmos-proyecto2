/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.ProductoMayorista;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hvill
 */
public class Logica {

    public Logica() {
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
}
