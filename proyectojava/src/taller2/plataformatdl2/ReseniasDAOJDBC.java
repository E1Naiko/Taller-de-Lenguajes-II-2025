package taller2.plataformatdl2;

import java.sql.*;

public class ReseniasDAOJDBC implements ReseniasDAO {
    public static void crearTabla() {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         System.out.println("Opened database successfully");
         
         stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS RESENIAS " +
         "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
         " IdUsuario           INTEGER     NOT NULL, " + 
         " IdPelicula          INTEGER     NOT NULL, " + 
         " Calificacion        INTEGER     NOT NULL, " + 
         " Comentario          TEXT        NOT NULL, " + 
         "aprobado             INTEGER     NOT NULL   DEFAULT 0," +  // 0 = false, 1 = true
         "hora                 TEXT        NOT NULL   DEFAULT (datetime('now'))" +
            ")";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      System.out.println("PlataformaTDL2 - Tabla Creada Exitosamente");
    }

    @Override
    public void crearTablaResenias() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'crearTablaResenias'");
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