package taller2.DB.DAO;


import taller2.DB.JDBC.MetadatosDAOJDBC;
import taller2.DB.JDBC.PeliculasDAOJDBC;
import taller2.DB.JDBC.ReseniasDAOJDBC;
import taller2.DB.JDBC.UsuariosFinalDAOJDBC;

public class Factory {
    private static PeliculasDAO peliculasDAO = null;
    private static ReseniasDAO reseniasDAO = null;
    private static UsuariosFinalDAO usuariosFinalDAO = null;
    private static MetadatosDAO metadatosDAO = null;
    
    static {
        try {
            peliculasDAO = new PeliculasDAOJDBC();
            peliculasDAO.crearTablaPeliculas();
            reseniasDAO = new ReseniasDAOJDBC();
            reseniasDAO.crearTablaResenias();
            usuariosFinalDAO = new UsuariosFinalDAOJDBC();
            usuariosFinalDAO.crearTablaUsuarioFinal();
            metadatosDAO = new MetadatosDAOJDBC();
            metadatosDAO.crearTablaMetadatos();

            
        } catch (Exception e) {
            System.err.println("FactoryDAO static init error: " + e.getClass().getName() + ": " + e.getMessage());
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

    /** 
     * @return MetadatosDAO
     */
    public static MetadatosDAO getMetadatosDAO() {
        return metadatosDAO;
    }
}
