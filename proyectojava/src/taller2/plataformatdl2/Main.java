package taller2.plataformatdl2;

import taller2.DB.DAO.Factory;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import taller2.plataformatdl2.view.LoginVista; 
import taller2.plataformatdl2.controller.LoginController; 

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
        boolean skipCSVUpdate = false;
        try {
            new Factory(); 
            System.out.println("Base de datos conectada joya.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Che, reventó la conexión a la base de datos:\n" + e.getMessage(), "Error Fatal", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return; 
        }
        
        if (args != null) {
            for (String arg : args) {
                
                if ("-AdminMode".equals(arg)) {
                    MenuResenia menu = new MenuResenia();
                    menu.usarMenuDeConsola();
                }
                
                if ("-SkipCSVUpdate".equals(arg)) {
                    skipCSVUpdate = true;
                }
            }
        }
        
        if (!skipCSVUpdate) Factory.importarListaACSV();
        
        SwingUtilities.invokeLater(() -> {
            try {
                // Instanciamos la Vista
                LoginVista vista = new LoginVista();
                
                // Instanciamos el Controlador 
                new LoginController(vista);
                
                // Hacemos visible la ventana
                vista.setVisible(true);
                System.out.println("Ventana de Login visible.");
                
            } catch (Exception e) {
                System.err.println("Explotó Swing, la concha de la lora:");
                e.printStackTrace();
            }
        });
        System.out.println("Cerrando Programa.");
    }
}
