package taller2.plataformatdl2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List; // Necesario para ordenar
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.ObjectUtils.Null;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeContenido.ConsultaPeliculasOMDb;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.Usuario;
import taller2.plataformatdl2.Utilities.ImportarCSV;
import taller2.plataformatdl2.Utilities.ComparadorPeliculaPorGenero;
import taller2.plataformatdl2.Utilities.ComparadorPeliculaPorTitulo;
import taller2.plataformatdl2.view.LoginVista;
import taller2.plataformatdl2.view.MenuPrincipalVista;
import taller2.plataformatdl2.view.DetallesPeliculaVista;

@SuppressWarnings("unused")
public class MenuPrincipalController implements ActionListener {
    
    private MenuPrincipalVista vista;
    private Usuario usuario;
    private List<Pelicula> cachePeliculas;  
    private List<Pelicula> peliculasVistas;  
    
    
    public MenuPrincipalController(Usuario usuario, MenuPrincipalVista vista) { 
        this.usuario = usuario;
        this.vista = vista;
        inicializar(); 
    }
    
    private void inicializar() {
        if (usuario != null) { // Para mostrar el nombre en la vista
            vista.setNombreUsuario(usuario.getNombre());
        }    
        vista.addListeners(this);
        actualizarCatalogo();
        vista.setVisible(true);
    }
    
    private void actualizarCatalogo() {
        vista.setCargando(true);
        Thread worker = new Thread(() -> { // Para que Cargue las peliculas en segundo plano
            try {
                Thread.sleep(800);  
                try {
                    cachePeliculas = ImportarCSV.getPeliculasParseadas();
                    if (cachePeliculas != null) {
                        peliculasVistas = cachePeliculas.stream()
                        .filter(p -> p!= null)
                        .limit(10)
                        .collect(Collectors.toList());
                    } else {
                        peliculasVistas = new ArrayList<>();
                    }
                    SwingUtilities.invokeLater(() -> {
                        vista.cargarPeliculas(peliculasVistas, this);
                        vista.setCargando(false);
                        // Por si tira null tiramos un mensaje
                        if (cachePeliculas == null) {
                            System.err.println("Warning: Factory.getListaPeliculas() devolvió NULL.");
                        }
                    });
                } catch (Exception e) {
                    System.err.println("Error procesando lista");
                    System.err.println(e);
                }                
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> vista.setCargando(false));
            }
        });      
        worker.start();
    }

    // Metodo para randomizar las peliculas la hacer click en continuar
    private void randomizarCatalogo() {
        vista.setCargando(true);
        Thread worker = new Thread(() -> {
            try {
                List<Pelicula> listaEntera = Factory.getListaPeliculas(); // Agarramos todas
                if (listaEntera != null) {
                    List<Pelicula> copia = new ArrayList<>(listaEntera);
                    Collections.shuffle(copia); // Se mezcla                 
                    // Agarramos 10 randoms
                    cachePeliculas = copia.stream().filter(p->p!=null).limit(10).collect(Collectors.toList());                 
                    SwingUtilities.invokeLater(() -> {
                        vista.cargarPeliculas(cachePeliculas, this); // Actualizamos la tabla
                        vista.setCargando(false);
                        vista.mostrarMensaje("¡Catálogo randomizado!");
                    });
                }
            } catch (Exception e) { e.printStackTrace(); }
        });
        worker.start();
    }

    // Metodo de la busqueda de peliculas
    private void filtrarCatalogo() {
        String termino = vista.getTextoBusqueda().toLowerCase().trim();
        if (termino.isEmpty()){
            vista.mostrarMensaje("Escribir algo para buscar...");
        }
        vista.setCargando(true); // Aca ponemos el perrito mientra busca la pelicula
        Thread apiWorker = new Thread(() -> {
            try {
                // Buscamos en la API
                ConsultaPeliculasOMDb coneccionApi = new ConsultaPeliculasOMDb();
                Pelicula peliApi = coneccionApi.buscarPeliculaApi(termino);
                
                SwingUtilities.invokeLater(() -> {
                    vista.setCargando(false); // Apagamos el perrito
                    
                    if (peliApi != null) {
                        // ABRIMOS LA VENTANA NUEVA (JDialog)
                        mostrarVentanaDetalle(peliApi);
                    } else {
                        vista.mostrarMensaje("No encontré esa peli ni abajo de las piedras.");
                    }
                });
                
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    vista.setCargando(false);
                    vista.mostrarMensaje("Error de conexión con la API.");
                });
            }
        });
        apiWorker.start();
    }

    // --- Para mostrar pantalla del resultado de la busqueda ---
    private void mostrarVentanaDetalle(Pelicula p) {
        // Creamos el dialog pasándole la vista principal como padre
        DetallesPeliculaVista dialog = new DetallesPeliculaVista(vista, p);
        // Le damos acción al botón CONTINUAR
        dialog.addContinuarListener(e -> {
            dialog.dispose(); // Cerramos el popup
            randomizarCatalogo(); // MEZCLAMOS LA TABLA al hacer click en continuar
        });
        
        dialog.setVisible(true); // Mostramos el popup
    }
    
    // --- LÓGICA DE ORDENAMIENTO ---
    private void ordenarPorTitulo() {
        if (cachePeliculas == null || cachePeliculas.isEmpty()) return;
        // Usamos el Comparator que tenemos para título del proyecto
        Collections.sort(cachePeliculas, new ComparadorPeliculaPorTitulo());
        vista.cargarPeliculas(cachePeliculas, this);
        vista.mostrarMensaje("Ordenado por Título alfabéticamente.");
    }
    
    private void ordenarPorGenero() {
        if (cachePeliculas == null || cachePeliculas.isEmpty()) return;
        // Usamos el Comparator que tenemos para género del proyecto
        Collections.sort(cachePeliculas, new ComparadorPeliculaPorGenero());
        vista.cargarPeliculas(cachePeliculas, this);
        vista.mostrarMensaje("Ordenado por Género.");
    }
    
    // --- ACCIONES DE FILA ---
    private void calificarPelicula(Pelicula p) {
        if (p == null) return;
        String titulo = (p.getMetadatos() != null) ? p.getMetadatos().getTitulo() : "Peli";
        // Por ahora mantenemos el mensaje de prueba ya que todavia no implementamos eso de calificar
        vista.mostrarMensaje("Abrir ventana de CALIFICAR para: " + titulo);
    }
    
    private void cerrarSesion() {
        vista.dispose();
        LoginVista loginVista = new LoginVista();
        new LoginController(loginVista);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { // Manejo de eventos
        String comando = e.getActionCommand(); 
        switch (comando) {
            case "LOGOUT":
            cerrarSesion();
            break;
            case "BUSCAR":
            filtrarCatalogo();
            break;
            case "ORDENAR_TITULO":
            ordenarPorTitulo();
            break;
            case "ORDENAR_GENERO":
            ordenarPorGenero();
            break;
            case "CALIFICAR":
            if (e.getSource() instanceof JButton) {
                JButton btn = (JButton) e.getSource();
                Object obj = btn.getClientProperty("PELICULA_DATA");
                if (obj instanceof Pelicula) {
                    calificarPelicula((Pelicula) obj);
                }
            }
            break;
        }
    }
}