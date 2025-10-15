package taller2.DB.JDBC;

import java.sql.*;
import taller2.DB.DAO.ReseniasDAO;
import taller2.plataformatdl2.Resena;

public class ReseniasDAOJDBC implements ReseniasDAO {
  @Override
  public void crearTablaResenias() {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      System.out.println("PlataformaTDL2 - ReseniasDAOJDBC - Creando Tabla");

      stmt = c.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS RESENIAS " +
          "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
          " Id_Usuario           INTEGER     NOT NULL, " +
          " Id_Pelicula          INTEGER     NOT NULL, " +
          " Calificacion        INTEGER     NOT NULL, " +
          " Comentario          TEXT        NOT NULL, " +
          " Aprobado             INTEGER     NOT NULL   DEFAULT 0," + // 0 = false, 1 = true
          " Hora                 TEXT        NOT NULL   DEFAULT (datetime('now')))";
      stmt.executeUpdate(sql);
      System.out.println("PlataformaTDL2 - ReseniasDAOJDBC - Tabla Creada Exitosamente");
      stmt.close();
      c.close();
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  @Override
  public void insertarResenia(int idUsuario, int idPelicula, Resena resenia) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinal - Intentando insertar elemento");

            stmt = c.createStatement();
            String sql = "INSERT INTO RESENIAS ('Id_Usuario,Id_Pelicula,Calificacion,Comentario,Aprobado,Hora') "
                    +
                    "VALUES ('" + idUsuario + ", " + idPelicula + ", " + 
                    resenia.getPuntuacion() + ", " +
                    resenia.getPuntuacion() + ", " +
                    "');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("\"PlataformaTDL2 - UsuariosFinal - Elemento insertado correctamente");
    }

  @Override
  public void eliminarResenia() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'eliminarResenia'");
  }

  @Override
  public int encontrarIdResenia() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'encontrarIdResenia'");
  }

  @Override
  public ResultSet devolverResenia(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'devolverResenia'");
  }

  @Override
  public Resena devolverResenia() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'devolverResenia'");
  }
}