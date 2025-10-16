package taller2.plataformatdl2;

public class UsuarioFinal extends Usuario {
    public String idioma;
    public String generosPreferidos;
    public String listaPreferida;
    public String historial;

    /**
     * Constructor para la clase UsuarioFinal
     * @param idioma idioma preferido del usuario
     * @param generosPreferidos géneros preferidos del usuario
     * @param listaPreferida lista preferida o de reproducción del usuario
     * @param historial historial de reproducciones del usuario
     */

    public UsuarioFinal(String nombre, String email, String contrasena, String idioma, String generosPreferidos, String listaPreferida, String historial) {
        super(nombre,email,contrasena);
        this.idioma = idioma;
        this.generosPreferidos = generosPreferidos;
        this.listaPreferida = listaPreferida;
        this.historial = historial;

    }

    /** 
     * @return String
     */
    // Getters y Setters

    public String getIdioma() {
        return idioma;
    }

    /** 
     * @param idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /** 
     * @return String
     */
    public String getGenerosPreferidos() {
        return generosPreferidos;
    }

    /** 
     * @param generosPreferidos
     */
    public void setGenerosPreferidos(String generosPreferidos) {
        this.generosPreferidos = generosPreferidos;
    }

    /** 
     * @return String
     */
    public String getListaPreferida() {
        return listaPreferida;
    }

    /** 
     * @param listaPreferida
     */
    public void setListaPreferida(String listaPreferida) {
        this.listaPreferida = listaPreferida;
    }

    /** 
     * @return String
     */
    public String getHistorial() {
        return historial;
    }

    /** 
     * @param historial
     */
    public void setHistorial(String historial) {
        this.historial = historial;
    }

}
