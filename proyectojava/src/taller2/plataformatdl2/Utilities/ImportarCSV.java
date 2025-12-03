package taller2.plataformatdl2.Utilities;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import taller2.DB.DAO.Factory;
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

public class ImportarCSV implements Runnable{
    private int total = 0;
    private int correctas = 0;
    private int errores = 0;
    private static List<Pelicula> peliculasParseadas = new ArrayList<Pelicula>();
    private String direccion = new File("proyectojava/src/taller2/DB/movies_database.csv").getAbsolutePath();
    
    
    public void run() {
        try {
            cargarListaPeliculas();
            
            Lista_A_Bd pasajeDeDatos = new Lista_A_Bd();

            Factory.getPeliculasDAO().setImprimirDebug(false);
            Factory.getMetadatosDAO().setImprimirDebug(false);
            pasajeDeDatos.pasarListaPeliculas_a_BD(peliculasParseadas);
            Factory.getPeliculasDAO().setImprimirDebug(true);
            Factory.getMetadatosDAO().setImprimirDebug(true);

            System.out.println("CargaCSV - Carga terminada");
        }
        catch (Exception e) {
            System.err.println("Error procesando lista");
            System.err.println(e);
        }
    }
    
    
    private void cargarListaPeliculas() throws Exception{
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
                    String[] elenco = {"DEBUG"};
                    String[] subtitulos = {"DEBUG"};
                    // Crear objeto Pelicula con los campos parseados
                    Metadatos metadatos = new Metadatos(
                        campos[1], // String titulo
                        campos[2], // String sinopsis
                        elenco, // String[] elenco
                        "DEBUG", // String director
                        LocalTime.MIN, // LocalTime duracion
                        campos[6], // String idioma
                        subtitulos, // String[] subtitulos
                        Float.parseFloat(campos[5]), // float rating_promedio
                        // Assumes the year is the first 4 characters, e.g., "2021-12-15"
                        Integer.parseInt(campos[0].strip().substring(0, 4)), // int anio
                        campos[8] // String urlPoster
                    );
                    
                    
                    
                    Pelicula pelicula = new Pelicula(
                        Calidades.DEF,   // Calidades calidad;
                        "DEBUG",   // String audio;
                        "DEBUG",   // String direccionArchivo;
                        tomarPrimerGenero(campos[7]),   // Genero genero;
                        metadatos   // Metadatos metadatos;
                    );
                    
                    peliculasParseadas.add(pelicula);
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear número en registro: " + e.getMessage());
                }
            }
            
            
            correctas = peliculasParseadas.size();
            total = correctas + errores;
            float tazaError = ((float) errores / ((float) total ))*100;
            System.out.println("CargaCSV - CSV a memoria con exito: \n - "
            + peliculasParseadas.size() + " peliculas parseadas correctamente \n - " + errores + " errores");
            System.out.printf(" - Taza de error del %.4f %c \n", tazaError, '%'); // en este caso uso printf para hacer uso del redondeo de numeros de C
        }
    }
    
    
    
    public static List<Pelicula> getPeliculasParseadas() {
        return peliculasParseadas;
    }
    
    
    
    private Genero tomarPrimerGenero(String entrada){
        Genero genero;
        String[] generosSeparados = entrada.split(",");
        
        switch (generosSeparados[0].toUpperCase()) {
            case "ACTION":
            genero = Genero.ACCION;
            break;
            case "ADVENTURE":
            genero = Genero.AVENTURA;
            break;
            case "COMEDY":
            genero = Genero.COMEDIA;
            break;
            case "DRAMA":
            genero = Genero.DRAMA;
            break;
            case "SCIENCE FICTION":
            genero = Genero.CIENCIA_FICCION;
            break;
            case "TERROR":
            genero = Genero.TERROR;
            break;
            case "THRILLER":
            genero = Genero.SUSPENSO;
            break;
            case "FANTASY":
            genero = Genero.FANTASIA;
            break;
            case "ROMACE":
            genero = Genero.ROMANCE;
            break;
            case "DOCUMENTAL":
            genero = Genero.DOCUMENTAL;
            break;
            case "ANIMATION":
            genero = Genero.ANIMACION;
            break;
            case "MUSICAL":
            genero = Genero.MUSICAL;
            break;
            case "CRIME":
            genero = Genero.CRIMEN;
            break;
            
            default:
            genero = Genero.DEBUG;
            break;
        }
        return genero;
    }
    
}