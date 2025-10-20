package taller2.plataformatdl2.ManejoDeContenido;
import taller2.plataformatdl2.ManejoDeUsuarios.*;

/**
 * Clase que representa una reseña realizada por un usuario sobre un contenido visual en la plataforma de streaming.
 * Contiene atributos y métodos específicos para gestionar las reseñas.
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.0 - 2025-10-04
 * @see Usuario
 * @see Contenido
 */

public class Resena {
    public Usuario usuario;
    public Contenido contenidovisual;
    public int  puntuacion;
    public String comentario;

    /**
     * Constructor de la clase Resena.
     * @param usuario      El usuario que realiza la reseña.
     * @param contenidovisual El contenido visual al que se refiere la reseña.
     * @param puntuacion   La puntuación dada al contenido (de 1 a 5).
     * @param comentario   El comentario del usuario sobre el contenido.
     */

    public Resena(Usuario usuario, Contenido contenidovisual, int puntuacion, String comentario) {
        this.usuario = usuario;
        this.contenidovisual = contenidovisual;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    /**
     * Getters y Setters para los atributos de la clase Resena.
     * @return Atributos de la clase Resena.
     * 
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /** 
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /** 
     * @return Contenido
     */
    public Contenido getContenidovisual() {
        return contenidovisual;
    }
    /** 
     * @param contenidovisual
     */
    public void setContenidovisual(Contenido contenidovisual) {
        this.contenidovisual = contenidovisual;
    }
    /** 
     * @return int
     */
    public int getPuntuacion() {
        return puntuacion;
    }
    /** 
     * @param puntuacion
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    /** 
     * @return String
     */
    public String getComentario() {
        return comentario;
    }
    /** 
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // Metodo para obtener Reseña completa
    //public String obtenerResena() {
    // }

}
