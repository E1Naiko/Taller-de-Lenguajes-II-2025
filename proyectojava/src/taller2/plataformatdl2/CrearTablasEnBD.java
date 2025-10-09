package taller2.plataformatdl2;

import java.sql.*;

public class CrearTablasEnBD {
    public static void crearTabla() {
        Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:test.db");
         System.out.println("Opened database successfully");
         
         stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
         "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
         " NAME           TEXT    NOT NULL, " + 
         " AGE            INT     NOT NULL, " + 
         " ADDRESS        CHAR(50), " + 
         " SALARY         REAL)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
      System.out.println("PlataformaTDL2 - Tabla Creada Exitosamente");
    }
}