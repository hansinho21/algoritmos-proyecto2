/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Usuario;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author jeison
 */
public class CRUDS {
    private Data data;
    LinkedList<Usuario> listaUsuarios;

    public CRUDS() throws IOException {
        data = new Data();
        listaUsuarios = data.getListaUsuarios();
    }
    
    
    
    public LinkedList AgregarUsuario(Usuario usuario) {
        boolean exist = false;
        if (listaUsuarios.isEmpty()) {
            listaUsuarios.add(usuario);
            Data.setListaUsuarios(listaUsuarios);
            exist = true;
        } else {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).getId()==(usuario.getId())) {
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
    
    public LinkedList eliminarUsuario(Usuario usuario){
        boolean exist = false;
        System.out.println(listaUsuarios.size() + "Inicio");

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId()==(usuario.getId())) {
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
    
    public LinkedList editarUsuario(Usuario usuario){
        boolean exist = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId()==(usuario.getId())) {
                exist = true;
                listaUsuarios.remove(i);
                listaUsuarios.add(usuario);
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
