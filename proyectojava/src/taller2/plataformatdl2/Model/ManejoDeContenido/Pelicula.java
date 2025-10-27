package taller2.plataformatdl2.Model.ManejoDeContenido;

public class Pelicula extends Contenido{
    Metadatos metadatosPelicula;

    public Pelicula(String calidad, String audio, String direccionArchivo, Genero genero, Metadatos metadatosPelicula){
        super(calidad, audio, direccionArchivo);
    };
}
