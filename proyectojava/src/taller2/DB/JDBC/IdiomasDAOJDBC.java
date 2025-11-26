package taller2.DB.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.IdiomasDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.Idiomas;

public class IdiomasDAOJDBC implements IdiomasDAO{
    
    @Override
    public void crearTablaIdiomas() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - Idiomas - crearTablaIdiomas - Creando Tabla.");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS IDIOMAS " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " idPelicula     INTEGER     NOT NULL, " +
            " Idioma         TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - Idiomas - Tabla Creada Exitosamente.");
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    @Override
    public void insertarIdioma(int idPeliculaAsociado, String idioma) {
        Connection c = null;
        
        try {
            
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - Idiomas - crearTablaIdiomas - Intentando insertar elemento.");
            
            String sql = "INSERT INTO IDIOMAS (idPelicula, Idioma) VALUES (?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPeliculaAsociado);
                pstmt.setString(2, idioma);
                
                pstmt.executeUpdate();
                c.commit();
                
            }
            
            System.out.println("\"PlataformaTDL2 - Idiomas - crearTablaIdiomas - Elemento insertado correctamente.");
            
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    @Override
    public List<Idiomas> devolverIdiomasViaId(int idIdiomas) {
        List<Idiomas> ret = new ArrayList<Idiomas>();
        
        return ret;
    }
    
    @Override
    public int encontrarId(int idPeliculaAsociado, String idioma) {
        int idEncontrada = 0;
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - Idiomas - crearTablaIdiomas - Intentando encontrar id del elemento");
            
            String sql = "SELECT ID FROM IDIOMAS (idPelicula, Idioma) VALUES (?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPeliculaAsociado);
                pstmt.setString(2, idioma);
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    idEncontrada = rs.getInt("ID");
                    System.out.println("\"PlataformaTDL2 - Idiomas - crearTablaIdiomas - id del elemento encontrada correctamente");
                } else {
                    System.out.println("\"PlataformaTDL2 - Idiomas - crearTablaIdiomas - ERROR no se encontro id del elemento");
                }
                rs.close();
            }
            
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return idEncontrada;
    }
    
    @Override
    public boolean existeIdiomaEnTalba(int idPeliculaAsociado, String Idioma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existeIdiomaEnTalba'");
    }
    
}
