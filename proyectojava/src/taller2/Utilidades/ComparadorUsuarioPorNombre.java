package taller2.Utilidades;

import taller2.Model.ManejoDeUsuarios.UsuarioFinal;
import java.util.Comparator;

/**
 * Esta clase implementa la interfaz Comparator para enseñarle a Java
 * cómo ordenar dos objetos UsuarioFinal basándose en el nombre de su Persona.
 */
public class ComparadorUsuarioPorNombre implements Comparator<UsuarioFinal> {
    
    @Override
    public int compare(UsuarioFinal u1, UsuarioFinal u2) {
        // Obtener los nombres de las personas asociadas a los usuarios
        String nombre1 = u1.getPersona().getNombre();
        String nombre2 = u2.getPersona().getNombre();
        // compareToIgnoreCase hace la comparación alfabética (A-Z) sin importar mayúsculas
        return nombre1.compareToIgnoreCase(nombre2);
    }
}
