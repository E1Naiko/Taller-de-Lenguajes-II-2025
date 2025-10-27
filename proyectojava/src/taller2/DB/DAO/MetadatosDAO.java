package taller2.DB.DAO;

import taller2.plataformatdl2.Model.ManejoDeContenido.Metadatos;

public interface MetadatosDAO {
    public void crearTablaMetadatos();
    public void insertarMetadatos(Metadatos metadatos);
    public void eliminarMetadatos(int idMetadatos);
    public int encontrarIdMetadatos(Metadatos metadatos);
    public Metadatos devolverMetadatosViaId(int idMetadatos);
}
