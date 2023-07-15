package org.example;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=UTC";
        String username ="root";
        String password ="12345";



        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement stmt = connection.createStatement();
             ResultSet resultado = stmt.executeQuery("SELECT * FROM productos")) {

            while (resultado.next()){
                System.out.print(resultado.getInt("id"));
                System.out.print("      |      ");
                System.out.print(resultado.getString("nombre"));
                System.out.print("      |      ");
                System.out.print(resultado.getDouble("precio"));
                System.out.print("      |      ");
                System.out.println(resultado.getDate("fecha_registro"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
