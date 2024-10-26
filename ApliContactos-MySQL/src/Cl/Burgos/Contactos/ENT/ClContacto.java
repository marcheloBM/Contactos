/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.ENT;

import Cl.Burgos.Contactos.EXP.ExpContacto;
import Cl.Burgos.Contactos.FUN.Metodos;
import java.util.Date;

/**
 *
 * @author march
 */
public class ClContacto {
    Metodos m = new Metodos();
    private int idContacto;
    private String nombre;
    private String apellido;
    private String apodo;
    private int celular;
    private int celular2;
    private int trabajo;
    private int casa;
    private String correo;
    private String correo2;
    private Date fechaInsert;

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) throws Exception {
        if(idContacto<1){
            throw new ExpContacto(ExpContacto.ERR_idContacto);
        }else
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getCelular2() {
        return celular2;
    }

    public void setCelular2(int celular2) {
        this.celular2 = celular2;
    }

    public int getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(int trabajo) {
        this.trabajo = trabajo;
    }

    public int getCasa() {
        return casa;
    }

    public void setCasa(int casa) {
        this.casa = casa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(!m.validateEmail(correo)){
//            throw new ExpCliente(ExpCliente.ERR_coreeo);
        }else
        this.correo = correo;
    }

    public String getCorreo2() {
        return correo2;
    }

    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }

    public ClContacto(String nombre, String apellido, String apodo, int celular, int celular2, int trabajo, int casa, String correo, String correo2) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.celular = celular;
        this.celular2 = celular2;
        this.trabajo = trabajo;
        this.casa = casa;
        this.correo = correo;
        this.correo2 = correo2;
    }

    public ClContacto(int idContacto, String nombre, String apellido, String apodo, int celular, int celular2, int trabajo, int casa, String correo, String correo2) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.celular = celular;
        this.celular2 = celular2;
        this.trabajo = trabajo;
        this.casa = casa;
        this.correo = correo;
        this.correo2 = correo2;
    }

    public ClContacto(int idContacto) {
        this.idContacto = idContacto;
    }
    
    
    
}
