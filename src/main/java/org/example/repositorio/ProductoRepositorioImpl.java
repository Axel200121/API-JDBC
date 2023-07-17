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
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);// mandamos a llamar la funcion crearProducto
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql; //hacemos la consulta
        if (producto.getId() != null && producto.getId() > 0) { // hace una condicón si es una actualizacion o un insert
            sql = "UPDATE productos SET nombre=?, precio=? WHERE id=?";
        } else {
            sql = "INSERT INTO prodcutos(nombre, precio, fecha_registro) VALUES (?, ?, ?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(3, producto.getId());
            } else {
                stmt.setDate(3, new Date(producto.getFechaRegistro().getTime()));//convertimos java date util a javaDate
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")) {
            stmt.setLong(1,1);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
