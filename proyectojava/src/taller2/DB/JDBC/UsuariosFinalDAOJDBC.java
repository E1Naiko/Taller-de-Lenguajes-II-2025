package taller2.DB.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import taller2.DB.DAO.UsuariosFinalDAO;
import taller2.plataformatdl2.Model.ManejoDeContenido.Genero;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.*;

public class UsuariosFinalDAOJDBC implements UsuariosFinalDAO {
    
    @Override
    public void crearTablaUsuarioFinal() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            System.out.println("PlataformaTDL2 - UsuariosFinal - crearTablaUsuarioFinal - Creando Tabla.");
            
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USUARIOS_FINAL " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " Nombre                  TEXT         NOT NULL, " +
            " Apellido                TEXT         NOT NULL, " +
            " DNI                     INTEGER      NOT NULL, " +
            " Email                   TEXT         NOT NULL, " +
            " Contrasena              TEXT         NOT NULL, " +
            " Idioma                  TEXT         NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("PlataformaTDL2 - UsuariosFinal - crearTablaUsuarioFinal - Tabla Creada Exitosamente.");
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
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - insertarUsuarioFinal - Intentando insertar elemento.");
            
            String sql = "INSERT INTO USUARIOS_FINAL (Nombre, Apellido, DNI, Email, Contrasena, Idioma) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getApellido());
                pstmt.setInt(3, usuario.getDNI());
                pstmt.setString(4, usuario.getEmail());
                pstmt.setString(5, usuario.getContrasena());
                pstmt.setString(6, usuario.getIdioma());
                pstmt.executeUpdate();
            }
            
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - insertarUsuarioFinal - Elemento insertado correctamente.");
            
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    
    
    /** 
    * @param idUsuario
    */
    @Override
    public void eliminarUsuarioFinal(int idUsuario) {
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - eliminarUsuarioFinal - Intentando eliminar elemento");
            
            String sql = "DELETE FROM USUARIOS_FINAL WHERE ID = ?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setInt(1, idUsuario);
                if (pstmt.executeUpdate() == 0) {
                    System.out.println("PlataformaTDL2 - UsuariosFinalDAO - eliminarUsuarioFinal - No se encontró reseña con ID " + idUsuario);
                }
            }
            
            c.commit();
            c.close();
            
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - eliminarUsuarioFinal - Elemento eliminado correctamente");
            
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
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - devolverIdUsuarioFinal - Intentando encontrar id del elemento");
            
            String sql = "SELECT ID FROM USUARIOS_FINAL WHERE Nombre=? AND Email=? AND Contrasena=? AND Idioma=?";
            try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getContrasena());
                pstmt.setString(4, usuario.getIdioma());
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    idEncontrada = rs.getInt("ID");
                    System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - devolverIdUsuarioFinal - id del elemento encontrada correctamente");
                } else {
                    System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - devolverIdUsuarioFinal - ERROR no se encontro id del elemento");
                }
                rs.close();
            }
            
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return idEncontrada;
    }
    
    
    
    
    /** 
    * @param usuario
    * @return boolean
    */
    public boolean existeUsuario(UsuarioFinal usuario){
        return devolverIdUsuarioFinal(usuario)!=0 ? true : false;
    }
    
    
    
    /** 
    * @param nombreUsuario
    * @param contrasenia
    * @return int
    */
    public int encontrarIdUsuarioViaLogin(String email, String contrasenia){
        int idEncontrada = 0;
        Connection c = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaLogin - Intentando encontrar id del elemento");
            
            String sql = "SELECT ID FROM USUARIOS_FINAL WHERE Email = ? AND Contrasena = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, contrasenia);
            
            // Intenamos comparar como lo veniamos haciendo pero se rompia el codigo
            // asi que compiamos el uso de prepared statement
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idEncontrada = rs.getInt("ID");
                System.out.println("PlataformaTDL2 - Login exitoso para usuario: " + email);
            } else {
                System.out.println("PlataformaTDL2 - Credenciales inválidas para: " + email);
            }
            
            if (idEncontrada==0)
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaLogin - ERROR no se encontro id del elemento");
            else
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaLogin - id del elemento encontrada correctamente");
            
            rs.close();
            pstmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return idEncontrada;
    }
    
    
    
    
    /** 
    * @param nombreUsuario
    * @param contrasenia
    * @return boolean
    */
    public boolean checkUsuarioViaLogin(String nombreUsuario, String contrasenia){
        return encontrarIdUsuarioViaLogin(nombreUsuario, contrasenia)!=0 ? true : false;
    }
    
    
    
    /** 
    * @param id
    * @return UsuarioFinal
    */
    public UsuarioFinal devolverUsuarioFinalViaId(int id){
        Connection c = null;
        Statement stmt = null;
        
        String nombre = null;
        String apellido = null;
        int DNI = 0;
        String email = null;
        String contrasena = null;
        String idioma = null;
        List<Genero> generosPreferidos = null;
        String listaPreferida = null;
        String historial = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - devolverUsuarioFinalViaId - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM USUARIOS_FINAL WHERE ID=" + id +
            ";" );
            
            if (rs.next()){
                nombre = rs.getString("Nombre");
                apellido = rs.getString("Apellido");
                DNI = rs.getInt("DNI");
                email = rs.getString("Email");
                contrasena = rs.getString("Contrasena");
                idioma = rs.getString("Idioma");
                generosPreferidos = null; // TODO - Objeto temporal . Hay que implementar la tabla de generos
                listaPreferida = "PRUEBA GENERAL"; // TODO - String temporal
                historial = "PRUEBA GENERAL"; // TODO - String temporal
            }
            
            rs.close();
            stmt.close();
            c.close();
            UsuarioFinal usuario = new UsuarioFinal(nombre, apellido, DNI, email, contrasena, idioma, generosPreferidos, listaPreferida, historial);
            return usuario;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }
    
    
    
    /** 
    * @param dni
    * @return int
    */
    @Override
    public int encontrarIdUsuarioViaDNI(int dni) {
        int idEncontrada = 0;
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
            c.setAutoCommit(false);
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaDNI - Intentando encontrar id del elemento");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM USUARIOS_FINAL WHERE DNI=" + dni + ";" );
            
            if (rs.next()) {
                idEncontrada = rs.getInt("ID");
            }
            
            if (idEncontrada==0)
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaDNI - ERROR no se encontro id del elemento");
            else
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaDNI - id del elemento encontrada correctamente");
            
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return idEncontrada;
    }
    
    
    
    /** 
    * @param dni
    * @return boolean
    */
    @Override
    public boolean checkUsuarioViaDNI(int dni) {
        return encontrarIdUsuarioViaDNI(dni)!=0 ? true : false;
    }
    
    
    
    /** 
    * @return List<UsuarioFinal>
    */
    @Override
    public List<UsuarioFinal> obtenerUsuarios() {
        // Aclaración: somos totalmente concientes que hay formas mas optimizadas de devolver todos los usuarios
        //   pero elegimos usar esta ya que reutiliza codigo
        List<UsuarioFinal> lista = new ArrayList<UsuarioFinal>();
        int maxId = this.getMaxId();
        System.out.println("DEBUG - maxId encontrada " + maxId);
        
        for (int i=1; i<=maxId; i++)
        lista.add(this.devolverUsuarioFinalViaId(i));
        
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
            System.out.println("\"PlataformaTDL2 - UsuariosFinalDAO - encontrarIdUsuarioViaDNI - Intentando encontrar maxId del elemento");
            
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