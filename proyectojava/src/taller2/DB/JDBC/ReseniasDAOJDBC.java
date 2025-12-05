package taller2.DB.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.Factory;
import taller2.DB.DAO.ReseniasDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;

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
    
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
      c.setAutoCommit(false);
      System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando encontrar id del elemento");
      
      String sql = "SELECT ID FROM RESENIAS WHERE ID_USUARIO=? AND ID_PELICULA=? AND Puntuacion=? AND Comentario=? AND Aprobado=?";
      try (PreparedStatement pstmt = c.prepareStatement(sql)) {
        pstmt.setInt(1, idUsuario);
        pstmt.setInt(2, idPelicula);
        pstmt.setInt(3, resenia.getPuntuacion());
        pstmt.setString(4, resenia.getComentario());
        pstmt.setInt(5, aprobado);
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
          idEncontrada = rs.getInt("ID");
        }
        rs.close();
      }
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    return idEncontrada;
  }
  
  /** 
  * @return List<Resena>
  */
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
  
  /** 
  * @param id
  * @return Resena
  */
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
    
    /** 
    * @param idResenia
    * @return boolean
    */
    public boolean reseniaExiste(int idResenia){
      return devolverReseniaViaId(idResenia)!=null ? true : false;
    }
    
    /** 
    * @param id
    */
    @Override
    public void aprobarReseniaViaId(int id) {
      try (Connection c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db")) {
        Class.forName("org.sqlite.JDBC");
        c.setAutoCommit(false);
        System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando aprobar reseña con ID: " + id);
        
        String sql = "UPDATE RESENIAS SET Aprobado = 1 WHERE ID = ?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
          pstmt.setInt(1, id);
          int filasActualizadas = pstmt.executeUpdate();
          
          if (filasActualizadas == 0) {
            System.out.println("PlataformaTDL2 - ReseniasDAO - No se encontró reseña con ID " + id);
          } else {
            System.out.println("PlataformaTDL2 - ReseniasDAO - Reseña aprobada correctamente");
          }
        }
        
        c.commit();
      } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
      }
    }
    
    @Override
    public int devolverIdViaUsuarioYPelicula(UsuarioFinal usuario, Pelicula pelicula) {
      int idEncontrada = 0;
      int idUsuario = 0;
      int idPelicula = 0;
      Connection c = null;
      if (Factory.getUsuariosFinalDAO().existeUsuario(usuario) && Factory.getPeliculasDAO().existePelicula(pelicula)){
        idUsuario = Factory.getUsuariosFinalDAO().devolverIdUsuarioFinal(usuario);
        idPelicula = Factory.getPeliculasDAO().encontrarIdPelicula(pelicula);
      }
      else
        return 0;
      
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
        c.setAutoCommit(false);
        System.out.println("\"PlataformaTDL2 - ReseniasDAO - Intentando encontrar id del elemento " + usuario.getEmail() + " " + pelicula.getMetadatos().getTitulo());
        
        String sql = "SELECT ID FROM RESENIAS WHERE ID_USUARIO=? AND ID_PELICULA=?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
          pstmt.setInt(1, idUsuario);
          pstmt.setInt(2, idPelicula);
          
          ResultSet rs = pstmt.executeQuery();
          if (rs.next()) {
            idEncontrada = rs.getInt("ID");
          }
          rs.close();
        }
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      if (idEncontrada>0) System.out.println("\"PlataformaTDL2 - ReseniasDAO - id del elemento: " + idEncontrada);
      else System.out.println("\"PlataformaTDL2 - ReseniasDAO - Id del elemento no encontrada");
      return idEncontrada;
    }
    
    @Override
    public boolean reseniaExiste(UsuarioFinal usuario, Pelicula pelicula) {
      return devolverIdViaUsuarioYPelicula(usuario, pelicula)>0;
    }
    
  }