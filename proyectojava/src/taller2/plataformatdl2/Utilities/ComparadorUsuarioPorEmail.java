package taller2.plataformatdl2.Utilities;

import java.util.Comparator;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
/**
 * Esta clase implementa la interfaz Comparator para enseñarle a Java
 * cómo ordenar dos objetos UsuarioFinal basándose en su email.
 */
public class ComparadorUsuarioPorEmail implements Comparator<UsuarioFinal> {

    /** 
     * @param u1
     * @param u2
     * @return int
     */
    @Override
    public int compare(UsuarioFinal u1, UsuarioFinal u2) {
        // Obtener los emails de las personas asociadas a los usuarios
        String email1 = u1.getEmail();
        String email2 = u2.getEmail();
        // compareToIgnoreCase hace la comparación alfabética (A-Z) sin importar mayúsculas
        return email1.compareToIgnoreCase(email2);
    }
    
}
