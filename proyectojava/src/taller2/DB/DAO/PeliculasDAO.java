package taller2.DB.DAO;

import taller2.plataformatdl2.ManejoDeContenido.*;;

public interface PeliculasDAO {
    public void crearTablaPeliculas();
    public void insertarPeliculas();
    public void eliminarPeliculas();
    public ContenidoDAO encontrarPelicula(int id);
    public int encontrarIdPelicula(ContenidoDAO pelicula);
}
