package taller2.DB.DAO;

import java.sql.ResultSet;

import taller2.plataformatdl2.Usuario;

public interface UsuariosFinalDAO {
    public void crearTablaUsuarioFinal();
    public void insertarUsuarioFinal(Usuario usuario);
    public void eliminarUsuarioFinal();
    public int encontrarIdUsuarioFinal();
    public Usuario devolverUsuarioFinal();
    public ResultSet devolverUsuarioFinal(int id);
}
