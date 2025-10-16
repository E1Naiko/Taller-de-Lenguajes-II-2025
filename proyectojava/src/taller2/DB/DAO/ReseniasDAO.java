package taller2.DB.DAO;

import taller2.plataformatdl2.Resena;

public interface ReseniasDAO {
    public void crearTablaResenias();
    public void insertarResenia(int idUsuario, int idPelicula, Resena resenia, int aprobado);
    public void eliminarResenia(int idResenia);
    public int encontrarIdResenia(int idUsuario, int idPelicula, Resena resenia);
}
