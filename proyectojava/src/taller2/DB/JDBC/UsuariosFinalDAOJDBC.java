package taller2.DB.JDBC;

import java.sql.*;
import taller2.DB.DAO.UsuariosFinalDAO;
import taller2.plataformatdl2.Usuario;

public class UsuariosFinalDAOJDBC implements UsuariosFinalDAO {

    @Override
    public void crearTablaUsuarioFinal() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS RESENIAS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Nombre                  TEXT     NOT NULL, " +
                    " Email                   TEXT     NOT NULL, " +
                    " Contrasena              TEXT     NOT NULL, " +
                    " Idioma                  TEXT     NOT NULL, " +
                    " Generos_Preferidos      TEXT     NOT NULL, " + // TODO - IMPLEMENTAR TABLA DE Generos_Preferidos
                    " Historial               TEXT     NOT NULL, " + // TODO - IMPLEMENTAR TABLA DE Historial
                    " Lista_Preferida         TEXT     NOT NULL, " + // TODO - IMPLEMENTAR TABLA DE Lista_Preferida
                    ")";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("PlataformaTDL2 - UsuariosFinal - Tabla Creada Exitosamente");
    }

    @Override
    public void insertarUsuarioFinal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertarUsuarioFinal'");
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
