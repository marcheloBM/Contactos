/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.EXP;

/**
 *
 * @author march
 */
public class ExpContacto extends Exception {
    public static final int ERR_idContacto=1;
    public static final int ERR_rut=2;
    public static final int ERR_nombre=3;
    public static final int ERR_apellido=4;
    public static final int ERR_celular=5;
    public static final int ERR_coreeo=6;
    
    public ExpContacto(int error) throws Exception
    {
        switch(error)
        {
            case ERR_idContacto:
                throw new Exception("Error idContacto no puede ser 0");
            case ERR_rut:
                throw new Exception("Error RUT Incorrecto");
            case ERR_nombre:
                throw new Exception("Error Nombre entre 4..25");
            case ERR_apellido:
                throw new Exception("Error Apellido entre 4..25");
            case ERR_celular:
                throw new Exception("Error Celular solo 9 digitos");
            case ERR_coreeo:
                throw new Exception("Error Correo Incorrecto");
                default:
                           throw new Exception("Error desconocido "+ error);
        }
    }
}
