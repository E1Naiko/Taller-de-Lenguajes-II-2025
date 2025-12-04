package taller2.plataformatdl2.Utilities;

import java.util.Comparator;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class ComparadorTopRating implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        int ret;
        float rating1 = p1.getMetadatos().getRating_promedio();
        float rating2 = p2.getMetadatos().getRating_promedio();

        if (rating1 > rating2)
            ret = -1;
        else
            if (rating1 < rating2)
                ret = 1;
            else
                ret = 0;

        return ret; // Ordena de mayor promedio a menor
    }
    
}

