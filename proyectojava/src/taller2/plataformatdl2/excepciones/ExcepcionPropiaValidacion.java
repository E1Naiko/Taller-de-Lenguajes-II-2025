package taller2.plataformatdl2.excepciones;

public class ExcepcionPropiaValidacion extends Exception  {
    // Constructor vacio
    public ExcepcionPropiaValidacion() {
        super();
    }
    // Constructor con mensaje y la causa original
    public ExcepcionPropiaValidacion(String mensaje) {
        super(mensaje);
    }
}
