/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Domain.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author jeison
 */
public class Data {
    
    private static LinkedList<Usuario> listaUsuarios = new LinkedList<>();

    /**
     *
     * @throws IOException
     */
    public Data() throws IOException {
        if (listaUsuarios.isEmpty()) {
//            fillListaUsuarios();
        }
    }

    public static LinkedList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void setListaUsuarios(LinkedList<Usuario> listaUsuarios) {
        Data.listaUsuarios = listaUsuarios;
    }
    
    

//    private void fillListaUsuarios() throws FileNotFoundException, IOException {
//        String sCadena;
//        FileReader fr = new FileReader("Usuarios.txt");
//        BufferedReader bf = new BufferedReader(fr);
//        while ((sCadena = bf.readLine()) != null) {
//            Usuario usuario = new Usuario();
//            String[] aux = sCadena.split(";");
//            usuario.setId(0);
//            usuario.setNombre(aux[1]);
//            usuario.setRol(aux[2]);
//            usuario.setUsuario(aux[3]);
//            usuario.setContrasena(aux[4]);
//        }
//        bf.close();
//    }
}
