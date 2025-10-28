package taller2.plataformatdl2.Utilidades;

import java.util.Comparator;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class ComparadorPeliculaPorGenero implements Comparator<Pelicula> {
    /** 
     * @param p1
     * @param p2
     * @return int
     */
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        // El género está en la clase padre 'Contenido'
        return genero1.compareToIgnoreCase(genero2);
    }
}