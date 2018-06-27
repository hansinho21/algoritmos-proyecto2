/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hvill
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date date = new Date(2, 3, 18);
        System.out.println(date);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String expirationDate = dateFormat.format(date);
        System.out.println(expirationDate);

        Date fecha = null;

        try {
            fecha = dateFormat.parse(expirationDate);
            System.out.println(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
            


    }

}
