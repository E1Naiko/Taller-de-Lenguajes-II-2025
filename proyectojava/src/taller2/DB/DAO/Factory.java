package taller2.DB.DAO;


import java.time.LocalTime;
import java.util.List;

import taller2.DB.CargaCSV;
import taller2.DB.ConsultaPeliculasOMDb;
import taller2.DB.JDBC.MetadatosDAOJDBC;
import taller2.DB.JDBC.PeliculasDAOJDBC;
import taller2.DB.JDBC.ReseniasDAOJDBC;
import taller2.DB.JDBC.UsuariosFinalDAOJDBC;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class Factory {
    private static int DEBUG_LIMITE = 0;
    private static LocalTime tiempo;
    private static PeliculasDAO peliculasDAO = null;
    private static ReseniasDAO reseniasDAO = null;
    private static UsuariosFinalDAO usuariosFinalDAO = null;
    private static MetadatosDAO metadatosDAO = null;
    @SuppressWarnings("unused")
    private static ConsultaPeliculasOMDb consultaOmdb = null;
    private static CargaCSV accesoCSV = null;
    private static List<Pelicula> listaPeliculas = null;
    
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
            consultaOmdb = new ConsultaPeliculasOMDb();

            System.out.println("FACTORY - TEST API");
            ConsultaPeliculasOMDb.consultarPelicula("Guardians of the Galaxy Vol. 2");
            
            System.out.println("FACTORY - TEST Carga CSV");
            accesoCSV = new CargaCSV();
            listaPeliculas = accesoCSV.getPeliculasParseadas();
            System.out.println("Factory - Pasando de memoria a db");
            //peliculasDAO.setImprimirDebug(false);
            //metadatosDAO.setImprimirDebug(false);
            pasarListaPeliculas_a_BD();
            peliculasDAO.setImprimirDebug(true);
            metadatosDAO.setImprimirDebug(true);
            
        } catch (Exception e) {
            System.err.println("FactoryDAO static init error: " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void pasarListaPeliculas_a_BD(){
        int ini, fin;
        ini = tiempo.now().toSecondOfDay();
        int i = 0;
        if (!listaPeliculas.isEmpty() && peliculasDAO!=null){
            for (Pelicula peli: listaPeliculas){
                i++;
                if (!peliculasDAO.existePelicula(peli) || !metadatosDAO.existeMetadatos(peli.getMetadatos())){
                    peliculasDAO.insertarPeliculas(peli);
                }
                if (DEBUG_LIMITE!= 0 && i>=DEBUG_LIMITE) break;
            }
        }
        fin = tiempo.now().toSecondOfDay();
        System.out.println("Factory - pasarListaPeliculas_a_BD - Terminado en " + (fin-ini) + " segundos");
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
    
    public static List<Pelicula> getListaPeliculasCSV() {
        return listaPeliculas;
    }
}
