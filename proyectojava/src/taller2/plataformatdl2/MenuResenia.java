package taller2.plataformatdl2;

import java.util.Scanner;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;
import taller2.plataformatdl2.Model.ManejoDeContenido.Resena;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;

public class MenuResenia {
    public MenuResenia(){
    }

    public void cargarUsuarioEnUsuariosFinalDAO(){
        
        UsuarioFinal nuevoUsuario = cargarUsuario();
        Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUsuario);
    }

    public void cargarPeliculaEnPeliculasDAO(){
        
        Pelicula nuevaPelicula = cargarPelicula();
        Factory.getPeliculasDAO().insertarPeliculas(nuevaPelicula);
    }

    public void cargarPeliculaEnReseniasDAO(){
        Resena nuevaResenia = cargarResenia();
        //Factory.getReseniasDAO().insertarResenia();// pasar todo a logica usando interfaces
    }


    /** 
     * @return UsuarioFinal
     */
    //Metodo para registrar Usuario
    private UsuarioFinal cargarUsuario(){
        Scanner scanner= new Scanner(System.in);
        String nombre= null;
        String email= null;
        String contrasena= null;
        String idioma= null;

        do {
            System.out.print("Ingrese nombre: ");
            nombre= scanner.nextLine();
        } while (!verificarNombre(nombre));
    
        do {
            System.out.println("Ingresar Email: ");
            email = scanner.nextLine();
        } while (!verificarEmail(email));
    
        do {
            System.out.println("Ingresar Contraseña: ");
            contrasena = scanner.nextLine();
        } while (!verificarContrasena(contrasena));

        do {
            System.out.println("Ingresar Idioma: ");
            idioma = scanner.nextLine();
        } while (!verificarIdioma(idioma));

        System.out.println("Ingresar Generos Preferidos: ");
        String generosPreferidos ="Enumerativo"; // TODO Debe ser enumerativo pero por el momento es String
        System.out.println("Ingresar Lista Preferida: ");
        String listaPreferida = "ListaVacia"; //TODO Tambien como en Historial
        System.out.println("Ingresar Historial: ");
        String historial = "ListaVacia"; // TODO Por el momento lo tratamos como string luego cambiamos a lista
        
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
        System.out.println(nuevoUsuario.toString());
        scanner.close();
        return nuevoUsuario;
    }

    private boolean verificarNombre(String nombreIN){ 
        boolean res = true;
        if (!res){
            if (nombreIN.trim().isEmpty()) { // Verifica si el nombre no esta vacio
            System.out.println ("El nombre no puede estar vacio");
            res= false;
        } else if (!nombreIN.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")) {
            System.out.println("EL nombre solo puede tener letras y espacios");
            res= false;
        }
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }

    private boolean verificarEmail(String emailIN){ 
        boolean res = true;
        if (!res){
            if (emailIN.trim().isEmpty()) {
            System.out.println("El email no puede estar vacio");
            res= false;
        } else if (!emailIN.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("El email no cumple con el formato aaa@bbb");
            res= false;
        }
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }

    private boolean verificarContrasena(String contrasenaIN){ 
        boolean res = true;
        if (!res){
            if (contrasenaIN.trim().isEmpty()) {
            System.out.println("La contraseña no puede estar vacia");
            res= false;
        }
        }
        return res;
    }

    private boolean verificarIdioma(String idiomaIN){ 
        boolean res = true;
        if (!res){
         if (idiomaIN.trim().isEmpty()) {
            System.out.println("El idioma no puede estar vacio");
            res= false;
        }
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }

    private Pelicula cargarPelicula(){
        Scanner scanner= new Scanner(System.in);

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
        scanner.close();
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

    private Pelicula cargarResenia(){
        Scanner scanner= new Scanner(System.in);
        String actNombre;
        String actContrasenia;
        boolean login = false;

        do {
            System.out.println("Ingrese nombre de usuario: ");
            actNombre = scanner.next();
            System.out.println("Ingrese contrasenia: ");
            actContrasenia = scanner.next();
            
            login = Factory.getUsuariosFinalDAO().checkearUsuarioViaLogin(actNombre, actContrasenia);
            if (login)
                System.out.println("Usuario ingresado con exito.");
            else
                System.out.println("Error, usuario o contrasenia no válido.");
            
        } while (!login);


/*
        do {
            System.out.println("Ingrese Calidad: ");
            calidad = scanner.nextLine();

        } while (!verificarCalidad(calidad));
        
        Resena nuevaResenia = new Resena();
        scanner.close();
        return nuevaResenia;
    }
*/
    /*private boolean verificarCalidad(String calidadIN){ // TODO - Definir criterio para calidad
        boolean res = true;

        if (!res){
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }*/
}
