package taller2.DB.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.ElencoDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.Idiomas;

public class ElencoDAOJDBC implements ElencoDAO{

    @Override
    public void crearTablaElenco() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - IdiomasDAO - crearTablaIdiomas - Creando Tabla.");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS IDIOMAS " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " idPelicula     INTEGER     NOT NULL, " +
            " Idioma         TEXT        NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - IdiomasDAO - Tabla Creada Exitosamente.");
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void insertarElenco(int idPeliculaAsociado, String Elenco) {
        Connection c = null;
        
        try {
            
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - IdiomasDAO - insertarIdioma - Intentando insertar elemento.");
            
            String sql = "INSERT INTO IDIOMAS (idPelicula, Idioma) VALUES (?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPeliculaAsociado);
                pstmt.setString(2, Elenco);
                
                pstmt.executeUpdate();
                c.commit();
                
            }
            
            System.out.println("\"PlataformaTDL2 - IdiomasDAO - insertarIdioma - Elemento insertado correctamente.");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

@Override
    public void insertarElenco(int idPeliculaAsociado, List<String> Elenco) {
        Connection c = null;
        
        try {
            
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - IdiomasDAO - insertarIdioma - Intentando insertar elemento.");
            
            String sql = "INSERT INTO IDIOMAS (idPelicula, Idioma) VALUES (?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPeliculaAsociado);
                pstmt.setString(2, idioma);
                
                pstmt.executeUpdate();
                c.commit();
                
            }
            
            System.out.println("\"PlataformaTDL2 - IdiomasDAO - insertarIdioma - Elemento insertado correctamente.");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public List<String> devolverElencoViaIdPelicula(int idPeliculaAsociado) {
        List<Idiomas> ret = new ArrayList<Idiomas>();
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - IdiomasDAO - encontrarId - Intentando encontrar id del elemento");
            
            String sql = "SELECT Idioma FROM IDIOMAS WHERE idPelicula = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPeliculaAsociado);
                
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()){
                    ret.add(Idiomas.valueOf(rs.getString("Idioma")));
                }

                if (ret.isEmpty())
                    System.out.println("\"PlataformaTDL2 - IdiomasDAO - encontrarId - ERROR no se encontro id del elemento");
                else
                    System.out.println("\"PlataformaTDL2 - IdiomasDAO - encontrarId - id del elemento encontrada correctamente");
                
                rs.close();
            }
            
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return ret;
    }

    @Override
    public int encontrarComienzoListaViaIdPelicula(int idPeliculaAsociado) {
        int idEncontrada = 0;
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - IdiomasDAO - encontrarId - Intentando encontrar id del elemento");
            
            String sql = "SELECT ID FROM IDIOMAS WHERE idPelicula = ? AND Idioma = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idPeliculaAsociado);
                pstmt.setString(2, idioma);
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    idEncontrada = rs.getInt("ID");
                    System.out.println("\"PlataformaTDL2 - IdiomasDAO - encontrarId - id del elemento encontrada correctamente");
                } else {
                    System.out.println("\"PlataformaTDL2 - IdiomasDAO - encontrarId - ERROR no se encontro id del elemento");
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
    public boolean existeElencoViaIdPelicula(int idPeliculaAsociado) {
        return encontrarComienzoListaViaIdPelicula(idPeliculaAsociado) > 0;
    }
    
}
