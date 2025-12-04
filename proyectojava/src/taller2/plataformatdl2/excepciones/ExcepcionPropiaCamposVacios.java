package taller2.plataformatdl2.excepciones;

public class ExcepcionPropiaCamposVacios extends Exception {
    // Constructor vacio
    public ExcepcionPropiaCamposVacios() {
        super();
    }
    // Constructor con mensaje personalizado
    public ExcepcionPropiaCamposVacios(String mensaje) {
        super(mensaje);
    }
}