package taller2.DB.DAO;

import taller2.plataformatdl2.Contenido;

public interface PeliculasDAO {
    public void crearTablaPeliculas();
    public void insertarPeliculas();
    public void eliminarPeliculas();
    public Contenido encontrarPelicula(int id);
    public int encontrarIdPelicula(Contenido pelicula);
}
