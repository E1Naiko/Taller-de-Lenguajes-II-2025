package taller2.plataformatdl2;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
import taller2.plataformatdl2.Utilidades.*;

public class MenuResenia {
    private static Scanner scanner = new Scanner(System.in);
    public MenuResenia(){
    }
    
    public void usarMenuDeConsola(){
        int entrada;
        
        boolean bucle = true;
        while (bucle) {
            imprimirOpciones();
            entrada = scanner.nextInt();
            switch (entrada) {
                case 0:
                bucle = false;
                break;
                
                case 1: // Imprimir Opciones (1)
                imprimirOpciones();
                break;
                
                case 2: // Ingresar Usuario a BD (2)
                cargarUsuarioEnUsuariosFinalDAO();
                break;
                
                case 3: // Ingresar Pelicula a BD (3)
                cargarPeliculaEnPeliculasDAO();
                break;
                
                case 4: // Listar Usuarios (4)
                listarUsuariosOrdenados();
                break;
                
                case 5: // Listar Peliculas (5)
                listarPeliculasOrdenadas();
                break;
                
                case 6: // Ingresar Resenia (6)
                cargarReseniaEnReseniasDAO();
                break;
                
                case 7: // Aprobar Reseña (7)
                aprobarReseña();
                break;
                
                default:
                System.out.println("Error opcion no valida.");
                break;
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
        scanner.close();
    }
    
    private void cargarUsuarioEnUsuariosFinalDAO() {
        // Llamamos al método que carga los datos
        System.out.println("--- Inicio de Registro de Usuario ---");
        UsuarioFinal nuevoUsuario = cargarUsuario();
        
        //Mostrar al usuario todos los datos que acaba de ingresar.
        System.out.println("\n=============================================");
        System.out.println("    Por favor, revise los datos ingresados:");
        System.out.println(nuevoUsuario.toString()); 
        System.out.println("=============================================");
        
        //Preguntar si los datos son correctos.
        String confirmacion = "";
        do {
            System.out.print("¿Los datos son correctos? (s/n): ");
            confirmacion = scanner.next();
        } while (!confirmacion.equalsIgnoreCase("s") && !confirmacion.equalsIgnoreCase("n"));
        
        //Si confirma, se guardan en la Base de Datos.
        if (confirmacion.equalsIgnoreCase("s")) {
            Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUsuario);
            System.out.println("¡Usuario guardado exitosamente!");
        } else {
            System.out.println("Operación cancelada. El usuario no ha sido guardado.");
        }
    }
    
    
    
    private void cargarPeliculaEnPeliculasDAO(){
        System.out.println("--- Inicio de Carga de Nueva Película ---");
        //Llamamos al método que carga los datos
        Pelicula nuevaPelicula = cargarPelicula();
        
        //Mostrar al usuario todos los datos que acaba de ingresar.
        System.out.println("\n=============================================");
        System.out.println("    Por favor, revise los datos ingresados:");
        System.out.println(nuevaPelicula.toString()); 
        System.out.println("=============================================");
        
        //Preguntar si los datos son correctos.
        String confirmacion = "";
        do {
            System.out.print("¿Los datos son correctos? (s/n): ");
            confirmacion = scanner.next();
        } while (!confirmacion.equalsIgnoreCase("s") && !confirmacion.equalsIgnoreCase("n"));
        
        //Si confirma, se guardan en la Base de Datos.
        if (confirmacion.equalsIgnoreCase("s")) {
            Factory.getPeliculasDAO().insertarPeliculas(nuevaPelicula);
            System.out.println("¡Película guardada exitosamente!");
        } else {
            System.out.println("Operación cancelada. La película no ha sido guardada.");
        }
    }   
    
    //private void cargarPeliculaEnPeliculasDAO(){  
    //Pelicula nuevaPelicula = cargarPelicula();
    //Factory.getPeliculasDAO().insertarPeliculas(nuevaPelicula);
    //}
    
    private void cargarReseniaEnReseniasDAO(){
        Resena nuevaResenia = cargarResenia();
        //Factory.getReseniasDAO().insertarResenia();// pasar todo a logica usando interfaces
    }
    
    
    
    
    
    
    
    
    // ----------------------------------- MANEJO DE USUARIO -----------------------------------
    
    /** 
    * @return UsuarioFinal
    */
    //Metodo para registrar Usuario
    private UsuarioFinal cargarUsuario(){
        String nombre = null;
        String apellido = null;
        int dni = 0;
        boolean dniValido = false;
        String email = null;
        String contrasena = null;
        String idioma = null;
        List<Genero> generosPreferidos = new ArrayList<>();
        
        do {
            System.out.print("Ingrese nombre: ");
            nombre = scanner.next();
        } while (!verificarNombre(nombre));
        
        do { 
            System.out.print("Ingrese apellido: ");
            apellido = scanner.next();
        } while (!verificarApellido(apellido));
        
        do { 
            System.out.print("Ingrese DNI: ");
            String input = scanner.next();
            // 1. Validamos que no esté vacío
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
            email = scanner.next();
        } while (!verificarEmail(email));
        
        do {
            System.out.println("Ingresar Contraseña: ");
            contrasena = scanner.next();
        } while (!verificarContrasena(contrasena));
        
        do {
            System.out.println("Ingresar Idioma: ");
            idioma = scanner.next();
        } while (!verificarIdioma(idioma));
        
        String respuesta = "";
        System.out.println("\n--- Ingresar Géneros Preferidos ---");
        //Pregunta si quiere agregar (al menos) un género
        do {
            Genero generoSeleccionado = null;
            //Pide UN género y lo valida 
            do {
                System.out.println("Ingrese un género (o escriba 'fin' para terminar): ");
                String generoInput = scanner.next();
                if (generoInput.equalsIgnoreCase("fin")) {
                    respuesta = "n"; // Forzamos la salida del bucle externo
                    break; // Rompemos el bucle interno
                } 
                generoSeleccionado = verificarGenero(generoInput); 
                //Verificación extra: que no lo agregue dos veces
                if (generoSeleccionado != null && generosPreferidos.contains(generoSeleccionado)) {
                    System.out.println("Error: Ese género ya fue agregado a la lista.");
                    generoSeleccionado = null; // Para que el bucle interno siga pidiendo
                }
            } while (generoSeleccionado == null && !respuesta.equalsIgnoreCase("n"));
            //Si el género es válido Y no escribió 'fin', se agrega
            if (generoSeleccionado != null) {
                generosPreferidos.add(generoSeleccionado);
                System.out.println("'" + generoSeleccionado.name() + "' agregado.");
                System.out.print("¿Desea agregar otro género? (s/n): ");
                respuesta = scanner.next();
            }
        } while (respuesta.equalsIgnoreCase("s"));
        //Al final, mostramos la lista completa
        if(generosPreferidos.isEmpty()){
            System.out.println("No se seleccionó ningún género preferido.");
        } else {
            System.out.println("Géneros preferidos seleccionados: " + generosPreferidos);
        }
        
        
        System.out.println("Ingresar Lista Preferida: ");
        String listaPreferida = "ListaVacia"; //FIXME - Tambien como en Historial
        System.out.println("Ingresar Historial: ");
        String historial = "ListaVacia"; // TODO - Por el momento lo tratamos como string luego cambiamos a lista
        
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,apellido,dni,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
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
    * 
    * @return Genero
    */
    private Genero verificarGenero(String generoIN) {
        if (generoIN.trim().isEmpty()) {
            System.out.println("Error: No se puede ingresar un género vacío.");
            return null;
        }
        
        try {
            Genero genero = Genero.valueOf(generoIN.toUpperCase());
            return genero;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: El género '" + generoIN + "' no es válido.");
            System.out.print("Géneros disponibles: ");
            for (Genero g : Genero.values()) {
                System.out.print(g.name() + " ");
            }
            System.out.println();
            return null;
        }
    }
    
    
    
    
    
    
    
    // ----------------------------------- MANEJO DE PELICULA -----------------------------------
    /**
    * @return Metadatos
    */
    private Metadatos cargarMetadatos() {
        String titulo;
        String resumen;
        String[] elenco;
        String director;
        Time duracion = null;
        String idioma;
        String[] subtitulos;
        
        do {
            System.out.print("Ingrese Título: ");
            titulo = scanner.next();
        } while (!verificarCampoRequerido(titulo, "Título"));

        System.out.print("Ingrese Resumen (Opcional, presione Enter para omitir): ");
        resumen = scanner.next();
        if (resumen.isEmpty()) {
            resumen = "NULL"; // O null, si tu BD lo prefiere
        }

        do {
            System.out.print("Ingrese Director: ");
            director = scanner.next();
        } while (!verificarCampoRequerido(director, "Director"));

        do {
            System.out.print("Ingrese Idioma: ");
            idioma = scanner.next();
        } while (!verificarCampoRequerido(idioma, "Idioma"));
        
        do {
            System.out.print("Ingrese Duración (formato HH:mm:ss, ej: 01:30:00): ");
            String duracionStr = scanner.next();
            duracion = verificarDuracion(duracionStr);
        } while (duracion == null); 
        
        // Usamos una lista temporal y luego la convertimos a array
        List<String> elencoList = new ArrayList<>();
        String respuestaElenco = "";
        System.out.println("--- Ingresar Elenco (miembros del reparto) ---");
        do {
            System.out.print("Ingrese nombre de un actor/actriz (o 'fin' para no agregar): ");
            String actor = scanner.next();
            if (actor.equalsIgnoreCase("fin") || actor.trim().isEmpty()) {
                if(elencoList.isEmpty()) System.out.println("Elenco omitido.");
                break;
            } 
            elencoList.add(actor.trim());
            System.out.print("¿Desea agregar otro miembro? (s/n): ");
            respuestaElenco = scanner.next();
        } while (respuestaElenco.equalsIgnoreCase("s"));
        
        // Convertimos la lista a un array de String
        elenco = elencoList.toArray(new String[0]);
        List<String> subtitulosList = new ArrayList<>();
        String respuestaSub = "";
        do {
            System.out.print("Ingrese un idioma de subtítulo (o 'fin' para no agregar): ");
            String sub = scanner.next();  
            if (sub.equalsIgnoreCase("fin") || sub.trim().isEmpty()) {
                if(subtitulosList.isEmpty()) System.out.println("Subtítulos omitidos.");
                break;
            }
            subtitulosList.add(sub.trim());
            System.out.print("¿Desea agregar otro idioma? (s/n): ");
            respuestaSub = scanner.next();
        } while (respuestaSub.equalsIgnoreCase("s"));
        // Convertimos la lista a un array de String
        subtitulos = subtitulosList.toArray(new String[0]);
        System.out.println("--- Metadatos cargados exitosamente ---");
        return new Metadatos(titulo, resumen, elenco, director, duracion, idioma, subtitulos);
    }
    
    private boolean verificarCampoRequerido(String input, String nombreCampo) {
        if (input.trim().isEmpty()) {
            System.out.println("Error: El campo '" + nombreCampo + "' no puede estar vacío.");
            return false;
        }
        return true;
    }
    
    private Time verificarDuracion(String input) {
        if (input.trim().isEmpty()) {
            System.out.println("Error: La duración no puede estar vacía.");
            return null;
        }
        try {
            Time duracion = Time.valueOf(input); // Formato "hh:mm:ss"
            return duracion;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Formato de duración incorrecto. Debe ser HH:mm:ss");
            return null;
        }
    }
    /** 
    * @return Pelicula
    */
    private Pelicula cargarPelicula(){ 
        
        String calidad = null;
        String audio = null;
        String direccionArchivo = null;
        Genero genero = null; 
        
        do {
            System.out.println("Ingrese Calidad: ");
            calidad = scanner.next();
        } while (!verificarCalidad(calidad));
        
        do {    
            System.out.println("Ingrese Audio: ");
            audio = scanner.next();
        } while (!verificarAudio(audio));
        
        // FIXME - Replantear el uso de direccionArchivo
        // TODO - "Todos los datos son requeridos a excepcion del Resumen" que carajos?
        
        do {
            System.out.println("Ingrese Direccion del Archivo: ");
            direccionArchivo = scanner.next();
        } while (!verificarDireccionArchivo(direccionArchivo));
        
        do {
            System.out.println("Ingrese Género: ");
            String generoInput = scanner.next();
            genero = verificarGenero(generoInput); 
        } while (genero == null); //null si el género no es válido
        
        Metadatos metadatosPelicula= cargarMetadatos();
        Pelicula nuevaPelicula = new Pelicula(calidad, audio, direccionArchivo, genero, metadatosPelicula); //TODO Modifique el constructor de Pelicula verificar en el DAO
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
    
    
    
    
    
    // ----------------------------------- MANEJO DE LISTAS USUARIOS Y PELICULA -----------------------------------
    
    /**
    * Muestra todos los usuarios y permite ordenarlos por Nombre o Email.
    */
    private void listarUsuariosOrdenados() {
        System.out.println("--- Listado de Usuarios Registrados ---");
        
        //Obtener la lista COMPLETA de usuarios desde el DAO
        List<UsuarioFinal> usuarios = Factory.getUsuariosFinalDAO().obtenerUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        //Preguntar al usuario el criterio de ordenación
        System.out.println("¿Cómo desea ordenar la lista?");
        System.out.println("  1. Por Nombre de Persona (A-Z)");
        System.out.println("  2. Por Email (A-Z)");
        System.out.println("  Cualquier otra tecla para cancelar.");
        System.out.print("Seleccione una opción: ");
        
        String opcion = scanner.next();
        switch (opcion) {
            case "1":
            // Aquí usamos la interfaz: le pasamos un *objeto* que sabe cómo comparar por nombre
            Collections.sort(usuarios, new ComparadorUsuarioPorNombre());
            System.out.println("\n--- Usuarios ordenados por Nombre ---");
            break;
            case "2":
            // Y aquí le pasamos un *objeto* que sabe cómo comparar por email
            Collections.sort(usuarios, new ComparadorUsuarioPorEmail());
            System.out.println("\n--- Usuarios ordenados por Email ---");
            break;
            default:
            System.out.println("\n--- Usuarios sin ordenar ---");
            // No se ordena, se muestra en el orden de la BD
            break;
        }
        //Mostrar la lista
        for (UsuarioFinal usuario : usuarios) {
            System.out.println("---------------------------------");
            System.out.println(usuario.toString());
        }
    }
    
    /** 
    * Muestra todas las películas y permite ordenarlas por Título, Género o Duración.
    *
    */
    private void listarPeliculasOrdenadas() {
        System.out.println("--- Listado de Películas Registradas ---");
        
        //Obtener la lista COMPLETA de películas desde el DAO
        List<Pelicula> peliculas = Factory.getPeliculasDAO().obtenerPeliculas();
        
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas en el sistema.");
            return;
        }
        
        //Preguntar el criterio de ordenación
        System.out.println("¿Cómo desea ordenar la lista?");
        System.out.println("  1. Por Título (A-Z)");
        System.out.println("  2. Por Género (A-Z)");
        System.out.println("  3. Por Duración (Más corta a más larga)");
        System.out.println("  Cualquier otra tecla para mostrar sin ordenar.");
        System.out.print("Seleccione una opción: ");
        
        String opcion = scanner.next();
        
        //Aplicar el Comparador elegido
        switch (opcion) {
            case "1":
            Collections.sort(peliculas, new ComparadorPeliculaPorTitulo());
            System.out.println("\n--- Películas ordenadas por Título ---");
            break;
            case "2":
            Collections.sort(peliculas, new ComparadorPeliculaPorGenero());
            System.out.println("\n--- Películas ordenadas por Género ---");
            break;
            case "3":
            Collections.sort(peliculas, new ComparadorPeliculaPorDuracion());
            System.out.println("\n--- Películas ordenadas por Duración ---");
            break;
            default:
            System.out.println("\n--- Películas (orden por defecto de BD) ---");
            break;
        }
        
        //Mostrar la lista
        for (Pelicula peli : peliculas) {
            System.out.println("---------------------------------");
            System.out.println(peli.toString()); 
        }
        System.out.println("---------------------------------");
    }
    
    
    // ----------------------------------- MANEJO DE RESENIA -----------------------------------
    
    /** 
    * @return Resena
    */
    private Resena cargarResenia(){
        int id = login();
        
        System.out.println("--- Lista de Películas Disponibles ---");
        //Traemos la lista de películas del DAO
        List<Pelicula> peliculas = Factory.getPeliculasDAO().obtenerPeliculas();
        if (peliculas.isEmpty()) {
            System.out.println("Error: No hay películas cargadas para reseñar.");
            return null;
        }
        //Mostramos la lista numerada
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + peliculas.get(i).getMetadatos().getTitulo());
        }
        System.out.println("  0. Cancelar");
        //Pedimos que elija una
        int seleccion = -1;
        Contenido contenido = null; // Tu variable 'contenido' ahora se llena acá
        do {
            System.out.print("Escribir el número de la película que desea reseñar: ");
            try {
                seleccion = scanner.nextInt(); 
                if (seleccion > 0 && seleccion <= peliculas.size()) {
                    // ¡Selección válida!
                    contenido = peliculas.get(seleccion - 1); // -1 por el índice del array
                } else if (seleccion == 0) {
                    System.out.println("Operación cancelada.");
                    return null;
                } else {
                    System.out.println("Error: Número fuera de rango.");
                    seleccion = -1; 
                }
            } catch (Exception e) {
                System.out.println("Error: Debe ser un número.");
                scanner.next(); //Limpiamos el buffer si puso letras
                seleccion = -1;
            }
        } while (seleccion == -1);
        
        //Contenido contenido = null; // TODO - No hay tabla de contenidos implementada, solo hace falta asociar la di
        
        int puntuacion = 0;
        do {
            System.out.println("Ingrese Calidad: ");
            puntuacion = scanner.nextInt();
            
        } while (!verificarPuntuacion(puntuacion));
        
        String comentario  = "PRUEBA GENERAL";
        do {
            System.out.println("Ingrese su comentario: ");
            comentario = scanner.next();
            
        } while (!verificarComentario(comentario));
        
        Resena nuevaResenia = new Resena(Factory.getUsuariosFinalDAO().devolverUsuarioFinalViaId(id), contenido, puntuacion, comentario);
        System.out.println("\n--- Por favor, confirme los datos de la reseña ---");
        System.out.println(nuevaResenia.toString());
        System.out.println("==============================================");
        String confirmacion = "";
        do {
            System.out.print("¿Desea guardar esta reseña? (s/n): ");
            confirmacion = scanner.next();
        } while (!confirmacion.equalsIgnoreCase("s") && !confirmacion.equalsIgnoreCase("n")); 

        if (confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Reseña guardada");
            return nuevaResenia; 
        } else {
            System.out.println("Operación cancelada por el usuario.");
            return null; 
        }
    }
    
    /** 
    * @param (!res
    * @return boolean
    */
    private boolean verificarIdContenido(int idContenidoIN){ // TODO - Definir criterio para dirContenido
        boolean res = true;
        
        if (!res){
            System.out.println("ERROR - CALIDAD NO VALIDA");
        }
        return res;
    }
    
    /** 
    * @param puntuacionIN
    * @return boolean
    */
    private boolean verificarPuntuacion(int puntuacionIN){
        boolean res = (puntuacionIN >= 0 && puntuacionIN <= 10);
        
        if (!res){
            System.out.println("Error: La puntuación debe ser un número entre 0 y 10.");
        }
        return res;
    }
    
    /** 
    * @param !res
    * @return int
    */
    private boolean verificarComentario(String comentarioIN){
        //No puede estar vacío
        if (comentarioIN.trim().isEmpty()) {
            System.out.println("Error: El comentario no puede estar vacío.");
            return false;
        }
        
        //Límite de caracteres (ejemplo)
        if (comentarioIN.length() > 500) {
            System.out.println("Error: El comentario es muy largo (máx 500 caracteres).");
            return false;
        }
        
        return true; // Si pasa todo, es válido
    }
    
    
    
    // ----------------------------------- MANEJO DE APROBAR RESEÑA -----------------------------------
    
    private void aprobarReseña(){
        List<Resena> noAprobadas = Factory.getReseniasDAO().devolverReseniasNoAprobadas();
        this.imprimirNoAprobadas(noAprobadas);
    }
    
    private void imprimirNoAprobadas(List<Resena> lista){
        int i=0;
        for (Resena resena : lista) {
            System.out.println(" -" + i + " - ");
            imprimirResenia(resena);
            i++;
        }
        System.out.println("Que reseña desea aprobar?");
        int select = scanner.nextInt();
        
        if (select >= 0 && select < lista.size()){
            Resena resena = lista.get(select);
            int idResena = Factory.getReseniasDAO().encontrarIdResenia(
            Factory.getUsuariosFinalDAO().devolverIdUsuarioFinal((UsuarioFinal) resena.getUsuario()),
            Factory.getPeliculasDAO().encontrarIdPelicula(resena.getContenidovisual()),
            resena, 0);
            if (Factory.getReseniasDAO().reseniaExiste(idResena)){
                System.out.println("Reseña seleccionada:");
                imprimirResenia(resena);
                
                System.out.println("Aprobar reseña? (SI/NO)");
                String completar = scanner.next();
                while (completar.toUpperCase().equals("SI") || completar.toUpperCase().equals("NO") ){
                    System.out.println("Error opcion no valida");
                    completar = scanner.next();
                }
                
                if (completar.equals("SI")){
                    Factory.getReseniasDAO().aprobarReseniaViaId(idResena);
                }
            }
            else
            System.out.println("Error - La Resenia no existe");
        }
        else
        System.out.println("Error - La Resenia no existe");
    }
    
    private void imprimirResenia(Resena resenia){
        System.out.print(resenia.getUsuario().getNombre() + " " +
        resenia.getUsuario().getApellido() + " - " +
        resenia.getContenidovisual().getMetadatos().getTitulo() +
        ", Puntuación: " + resenia.getPuntuacion() + 
        ", Comentario: " + resenia.getComentario());
    }
    
    
    
    
    
    // ----------------------------------- MANEJO DE OPCIONES EN MENU PRINCIPAL -----------------------------------
    
    /** 
    * @return int
    */
    private int login(){
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
        
        return Factory.getUsuariosFinalDAO().encontrarIdUsuarioViaLogin(actNombre, actContrasenia);
    }
    
    
    
    private static void imprimirOpciones(){
        System.out.println("Opciones Disponibles:" +
        "Salir (0), " +
        "Imprimir Opciones (1), " +
        "Ingresar Usuario a BD (2), " +
        "Ingresar Pelicula a BD (3), " +
        "Listar Usuarios (4), " +
        "Listar Peliculas (5), " +
        "Ingresar Resenia (6), " +
        "Aprobar Reseña (7)" +
        ".");
        System.out.println("Que operacion desea ejecutar?");
    }

}
