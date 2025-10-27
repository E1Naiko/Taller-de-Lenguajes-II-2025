package taller2.plataformatdl2;

import java.util.Scanner;

import taller2.DB.DAO.Factory;

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
        int entrada;
        
        Factory factory = new Factory();
        MenuResenia menu = new MenuResenia();
        
        imprimirOpciones();
        boolean bucle = true;
        while (bucle) {
            entrada = lecturaDeOpcion();
            switch (entrada) {
                case 0:
                    bucle = false;
                break;
                
                case 1: // Imprimir Opciones (1)
                    imprimirOpciones();
                break;
                
                case 2: // Ingresar Usuario a BD (2)
                    menu.cargarUsuarioEnUsuariosFinalDAO();
                break;
                
                case 3: // Ingresar Pelicula a BD (3)
                    menu.cargarPeliculaEnPeliculasDAO();
                break;

                case 4: // Ingresar Reseña a BD (4)
                    menu.cargarReseniaEnReseniasDAO();
                break;

                case 5: //Listar Usuarios
                    menu.listarUsuariosOrdenados();
                break;
                
                default:
                System.out.println("Error opcion no valida.");
                break;
            }
        }
        
        System.out.println("Cerrando Programa.");
    }
    
    private static void imprimirOpciones(){
        System.out.println("Opciones Disponibles:" +
        "Salir (0), " +
        "Imprimir Opciones (1), " +
        "Ingresar Usuario a BD (2), " +
        "Ingresar Pelicula a BD (3), " +
        "Listar Usuarios (4), " +
        ".");
        System.out.println("Que operacion desea ejecutar?");
    }

    /** 
     * @return int
     */
    private static int lecturaDeOpcion(){
        Scanner in = new Scanner(System.in);
        int entrada = in.nextInt();
        in.close();
        return entrada;
    }
}
