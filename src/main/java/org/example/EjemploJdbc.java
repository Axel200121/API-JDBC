package org.example;

import org.example.modelo.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.utils.ConexionBD;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {

    public static void main(String[] args) {


        try (Connection connection = ConexionBD.getInstance();) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============== Listar ==============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============== Obtener por ID ==============");
            System.out.println(repositorio.porId(2L));

            System.out.println("============== Insertar nuevo producto ==============");
            Producto producto = new Producto();
            producto.setNombre("Teclado mecanico");
            producto.setPrecio(500);
            producto.setFechaRegistro(new Date()); // mandamos llamar el new date de java Util
            repositorio.guardar(producto);
            System.out.println("Producto agregago exitosamente");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
