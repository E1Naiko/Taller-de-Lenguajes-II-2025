package taller2.DB.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.Factory;
import taller2.DB.DAO.ReseniasDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;

public class ReseniasDAOJDBC implements ReseniasDAO {
  @Override
  public void crearTablaResenias() {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      System.out.println("PlataformaTDL2 - ReseniasDAO - Creando Tabla");
      
      stmt = c.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS RESENIAS " +
      "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
      " Id_Usuario           INTEGER     NOT NULL, " +
      " Id_Pelicula          INTEGER     NOT NULL, " +
      " Puntuacion           INTEGER     NOT NULL, " +
      " Comentario           TEXT        NOT NULL, " +
      " Aprobado             INTEGER     NOT NULL   DEFAULT 0," + // 0 = false, 1 = true
      " Hora                 TEXT        NOT NULL   DEFAULT (datetime('now')))";
      stmt.executeUpdate(sql);
      System.out.println("PlataformaTDL2 - ReseniasDAO - Tabla Creada Exitosamente");
      stmt.close();
      c.close();
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }
  
  /** 
  * @param idUsuario
  * @param idPelicula
  * @param resenia
  * @param aprobado
  */
  @Override
  public void insertarResenia(int idUsuario, int idPelicula, Resena resenia, int aprobado) {
    if  (aprobado == 0 || aprobado == 1)  {
      Connection c = null;
      
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
        c.setAutoCommit(false);
        
        System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando insertar elemento.");
        
        String sql = "INSERT INTO RESENIAS (Id_Usuario, Id_Pelicula, Puntuacion, Comentario, Aprobado, Hora) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
          pstmt.setInt(1, idUsuario);
          pstmt.setInt(2, idPelicula);
          pstmt.setInt(3, resenia.getPuntuacion());
          pstmt.setString(4, resenia.getComentario());
          pstmt.setInt(5, aprobado);
          pstmt.setString(6, java.time.LocalDateTime.now().toString()); // Trae la hora actual, robado de la api de java
          pstmt.executeUpdate();
        }
        
        c.commit();
        c.close();
        
        System.out.println("\"PlataformaTDL2 - ReseniasDAO - Elemento insertado correctamente.");
        
      } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
      }
    }
    else{
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - ERROR variable aprobado fuera de rango.");
    }
  }
  
  /** 
  * @param idResenia
  */
  @Override
  public void eliminarResenia(int idResenia) {
    Connection c = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando eliminar elemento");
      
      String sql = "DELETE FROM RESENIAS WHERE ID = ?";
      try (PreparedStatement pstmt = c.prepareStatement(sql)) {
        pstmt.setInt(1, idResenia);
        if (pstmt.executeUpdate() == 0) {
          System.out.println("PlataformaTDL2 - ReseniasDAO - No se encontró reseña con ID " + idResenia);
        }
      }
      
      c.commit();
      c.close();
      
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - Elemento eliminado correctamente");
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
  
  /** 
  * @param idUsuario
  * @param idPelicula
  * @param resenia
  * @return int
  */
  @Override
  public int encontrarIdResenia(int idUsuario, int idPelicula, Resena resenia, int aprobado) {
    int idEncontrada = 0;
    Connection c = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando encontrar id del elemento");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM RESENIAS WHERE ID_USUARIO=" + idUsuario +
      " AND ID_PELICULA=" + idPelicula +
      " AND Puntuacion=" + resenia.getPuntuacion() + 
      " AND Comentario=" + resenia.getComentario() + 
      " AND Aprobado=" + aprobado);
      
      if (rs.next())
      idEncontrada = rs.getInt("ID");
      
      if (idEncontrada==0)
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - ERROR no se encontro id del elemento");
      else
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - id del elemento encontrada correctamente");
      
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    return idEncontrada;
  }
  
  @Override
  public List<Resena> devolverReseniasNoAprobadas() {
    Connection c = null;
    Statement stmt = null;
    List<Resena> lista = new ArrayList<Resena>();
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando encontrar todos los elementos deonde Aprobado = 0");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM RESENIAS WHERE Aprobado=0;" );
      
      while (rs.next()) {
        lista.add(new Resena(
        Factory.getUsuariosFinalDAO().devolverUsuarioFinalViaId(rs.getInt("Id_Usuario")),
        Factory.getPeliculasDAO().devolverPeliculaViaId(rs.getInt("Id_Pelicula")),
        rs.getInt("Puntuacion"),
        rs.getString("Comentario"))
        );
      }
      
      rs.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    return lista;
  }
  
  public Resena devolverReseniaViaId(int id){
    Connection c = null;
    Statement stmt = null;
    Resena ret = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando encontrar id del elemento");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM RESENIAS WHERE ID=" + id);
      
      if (rs.next())
      ret = new Resena(
      Factory.getUsuariosFinalDAO().devolverUsuarioFinalViaId(rs.getInt("Id_Usuario")),
      Factory.getPeliculasDAO().devolverPeliculaViaId(rs.getInt("Id_Pelicula")),
      rs.getInt("Puntuacion"),
      rs.getString("Comentario"));
      
      rs.close();
      stmt.close();
      c.close();
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    return ret;
  }
  
  public boolean reseniaExiste(int idResenia){
    return devolverReseniaViaId(idResenia)!=null ? true : false;
  }

  @Override
  public void aprobarReseniaViaId(int id) {
  Connection c = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando encontrar id del elemento");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "UPDATE RESENIAS set Aprobado = 1 where ID=" + id +
      ";" );
      
      rs.close();
      stmt.close();
      c.close();
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  }
}