package taller2.DB.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.Factory;
import taller2.DB.DAO.PeliculasDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;

public class PeliculasDAOJDBC implements PeliculasDAO {
    private boolean imprimirDebug = true;
    
    @Override
    public void crearTablaPeliculas() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            if (imprimirDebug) System.out.println("PlataformaTDL2 - PeliculasDAOJDBC - crearTablaPeliculas - Creando Tabla");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PELICULAS " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " Direccion_Archivo      TEXT       NOT NULL, " +
            " Calidad                TEXT       NOT NULL, " +
            " Audio                  TEXT       NOT NULL, " +
            " IdMetadatos            INTEGER    NOT NULL)";
            stmt.executeUpdate(sql);
            if (imprimirDebug) System.out.println("PlataformaTDL2 - PeliculasDAOJDBC - crearTablaPeliculas - Tabla Creada Exitosamente");
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
        Factory.getMetadatosDAO().insertarMetadatos(pelicula.getMetadatos());
        Connection c = null;
        
        try {
            
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - insertarPeliculas - Intentando insertar elemento.");
            
            String sql = "INSERT INTO PELICULAS (Direccion_Archivo, Calidad, Audio, IdMetadatos) VALUES (?,?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, pelicula.getDireccionArchivo());
                pstmt.setString(2, pelicula.getCalidad().toString());
                pstmt.setString(3, pelicula.getAudio());
                
                int idMeta = Factory.getMetadatosDAO().encontrarIdMetadatos(pelicula.getMetadatos());
                pstmt.setInt(4, idMeta);
                
                pstmt.executeUpdate();
                c.commit();
                
            }
            
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - insertarPeliculas - Elemento insertado correctamente.");
            
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
            
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - eliminarPeliculas - Intentando eliminar elemento");
            
            String sql = "DELETE FROM PELICULAS WHERE ID=?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPelicula);
                if (pstmt.executeUpdate() == 0) {
                    if (imprimirDebug) System.out.println("PlataformaTDL2 - PeliculasDAOJDBC - eliminarPeliculas - No se encontró reseña con ID " + idPelicula);
                }
            }
            
            c.commit();
            c.close();
            
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAOJDBC - eliminarPeliculas - Elemento eliminado correctamente");
            
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
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAO - encontrarIdPelicula - Intentando encontrar id del elemento");
            
            String sql = "SELECT ID FROM PELICULAS WHERE Direccion_Archivo=? AND Calidad=? AND Audio=?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, pelicula.getDireccionArchivo());
                pstmt.setString(2, pelicula.getCalidad().toString());
                pstmt.setString(3, pelicula.getAudio());
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    idEncontrada = rs.getInt("ID");
                    if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAO - encontrarIdPelicula - id del elemento encontrada correctamente");
                } else {
                    if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAO - encontrarIdPelicula - ERROR no se encontro id del elemento");
                }
                rs.close();
            }
            
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return idEncontrada;
    }
    
    
    
    /** 
    * @param idPelicula
    * @return Pelicula
    */
    @Override
    public Pelicula devolverPeliculaViaId(int idPelicula) {
        Connection c = null;
        Statement stmt = null;
        
        Calidades calidad = null;
        String audio = null;
        String dirArchivo = null;
        Genero genero = null;
        int idMetadatos = 0;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAO - devolverPeliculaViaId - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PELICULAS WHERE ID=" + idPelicula +
            ";" );
            
            if (rs.next()){
                calidad = Calidades.valueOf(rs.getString("Calidad"));
                audio = rs.getString("Audio");
                dirArchivo = rs.getString("Direccion_Archivo");
                idMetadatos = rs.getInt("IdMetadatos");
            }
            
            rs.close();
            stmt.close();
            c.close();
            Pelicula ret = new Pelicula(calidad, audio, dirArchivo, genero, Factory.getMetadatosDAO().devolverMetadatosViaId(idMetadatos));
            return ret;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }
    
    /** 
    * @return List<Pelicula>
    */
    @Override
    public List<Pelicula> obtenerPeliculas() {
        // Aclaración: somos totalmente concientes que hay formas mas optimizadas de devolver todos los usuarios
        //   pero elegimos usar esta ya que reutiliza codigo
        List<Pelicula> lista = new ArrayList<Pelicula>();
        int maxId = this.getMaxId();
        
        for (int i=1; i<=maxId; i++)
            lista.add(this.devolverPeliculaViaId(i));
        
        return lista;
    }
    
    
    
    /** 
    * @return int
    */
    public int getMaxId() {
        int maxId = 0;
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - PeliculasDAO - getMaxId - Intentando encontrar maxId del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS last_id FROM Peliculas");
            
            maxId = rs.getInt("last_id");
            
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return maxId;
    }
    
    public boolean existePelicula(Contenido pelicula){
        return encontrarIdPelicula(pelicula) > 0;
    }
    
    public void setImprimirDebug(boolean imprimirDebug) {
        this.imprimirDebug = imprimirDebug;
    }
}
