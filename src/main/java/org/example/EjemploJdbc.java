package org.example;

import org.example.utils.ConexionBD;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {


        try (Connection connection = ConexionBD.getInstance();
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
