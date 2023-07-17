package org.example;

import org.example.modelo.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.utils.ConexionBD;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {


        try (Connection connection = ConexionBD.getInstance();) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            repositorio.listar().forEach(System.out::println);

            System.out.println(repositorio.porId(2L));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
