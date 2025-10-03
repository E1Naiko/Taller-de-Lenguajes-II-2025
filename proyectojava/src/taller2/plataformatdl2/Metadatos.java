package taller2.plataformatdl2;
import java.sql.Time;

/**
 * Clase que representa los metadatos asociados a los contenidos en la plataforma de streaming.
 * Contiene atributos y métodos específicos para gestionar los metadatos.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.0 - 2025-09-15
 * @see Contenido
 */

public class Metadatos {
    public String titulo;
    public String sinopsis;
    public String[] elenco;
    public String director;
    public Time duracion;
    public String idioma;
    public String[] subtitulos;

    /**
     * Constructor de la clase Metadatos.
     * 
     * @param titulo    El título del contenido.
     * @param sinopsis  La sinopsis del contenido.
     * @param elenco    El elenco del contenido.
     * @param director  El director del contenido.
     * @param duracion  La duración del contenido.
     * @param idioma    El idioma del contenido.
     * @param subtitulos Los subtítulos disponibles para el contenido.
     */ 

    public Metadatos(String titulo, String sinopsis, String[] elenco, String director, Time duracion, String idioma, String[] subtitulos) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.elenco = elenco;
        this.director = director;
        this.duracion = duracion;
        this.idioma = idioma;
        this.subtitulos = subtitulos;




    
    
}
