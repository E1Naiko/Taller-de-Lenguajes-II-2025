package taller2.DB;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import taller2.plataformatdl2.Model.ManejoDeContenido.Calidades;
import taller2.plataformatdl2.Model.ManejoDeContenido.Genero;
import taller2.plataformatdl2.Model.ManejoDeContenido.Metadatos;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CargaCSV {
    private int errores = 0;
    private List<Pelicula> peliculasParseadas = new ArrayList<Pelicula>();
    private String direccion = new File("proyectojava/src/taller2/DB/movies_database.csv").getAbsolutePath();
    
    
    public CargaCSV() throws Exception{
        // Vamos a usar la libreria OpenCSV
        // Configuro para la lib para detectar separadores (,) y elementos compuestos o strings grandes (") para manejar campos con comas dentro de comillas
        CSVParser csvParser = new CSVParserBuilder()
        .withSeparator(',')
        .withQuoteChar('"')
        .withIgnoreLeadingWhiteSpace(true)
        .build();
        
        // Leo el archivo CSV con configuracion UTF_8 para evitarnos caracteres "raros"
        try (FileReader lectorArchivo = new FileReader(direccion, StandardCharsets.UTF_8);
        CSVReader lectorCSV = new CSVReaderBuilder(lectorArchivo)
        .withCSVParser(csvParser)
        .withSkipLines(1) // Saltar la primera línea si el CSV tiene encabezados
        .build()) {
            
            // Leer todos los registros del CSV
            List<String[]> registros = lectorCSV.readAll();
            
            // Procesar cada registro
            for (String[] campos : registros) {
                /*
                0   // Release_Date
                1   // Title
                2   // Overview
                3   // Popularity
                4   // Vote_Count
                5   // Vote_Average
                6   // Original_Language
                7   // Genre
                8   // Poster_Url
                */
                
                // Validar que el registro tenga todos los campos requeridos, si no los tiene lo cuento como error
                if (campos.length != 9) {
                    errores++;
                    continue;
                }
                
                try {
                    // Crear objeto Pelicula con los campos parseados
                    Metadatos metadatos = new Metadatos(
                        campos[1], // String titulo
                        campos[2], // String sinopsis
                        null, // String[] elenco
                        "DEBUG", // String director
                        LocalTime.MIN, // LocalTime duracion
                        campos[6], // String idioma
                        null, // String[] subtitulos
                        Float.parseFloat(campos[5]), // float rating_promedio
                        // Assumes the year is the first 4 characters, e.g., "2021-12-15"
                        Integer.parseInt(campos[0].strip().substring(0, 4)), // int anio
                        campos[8] // String urlPoster
                    );
                    
                    
                    Pelicula pelicula = new Pelicula(
                        Calidades.DEF,   // Calidades calidad;
                        "DEBUG",   // String audio;
                        "DEBUG",   // String direccionArchivo;
                        Genero.valueOf(campos[7]),   // Genero genero;
                        metadatos   // Metadatos metadatos;
                    );
                    
                    peliculasParseadas.add(pelicula);
                    
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear número en registro: " + e.getMessage());
                }
            }
            
            System.out.println("Proceso finalizado: " + peliculasParseadas.size() + " peliculas parseadas correctamente con " + errores + " errores");
        }
    }
    
    
    public List<Pelicula> getPeliculasParseadas() {
        return peliculasParseadas;
    }
}