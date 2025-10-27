package taller2.plataformatdl2.Utilidades;

import java.util.Comparator;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class ComparadorPeliculaPorGenero implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        // El género está en la clase padre 'Contenido'
        String genero1 = p1.getGenero();
        String genero2 = p2.getGenero();
        return genero1.compareToIgnoreCase(genero2);
    }
}