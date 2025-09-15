package taller2.plataformatld2;

import java.sql.Time;

/**
 * Clase que representa el reproductor de la plataforma de streaming.
 * Esta clase maneja la reproducción de contenidos, incluyendo el control de tiempo,
 * subtítulos y el streaming Adaptativo (ABS).
 * 
 * @author Alam Meza y Nicolas Peñalba
 * @version 1.0 - 2025-09-15
 */

public class Reproductor {
    private Contenido contenidoActual;
    private Time tiempoActual;
    private Time tiempoInicio;
    // Aqui se declara tipo de datos objetos que no esta en la carpeta
    //private Subtitulo subtituloActual;
    //private AdaptativeBitrateStreaming controlABS;

    /**
     * Constructor de la clase Reproductor.
     * 
     * @param contenido El contenido a reproducir.
     * @param subtitulo El subtitulo a usar.
     * @param abs El control de streaming adaptativo.
     * @param tiempoInicio El tiempo de inicio de la reproducción.
     * @param tiempoActual El tiempo actual de la reproducción.
     */

    public Reproductor(Contenido contenido, /**Subtitulo subtitulo, AdaptativeBitrateStreaming abs **/ Time tiempoInicio, Time tiempoActual) {
        this.contenidoActual = contenido;
        //this.subtituloActual = subtitulo;
        //this.controlABS = abs;
        this.tiempoInicio = tiempoInicio;
        this.tiempoActual = tiempoActual;
    }

    // --- Getters y Setters ---

    public Contenido getContenidoActual() {
        return contenidoActual;
    }

    public void setContenidoActual(Contenido contenidoActual) {
        this.contenidoActual = contenidoActual;
    }

    public Time getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(Time tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public Time getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(Time tiempoInicio) {
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

    public void play(Contenido Cont) {
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

    public void actualizarHistoria(Contenido cont) {
        // Logica para actualizar la historia de reproduccion del usuario..
    }

    public Time calcularTiempoVistos() {
        // Logica para calcular el tiempo restante de la reproducción..
        return null;
    }

}
