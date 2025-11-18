package taller2.plataformatdl2.Utilities;

import java.sql.Time;
import java.util.Comparator;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class ComparadorPeliculaPorDuracion implements Comparator<Pelicula> {
    /** 
     * @param p1
     * @param p2
     * @return int
     * @author Alam Meza
     */
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        // La duración es un 'Time', que ya sabe cómo compararse
        Time duracion1 = p1.getMetadatos().getDuracion();
        Time duracion2 = p2.getMetadatos().getDuracion();
        return duracion1.compareTo(duracion2); // Ordena de más corto a más largo
    }
}
