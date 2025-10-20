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
        Scanner in = new Scanner(System.in);
        int entrada;
        
        Factory factory = new Factory();
        MenuResenia menu = new MenuResenia();

        imprimirOpciones();
        boolean bucle = true;
        while (bucle) {
            entrada = in.nextInt();
            switch (entrada) {
                    case 0:
                        bucle = false;
                    break;
                    case 1: // Imprimir Opciones (1)
                        imprimirOpciones();
                    
                    case 2: // Ingresar Usuario a BD (2)
                        menu.cargarUsuarioEnUsuariosFinalDAO();
                        break;
                    
                    case 3: // Ingresar Pelicula a BD (3)
                        menu.cargarPeliculaEnPeliculasDAO();
                        break;

                    default:
                        System.out.println("Error opcion no valida.");
                        break;
                }
        }

        System.out.println("Cerrando Programa.");
        in.close();
    }

    private static void imprimirOpciones(){
        System.out.println("Opciones Disponibles:" +
        "Salir (0), " +
        "Imprimir Opciones (1), " +
        "Ingresar Usuario a BD (2), " +
        "Ingresar Pelicula a BD (3), " +
        ".");
        System.out.println("Que operacion desea ejecutar?");
    }
}
