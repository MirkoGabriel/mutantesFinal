package com.mutantes.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Connection getConexion(String driver, String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection cnn = DriverManager.getConnection(url, user, pass);
        return cnn;
    }

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
         Connection cnn = DriverManager.getConnection("jdbc:mysql://db4free.net/mutantes","usermirko1234","Prueba1234");
         return cnn;
    }
}