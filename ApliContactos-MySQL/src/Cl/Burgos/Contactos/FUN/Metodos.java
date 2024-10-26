/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.FUN;

import Cl.Burgos.Contactos.BD.Log;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author march
 */
public class Metodos {
    //Coloca los puntos y guion del RUT
    public String formatear(String rut){
        int cont=0;
        String format;
        if(rut.length() == 0){
            return "";
        }else{
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            format = "-"+rut.substring(rut.length()-1);
            for(int i = rut.length()-2;i>=0;i--){
                format = rut.substring(i, i+1)+format;
                cont++;
                if(cont == 3 && i != 0){
                    format = "."+format;
                    cont = 0;
                }
            }
            return format;
        }
    }
    //Valida el Rut si esta correcto
    public static boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
            Log.log(e.getMessage());
        } catch (Exception e) {
            Log.log(e.getMessage());
        }
        return validacion;
    }
    //Validar Email
    public static boolean validateEmail(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //Validar Solo Letras
    Character s;
    public Boolean soloLetras(KeyEvent evt){
        s = evt.getKeyChar();
        if(!Character.isLetter(s) && s != KeyEvent.VK_SPACE 
                && s != KeyEvent.VK_BACK_SPACE){
            evt.consume();
            return true;
        }
        return false;
    }
    //Validar Numeros
    public Boolean soloNumeros(KeyEvent evt){
        s = evt.getKeyChar();
        if(!Character.isDigit(s) && s != KeyEvent.VK_BACK_SPACE){
            evt.consume();
            return true;
        }
        return false;
    }
}
