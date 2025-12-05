package taller2.DB.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

import taller2.DB.DAO.MetadatosDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.Metadatos;
import taller2.plataformatdl2.Utilities.TimeStringYSegundos;

public class MetadatosDAOJDBC implements MetadatosDAO {
    private boolean imprimirDebug = true;

    @Override
    public void crearTablaMetadatos() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            if (imprimirDebug) System.out.println("PlataformaTDL2 - Metadatos - crearTablaMetadatos - Creando Tabla.");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS METADATOS " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " Titulo                TEXT          NOT NULL, " +
            " Sinopsis              TEXT          NOT NULL, " +
            " Elenco                TEXT          NOT NULL, " +
            " Director              TEXT          NOT NULL, " +
            " Duracion              INTEGER       NOT NULL, " +
            " Idioma                TEXT          NOT NULL, " +  // TODO - crear tabla de Idiomas  - EXCEDE EL TP
            " Subtitulos            TEXT          NOT NULL, " +
            " RatingPromedio        REAL          NOT NULL, " +
            " Anio                  INTEGER       NOT NULL, " +
            " UrlPoster             TEXT          NOT NULL" +
            ")";
            stmt.executeUpdate(sql);
            if (imprimirDebug) System.out.println("PlataformaTDL2 - Metadatos - Tabla Creada Exitosamente.");
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
        TimeStringYSegundos convertidorStringSegundos = new TimeStringYSegundos(metadatos.duracion);

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            
            String sql = "INSERT INTO METADATOS (Titulo, Sinopsis, Elenco, Director, Duracion, Idioma, Subtitulos, RatingPromedio, Anio, UrlPoster) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, metadatos.getTitulo());
                pstmt.setString(2, metadatos.getSinopsis());
                
                // 
                String elencoStr = (metadatos.getElenco() != null) 
                ? String.join(", ", metadatos.getElenco()) 
                : null;
                pstmt.setString(3, elencoStr); // Ahora es un String
                
                pstmt.setString(4, metadatos.getDirector());
                pstmt.setInt(5, convertidorStringSegundos.getFormatoSegundos());
                pstmt.setString(6, metadatos.getIdioma());
                
                // TODO - SOLUCION TEMPORAL HECHA CON IA: hay que crear una tabla subtitulos en la bd - EXCEDE EL TP
                String subtitulosStr = (metadatos.getSubtitulos() != null) 
                ? String.join(", ", metadatos.getSubtitulos()) 
                : null;
                
                pstmt.setString(7, subtitulosStr); // Ahora es un String
                pstmt.setFloat(8, metadatos.getRating_promedio());
                pstmt.setInt(9, metadatos.getAnio());
                pstmt.setString(10, metadatos.getUrlPoster());
                pstmt.executeUpdate();
            }
            
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - crearTablaMetadatos - Elemento insertado correctamente.");
            
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
            
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - crearTablaMetadatos - Intentando eliminar elemento");
            
            String sql = "DELETE FROM METADATOS WHERE ID = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idMetadatos);
                if (pstmt.executeUpdate() == 0) {
                    if (imprimirDebug) System.out.println("PlataformaTDL2 - MetadatosDAO - crearTablaMetadatos - No se encontró metadatos con ID " + idMetadatos);
                }
            }
            
            c.commit();
            c.close();
            
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - crearTablaMetadatos - Elemento eliminado correctamente");
            
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
        TimeStringYSegundos convertidorStringSegundos = new TimeStringYSegundos(metadatos.duracion.toString());
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - encontrarIdMetadatos - Intentando encontrar id del elemento");
            
            // TODO - SOLUCION TEMPORAL HECHA CON IA: hay que crear una tabla elenco en la bd - EXCEDE EL TP
            String elencoStr = (metadatos.getElenco() != null) 
            ? String.join(", ", metadatos.getElenco()) 
            : null;
            // TODO - SOLUCION TEMPORAL HECHA CON IA: hay que crear una tabla subtitulos en la bd - EXCEDE EL TP
            String subtitulosStr = (metadatos.getSubtitulos() != null) 
            ? String.join(", ", metadatos.getSubtitulos()) 
            : null;
            
            stmt = c.createStatement();
            String sql = "SELECT * FROM METADATOS WHERE " +
            "Titulo=? AND Sinopsis=? AND Elenco=? AND Director=? AND Duracion=? AND Idioma=? AND Subtitulos=? AND RatingPromedio=? AND Anio=? AND UrlPoster=?";
            
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, metadatos.getTitulo());
            ps.setString(2, metadatos.getSinopsis());
            ps.setString(3, elencoStr);
            ps.setString(4, metadatos.getDirector());
            ps.setInt(5, convertidorStringSegundos.getFormatoSegundos());
            ps.setString(6, metadatos.getIdioma());
            ps.setString(7, subtitulosStr);
            ps.setFloat(8, metadatos.getRating_promedio());
            ps.setInt(9, metadatos.getAnio());
            ps.setString(10, metadatos.getUrlPoster());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next())
            idEncontrada = rs.getInt("ID");
            
            if (idEncontrada==0)
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - encontrarIdMetadatos - ERROR no se encontro id del elemento");
            else
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - encontrarIdMetadatos - id del elemento encontrada correctamente");
            
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
        TimeStringYSegundos convertidorStringSegundos;
        
        String titulo = null;
        String sinopsis = null;
        String[] elenco = null;
        String director = null;
        LocalTime duracion = null;
        String idioma = null;
        String[] subtitulos = null;
        float rating_promedio = 0.0f;
        int anio = 0;
        String urlPoster = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            if (imprimirDebug) System.out.println("\"PlataformaTDL2 - MetadatosDAO - devolverMetadatosViaId - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM METADATOS WHERE ID=" + idMetadatos +
            ";" );
            
            if (rs.next()){
                titulo = rs.getString("Titulo");
                sinopsis = rs.getString("Sinopsis");
                //elenco = rs.getString("Elenco");
                director = rs.getString("Director");
                convertidorStringSegundos = new TimeStringYSegundos(rs.getInt("Duracion"));
                duracion = convertidorStringSegundos.getFormatoTime();
                idioma = rs.getString("Idioma");
                // subtitulos = res getString("");
                rating_promedio = rs.getFloat("RatingPromedio");
                anio = rs.getInt("Anio");
                urlPoster = rs.getString("UrlPoster");
            }
            
            rs.close();
            stmt.close();
            c.close();
            Metadatos ret = new Metadatos(titulo, sinopsis, elenco, director, duracion, idioma, subtitulos, rating_promedio, anio, urlPoster);
            return ret;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }
    
    @Override
    public boolean existeMetadatos(Metadatos metadatos) {
        return encontrarIdMetadatos(metadatos) > 0;
    }
    
    public void setImprimirDebug(boolean imprimirDebug) {
        this.imprimirDebug = imprimirDebug;
    }
}