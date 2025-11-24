package taller2.plataformatdl2.Model.ManejoDeContenido;

public class Pelicula extends Contenido{
    public Pelicula(Calidades calidad, String audio, String direccionArchivo, Genero genero, Metadatos metadatosPelicula){
        super(calidad, audio, direccionArchivo, metadatosPelicula);
    }

    /** 
     * @return String
     */
    public String toString(){
        return super.toString();
    }
}
