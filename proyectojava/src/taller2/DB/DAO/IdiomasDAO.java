package taller2.DB.DAO;

import java.util.List;

import taller2.plataformatdl2.Model.ManejoDeContenido.Idiomas;

public interface IdiomasDAO {
    public void crearTablaIdiomas();
    public void insertarIdioma(int idPeliculaAsociado, String Idioma);
    public List<Idiomas> devolverIdiomasViaId(int idIdiomas);
    public int encontrarId(int idPeliculaAsociado, String idioma);
    public boolean existeIdiomaEnTalba(int idPeliculaAsociado, String idioma);
}
