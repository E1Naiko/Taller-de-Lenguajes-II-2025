package taller2.plataformatdl2.Model.ManejoDeContenido;

public enum Genero {
    ACCION("Acción"),
    AVENTURA("Aventura"),
    COMEDIA("Comedia"), 
    DRAMA("Drama"),
    CIENCIA_FICCION("Ciencia Ficción"),
    TERROR("Terror"),
    SUSPENSO("Suspenso"),
    FANTASIA("Fantasía"),
    ROMANCE("Romance"),
    DOCUMENTAL("Documental"),
    ANIMACION("Animación"),
    MUSICAL("Musical"),
    CRIMEN("Crimen"),
    DEBUG("Debug");

    // Campo para almacenar el nombre legible
    private final String genero;

    // Constructor del enum
    Genero(String genero) {
        this.genero = genero;
    }

    // Getter para obtener el nombre del género
    public String getGenero() {
        return this.genero;
    }

    @Override
    public String toString() {
        return this.genero;
    }
}