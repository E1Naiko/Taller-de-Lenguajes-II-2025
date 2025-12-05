package taller2.plataformatdl2.excepciones;

public class ValidacionException extends Exception  {
    // Constructor vacio
    public ValidacionException(enumErroresValidacion error) {
        super(error.getMensaje());
    }
}
