package taller2.DB.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import taller2.DB.DAO.MetadatosDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.Metadatos;

public class MetadatosDAOJDBC implements MetadatosDAO {
    
    @Override
    public void crearTablaMetadatos() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - Metadatos - Creando Tabla.");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS METADATOS " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " Titulo         TEXT       NOT NULL, " +
            " Sinopsis       TEXT       NOT NULL, " +
            " Elenco         TEXT, " +  // TODO - crear tabla de Elenco
            " Director       TEXT       NOT NULL, " +
            " Duracion       TIME       NOT NULL, " +
            " Idioma         TEXT, " +  // TODO - crear tabla de Idiomas
            " Subtitulos     TEXT       NOT NULL" +
            ")";
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - Metadatos - Tabla Creada Exitosamente.");
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /** 
     * @param metadatos
     */
    @Override
    public void insertarMetadatos(Metadatos metadatos) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - Intentando insertar elemento.");
            
            String sql = "INSERT INTO METADATOS (Titulo, Sinopsis, Elenco, Director, Duracion, Idioma, Subtitulos) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, metadatos.getTitulo());
                pstmt.setString(2, metadatos.getSinopsis());
                // TODO  - pstmt.setString(3, null;
                pstmt.setString(4, metadatos.getDirector());
                pstmt.setTime(5, metadatos.getDuracion());
                pstmt.setString(6, metadatos.getIdioma());
                // TODO  - pstmt.setString(7, metadatos.getSubtitulos());
                pstmt.executeUpdate();
            }
            
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - Elemento insertado correctamente.");
            
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /** 
     * @param idMetadatos
     */
    @Override
    public void eliminarMetadatos(int idMetadatos) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - Intentando eliminar elemento");
            
            String sql = "DELETE FROM METADATOS WHERE ID = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idMetadatos);
                if (pstmt.executeUpdate() == 0) {
                    System.out.println("PlataformaTDL2 - MetadatosDAO - No se encontró metadatos con ID " + idMetadatos);
                }
            }
            
            c.commit();
            c.close();
            
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - Elemento eliminado correctamente");
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /** 
     * @param metadatos
     * @return int
     */
    @Override
    public int encontrarIdMetadatos(Metadatos metadatos) {
        int idEncontrada = 0;
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM METADATOS WHERE Titulo=" + metadatos.getTitulo() +
            " AND Sinopsis=" + metadatos.getSinopsis() +
            //" AND Elenco=" + metadatos.getElenco() +
            " AND Director=" + metadatos.getDirector() +
            " AND Duracion=" + metadatos.getDuracion() +
            " AND Idioma=" + metadatos.getIdioma() +
            //" AND Subtitulos=" + 
            ";" );
            
            if (rs.next())
            idEncontrada = rs.getInt("ID");
            
            if (idEncontrada==0)
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - ERROR no se encontro id del elemento");
            else
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - id del elemento encontrada correctamente");
            
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return idEncontrada;
    }
    
    /** 
     * @param idMetadatos
     * @return Metadatos
     */
    @Override
    public Metadatos devolverMetadatosViaId(int idMetadatos) {
        Connection c = null;
        Statement stmt = null;
        
        String titulo = null;
        String sinopsis = null;
        String[] elenco = null;
        String director = null;
        Time duracion = null;
        String idioma = null;
        String[] subtitulos = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - MetadatosDAO - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM METADATOS WHERE ID=" + idMetadatos +
            ";" );
            
            if (rs.next()){
                titulo = rs.getString("Titulo");
                sinopsis = rs.getString("Sinopsis");
                //elenco = rs.getString("Elenco");
                director = rs.getString("Director");
                duracion = rs.getTime("Duracion");
                idioma = rs.getString("");
                // subtitulos = res getString("");
            }
            
            rs.close();
            stmt.close();
            c.close();
            Metadatos ret = new Metadatos(titulo, sinopsis, elenco, director, duracion, idioma, subtitulos);
            return ret;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }
    
    
    
}
