package taller2.plataformatdl2;

import java.util.Scanner;
import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeContenido.Contenido;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;
import taller2.plataformatdl2.Model.ManejoDeContenido.Resena;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;

public class MenuResenia {
    public MenuResenia(){
    }

    public void cargarUsuarioEnUsuariosFinalDAO() {
        Scanner scanner = new Scanner(System.in);
        
        // Llamamos al método que carga los datos y le PASAMOS el scanner.
        System.out.println("--- Inicio de Registro de Usuario ---");
        UsuarioFinal nuevoUsuario = cargarUsuario(scanner);

        //Mostrar al usuario todos los datos que acaba de ingresar.
        System.out.println("\n=============================================");
        System.out.println("    Por favor, revise los datos ingresados:");
        System.out.println(nuevoUsuario.toString()); 
        System.out.println("=============================================");

        //Preguntar si los datos son correctos.
        String confirmacion = "";
        do {
            System.out.print("¿Los datos son correctos? (s/n): ");
            confirmacion = scanner.nextLine();
        } while (!confirmacion.equalsIgnoreCase("s") && !confirmacion.equalsIgnoreCase("n"));

        //Si confirma, se guardan en la Base de Datos.
        if (confirmacion.equalsIgnoreCase("s")) {
            Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUsuario);
            System.out.println("¡Usuario guardado exitosamente!");
        } else {
            System.out.println("Operación cancelada. El usuario no ha sido guardado.");
        }
        scanner.close();
    }

    // TODO - Este es el metodo sin la verifacion de datos por el usuario
    //public void cargarUsuarioEnUsuariosFinalDAO(){
        //UsuarioFinal nuevoUsuario = cargarUsuario();
        //Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUsuario);
    //}

    public void cargarPeliculaEnPeliculasDAO(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Inicio de Carga de Nueva Película ---");
        //Llamamos al método que carga los datos
        Pelicula nuevaPelicula = cargarPelicula(scanner);

        //Mostrar al usuario todos los datos que acaba de ingresar.
        System.out.println("\n=============================================");
        System.out.println("    Por favor, revise los datos ingresados:");
        System.out.println(nuevaPelicula.toString()); 
        System.out.println("=============================================");

        //Preguntar si los datos son correctos.
        String confirmacion = "";
        do {
            System.out.print("¿Los datos son correctos? (s/n): ");
            confirmacion = scanner.nextLine();
        } while (!confirmacion.equalsIgnoreCase("s") && !confirmacion.equalsIgnoreCase("n"));

        //Si confirma, se guardan en la Base de Datos.
        if (confirmacion.equalsIgnoreCase("s")) {
            Factory.getPeliculasDAO().insertarPeliculas(nuevaPelicula);
            System.out.println("¡Película guardada exitosamente!");
        } else {
            System.out.println("Operación cancelada. La película no ha sido guardada.");
        }
        scanner.close();
    }   

    //public void cargarPeliculaEnPeliculasDAO(){  
        //Pelicula nuevaPelicula = cargarPelicula();
        //Factory.getPeliculasDAO().insertarPeliculas(nuevaPelicula);
    //}

    public void cargarReseniaEnReseniasDAO(){
        Resena nuevaResenia = cargarResenia();
        //Factory.getReseniasDAO().insertarResenia();// pasar todo a logica usando interfaces
    }


    /** 
     * @return UsuarioFinal
     */
    //Metodo para registrar Usuario
    private UsuarioFinal cargarUsuario(Scanner scanner){
        String nombre= null;
        String apellido= null;
        int dni= 0;
        boolean dniValido = false;
        String email= null;
        String contrasena= null;
        String idioma= null;

        do {
            System.out.print("Ingrese nombre: ");
            nombre= scanner.nextLine();
        } while (!verificarNombre(nombre));
        
        do { 
            System.out.print("Ingrese apellido: ");
            apellido= scanner.nextLine();
        } while (!verificarApellido(apellido));

        do { 
            System.out.print("Ingrese DNI (7 u 8 dígitos): ");
            String input = scanner.nextLine();
            //Validamos que no esté vacío
            if (input.trim().isEmpty()) {
                System.out.println("Error: El DNI no puede estar vacío.");
                continue; 
            }

            // 2. Validamos que sea un número
            try {
                dni = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: El DNI debe ser un valor numérico.");
                continue;
            }
            dniValido = verificarDNI(dni);
        } while (!dniValido);

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
        return nuevoUsuario;
    }

    /** 
     * @param nombreIN
     * @return boolean
     */
    private boolean verificarNombre(String nombreIN){ 
        if (nombreIN.trim().isEmpty()) { // Verifica si el nombre no esta vacio
            System.out.println ("El nombre no puede estar vacio");
            return false;
        } 
        if (!nombreIN.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")) { //Validacion de formato de nombre
            System.out.println("EL nombre solo puede tener letras y espacios");
            return false;
        }
        return true;
    }

    /** 
     * @param apellidoIN
     * @return boolean
     */
    private boolean verificarApellido(String apellidoIN){ 
        if (apellidoIN.trim().isEmpty()) { // Verifica si el apellido no esta vacio
            System.out.println("El apellido no puede estar vacio");
            return false;
        } 
        if (!apellidoIN.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")) { //Validacion de formato de apellido
            System.out.println("El apellido solo puede tener letras y espacios");
            return false;
        }
        return true;
    }

    /** 
     * @param dniIN
     * @return boolean
     */
    private boolean verificarDNI(int dniIN){ 
        String dniStr = Integer.toString(dniIN);
        //Validar formato
        if (!dniStr.matches("\\d{7,8}")) {
            System.out.println("Error: El DNI debe tener entre 7 y 8 digitos numericos.");
        return false;
        }
        //TODO lo pongo en comentario la verificacion de unicidad porque no tenemos el metodo en UsuarioFinalDAO
        //if (Factory.getUsuariosFinalDAO().existeDNI(dniIN)) { //TODO {Prioridad ALTA} se debe implementar este metodo en UsuarioFinalDAO para verificar si el DNI ya existe
           // System.out.println("Error: El DNI " + dniIN + " ya se encuentra registrado en el sistema.");
           // return false;
       // }
        return true;
    }

    /** 
     * @param emailIN
     * @return boolean
     */
    private boolean verificarEmail(String emailIN){ 
        if (emailIN.trim().isEmpty()) { // Verifica si el email no esta vacio
            System.out.println("El email no puede estar vacio");
            return false;
        } 
        if (!emailIN.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Validacion de formato de email
            System.out.println("El email no cumple con el formato aaa@bbb");
            return false;  
        }
        return true; 
    }

    /** 
     * @param contrasenaIN
     * @return boolean
     */
    private boolean verificarContrasena(String contrasenaIN){ 
        if (contrasenaIN.trim().isEmpty()) { // Verifica si la contraseña no esta vacia
            System.out.println("La contraseña no puede estar vacia");
            return false;
        }
        return true;
    }

    /** 
     * @param idiomaIN
     * @return boolean
     */
    private boolean verificarIdioma(String idiomaIN){ 
        if (idiomaIN.trim().isEmpty()) { // Verifica si el idioma no esta vacio
            System.out.println("El idioma no puede estar vacio");
            return false;
        }
        return true;
    }

    /** 
     * @return Pelicula
     */
    private Pelicula cargarPelicula(Scanner scanner){ //TODO Verificar si hace falta mas atributo como metadatos

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

    /** 
     * @param (!res
     * @return boolean
     */
    private boolean verificarCalidad(String calidadIN){ 
        if (calidadIN.trim().isEmpty()) { // Verifica si el idioma no esta vacio
            System.out.println("La calidad no puede estar vacia");
            return false;
        }
        return true;
    }

    /** 
     * @param (!res
     * @return boolean
     */
    private boolean verificarAudio(String audioIN){ 
        if (audioIN.trim().isEmpty()) { // Verifica si el idioma no esta vacio
            System.out.println("EL audio no puede estar vacio");
            return false;
        }
        return true;
    }
    
    /** 
     * @param (!res
     * @return boolean
     */
    private boolean verificarDireccionArchivo(String direccionArchivoIN){ 
        if (direccionArchivoIN.trim().isEmpty()) { // Verifica si el idioma no esta vacio
            System.out.println("La direccion de archivo no puede estar vacia");
            return false;
        }
        return true;
    }

    /** 
     * @return Resena
     */
    private Resena cargarResenia(){
        int id = login();
        Contenido contenido = null; // TODO Object temporal
        /*
        do {
            System.out.println("Ingrese Calidad: ");
            calidad = scanner.nextLine();

        } while (!verificarCalidad(calidad));
        scanner.close();
        
        */

        int puntuacion = 0; // TODO int temporal
        /*
        do {
            System.out.println("Ingrese Calidad: ");
            calidad = scanner.nextLine();

        } while (!verificarCalidad(calidad));
        scanner.close();
        
        */

        String comentario  = "PRUEBA GENERAL"; // TODO String temporal
        /*
        do {
            System.out.println("Ingrese Calidad: ");
            calidad = scanner.nextLine();

        } while (!verificarCalidad(calidad));
        scanner.close();
        
        */

        Resena nuevaResenia = new Resena(Factory.getUsuariosFinalDAO().encontrarUsuarioViaId(id), contenido, puntuacion, comentario);
        return nuevaResenia;
    }

    /** 
     * @param !res
     * @return int
     */
    /*private boolean verificarCalidad(String calidadIN){ // TODO - Definir criterio para calidad
        boolean res = true;

        if (!res){
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }*/

    private int login(){
        Scanner scanner= new Scanner(System.in);
        String actNombre;
        String actContrasenia;
        boolean login = false;

        do {
            System.out.println("Ingrese nombre de usuario: ");
            actNombre = scanner.next();
            System.out.println("Ingrese contrasenia: ");
            actContrasenia = scanner.next();
            
            login = Factory.getUsuariosFinalDAO().checkUsuarioViaLogin(actNombre, actContrasenia);
            if (login)
                System.out.println("Usuario ingresado con exito.");
            else
                System.out.println("Error, usuario o contrasenia no válido.");
            
        } while (!login);
        scanner.close();

        return Factory.getUsuariosFinalDAO().encontrarIdUsuarioViaLogin(actNombre, actContrasenia);
    }
}
