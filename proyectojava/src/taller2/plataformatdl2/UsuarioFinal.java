package taller2.plataformatdl2;

public class UsuarioFinal extends Usuario {
    public String idioma;

    /**
     * Contructor para la clase UsuarioFinal
     * @param idioma
     */

    public UsuarioFinal(String nombre, String email, String contrasena, String idioma) {
        super(nombre,email,contrasena);
        this.idioma = idioma;
    }

    // Getters y Setters

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

}
