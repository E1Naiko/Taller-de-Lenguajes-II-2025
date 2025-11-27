package taller2.DB.DAO;

import java.util.List;

public interface ElencoDAO {
    public void crearTablaElenco();
    public void insertarElenco(int idPeliculaAsociado, String Elenco);
    public void insertarElenco(int idPeliculaAsociado, List<String> Elenco);
    public List<String> devolverElencoViaIdPelicula(int idPeliculaAsociado);
    public int encontrarComienzoListaViaIdPelicula(int idPeliculaAsociado);
    public boolean existeElencoViaIdPelicula(int idPeliculaAsociado);
}
