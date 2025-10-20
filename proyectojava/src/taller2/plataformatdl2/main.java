package taller2.plataformatdl2;

import taller2.DB.DAO.*;
import taller2.DB.JDBC.*;
import taller2.plataformatdl2.ManejoDeUsuarios.UsuarioFinal;

import java.util.*;

/**
 * Clase principal que representa la plataforma de streaming.
 * Esta clase maneja los usuarios y el catálogo de contenidos.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.1 - 2025-10-15
 */

public class Main {
    
    /** 
     * @param args
     */
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
    /** 
     * @return UsuarioFinal
     */
    //Metodo para registrar Usuario
    private static UsuarioFinal cargarUsuario(){
        List<String> errores = new ArrayList<>(); //Creo una lista de errores para validar los errores en un solo intento
        Scanner scanner= new Scanner(System.in);
        // Validar Nombre
        System.out.println("Ingresar Nombre: ");
        String nombre= scanner.nextLine();
        if (nombre.trim().isEmpty()) { // Verifica si el nombre no esta vacio
            errores.add ("El nombre no puede estar vacio");
        } else if (!nombre.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")) {
            errores.add("EL nombre solo puede tener letras y espacios");
        }

        // Validar Email
        System.out.println("Ingresar Email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            errores.add("El email no puede estar vacio");
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errores.add("El email no cumple con el formato aaa@bbb");
        }
        // Validar Contraseña que no este
        System.out.println("Ingresar Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.println("Ingresar Idioma: ");
        String idioma = scanner.nextLine();
        System.out.println("Ingresar Generos Preferidos: ");
        String generosPreferidos =scanner.nextLine();
        System.out.println("Ingresar Lista Preferida: ");
        String listaPreferida = scanner.nextLine();
        System.out.println("Ingresar Historial: ");
        String historial = scanner.nextLine();
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
        System.out.println(nuevoUsuario.toString());
        scanner.close();
        return nuevoUsuario;
    }
}
