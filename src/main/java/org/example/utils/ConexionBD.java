package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=UTC";
    private static String username ="root";
    private static String password ="12345";
    private static Connection connection;

    public static  Connection getInstance() throws SQLException {

        if (connection == null){ // si la conexión es igual a vacio
            connection = DriverManager.getConnection(url,username, password); // nos conectamos a la base de datos
        }
        return  connection; //devolvemos la conexicón
    }

}
