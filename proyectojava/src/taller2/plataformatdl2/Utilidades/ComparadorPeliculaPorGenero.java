package taller2.plataformatdl2.Utilidades;

import java.util.Comparator;

import taller2.plataformatdl2.Model.ManejoDeContenido.Genero;
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
        // Chequeamos si los generos son nulls o no
        Genero g1 = p1 != null ? p1.getGenero() : null;
        Genero g2 = p2 != null ? p2.getGenero() : null;

        if (g1 == null && g2 == null) return 0;
        if (g1 == null) return 1; // los nulos van al final
        if (g2 == null) return -1;
        return g1.name().compareToIgnoreCase(g2.name());
    }
}