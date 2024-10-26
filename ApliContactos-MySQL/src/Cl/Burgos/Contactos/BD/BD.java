/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class BD {
    public static  enum typeData {swBd,swArreglo,swLista,swHashTable};
    public static typeData swData = typeData.swBd;
    private static BD bd;
    private Statement stmt;
    private static String myDriver = "com.mysql.jdbc.Driver";
    
    private static String myUrl = "jdbc:mysql://localhost:3306/contactos";String user="root";String clave="";
    
    private Connection cnn;
    
    private BD()
    {       
       try {
            Class.forName(myDriver);
            cnn = DriverManager.getConnection(myUrl, user, clave);
             stmt=cnn.createStatement();
        } catch (ClassNotFoundException ex) {
		    Log.log("Class Not Found " + ex.getMessage());
		    new Exception("Class Not Found" + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN"+ex);
        } catch (SQLException ex) {
		   Log.log("Sql Conexion " + ex.getMessage());
		   new Exception("Sql Conexion " + ex.getMessage());
                   JOptionPane.showMessageDialog(null, "SQL Conexion"+ex);
        }
    }
    
    public static BD getInstance(){ 
        if (bd==null) bd=new BD();
        return bd;
    }
    
    public boolean sqlEjecutar(String sql) throws SQLException{
	    int rs=0;
        try {
            rs = stmt.executeUpdate(sql);
            boolean resp=(rs>0)?true:false;
			return resp;
			
        } catch (SQLException ex) {
		    Log.log("Sql Ejecutar " + ex.getMessage());
		    new Exception("Sql Ejecutar " + ex.getMessage());
			return false;
        }
    }  
    
    public ResultSet sqlSelect(String sql){
        
        try {
            ResultSet rs= stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
		    Log.log("Sql Select " + ex.getMessage());
		    new Exception("Sql Select " + ex.getMessage());
        }
        return null;
    }
}
