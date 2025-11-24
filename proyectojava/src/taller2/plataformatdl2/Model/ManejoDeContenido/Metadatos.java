package taller2.plataformatdl2.Model.ManejoDeContenido;

import java.time.LocalTime;

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
    public LocalTime duracion;
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
    
    public Metadatos(String titulo, String sinopsis, String[] elenco, String director, LocalTime duracion, String idioma, String[] subtitulos) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.elenco = elenco;
        this.director = director;
        this.duracion = duracion;
        this.idioma = idioma;
        this.subtitulos = subtitulos;
    }
    
    /**
    * Getters y Setters para los atributos de la clase Metadatos.
    * @return Atributos de la clase Metadatos.
    * 
    */
    
    public String getTitulo() {
        return titulo;
    }
    /** 
    * @param titulo
    */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /** 
    * @return String
    */
    public String getSinopsis() {
        return sinopsis;
    }
    /** 
    * @param sinopsis
    */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    /** 
    * @return String[]
    */
    public String[] getElenco() {
        return elenco;
    }
    /** 
    * @param elenco
    */
    public void setElenco(String[] elenco) {
        this.elenco = elenco;
    }
    /** 
    * @return String
    */
    public String getDirector() {
        return director;
    }
    /** 
    * @param director
    */
    public void setDirector(String director) {
        this.director = director;
    }
    /** 
    * @return LocalTime
    */
    public LocalTime getDuracion() {
        return duracion;
    }
    /** 
    * @param duracion
    */
    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }
    /** 
    * @return String
    */
    public String getIdioma() {
        return idioma;
    }
    /** 
    * @param idioma
    */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    /** 
    * @return String[]
    */
    public String[] getSubtitulos() {
        return subtitulos;
    }
    /** 
    * @param subtitulos
    */
    public void setSubtitulos(String[] subtitulos) {
        this.subtitulos = subtitulos;
    }
    
    /** 
    * @param actor
    * @return boolean
    */
    // Metodo para eliminar elenco
    public boolean removeElenco(String actor) {
        // Buscar el actor en el arreglo de elenco
        return true;
    }
    
    // HECHO CON LMARENA
    /**
    * Devuelve una representación textual amigable de los metadatos del contenido.
    * Útil para mostrar información en interfaces, logs o depuración.
    * Formatea arrays (elenco y subtítulos) como listas legibles y convierte la duración a formato HH:MM:SS.
    * 
    * @return Cadena con los metadatos clave del contenido.
    */
    @Override
    public String toString() {
        // Formatear elenco: ["Actor 1", "Actor 2", ...] → "Actor 1, Actor 2, ..."
        String elencoStr = elenco != null && elenco.length > 0
        ? String.join(", ", elenco)
        : "Sin elenco";
        
        // Formatear subtítulos: ["Español", "Inglés"] → "Español, Inglés"
        String subtitulosStr = subtitulos != null && subtitulos.length > 0
        ? String.join(", ", subtitulos)
        : "Sin subtítulos";
        
        // Formatear duración: LocalTime → "HH:MM:SS"
        String duracionStr = duracion != null
        ? duracion.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"))
        : "Duración desconocida";
        
        return "Metadatos{" +
        "título='" + titulo + '\'' +
        ", sinopsis='" + (sinopsis != null ? sinopsis : "Sin sinopsis") + '\'' +
        ", elenco=[" + elencoStr + "]" +
        ", director='" + (director != null ? director : "Desconocido") + '\'' +
        ", duración=" + duracionStr +
        ", idioma='" + (idioma != null ? idioma : "No especificado") + '\'' +
        ", subtítulos=[" + subtitulosStr + "]" +
        '}';
    }
}
