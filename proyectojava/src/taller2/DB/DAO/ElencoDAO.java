package taller2.DB.DAO;

// TODO - Tabla Elenco lista, falta conectarla con el resto de la bd
public interface ElencoDAO {
    public void crearTablaElenco();
    public void insertarElenco(String[] Elenco);
    public String[] devolverElencoViaId(int idElenco);
}
