package taller2.DB.DAO;


import taller2.DB.JDBC.PeliculasDAOJDBC;
import taller2.DB.JDBC.ReseniasDAOJDBC;
import taller2.DB.JDBC.UsuariosFinalDAOJDBC;

public class FactoryDAO {
    private static PeliculasDAO peliculasDAO = new PeliculasDAOJDBC();
    private static ReseniasDAO reseniasDAO = new ReseniasDAOJDBC();
    private static UsuariosFinalDAO usuariosFinalDAO = new UsuariosFinalDAOJDBC();
    
    public FactoryDAO(){
        if (peliculasDAO == null){
            peliculasDAO = new PeliculasDAOJDBC();
            peliculasDAO.crearTablaPeliculas();
        }
        if (reseniasDAO == null){
            reseniasDAO = new ReseniasDAOJDBC();
            reseniasDAO.crearTablaResenias();
        }
        
        if (usuariosFinalDAO == null){
            usuariosFinalDAO = new UsuariosFinalDAOJDBC();
            usuariosFinalDAO.crearTablaUsuarioFinal();
        }
    }
    
    /** 
    * @return PeliculasDAO
    */
    public static PeliculasDAO getPeliculasDAO() {
        return peliculasDAO;
    }
    
    /** 
    * @return ReseniasDAO
    */
    public static ReseniasDAO getReseniasDAO() {
        return reseniasDAO;
    }
    
    /** 
    * @return UsuariosFinalDAO
    */
    public static UsuariosFinalDAO getUsuariosFinalDAO() {
        return usuariosFinalDAO;
    }
}
