package taller2.plataformatdl2;

import taller2.DB.DAO.*;
import taller2.DB.JDBC.*;

/**
 * Clase principal que representa la plataforma de streaming.
 * Esta clase maneja los usuarios y el catálogo de contenidos.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.1 - 2025-10-15
 * 
 */

public class Main {
    
    public static void main(String[] args) {
        PeliculasDAO peliculasDAO = new PeliculasDAOJDBC();
        ReseniasDAO reseniasDAO = new ReseniasDAOJDBC();
        UsuariosFinalDAO usuariosFinalDAO = new UsuariosFinalDAOJDBC();

        peliculasDAO.crearTablaPeliculas();
        reseniasDAO.crearTablaResenias();
        usuariosFinalDAO.crearTablaUsuarioFinal();
    }
}