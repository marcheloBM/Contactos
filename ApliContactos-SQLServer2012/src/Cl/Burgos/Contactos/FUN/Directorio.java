/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.FUN;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author march
 */
public class Directorio {
    public String selecDirectrorio(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(null);
//        System.out.println(fileChooser.getSelectedFile());
        //String url = "c:/Users/march/Desktop/";
        String url = fileChooser.getSelectedFile().toString();
        //url=url.replace('\\', '/');
        return url;
    }
    public void abrirArchivo(String url) throws IOException{
        File objetofile = new File (url);
        Desktop.getDesktop().open(objetofile);
    }
    
    public void crearDirecPre() throws IOException{
        String OrigenCarpeta = "reparaciones"; 
        File directorio = new File("d:/"+ OrigenCarpeta ); 
        directorio.mkdir(); 
        Desktop.getDesktop().open(directorio);
    }
    public File selectDirecPre() throws IOException{
        String url = "d:/Reparaciones";
        File f = new File(url);
        return f;
    }
}
