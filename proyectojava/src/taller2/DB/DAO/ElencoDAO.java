package taller2.DB.DAO;

public interface ElencoDAO {
    public void crearTablaElenco();
    public void insertarElenco(String[] Elenco);
    public String[] devolverElencoViaId(int idElenco);
}
