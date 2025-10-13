package taller2.plataformatdl2;

import java.sql.ResultSet;

public interface UsuariosFinalDAO {
    public void crearTablaUsuarioFinal();
    public void insertarUsuarioFinal();
    public void eliminarUsuarioFinal();
    public int encontrarIdUsuarioFinal();
    public Usuario devolverUsuarioFinal();
    public ResultSet devolverUsuarioFinal(int id);
}
