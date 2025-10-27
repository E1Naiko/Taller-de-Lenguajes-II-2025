package taller2.DB.DAO;

import java.util.List;

import taller2.plataformatdl2.Model.ManejoDeContenido.*;

public interface PeliculasDAO {
    public void crearTablaPeliculas();
    public void insertarPeliculas(Pelicula pelicula);
    public void eliminarPeliculas(int idPelicula);
    public int encontrarIdPelicula(Contenido pelicula);
    public Pelicula devolverPeliculaViaId(int idPelicula);
    public List<Pelicula> obtenerPeliculas();
    public int getMaxId();
}
