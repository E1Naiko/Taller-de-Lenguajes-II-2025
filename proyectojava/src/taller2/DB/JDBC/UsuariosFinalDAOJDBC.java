package taller2.DB.JDBC;

import java.sql.*;
import taller2.DB.DAO.UsuariosFinalDAO;
import taller2.plataformatdl2.UsuarioFinal;

public class UsuariosFinalDAOJDBC implements UsuariosFinalDAO {
    
    @Override
    public void crearTablaUsuarioFinal() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - UsuariosFinal - Creando Tabla.");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USUARIOS_FINAL " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " Nombre                  TEXT     NOT NULL, " +
            " Email                   TEXT     NOT NULL, " +
            " Contrasena              TEXT     NOT NULL, " +
            " Idioma                  TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - UsuariosFinal - Tabla Creada Exitosamente.");
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /** 
    * @param usuario
    */
    @Override
    public void insertarUsuarioFinal(UsuarioFinal usuario) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando insertar elemento.");
            
            String sql = "INSERT INTO USUARIOS_FINAL (Nombre, Email, Contrasena, Idioma) VALUES (?,?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getContrasena());
                pstmt.setString(4, usuario.getIdioma());
                pstmt.executeUpdate();
            }
            
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Elemento insertado correctamente.");
            
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    @Override
    public void eliminarUsuarioFinal(int idUsuario) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando eliminar elemento");
            
            String sql = "DELETE FROM USUARIOS_FINAL WHERE ID = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idUsuario);
                if (pstmt.executeUpdate() == 0) {
                    System.out.println("PlataformaTDL2 - UsuariosFinalDAO - No se encontró reseña con ID " + idUsuario);
                }
            }
            
            c.commit();
            c.close();
            
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Elemento eliminado correctamente");
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    /** 
    * @return int
    */
    @Override
    public int devolverIdUsuarioFinal(UsuarioFinal usuario) {
        int idEncontrada = 0;
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY WHERE NOMBRE=" + usuario.getNombre() +
            ", Email=" + usuario.getEmail() +
            ", Contrasena=" + usuario.getContrasena()  + 
            ", Idioma=" + usuario.getIdioma() + ";" );
            
            idEncontrada = rs.getInt("ID");
            
            if (idEncontrada==0)
                System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - ERROR no se encontro id del elemento");
            else
                System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - id del elemento encontrada correctamente");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return idEncontrada;
    }
}