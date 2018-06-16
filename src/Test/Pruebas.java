/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Domain.Usuario;
import Logic.CRUDS;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author jeison
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        CRUDS cruds = new CRUDS();
        LinkedList<Usuario> lista= new LinkedList<>();
        Usuario u = new Usuario();
        u.setContrasena("dgdfg");
        u.setId(2);
        u.setNombre("adasda");
        u.setRol("asdsad");
        u.setUsuario("adasda");
        cruds.AgregarUsuario(u);
        cruds.AgregarUsuario(u);
    }
    
}
