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
    	 Class.forName("org.postgresql.Driver");
         Connection cnn = DriverManager.getConnection("postgres://neswyazorynuqb:0bd44b860d23d11bfcd60d7e5f87e6acb7cf054f19e4426f8b8816e4dd345a1f@ec2-34-204-121-199.compute-1.amazonaws.com:5432/d2sn4ob89oboum", "neswyazorynuqb", "0bd44b860d23d11bfcd60d7e5f87e6acb7cf054f19e4426f8b8816e4dd345a1f");
         return cnn;
    }
}