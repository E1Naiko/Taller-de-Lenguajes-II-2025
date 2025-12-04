package taller2.plataformatdl2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.ObjectUtils.Null;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeContenido.ConsultaPeliculasOMDb;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.Usuario;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
import taller2.plataformatdl2.Model.ManejoDeContenido.Resena;
import taller2.plataformatdl2.Model.ManejoDeContenido.ImportarCSV;
import taller2.plataformatdl2.Utilities.ComparadorPeliculaPorGenero;
import taller2.plataformatdl2.Utilities.ComparadorPeliculaPorTitulo;
import taller2.plataformatdl2.view.LoginVista;
import taller2.plataformatdl2.view.MenuPrincipalVista;
import taller2.plataformatdl2.view.DetallesPeliculaVista;
import taller2.plataformatdl2.view.CalificarPeliculaVista;

@SuppressWarnings("unused")
public class MenuPrincipalController implements ActionListener {
    
    private MenuPrincipalVista vista;
    private Usuario usuario;
    private List<Pelicula> cachePeliculas;  
    private List<Pelicula> peliculasVistas;  

    private Set<Integer> peliculasCalificadasEnEstaSesion = new HashSet<>();
    
    
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
                List<Pelicula> listaEntera = ImportarCSV.getPeliculasParseadas(); // Agarramos todas
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
        if (peliculasVistas == null || peliculasVistas.isEmpty()) return;
        // Usamos el Comparator que tenemos para título del proyecto
        Collections.sort(peliculasVistas, new ComparadorPeliculaPorTitulo());
        vista.cargarPeliculas(peliculasVistas, this);
        vista.mostrarMensaje("Ordenado por Título alfabéticamente.");
    }
    
    private void ordenarPorGenero() {
        if (peliculasVistas == null || peliculasVistas.isEmpty()) return;
        // Usamos el Comparator que tenemos para género del proyecto
        Collections.sort(peliculasVistas, new ComparadorPeliculaPorGenero());
        vista.cargarPeliculas(peliculasVistas, this);
        vista.mostrarMensaje("Ordenado por Género.");
    }
    
    // --- ACCIONES DE FILA ---
    private void calificarPelicula(Pelicula p) {
        if (p == null) return;     
        CalificarPeliculaVista dialog = new CalificarPeliculaVista(vista, p.getMetadatos().getTitulo());     
        dialog.addConfirmarListener(e -> {
            try {
                // Obtener datos
                int puntaje = dialog.getPuntajeSeleccionado();
                String comentario = dialog.getTextoResenia();           
                // Crear Reseña
                Resena nuevaResena = new Resena(usuario, p, puntaje, comentario);           
                // Obtener IDs
                int idUsuario = 0;
                int idPelicula = -1;             
                if (usuario instanceof UsuarioFinal) {
                    idUsuario = Factory.getUsuariosFinalDAO().devolverIdUsuarioFinal((UsuarioFinal) usuario);
                }            
                if (Factory.getPeliculasDAO() != null) {
                    idPelicula = Factory.getPeliculasDAO().encontrarIdPelicula(p);
                }             
                if (idPelicula == -1) {
                    vista.mostrarMensaje("Esta peli no tiene ID. No se puede guardar en BD local.");
                    return;
                }
                // Insertar en BD
                if (Factory.getReseniasDAO() != null) {
                    Factory.getReseniasDAO().insertarResenia(idUsuario, idPelicula, nuevaResena, 1);                 
                    // Agregar al set temporal para bloquear el botón YA
                    peliculasCalificadasEnEstaSesion.add(idPelicula);                  
                    dialog.dispose();
                    vista.mostrarMensaje("¡Calificación registrada correctamente!");                   
                    actualizarVistaConListaVisible(); // Refrescar tabla                    
                } else {
                    vista.mostrarMensaje("Error: DAO Reseñas nulo.");
                }               
            } catch (Exception ex) {
                ex.printStackTrace();
                vista.mostrarMensaje("Error al guardar: " + ex.getMessage());
            }
        });      
        dialog.setVisible(true);
    }

    private void actualizarVistaConListaVisible() {
        SwingUtilities.invokeLater(() -> {
            // Le pasamos a la vista una función lambda para decidir si bloquea el botón
            vista.cargarPeliculas(peliculasVistas, this, p -> {
                // 1. Checkeo Temporal en memoria (lo que acabás de votar)
                int idPeli = -1;
                if (Factory.getPeliculasDAO() != null) {
                    idPeli = Factory.getPeliculasDAO().encontrarIdPelicula(p);
                }
                if (peliculasCalificadasEnEstaSesion.contains(idPeli)) {
                    return true; 
                }
                // 2. Checkeo en BD (Cuando tu compañero lo implemente)
                return Factory.getReseniasDAO().existeResenia(usuario.(), idPeli);     
            });
            vista.setCargando(false);
        });
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