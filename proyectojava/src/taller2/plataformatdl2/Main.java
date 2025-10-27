package taller2.plataformatdl2;

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
        Factory factory = new Factory();
        MenuResenia menu = new MenuResenia();
        
        menu.usarMenuDeConsola();
        
        System.out.println("Cerrando Programa.");
    }
    
    
}
