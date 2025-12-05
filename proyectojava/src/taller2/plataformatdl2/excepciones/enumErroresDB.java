package taller2.plataformatdl2.excepciones;

public enum enumErroresDB {
    DB_SIN_CONEXION("Error: No hay conexión con la base de datos."),
    DB_WARN_DAO_NULO("WARN: DAO nulo, simulando login para pruebas..."),
    DB_ERROR_CONECTANDO("Fallo total conectando a la base"),
    DB_NO_ENCONTRADO("Error: Elementos no encontrados en la BD");

    private final String mensaje;

    enumErroresDB(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
