package taller2.plataformatdl2;
import taller2.plataformatdl2.ManejoDeContenido.*;
import java.time.LocalTime;

/**
 * Clase que representa el reproductor de la plataforma de streaming.
 * Esta clase maneja la reproducción de contenidos, incluyendo el control de tiempo,
 * subtítulos y el streaming Adaptativo (ABS).
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.0 - 2025-09-15
 */

public class Reproductor {
    private ContenidoDAO contenidoActual;
    private LocalTime tiempoActual;
    private LocalTime tiempoInicio;
    // Aqui se declara tipo de datos objetos que no esta en la carpeta
    //private Subtitulo subtituloActual;
    //private AdaptativeBitrateStreaming controlABS;

    /**
     * Constructor de la clase Reproductor.
     * 
     * @param contenidoActual El contenido a reproducir.
     //* @param subtitulo El subtitulo a usar.
     //* @param abs El control de streaming adaptativo.
     * @param tiempoInicio El tiempo de inicio de la reproducción.
     * @param tiempoActual El tiempo actual de la reproducción.
     */

    public Reproductor(ContenidoDAO contenidoActual, /**Subtitulo subtitulo, AdaptativeBitrateStreaming abs **/ LocalTime tiempoInicio, LocalTime tiempoActual) {
        this.contenidoActual = contenidoActual;
        //this.subtituloActual = subtitulo;
        //this.controlABS = abs;
        this.tiempoInicio = tiempoInicio;
        this.tiempoActual = tiempoActual;
    }

    /** 
     * @return Contenido
     */
    // --- Getters y Setters ---

    public ContenidoDAO getContenidoActual() {
        return contenidoActual;
    }

    /** 
     * @param contenidoActual
     */
    public void setContenidoActual(ContenidoDAO contenidoActual) {
        this.contenidoActual = contenidoActual;
    }

    /** 
     * @return LocalTime
     */
    public LocalTime getTiempoActual() {
        return tiempoActual;
    }

    /** 
     * @param tiempoActual
     */
    public void setTiempoActual(LocalTime tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    /** 
     * @return LocalTime
     */
    public LocalTime getTiempoInicio() {
        return tiempoInicio;
    }

    /** 
     * @param tiempoInicio
     */
    public void setTiempoInicio(LocalTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    /**public Subtitulo getSubtituloActual() {
        return subtituloActual;
    }

    public void setSubtituloActual(Subtitulo subtituloActual) {
        this.subtituloActual = subtituloActual;
    }

    public AdaptativeBitrateStreaming getControlABS() {
        return controlABS;
    }

    public void setControlABS(AdaptativeBitrateStreaming controlABS) {
        this.controlABS = controlABS;
    }
    */
    
    // --- Métodos para manejar la reproducción ---

    public void play(ContenidoDAO cont) {
        // Logica para iniciar la reproducción del contenido..
    }

    public void pausar() {
        // Logica para pausar la reproducción..
    }

    public void adelantar() {
        // Logica para detener la reproducción..
    }

    public void retroceder() {
        // Logica para retroceder la reproducción..
    }       

    /** 
     * @param cont
     */
    public void actualizarHistoria(ContenidoDAO cont) {
        // Logica para actualizar la historia de reproduccion del usuario..
    }

    /** 
     * @return LocalTime
     */
    public LocalTime calcularTiempoVistos() {
        // Logica para calcular el tiempo restante de la reproducción..
        return null;
    }

}
