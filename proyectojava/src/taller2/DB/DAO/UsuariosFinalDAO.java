package taller2.DB.DAO;

import taller2.plataformatdl2.UsuarioFinal;

public interface UsuariosFinalDAO {
    public void crearTablaUsuarioFinal();
    public void insertarUsuarioFinal(UsuarioFinal usuario);
    public void eliminarUsuarioFinal(int idUsuario);
    public int devolverIdUsuarioFinal(UsuarioFinal usuario);
}
