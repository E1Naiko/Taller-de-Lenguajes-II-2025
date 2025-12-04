package taller2.plataformatdl2.excepciones;

public class ExcepcionPropiaDB extends Exception {
    // Constructor vacio
    public ExcepcionPropiaDB() {
        super();
    }
    // Constructor con la causa de la excepcion
    public ExcepcionPropiaDB(String mensaje) {
        super(mensaje);
    }
}
