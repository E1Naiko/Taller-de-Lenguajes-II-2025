package taller2.plataformatdl2.Model.ManejoDeContenido;

public enum Idiomas {
    ESPANOL("Español"),
    ENGLISH("English"),
    GUARANI("Guaraní");

    private final String idioma;

    Idiomas(String idioma){
        this.idioma = idioma;
    }

    public String getIdioma(){
        return idioma;
    }

    @Override
    public String toString(){
        return idioma;
    }
}
