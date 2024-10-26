/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.BD;

import Cl.Burgos.Contactos.FUN.Directorio;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class Log {
    public static void log(String msg){
        String url="C:\\Users\\march\\Desktop/myfile.log";
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(url, true)))) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            out.println(dateFormat.format(date) + ":"+ msg + "\r\n");
//            File objetofile = new File (url);
//            Desktop.getDesktop().open(objetofile);
            Directorio d = new Directorio();
            d.abrirArchivo("C:\\Users\\march\\Desktop");
            d.abrirArchivo(url);
            
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
            JOptionPane.showMessageDialog(null,"Hubo un error"+e);
        }
    }
}
