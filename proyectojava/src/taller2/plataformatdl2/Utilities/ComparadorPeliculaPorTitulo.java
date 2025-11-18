package taller2.plataformatdl2.Utilities;

import java.util.Comparator;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class ComparadorPeliculaPorTitulo implements Comparator<Pelicula> {
    /** 
     * @param p1
     * @param p2
     * @return int
     */
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        // Accedemos al título a través de los metadatos
        String titulo1 = p1.getMetadatos().getTitulo();
        String titulo2 = p2.getMetadatos().getTitulo();
        return titulo1.compareToIgnoreCase(titulo2);
    }
}