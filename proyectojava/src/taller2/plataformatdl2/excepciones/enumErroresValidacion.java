package taller2.plataformatdl2.excepciones;

public enum enumErroresValidacion {

    // Errores de validación
    CONTRASENIA_NO_COINCIDE("Error: las contraseñas no coinciden"),
    EMAIL_INVALIDO("Error: email no válido"),
    DNI_NO_NUMERICO("Error: DNI tiene que ser un número."),
    DNI_YA_EXISTE("Error: DNI ya existe."),
    USUARIO_YA_EXISTE("Error: Usuario ya existe");

    private final String mensaje;

    enumErroresValidacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

}
