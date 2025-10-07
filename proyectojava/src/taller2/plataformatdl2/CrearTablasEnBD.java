package taller2.plataformatdl2;

import java.sql.*;

import org.sqlite.SQLiteException;

public class CrearTablasEnBD{
    private void CrearTablasEnBD(Connection connection) throws SQLiteException {
        Statement stmt;
        stmt = connection.createStatement();
        String sql=" CREATE TABLE IF NOT EXISTS USUARIO ("+
        "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
        ","+    
        ","+
        ""+
        ");";
    }
}
