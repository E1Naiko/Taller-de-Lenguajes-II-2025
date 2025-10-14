package taller2.DB.DAO;

import java.sql.ResultSet;
import taller2.plataformatdl2.Resena;

public interface ReseniasDAO {
    public void crearTablaResenias();
    public void insertarResenia();
    public void eliminarResenia();
    public int encontrarIdResenia();
    public Resena devolverResenia();
    public ResultSet devolverResenia(int id);
}
