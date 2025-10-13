package taller2.plataformatdl2;

import java.sql.Time;

public interface ReseniasDAO {
    public void crearTablaResenias();
    public void insertarResenia();
    public void eliminarResenia();
    public void encontrarResenia();

    // SETTERS
    public void setCalificacion(int id);
    public void setComentario(int id);
    public void setAprobado(int id);
    public void setHora(int id);
    public void setIdUsuario(int id);
    public void setIdPelicula(int id);
    
    //GETTERS
    public int getCalificacion(int id);
    public String getComentario(int id);
    public Boolean getAprobado(int id);
    public Time getHora(int id);
    public int getIdUsuario(int id);
    public int getIdPelicula(int id);
}
