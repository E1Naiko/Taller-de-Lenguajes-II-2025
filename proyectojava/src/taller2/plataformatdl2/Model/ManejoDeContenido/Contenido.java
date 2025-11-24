package taller2.plataformatdl2.Model.ManejoDeContenido;

/**
* Clase que representa un contenido en la plataforma de streaming.
* Contiene calidad, audio y direccion archivo.
* 
* @author Alam Meza y Nicolas Peñalba
* @version 1.0 - 2025-09-15
*/

public abstract class Contenido {
    protected Calidades calidad;
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
    
    public Contenido(Calidades calidad, String audio, String direccionArchivo, Metadatos metadatos) {
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
    
    public Calidades getCalidad() {
        return calidad;
    }
    
    /** 
    * @param calidad
    */
    public void setCalidad(Calidades calidad) {
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
    
    // HECHO CON LMARENA
    /**
    * Devuelve una representación textual del contenido, incluyendo sus atributos clave.
    * Formatea valores nulos con mensajes amigables y reutiliza el toString de las clases anidadas.
    * 
    * @return Cadena con la información relevante del contenido.
    */
    @Override
    public String toString() {
        // Manejo de valores nulos o no proporcionados
        Calidades calidadStr = (calidad != null) ? calidad : Calidades.DEF;
        String audioStr = (audio != null) ? audio : "No especificado";
        String direccionStr = (direccionArchivo != null) ? direccionArchivo : "No disponible";
        String generoStr = (genero != null) ? genero.toString() : "Desconocido";
        String metadatosStr = (metadatos != null) ? metadatos.toString() : "Sin metadatos";
        
        return "Contenido{" +
        "calidad='" + calidadStr + '\'' +
        ", audio='" + audioStr + '\'' +
        ", direcciónArchivo='" + direccionStr + '\'' +
        ", género=" + generoStr +
        ", metadatos=" + metadatosStr +
        '}';
    }
    
}