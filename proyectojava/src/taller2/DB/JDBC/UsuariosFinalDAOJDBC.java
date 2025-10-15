package taller2.DB.JDBC;

import java.sql.*;
import taller2.DB.DAO.UsuariosFinalDAO;
import taller2.plataformatdl2.Usuario;
import taller2.plataformatdl2.UsuarioFinal;

public class UsuariosFinalDAOJDBC implements UsuariosFinalDAO {

    @Override
    public void crearTablaUsuarioFinal() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - UsuariosFinal - Creando Tabla");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USUARIOS_FINAL " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Nombre                  TEXT     NOT NULL, " +
                    " Email                   TEXT     NOT NULL, " +
                    " Contrasena              TEXT     NOT NULL, " +
                    " Idioma                  TEXT     NOT NULL, " +
                    " Generos_Preferidos      TEXT     NOT NULL, " + // TODO - IMPLEMENTAR TABLA DE Generos_Preferidos
                    " Historial               TEXT     NOT NULL, " + // TODO - IMPLEMENTAR TABLA DE Historial
                    " Lista_Preferida         TEXT     NOT NULL)"; // TODO - IMPLEMENTAR TABLA DE Lista_Preferida
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - UsuariosFinal - Tabla Creada Exitosamente");
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void insertarUsuarioFinal(UsuarioFinal usuario) {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinal - Intentando insertar elemento");
            String sql = "INSERT INTO USUARIOS_FINAL (Nombre, Email, Contrasena, Idioma, Generos_Preferidos, Historial, Lista_Preferida) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getContrasena());
                pstmt.setString(4, usuario.getIdioma());
                pstmt.setString(5, usuario.getGenerosPreferidos());
                pstmt.setString(6, usuario.getHistorial());
                pstmt.setString(7, usuario.getListaPreferida());
                pstmt.executeUpdate();
            }

            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("\"PlataformaTDL2 - UsuariosFinal - Elemento insertado correctamente");
    }

    @Override
    public void eliminarUsuarioFinal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuarioFinal'");
    }

    @Override
    public int encontrarIdUsuarioFinal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarIdUsuarioFinal'");
    }

    @Override
    public Usuario devolverUsuarioFinal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolverUsuarioFinal'");
    }

    @Override
    public ResultSet devolverUsuarioFinal(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolverUsuarioFinal'");
    }

}
