package taller2.plataformatdl2.Utilities;

import java.time.LocalTime;

/**
* Clase para convertir entre formato de tiempo (HH:mm:ss o mm:ss) y segundos.
* Proporciona conversiones bidireccionales y validación de formatos.
* 
* @author Nicolas Peñalba
* @version 1.1
*/
public class TimeStringYSegundos {
    private String formatoString;    // Formato: "HH:mm:ss" o "mm:ss"
    private int formatoSegundos;     // Formato: segundos totales
    
    public TimeStringYSegundos(String entrada) {
        if (entrada.endsWith(" min")) {
            String numero = entrada.replace(" min", "").trim();
            int minutos = Integer.parseInt(numero);
            
            this.formatoSegundos = minutos * 60;
            this.formatoString = convertirSegundosAString(this.formatoSegundos); 
            return;
        }
        
        if (validarFormatoString(entrada)) {
            this.formatoString = entrada;
            this.formatoSegundos = convertirStringASegundos(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - Constructor(String) - Formato no válido. Use HH:mm:ss o mm:ss (ej: 01:30:45 o 30:45)");
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
    
    public TimeStringYSegundos(LocalTime entrada) {
        if (entrada != null) {
            this.formatoString = entrada.toString();
            this.formatoSegundos = convertirStringASegundos(this.formatoString);
        } else {
            System.err.println("Error - TimeStringYSegundos - Constructor(Time) - java.sql.Time de entrada es nulo");
            this.formatoString = "00:00:00";
            this.formatoSegundos = 0;
        }
    }
    
    /** 
    * @param entrada
    * @return boolean
    */private boolean validarFormatoString(String entrada) {
        if (entrada == null) {
            return false;
        }
        
        // Permite formato "178 min"
        if (entrada.endsWith(" min")) {
            String numero = entrada.replace(" min", "").trim();
            try {
                int minutos = Integer.parseInt(numero);
                return minutos >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        
        int longitud = entrada.length();
        if (longitud != 5 && longitud != 8) {
            return false;
        }
        
        try {
            if (longitud == 8) {
                if (entrada.charAt(2) != ':' || entrada.charAt(5) != ':') {
                    return false;
                }
                int horas = Integer.parseInt(entrada.substring(0, 2));
                int minutos = Integer.parseInt(entrada.substring(3, 5));
                int segundos = Integer.parseInt(entrada.substring(6, 8));
                return horas >= 0 && minutos >= 0 && minutos < 60 && segundos >= 0 && segundos < 60;
                
            } else { // mm:ss
                if (entrada.charAt(2) != ':') {
                    return false;
                }
                int minutos = Integer.parseInt(entrada.substring(0, 2));
                int segundos = Integer.parseInt(entrada.substring(3, 5));
                return minutos >= 0 && minutos < 60 && segundos >= 0 && segundos < 60;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    
    /** 
    * @param entrada
    * @return int
    */private int convertirStringASegundos(String entrada) {
        
        // Nuevo: "178 min"
        if (entrada.endsWith(" min")) {
            String numero = entrada.replace(" min", "").trim();
            try {
                int minutos = Integer.parseInt(numero);
                return minutos * 60;
            } catch (Exception e) {
                System.err.println("Error - TimeStringYSegundos - convertirStringASegundos - Formato 'min' inválido");
                return 0;
            }
        }
        
        try {
            int longitud = entrada.length();
            int horas, minutos, segundos;
            
            if (longitud == 8) {
                horas = Integer.parseInt(entrada.substring(0, 2));
                minutos = Integer.parseInt(entrada.substring(3, 5));
                segundos = Integer.parseInt(entrada.substring(6, 8));
            } else {
                horas = 0;
                minutos = Integer.parseInt(entrada.substring(0, 2));
                segundos = Integer.parseInt(entrada.substring(3, 5));
            }
            
            return (horas * 3600) + (minutos * 60) + segundos;
        } catch (Exception e) {
            System.err.println("Error - TimeStringYSegundos - convertirStringASegundos - Error: " + e.getMessage());
            return 0;
        }
    }
    
    
    /** 
    * @param entrada
    * @return String
    */
    private String convertirSegundosAString(int entrada) {
        int horas = entrada / 3600;
        int residuo = entrada % 3600;
        int minutos = residuo / 60;
        int segundos = residuo % 60;
        
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
    
    /** 
    * @return String
    */
    public String getFormatoString() {
        return formatoString;
    }
    
    /** 
    * @return int
    */
    public int getFormatoSegundos() {
        return formatoSegundos;
    }
    
    /** 
    * @return LocalTime
    */
    public LocalTime getFormatoTime() {
        return LocalTime.parse(this.formatoString);
    }
    
    /** 
    * @param entrada
    */
    public void setFormatoString(String entrada) {
        if (validarFormatoString(entrada)) {
            this.formatoString = entrada;
            this.formatoSegundos = convertirStringASegundos(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - setFormatoString - Formato no válido. Use HH:mm:ss o mm:ss");
        }
    }
    
    /** 
    * @param entrada
    */
    public void setFormatoSegundos(int entrada) {
        if (entrada >= 0) {
            this.formatoSegundos = entrada;
            this.formatoString = convertirSegundosAString(entrada);
        } else {
            System.err.println("Error - TimeStringYSegundos - setFormatoSegundos - Los segundos no pueden ser negativos");
        }
    }
}