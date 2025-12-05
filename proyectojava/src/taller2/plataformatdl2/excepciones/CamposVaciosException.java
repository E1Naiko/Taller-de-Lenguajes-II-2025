package taller2.plataformatdl2.excepciones;

public class CamposVaciosException extends Exception {
    // Constructor con mensaje personalizado
    public CamposVaciosException() {
        super("Error - Los campos se encuentran vacios");
    }
}