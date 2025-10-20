package taller2.plataformatdl2;

import java.util.Scanner;

import taller2.DB.DAO.FactoryDAO;

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
        
        FactoryDAO cntrlDAO = new FactoryDAO();
        MenuResenia menu = new MenuResenia();

        while (true) {

            entrada = in.nextInt();
            if (entrada == 0) {
                break;
            } else {
                switch (entrada) {
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        }

        in.close();
    }
    private static void imprimirOpciones(){
        System.out.println("");
    }
}
