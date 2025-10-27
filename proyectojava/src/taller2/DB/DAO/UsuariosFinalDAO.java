package taller2.DB.DAO;

import java.util.List;

import taller2.plataformatdl2.Model.ManejoDeUsuarios.*;

public interface UsuariosFinalDAO {
    public void crearTablaUsuarioFinal();
    public void insertarUsuarioFinal(UsuarioFinal usuario);
    public void eliminarUsuarioFinal(int idUsuario);
    public int devolverIdUsuarioFinal(UsuarioFinal usuario);
    public boolean existeUsuario(UsuarioFinal usuario);
    public boolean checkUsuarioViaLogin(String nombreUsuario, String contrasenia);
    public int encontrarIdUsuarioViaLogin(String nombreUsuario, String contrasenia);
    public UsuarioFinal devolverUsuarioFinalViaId(int id);
    public int encontrarIdUsuarioViaDNI(int dni);
    public boolean checkUsuarioViaDNI(int dni);
    public List<UsuarioFinal> obtenerUsuarios();
    public int getMaxId();
}