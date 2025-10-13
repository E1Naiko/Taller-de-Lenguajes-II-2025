package taller2.plataformatdl2;

import java.sql.ResultSet;

public interface ReseniasDAO {
    public void crearTablaResenias();
    public void insertarResenia();
    public void eliminarResenia();
    public int encontrarIdResenia();
    public Resena devolverResenia();
    public ResultSet devolverResenia(int id);
}
