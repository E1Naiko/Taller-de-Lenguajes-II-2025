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
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS RESENIAS " +
          "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
          " Id_Usuario           INTEGER     NOT NULL, " +
          " Id_Pelicula          INTEGER     NOT NULL, " +
          " Calificacion        INTEGER     NOT NULL, " +
          " Comentario          TEXT        NOT NULL, " +
          " Aprobado             INTEGER     NOT NULL   DEFAULT 0," + // 0 = false, 1 = true
          " Hora                 TEXT        NOT NULL   DEFAULT (datetime('now'))" +
          ")";
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
    System.out.println("PlataformaTDL2 - Tabla Creada Exitosamente");
  }

  @Override
  public void insertarResenia() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'insertarResenia'");
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