package taller2.plataformatdl2.Model.ManejoDeContenido;

/**
 * Clase que representa un contenido en la plataforma de streaming.
 * Contiene calidad, audio y direccion archivo.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.0 - 2025-09-15
 */

 public abstract class Contenido {
    protected String calidad; // FIXME - Cambiar a enum
    protected String audio;
    protected String direccionArchivo;
    protected Genero genero;
    protected Metadatos metadatos;

    /**
     * Constructor de la clase Contenido.
     * 
     * @param calidad          La calidad del contenido (e.g., HD, 4K).
     * @param audio            El tipo de audio del contenido (e.g., Stereo, Dolby).
     * @param direccionArchivo La dirección de archivo.
     */

    public Contenido(String calidad, String audio, String direccionArchivo, Metadatos metadatos) {
        this.calidad = calidad;
        this.audio = audio;
        this.direccionArchivo = direccionArchivo;
        this.metadatos = metadatos;
    }

    public Contenido(String direccionArchivo, Metadatos metadatos, Genero genero) {
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
     * @return Metadatos
     */
    public Metadatos getMetadatos() {
        return metadatos;
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
    public Genero getGenero() {
        return genero;
    }

    /** 
     * @param genero
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
 }