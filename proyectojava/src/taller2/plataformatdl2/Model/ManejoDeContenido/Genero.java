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
    CRIMEN("Crimen");
    
    private final String nombreMostrar;
    
    Genero(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }
    
    public String getNombreMostrar() {
        return nombreMostrar;
    }
    
    // toString sobrescrito
    @Override
    public String toString() {
        return nombreMostrar;
    }
    
    // Método estático útil para buscar por nombre
    public static Genero fromString(String texto) {
        for (Genero genero : Genero.values()) {
            if (genero.nombreMostrar.equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Género no encontrado: " + texto);
    }
}