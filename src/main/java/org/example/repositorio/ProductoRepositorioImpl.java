package org.example.repositorio;

import org.example.modelo.Producto;
import org.example.utils.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection getConnection() throws SQLException {// TODO: creamos el metodo y mandandamos a llamar la conexion
        return ConexionBD.getInstance();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet result = stmt.executeQuery("SELECT * FROM productos")){

            while (result.next()){
                Producto producto = crearProducto(result);
                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }



    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM productos WHERE id = ?")){

            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                producto = crearProducto(rs);// mandamos a llamar la funcion crearProducto
            }
            rs.close();//cerramos el ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    private  Producto crearProducto(ResultSet result) throws SQLException {
        Producto producto = new Producto();
        producto.setId(result.getLong("id"));
        producto.setNombre(result.getString("nombre"));
        producto.setPrecio(result.getDouble("precio"));
        producto.setFechaRegistro(result.getDate("fecha_registro"));
        return producto;
    }
}
