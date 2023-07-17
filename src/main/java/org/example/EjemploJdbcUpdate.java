package org.example;

import org.example.modelo.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.utils.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {

    public static void main(String[] args) {


        try (Connection connection = ConexionBD.getInstance();) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============== Listar ==============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============== Obtener por ID ==============");
            System.out.println(repositorio.porId(2L));

            System.out.println("============== Editar nuevo producto ==============");
            Producto producto = new Producto();
            producto.setId(3L);
            producto.setNombre("Teclado mecanico YEYIAN test");
            producto.setPrecio(1500);
            repositorio.guardar(producto);
            System.out.println("Producto editado exitosamente");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
