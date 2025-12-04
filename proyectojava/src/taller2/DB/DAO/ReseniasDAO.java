package taller2.DB.DAO;

import java.util.List;
import taller2.plataformatdl2.Model.ManejoDeContenido.*;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;

public interface ReseniasDAO {
    public void crearTablaResenias();
    public void insertarResenia(int idUsuario, int idPelicula, Resena resenia, int aprobado);
    public void eliminarResenia(int idResenia);
    public int encontrarIdResenia(int idUsuario, int idPelicula, Resena resenia, int Aprobado);
    public List<Resena> devolverReseniasNoAprobadas();
    public boolean reseniaExiste(int idResenia);
    public int devolverIdViaUsuarioYPelicula(UsuarioFinal usuario, Pelicula pelicula);
    public boolean reseniaExiste(UsuarioFinal usuario, Pelicula pelicula);
    public Resena devolverReseniaViaId(int id);
    public void aprobarReseniaViaId(int id);
}
