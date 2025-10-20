package taller2.plataformatdl2;

import java.util.Scanner;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.ManejoDeContenido.Pelicula;
import taller2.plataformatdl2.ManejoDeUsuarios.UsuarioFinal;

public class MenuResenia {
    public MenuResenia(){
    }

    public void cargarUsuarioEnUsuariosFinalDAO(Scanner scanner){
        UsuarioFinal nuevoUsuario = cargarUsuario(scanner);
        Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUsuario);
    }
    
    public void cargarPeliculaEnPeliculasDAO(Scanner scanner){
        Pelicula nuevaPelicula = cargarPelicula(scanner);
        Factory.getPeliculasDAO().insertarPeliculas(nuevaPelicula);
    }

    /** 
     * @return UsuarioFinal
     */
    //Metodo para registrar Usuario
    private static UsuarioFinal cargarUsuario(Scanner scanner){

        System.out.print("Ingrese nombre: ");
        String nombre= scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese idioma: ");
        String idioma = scanner.nextLine();
        System.out.print("Ingrese géneros preferidos (separados por comas): ");
        String generosPreferidos =scanner.nextLine();
        System.out.print("Ingrese lista preferida (opcional): ");
        String listaPreferida = scanner.nextLine();
        System.out.print("Ingrese historial (opcional): ");
        String historial = scanner.nextLine();
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
        System.out.println(nuevoUsuario.toString());
        return nuevoUsuario;
    }

    private Pelicula cargarPelicula(Scanner scanner){

        String calidad = null;
        String audio = null;
        String direccionArchivo = null;

        do {
            System.out.println("Ingrese Calidad: ");
            calidad = scanner.nextLine();

        } while (!verificarCalidad(calidad));
        
        do {
            System.out.println("Ingrese Audio: ");
            audio = scanner.nextLine();

        } while (!verificarAudio(audio));
        
        do {
            System.out.println("Ingrese Direccion del Archivo: ");
            direccionArchivo = scanner.nextLine();

        } while (!verificarDireccionArchivo(direccionArchivo));
        
        Pelicula nuevaPelicula = new Pelicula(calidad, audio, direccionArchivo);
        return nuevaPelicula;
    }

    private boolean verificarCalidad(String calidadIN){ // TODO - Definir criterio para calidad
        boolean res = true;

        if (!res){
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }

    private boolean verificarAudio(String audioIN){ // TODO - definir criterio para audio
        boolean res = true;

        if (!res){
            System.out.println("ERROR - TIPO DE AUDIO NO VALIDO");
        }
        return res;
    }
    
    private boolean verificarDireccionArchivo(String direccionArchivoIN){ // TODO - definir criterio para direccion de archivo
        boolean res = true;
        
        if (!res){
            System.out.println("ERROR - DIRECCION DE ARCHIVO NO VALIDA");
        }
        return res;
    }
}
