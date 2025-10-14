package taller2.DB.JDBC;

import java.sql.*;
import taller2.DB.DAO.PeliculasDAO;
import taller2.plataformatdl2.Contenido;

public class PeliculasDAOJDBC implements PeliculasDAO {

    @Override
    public void crearTablaPeliculas() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS RESENIAS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Direccion_Archivo      TEXT     NOT NULL, " +
                    " Calidad                TEXT     NOT NULL, " +
                    " Audio                  TEXT     NOT NULL, " +
                    " Genero                 TEXT     NOT NULL, " +
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("PlataformaTDL2 - Tabla Creada Exitosamente");
    }

    @Override
    public void insertarPeliculas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertarPeliculas'");
    }

    @Override
    public void eliminarPeliculas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPeliculas'");
    }

    @Override
    public Contenido encontrarPelicula(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPelicula'");
    }

    @Override
    public int encontrarIdPelicula(Contenido pelicula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarIdPelicula'");
    }

}
