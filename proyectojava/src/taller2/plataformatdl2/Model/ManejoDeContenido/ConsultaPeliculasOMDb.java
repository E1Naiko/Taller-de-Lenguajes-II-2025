package taller2.plataformatdl2.Model.ManejoDeContenido;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;

import org.json.JSONObject;

import taller2.plataformatdl2.Utilities.TimeStringYSegundos;

public class ConsultaPeliculasOMDb {
    // Reemplazá con tu API Key obtenida en https://www.omdbapi.com/apikey.aspx
    private static final String API_KEY = "f931eff1";        
    public Pelicula buscarPeliculaApi(String termino) throws Exception{
        Pelicula pelicula = null;
        try {
            // Armar la URL de consulta (encodear espacios con '+')
            String url = "https://www.omdbapi.com/?t=" + termino.replace(" ","+") + "&apikey=" + API_KEY;
            // test1: https://www.omdbapi.com/?t=Guardians+Of+The+Galaxy&apikey=f931eff1
            // test2: https://www.omdbapi.com/?t=Lord+Of+The+Rings&apikey=f931eff1
            
            // Crear cliente y solicitud
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
            
            // Enviar solicitud
            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
                
                // Procesar respuesta JSON
                JSONObject json = new JSONObject(response.body());
                if (json.has("Response") &&
                json.getString("Response").equals("True")) {
                    System.out.println("🎬 Título: " + json.getString("Title"));
                    System.out.println("📅 Año: " + json.getString("Year"));
                    System.out.println("📝 Sinopsis: " + json.getString("Plot"));
                    
                    String[] elenco = {json.getString("Actors")};
                    String[] subtitulos = {"DEBUG"};
                    
                    TimeStringYSegundos conversorDuracion = new TimeStringYSegundos(json.getString("Runtime"));
                    LocalTime duracion = conversorDuracion.getFormatoTime();
                    // Crear objeto Pelicula con los campos parseados
                    Metadatos metadatos = new Metadatos(
                        json.getString("Title"), // String titulo
                        json.getString("Plot"), // String sinopsis
                        elenco, // String[] elenco
                        json.getString("Director"), // String director
                        duracion, // LocalTime duracion
                        json.getString("Languaje"), // String idioma
                        subtitulos, // String[] subtitulos
                        obtenerPromedio(json.getString("Ratings")), // float rating_promedio
                        // Assumes the year is the first 4 characters, e.g., "2021-12-15"
                        Integer.parseInt(json.getString("Year")), // int anio
                        json.getString("Poster") // String urlPoster
                    );
                    
                    pelicula = new Pelicula(
                        Calidades.DEF,   // Calidades calidad;
                        "DEBUG",   // String audio;
                        "DEBUG",   // String direccionArchivo;
                        tomarPrimerGenero(json.getString("Genre")),   // Genero genero;
                        metadatos   // Metadatos metadatos;
                    );
                    
                } else {
                    System.out.println("❌ Película no encontrada o error en la consulta.");
                }
                
            } catch (Exception e) {
                System.out.println("Error al consultar la API: " + e.getMessage());
            }
            return pelicula;
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
        
        private float obtenerPromedio(String ratings){
            float res = 0;
            @SuppressWarnings("unused")
            int cantidad = 0;
            
            /*
            Encontramos que en general la api devuelve de la siguiente forma:
            "Ratings": [
            {
            "Source": "Internet Movie Database",
            "Value": "8.9/10"
            },
            {
            "Source": "Rotten Tomatoes",
            "Value": "92%"
            },
            {
            "Source": "Metacritic",
            "Value": "92/100"
            }
            ]
            */
            
            // Cortamos por la palabra "Value"
            String[] partes = ratings.split("\"Value\"");
            
            for (int i = 1; i < partes.length; i++) {
                String fragmento = partes[i];
                
                // Buscamos la primera comilla doble " que aparece luego de :
                int comilla1 = fragmento.indexOf('"');
                int comilla2 = fragmento.indexOf('"', comilla1 + 1);
                
                if (comilla1 == -1 || comilla2 == -1) continue;
                
                // Extraemos el valor
                String valor = fragmento.substring(comilla1 + 1, comilla2);
                
                // Las 3 paginas que devuelve tienen 3 formatos distintos asi que los convertimos
                float floatConvertido = convertirRating(valor); 
                
                if (floatConvertido >= 0) {
                    res += floatConvertido;
                    cantidad++;
                }
            }
            
            return res;
        }
        
        private float convertirRating(String valor) {
            if (valor.endsWith("/10")) {
                return Float.parseFloat(valor.split("/")[0]);
                
            } else if (valor.endsWith("%")) {
                return Float.parseFloat(valor.replace("%", "")) / 10;
                
            } else if (valor.endsWith("/100")) {
                return Float.parseFloat(valor.split("/")[0]) / 10;
            }
            
            return -1; // formato no reconocido
        }
    }