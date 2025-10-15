package taller2.plataformatdl2;

import taller2.DB.DAO.*;
import taller2.DB.JDBC.PeliculasDAOJDBC;
import taller2.DB.JDBC.ReseniasDAOJDBC;
import taller2.DB.JDBC.UsuariosFinalDAOJDBC;
import java.util.Scanner;

/**
 * Clase principal que representa la plataforma de streaming.
 * Esta clase maneja los usuarios y el catálogo de contenidos.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.1 - 2025-10-15
 */

public class Main {
    
    public static void main(String[] args) {
        PeliculasDAO peliculasDAO = new PeliculasDAOJDBC();
        ReseniasDAO reseniasDAO = new ReseniasDAOJDBC();
        UsuariosFinalDAO usuariosFinalDAO = new UsuariosFinalDAOJDBC();

        peliculasDAO.crearTablaPeliculas();
        reseniasDAO.crearTablaResenias();
        usuariosFinalDAO.crearTablaUsuarioFinal();

        UsuarioFinal nuevoUsuario= cargarUsuario();
        usuariosFinalDAO.insertarUsuarioFinal(nuevoUsuario);
    }
    //Metodo para registrar Usuario
    private static UsuarioFinal cargarUsuario(){
        Scanner scanner= new Scanner(System.in);
        String nombre= scanner.nextLine();
        String email = scanner.nextLine();
        String contrasena = scanner.nextLine();
        String idioma = scanner.nextLine();
        String generosPreferidos =scanner.nextLine();
        String listaPreferida = scanner.nextLine();
        String historial = scanner.nextLine();
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
        System.out.println(nuevoUsuario.toString());
        scanner.close();
        return nuevoUsuario;
    }
}