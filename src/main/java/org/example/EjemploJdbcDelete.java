package org.example;

import org.example.modelo.Producto;
import org.example.repositorio.ProductoRepositorioImpl;
import org.example.repositorio.Repositorio;
import org.example.utils.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcDelete {

    public static void main(String[] args) {


        try (Connection connection = ConexionBD.getInstance();) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============== Listar ==============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============== Obtener por ID ==============");
            System.out.println(repositorio.porId(2L));

            System.out.println("============== Eliminar producto =========== ===");
            repositorio.eliminar(3L);
            System.out.println("Producto eliminado exitosamente");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
