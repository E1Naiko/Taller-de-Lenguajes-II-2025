package taller2.DB.DAO;

import java.util.List;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;

public interface ReseniasDAO {
    public void crearTablaResenias();
    public void insertarResenia(int idUsuario, int idPelicula, Resena resenia, int aprobado);
    public void eliminarResenia(int idResenia);
    public int encontrarIdResenia(int idUsuario, int idPelicula, Resena resenia, int Aprobado);
    public List<Resena> devolverReseniasNoAprobadas();
}
