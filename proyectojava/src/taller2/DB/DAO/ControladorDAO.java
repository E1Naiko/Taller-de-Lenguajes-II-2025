package taller2.DB.DAO;

import taller2.DB.JDBC.PeliculasDAOJDBC;
import taller2.DB.JDBC.ReseniasDAOJDBC;
import taller2.DB.JDBC.UsuariosFinalDAOJDBC;

public class ControladorDAO {
    private static PeliculasDAO peliculasDAO = new PeliculasDAOJDBC();
    private static ReseniasDAO reseniasDAO = new ReseniasDAOJDBC();
    private static UsuariosFinalDAO usuariosFinalDAO = new UsuariosFinalDAOJDBC();

    public ControladorDAO(){
        peliculasDAO = new PeliculasDAOJDBC();
        reseniasDAO = new ReseniasDAOJDBC();
        usuariosFinalDAO = new UsuariosFinalDAOJDBC();

        peliculasDAO.crearTablaPeliculas();
        reseniasDAO.crearTablaResenias();
        usuariosFinalDAO.crearTablaUsuarioFinal();
    }

    
    public static PeliculasDAO getPeliculasDAO() {
        return peliculasDAO;
    }

    public static ReseniasDAO getReseniasDAO() {
        return reseniasDAO;
    }

    public static UsuariosFinalDAO getUsuariosFinalDAO() {
        return usuariosFinalDAO;
    }
}
