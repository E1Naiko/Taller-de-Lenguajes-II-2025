package taller2.plataformatdl2.excepciones;

public class ErroresDbException extends Exception {
    public ErroresDbException(enumErroresDB error) {
        super(error.getMensaje());
    }
}
