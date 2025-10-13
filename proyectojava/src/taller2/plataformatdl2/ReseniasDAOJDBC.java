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
    public void encontrarResenia() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'encontrarResenia'");
    }

    @Override
    public void setCalificacion(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setCalificacion'");
    }

    @Override
    public void setComentario(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setComentario'");
    }

    @Override
    public void setAprobado(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setAprobado'");
    }

    @Override
    public void setHora(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setHora'");
    }

    @Override
    public void setIdUsuario(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setIdUsuario'");
    }

    @Override
    public void setIdPelicula(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setIdPelicula'");
    }

    @Override
    public int getCalificacion(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getCalificacion'");
    }

    @Override
    public String getComentario(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getComentario'");
    }

    @Override
    public Boolean getAprobado(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getAprobado'");
    }

    @Override
    public Time getHora(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getHora'");
    }

    @Override
    public int getIdUsuario(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getIdUsuario'");
    }

    @Override
    public int getIdPelicula(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getIdPelicula'");
    }
}