package taller2.DB.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.Factory;
import taller2.DB.DAO.ReseniasDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;;

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
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando encontrar id del elemento");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM RESENIAS WHERE ID_USUARIO=" + idUsuario +
      " AND ID_PELICULA=" + idPelicula +
      " AND Puntuacion=" + resenia.getPuntuacion() + 
      " AND Comentario=" + resenia.getComentario() + 
      " AND Aprobado=" + aprobado);
      
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
      System.exit(0);
    }
    return idEncontrada;
  }
  
  /** 
  * @param id
  * @return Resena
  */
  public Resena devolverReseniaViaId(int idResenia){
    Connection c = null;
    Statement stmt = null;
    
    UsuarioFinal usuario = null;
    int puntuacion = 0;
    String comentario = null;
    int idContenido = 0;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando encontrar id del elemento");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM USUARIOS_FINAL WHERE ID=" + idResenia +
      ";" );
      
      if (rs.next()){
        int idUsuario = rs.getInt("IdUsuario");
        usuario = taller2.DB.DAO.Factory.getUsuariosFinalDAO().devolverUsuarioFinalViaId(idUsuario);
        idContenido = rs.getInt("IdPelicula");
        puntuacion = rs.getInt("Puntuacion");
        comentario = rs.getString("Comentario");
      }
      
      rs.close();
      stmt.close();
      c.close();
      Resena resenia = new Resena(usuario, Factory.getPeliculasDAO().devolverPeliculaViaId(idContenido), puntuacion, comentario);
      return resenia;
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    return null;
  }
  
  /** 
  * @return List<Resena>
  */
  @Override
  public List<Resena> obtenerResenias() {
    // Aclaración: somos totalmente concientes que hay formas mas optimizadas de devolver todos los usuarios
    //   pero elegimos usar esta ya que reutiliza codigo
    List<Resena> lista = new ArrayList<Resena>();
    int maxId = this.getMaxId();
    
    for (int i=1; i<=maxId; i++)
    lista.add(this.devolverReseniaViaId(i));
    
    return lista;
  }
  
  
  
  /** 
  * @return int
  */
  public int getMaxId() {
    int maxId = 0;
    Connection c = null;
    Statement stmt = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - Intentando encontrar maxId del elemento");
      
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS last_id FROM USUARIOS_FINAL");
      
      maxId = rs.getInt("last_id");
      
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    return maxId;
  }
}