package taller2.plataformatdl2.Model.ManejoDeContenido;

public class Pelicula extends Contenido{
    Metadatos metadatosPelicula;

    public Pelicula(String calidad, String audio, String direccionArchivo, Metadatos metadatosPelicula){
        super(calidad, audio, direccionArchivo);
        this.metadatosPelicula= metadatosPelicula;
    }

    /** 
     * @return Metadatos
     */
    public Metadatos getMetadatos() {
        return metadatosPelicula;
    }
    /** 
     * @param Metadatos
     */
    public void setSinopsis(Metadatos metadatosPelicula) {
        this.metadatosPelicula = metadatosPelicula;
    }
}
