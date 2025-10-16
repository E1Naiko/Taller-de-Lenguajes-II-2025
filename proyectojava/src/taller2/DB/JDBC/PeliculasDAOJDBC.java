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
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - PeliculasDAOJDBC - Creando Tabla");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PELICULAS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Direccion_Archivo      TEXT     NOT NULL, " +
                    " Calidad                TEXT     NOT NULL, " +
                    " Audio                  TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - PeliculasDAOJDBC - Tabla Creada Exitosamente");
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
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

    /** 
     * @param id
     * @return Contenido
     */
    @Override
    public Contenido encontrarPelicula(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPelicula'");
    }

    /** 
     * @param pelicula
     * @return int
     */
    @Override
    public int encontrarIdPelicula(Contenido pelicula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarIdPelicula'");
    }

}
