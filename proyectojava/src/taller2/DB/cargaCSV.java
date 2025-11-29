package taller2.DB;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import taller2.plataformatdl2.Model.ManejoDeContenido.Metadatos;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CargaCSV {
    private int errores = 0;
    public List<Pelicula> cargarCSV(String direccion) throws Exception {
        List<Pelicula> peliculasParseadas = new ArrayList<>();
        
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
                    /*
                    Metadatos
                    String titulo;
                    String sinopsis;
                    String[] elenco;
                    String director;
                    LocalTime duracion;
                    String idioma;
                    String[] subtitulos;
                    float rating_promedio;
                    int anio;
                    String urlPoster;
                    */
                    Metadatos metadatos = new Metadatos(
                        campos[1],
                        campos[2],
                        "DEBUG",
                        "DEBUG",
                        0,
                        campos[6],
                        null,
                        Integer.getInteger((String) campos[5].strip().subSequence(0, 3)),
                        anio,
                        urlPoster
                    );
                    /*
                    Pelicula
                    Calidades calidad;
                    String audio;
                    String direccionArchivo;
                    Genero genero;
                    Metadatos metadatos;
                    */
                   Pelicula pelicula = new Pelicula(
                       calidad,
                       audio,
                       direccionArchivo,
                       genero,
                       metadatos
                    );
                    
                    peliculasParseadas.add(pelicula);
                    System.out.printf("Pelicula parseada: %s (%d)%n", pelicula.getTitulo(), pelicula.getAnio());
                    
                } catch (NumberFormatException e) {
                    System.err.printf("Error al parsear número en registro: %s%n", e.getMessage());
                }
            }
            
            System.out.printf("Proceso finalizado: %d peliculas parseadas correctamente%n", peliculasParseadas.size());
        }
        
        return peliculasParseadas;
    }
    
    public int getErrores() {
        return errores;
    }
}