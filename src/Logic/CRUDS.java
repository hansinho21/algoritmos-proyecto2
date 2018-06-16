/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Bodega;
import Domain.Usuario;
import TDA.BinaryTree.LinkedBinaryTree;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author jeison
 */
public class CRUDS {

    private Data data;
    private LinkedBinaryTree arbolProductosMayoristas;
    private LinkedList<Usuario> listaUsuarios;
    private AdjacencyMatrixGraph grafoBodegas;

    public CRUDS() throws IOException {
        this.data = new Data();
        this.listaUsuarios = this.data.getListaUsuarios();
        this.arbolProductosMayoristas = this.data.getArbolProductosMayoristas();
        this.grafoBodegas = this.data.getGrafoBodegas();
    }

    //CRUDs Bodegas ----------------------------------------------------------------------------------------
    public void agregarBodega(Bodega bodega) throws GraphException {
        boolean exist = false;
        if (grafoBodegas.isEmpty()) {
            grafoBodegas.insertVerex(bodega);
            this.data.setGrafoBodegas(grafoBodegas);
        } else {
            for (int i = 0; i < grafoBodegas.getSize(); i++) {
                Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
                if (auxBodega.getId() == bodega.getId()) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The user already exists");
                }
            }//for
            if (exist == false) {
                grafoBodegas.insertVerex(bodega);
                this.data.setGrafoBodegas(grafoBodegas);
            }
        }
    }

    public void editarBodega(Bodega bodega) throws GraphException {
        boolean exist = false;
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            if (auxBodega.getId() == bodega.getId()) {
                exist = true;
                grafoBodegas.setVertex(i, bodega);
                this.data.setGrafoBodegas(grafoBodegas);
            }
        }
        if(exist == false){
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }

    public void eliminarBodega(Bodega bodega) throws GraphException {
        boolean exist = false;
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega auxBodega = (Bodega) grafoBodegas.getVertex(i);
            if (auxBodega.getId() == bodega.getId()) {
                exist = true;
                grafoBodegas.deleteVertex(bodega);
                this.data.setGrafoBodegas(grafoBodegas);
            }
        }
        if(exist == false){
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
    }

    //CRUDs Productos ----------------------------------------------------------------------------------------
    //CRUDs Usuario ----------------------------------------------------------------------------------------
    public LinkedList AgregarUsuario(Usuario usuario) {
        boolean exist = false;
        if (listaUsuarios.isEmpty()) {
            listaUsuarios.add(usuario);
            Data.setListaUsuarios(listaUsuarios);
            exist = true;
        } else {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).getId() == (usuario.getId())) {
                    exist = true;
                    JOptionPane.showMessageDialog(null, "The user already exists");
                }
            }
        }
        if (exist == false) {
            listaUsuarios.add(usuario);
            Data.setListaUsuarios(listaUsuarios);
        }
        System.out.println(listaUsuarios.size());
        return listaUsuarios;
    }

    public LinkedList eliminarUsuario(Usuario usuario) {
        boolean exist = false;
        System.out.println(listaUsuarios.size() + "Inicio");

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == (usuario.getId())) {
                exist = true;
                listaUsuarios.remove(i);
                this.data.setListaUsuarios(listaUsuarios);
                JOptionPane.showMessageDialog(null, "user removed succesfull");
            }
        }

        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }

        System.out.println(listaUsuarios.size() + "Final");
        return listaUsuarios;
    }

    public LinkedList editarUsuario(Usuario usuario) {
        boolean exist = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == (usuario.getId())) {
                exist = true;
                listaUsuarios.set(i, usuario);
                data.setListaUsuarios(listaUsuarios);
                JOptionPane.showMessageDialog(null, "correctly modified user");
            }
        }

        if (exist == false) {
            JOptionPane.showMessageDialog(null, "The user does not exist, verify the information.");
        }
        return listaUsuarios;
    }

}
