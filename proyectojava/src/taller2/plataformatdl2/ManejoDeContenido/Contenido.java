package taller2.plataformatdl2.ManejoDeContenido;

/**
 * Clase que representa un contenido en la plataforma de streaming.
 * Contiene calidad, audio y direccion archivo.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.0 - 2025-09-15
 */

 public abstract class Contenido {
    protected String calidad; // TODO cambiar a enum
    protected String audio;
    protected String direccionArchivo;
    protected String genero; // TODO cambiar a enum

    /**
     * Constructor de la clase Contenido.
     * 
     * @param calidad          La calidad del contenido (e.g., HD, 4K).
     * @param audio            El tipo de audio del contenido (e.g., Stereo, Dolby).
     * @param direccionArchivo La dirección de archivo.
     */

    public Contenido(String calidad, String audio, String direccionArchivo) {
        this.calidad = calidad;
        this.audio = audio;
        this.direccionArchivo = direccionArchivo;
    }

    public Contenido(String direccionArchivo, Metadatos metadatos, String genero) {
        this.direccionArchivo = direccionArchivo;
        this.genero = genero;
    }

    /** 
     * @return String
     */
    // --- Getters y Setters ---

    public String getCalidad() {
        return calidad;
    }

    /** 
     * @param calidad
     */
    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    /** 
     * @return String
     */
    public String getAudio() {
        return audio;
    }

    /** 
     * @param audio
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }

    /** 
     * @return String
     */
    public String getDireccionArchivo() {
        return direccionArchivo;
    }

    /** 
     * @param direccionArchivo
     */
    public void setDireccionArchivo(String direccionArchivo) {
        this.direccionArchivo = direccionArchivo;
    }

    /** 
     * @return String
     */
    public String getGenero() {
        return genero;
    }

    /** 
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
 }