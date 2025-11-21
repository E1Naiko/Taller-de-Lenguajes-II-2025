package taller2.DB;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ConsultaPeliculasOMDb {
    // Reemplazá con tu API Key obtenida en https://www.omdbapi.com/apikey.aspx
    private static final String API_KEY = "f931eff1";
    public static void main(String[] args) {
        String titulo = "titulo"; // Reemplazar por elitulo a buscar
        consultarPelicula(titulo);
    }
    
    public static void consultarPelicula(String titulo) {
        try {
            // Armar la URL de consulta (encodear espacios con '+')
            String url = "https://www.omdbapi.com/?t=" + titulo.replace(" ","+") + "&apikey=" + API_KEY;

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
                } else {
                    System.out.println("❌ Película no encontrada o error en la consulta.");
                }

            } catch (Exception e) {
                System.out.println("Error al consultar la API: " + e.getMessage());
            }
        }
    }
    