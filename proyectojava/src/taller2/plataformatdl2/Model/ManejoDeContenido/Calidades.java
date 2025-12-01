package taller2.plataformatdl2.Model.ManejoDeContenido;

public enum Calidades {
    DEF("NO ESPECIFICADA"),
    SD_480P("480p"),
    HD_720P("720p"),
    FULL_HD_1080P("1080p"),
    QHD_2K("2K"),
    UHD_4K("4K");
    
    private final String calidad;
    
    Calidades(String calidad) {
        this.calidad = calidad;
    }
    
    public String getCalidad() {
        return calidad;
    }

    public static Calidades fromCalidad(String calidad) {
        for (Calidades c : Calidades.values()) {
            if (c.calidad.equals(calidad)) {
                return c;
            }
        }
        return DEF;
    }
    
    @Override
    public String toString() {
        return calidad;
    }
}