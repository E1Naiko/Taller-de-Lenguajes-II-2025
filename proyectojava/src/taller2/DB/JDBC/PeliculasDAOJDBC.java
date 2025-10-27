package taller2.DB.JDBC;

import java.sql.*;
import taller2.DB.DAO.PeliculasDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;

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
    
    /** 
     * @param pelicula
     */
    @Override
    public void insertarPeliculas(Pelicula pelicula) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - Intentando insertar elemento.");
            
            String sql = "INSERT INTO PELICULAS (Direccion_Archivo, Calidad, Audio) VALUES (?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, pelicula.getDireccionArchivo());
                pstmt.setString(2, pelicula.getCalidad());
                pstmt.setString(3, pelicula.getAudio());
                pstmt.executeUpdate();
            }
            
            System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - Elemento insertado correctamente.");
            
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /** 
     * @param idPelicula
     */
    @Override
    public void eliminarPeliculas(int idPelicula) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            
            System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - Intentando eliminar elemento");
            
            String sql = "DELETE FROM PELICULAS WHERE ID = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPelicula);
                if (pstmt.executeUpdate() == 0) {
                    System.out.println("PlataformaTDL2 - PeliculasDAOJDBC - No se encontró reseña con ID " + idPelicula);
                }
            }
            
            c.commit();
            c.close();
            
            System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - Elemento eliminado correctamente");
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /** 
    * @param pelicula
    * @return int
    */
    @Override
    public int encontrarIdPelicula(Contenido pelicula) {
        int idEncontrada = 0;
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - PeliculasDAO - Intentando encontrar id del elemento");
            
            try (Statement stmt = c.createStatement()) {
                ResultSet rs = stmt.executeQuery( "SELECT * FROM PELICULAS WHERE Direccion_Archivo=" + pelicula.getDireccionArchivo() +
                " AND Calidad=" + pelicula.getCalidad() +
                " AND Idioma=" + pelicula.getAudio() + ";" );
                
                idEncontrada = rs.getInt("ID");
                
                if (idEncontrada==0)
                System.out.println("\"PlataformaTDL2 - PeliculasDAO - ERROR no se encontro id del elemento");
                else
                System.out.println("\"PlataformaTDL2 - PeliculasDAO - id del elemento encontrada correctamente");
                
                rs.close();
            }
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return idEncontrada;
    }

    @Override
    public Pelicula devolverPeliculaViaId(int idPelicula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolverPeliculaViaId'");
    }
    
}
