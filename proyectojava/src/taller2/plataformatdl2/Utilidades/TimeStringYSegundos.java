package taller2.plataformatdl2.Utilidades;

import java.sql.Time;

/**
* Clase para convertir entre formato de tiempo (HH:mm:ss) y segundos.
* Proporciona conversiones bidireccionales y validación de formatos.
* 
* @author Nicolas Peñalba
* @version 1.0
*/
public class TimeStringYSegundos {
    private String formatoString;    // Formato: "HH:mm:ss"
    private int formatoSegundos;     // Formato: segundos totales
    
    public TimeStringYSegundos(String entrada) {
        if (validarFormatoString(entrada)) {
            this.formatoString = entrada;
            this.formatoSegundos = convertirStringASegundos(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - Constructor(String) - Formato no válido. Use HH:mm:ss (ej: 01:30:45)");
            this.formatoString = "00:00:00";
            this.formatoSegundos = 0;
        }
    }
    
    public TimeStringYSegundos(int entrada) {
        if (entrada >= 0) {
            this.formatoSegundos = entrada;
            this.formatoString = convertirSegundosAString(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - Constructor(int) - Los segundos no pueden ser negativos");
            this.formatoSegundos = 0;
            this.formatoString = "00:00:00";
        }
    }

     public TimeStringYSegundos(Time entrada) {
        if (entrada != null) {
            // El formato String de java.sql.Time es compatible con HH:mm:ss
            this.formatoString = entrada.toString();
            this.formatoSegundos = convertirStringASegundos(this.formatoString);
        } else {
            System.err.println("Error - TimeStringYSegundos - Constructor(Time) - java.sql.Time de entrada es nulo");
            this.formatoString = "00:00:00";
            this.formatoSegundos = 0;
        }
    }
    
    private boolean validarFormatoString(String entrada) {
        // Validar que tenga exactamente 8 caracteres (HH:mm:ss)
        if (entrada == null || entrada.length() != 8) {
            return false;
        }
        
        // Validar estructura: número:número:número
        if (entrada.charAt(2) != ':' || entrada.charAt(5) != ':') {
            return false;
        }
        
        try {
            // Extraer horas, minutos y segundos
            int horas = Integer.parseInt(entrada.substring(0, 2));
            int minutos = Integer.parseInt(entrada.substring(3, 5));
            int segundos = Integer.parseInt(entrada.substring(6, 8));
            
            // Validar rangos
            if (horas >= 0 && minutos >= 0 && minutos < 60 && segundos >= 0 && segundos < 60) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        
        return false;
    }
    
    private int convertirStringASegundos(String entrada) {
        try {
            // Tomo cada segmento del HH:MM:SS
            int horas = Integer.parseInt(entrada.substring(0, 2));
            int minutos = Integer.parseInt(entrada.substring(3, 5));
            int segundos = Integer.parseInt(entrada.substring(6, 8));
            
            return (horas * 3600) + (minutos * 60) + segundos;
        } catch (Exception e) {
            System.err.println(" Error - - TimeStringYSegundos - convertirStringASegundos - Error al convertir String a segundos: " + e.getMessage());
            return 0;
        }
    }
    
    private String convertirSegundosAString(int entrada) {
        int horas = entrada / 3600;        // Horas: 7200 segundos / 3600 = 2 horas
        int residuo = entrada % 3600;      // Residuo: 7200 % 3600 = 0
        int minutos = residuo / 60;        // Minutos: 0 / 60 = 0
        int segundos = residuo % 60;       // Segundos: 0 % 60 = 0
        
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
    
    // ---- Getters ----
    
    public String getFormatoString() {
        return formatoString;
    }
    
    public int getFormatoSegundos() {
        return formatoSegundos;
    }

    public Time getFormatoTime() {
        // java.sql.Time.valueOf(String) espera el formato HH:mm:ss
        return Time.valueOf(this.formatoString);
    }

    public void setFormatoString(String entrada) {
        if (validarFormatoString(entrada)) {
            this.formatoString = entrada;
            this.formatoSegundos = convertirStringASegundos(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - setFormatoString -  Formato no válido. Use HH:mm:ss");
        }
    }
    public void setFormatoSegundos(int entrada) {
        if (entrada >= 0) {
            this.formatoSegundos = entrada;
            this.formatoString = convertirSegundosAString(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - setFormatoSegundos - Los segundos no pueden ser negativos");
        }
    }
}