/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.DAO;

import Cl.Burgos.Contactos.BD.BD;
import Cl.Burgos.Contactos.BD.Log;
import Cl.Burgos.Contactos.ENT.ClContacto;
import Cl.Burgos.Contactos.FUN.FormatoFecha;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author march
 */
public class DAOContacto {
    FormatoFecha fecha=new FormatoFecha();
    //******** Sql insetar
    public static boolean sqlInsert(ClContacto contacto)throws Exception, SQLException{
            String stSql  = "exec ProInsertarContacto ";
            stSql += "'" + contacto.getNombre()+ "',";
            stSql += "'" + contacto.getApellido()+ "',";
            stSql += "'" + contacto.getApodo()+ "',";
            stSql += "'" + contacto.getCelular()+ "',";
            stSql += "'" + contacto.getCelular2()+ "',";
            stSql += "'" + contacto.getTrabajo()+ "',";
            stSql += "'" + contacto.getCasa()+ "',";
            stSql += "'" + contacto.getCorreo()+ "',";
            stSql += "'" + contacto.getCorreo2()+ "'";
            stSql += " ";
            return BD.getInstance().sqlEjecutar(stSql);
		}
    //******** Sql actualizar
    public static boolean sqlUpdate(ClContacto contacto)throws Exception, SQLException{
			
            String stSql =  "exec ProModificarContacto ";
            stSql += "'" + contacto.getNombre()+ "',";
            stSql += "'" + contacto.getApellido()+ "',";
            stSql += "'" + contacto.getApodo()+ "',";
            stSql += "'" + contacto.getCelular()+ "',";
            stSql += "'" + contacto.getCelular2()+ "',";
            stSql += "'" + contacto.getTrabajo()+ "',";
            stSql += "'" + contacto.getCasa()+ "',";
            stSql += "'" + contacto.getCorreo()+ "',";
            stSql += "'" + contacto.getCorreo2()+ "',";
            stSql += "'" + contacto.getIdContacto()+ "'";
            stSql += "";
            return BD.getInstance().sqlEjecutar(stSql);
		}
    //******** SQL Eliminar
    public static boolean sqlDelete(ClContacto contacto)throws Exception, SQLException{
			
            String stSql =  "exec ProEliminarContacto ";
            stSql += " '" + contacto.getIdContacto()+ "'";
            Log.log(stSql);
            return BD.getInstance().sqlEjecutar(stSql);
		}
    //Lista Login
    public void leerContactos(long intDesde ,long intCuantos,DefaultTableModel tablaClientes,String strBusqueda ){
        String strConsulta;
        String datos[]=new String [11];
      
        strConsulta="exec ProLeeContacto "+intDesde+","+intCuantos+",'"+strBusqueda+"';";
      
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              datos[0]=Integer.toString(rs.getInt("idcontacto"));
              datos[1]=rs.getString("nombre");
              datos[2]=rs.getString("apellido");
              datos[3]=rs.getString("apodo");
              datos[4]=Integer.toString(rs.getInt("celular"));
//              if(datos[4].equals("0")){
//                    datos[4]=null;
//                }
              datos[5]=Integer.toString(rs.getInt("celular2"));
//              if(datos[5].equals("0")){
//                    datos[5]=null;
//                }
              datos[6]=Integer.toString(rs.getInt("trabajo"));
//              if(datos[6].equals("0")){
//                    datos[6]=null;
//                }
              datos[7]=Integer.toString(rs.getInt("casa"));
//              if(datos[7].equals("0")){
//                    datos[7]=null;
//                }
              datos[8]=rs.getString("correo");
              datos[9]=rs.getString("correo2");
              datos[10]=fecha.mostrarFecha(rs.getDate("fechaInsert"));
              
              tablaClientes.addRow(datos);
         }
         rs.close();
          }catch(SQLException e){
              Log.log(e.getMessage());
              System.out.println(e.getMessage());
          }
    }
    // Cuanta Cuantos Login Hay
    public long leerCuantos(String strBusqueda){
        String strConsulta;
        long cuantos = 0;
        strConsulta="exec ProCuantosContactos '" +strBusqueda +"';";
      
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
          System.out.println(strConsulta);
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              cuantos=Long.valueOf(rs.getString("cuantos"));
       
              return cuantos;
              
         }
         rs.close();
          }catch(SQLException e){
              Log.log(e.getMessage());
         System.out.println(e);
         System.out.println(strConsulta);
         return cuantos;
          }
       System.out.println(strConsulta);
        return cuantos;
       
        }
    //Busca
    public String[] leerCliente(ClContacto contacto){
        String strConsulta;
        String datos[]=new String [11];
        
        strConsulta="exec ProLeerContactos "+contacto.getIdContacto()+";";
     
      
        try{
         ResultSet rs=BD.getInstance().sqlSelect(strConsulta);
         
         
         while(rs.next()){
              //System.out.println(res.getString("Nombres"));
              datos[0]=rs.getString("idcontacto");
              datos[1]=rs.getString("nombre");
              datos[2]=rs.getString("apellido");
              datos[3]=rs.getString("apodo");
              datos[4]=rs.getString("celular");
              datos[5]=rs.getString("celular2");
              datos[6]=rs.getString("trabajo");
              datos[7]=rs.getString("casa");
              datos[8]=rs.getString("correo");
              datos[9]=rs.getString("correo2");
              datos[10]=rs.getString("fechaInsert");
              
                      
              rs.close();
              return datos;
              
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
         Log.log(e.getMessage());
 
         return datos;
          }
      
        return datos;
    }
}
