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
import taller2.plataformatdl2.Model.ManejoDeContenido.ImportarCSVaLista;
import taller2.plataformatdl2.Model.ManejoDeContenido.ImportarListaABd;
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
    private int cargasActuales = 0;
    
    public MenuPrincipalController(Usuario usuario, MenuPrincipalVista vista, List<Pelicula> cachPeliculas) { 
        this.usuario = usuario;
        this.vista = vista;
        this.cachePeliculas = cachPeliculas;
        inicializar(); 
    }
    
    private void inicializar() {
        if (usuario != null) { // Para mostrar el nombre en la vista
            vista.setNombreUsuario(usuario.getNombre());
        }    
        vista.addListeners(this);
        actualizarCatalogo();
        vista.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            ImportarListaABd cargaLista = new ImportarListaABd(this, cachePeliculas);
            new Thread(cargaLista).start();
        });
    }
    
    private void actualizarCatalogo() {
        vista.setCargando(true);
        Thread worker = new Thread(() -> { // Para que Cargue las peliculas en segundo plano
            try {
                try {
                    if (cachePeliculas != null) {
                        peliculasVistas = cachePeliculas.stream()
                        .filter(p -> p!= null)
                        .limit(10)
                        .collect(Collectors.toList());
                    } else {
                        peliculasVistas = new ArrayList<>();
                    }
                    SwingUtilities.invokeLater(() -> {
                        actualizarVistaConListaVisible();
                        actualizarCargando(false);
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
                SwingUtilities.invokeLater(() -> actualizarCargando(false));
            }
        });      
        worker.start();
    }
    
    // Metodo para randomizar las peliculas la hacer click en continuar
    private void randomizarCatalogo() {
        actualizarCargando(true);
        Thread worker = new Thread(() -> {
            try {
                if (cachePeliculas != null) {
                    Collections.shuffle(cachePeliculas); // Se mezcla                 
                    // Agarramos 10 randoms
                    peliculasVistas = cachePeliculas.stream().filter(p->p!=null).limit(10).collect(Collectors.toList());                 
                    SwingUtilities.invokeLater(() -> {
                        actualizarVistaConListaVisible();
                        actualizarCargando(false);
                        vista.mostrarMensaje("¡Catálogo randomizado!");
                    });
                }
            } catch (Exception e) { e.printStackTrace(); }
        });
        worker.start();
    }
    
    // Metodo de la busqueda de peliculas
    private void filtrarCatalogo() {
        boolean auxPerrito = true;
        String termino = vista.getTextoBusqueda().toLowerCase().trim();
        if (termino.isEmpty()){
            vista.mostrarMensaje("Escribir algo para buscar...");
        }
        actualizarCargando(true); // Aca ponemos el perrito mientra busca la pelicula
        Thread apiWorker = new Thread(() -> {
            try {
                // Buscamos en la API
                ConsultaPeliculasOMDb coneccionApi = new ConsultaPeliculasOMDb();
                Pelicula peliApi = coneccionApi.buscarPeliculaApi(termino);
                
                if (peliApi != null) {
                    // ABRIMOS LA VENTANA NUEVA (JDialog)
                    mostrarVentanaDetalle(peliApi);
                } else {
                    actualizarCargando(false); // Apagamos el perrito
                    
                    
                    vista.mostrarMensaje("No encontré esa peli ni abajo de las piedras.");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    actualizarCargando(false);
                    vista.mostrarMensaje("Error de conexión con la API.");
                });
            }
        });
        apiWorker.start();
        if (auxPerrito) actualizarCargando(false); // Apagamos el perrito
        
    }
    
    // --- Para mostrar pantalla del resultado de la busqueda ---
    private void mostrarVentanaDetalle(Pelicula p) {
        actualizarCargando(false); // Apagamos el perrito
        
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
        Collections.sort(peliculasVistas, new ComparadorPeliculaPorTitulo());
        actualizarVistaConListaVisible();
    }
    
    private void ordenarPorGenero() {
        if (peliculasVistas == null || peliculasVistas.isEmpty()) return;
        Collections.sort(peliculasVistas, new ComparadorPeliculaPorGenero());
        actualizarVistaConListaVisible();
    }
    
    private void actualizarVistaConListaVisible() {
        SwingUtilities.invokeLater(() -> {
            vista.cargarPeliculas(peliculasVistas, this, p -> {          
                // LÓGICA DE CHECKEO EN BD
                if (Factory.getReseniasDAO() != null && usuario instanceof UsuarioFinal) {
                    try {
                        
                        return Factory.getReseniasDAO().reseniaExiste((UsuarioFinal) usuario, p);
                    } catch (Exception e) {
                        System.err.println("Error verificando reseña: " + e.getMessage());
                        return false;
                    }
                }
                return false; 
            });
            actualizarCargando(false);
        });
    }
    
    // --- ACCIONES DE FILA ---
    private void calificarPelicula(Pelicula p) {
        if (p == null) return;
        
        CalificarPeliculaVista dialog = new CalificarPeliculaVista(vista, p.getMetadatos().getTitulo());
        
        dialog.addConfirmarListener(e -> {
            try {
                // 1. Obtener datos
                int puntaje = dialog.getPuntajeSeleccionado();
                String comentario = dialog.getTextoResenia();           
                // 2. Crear Reseña
                Resena nuevaResena = new Resena(usuario, p, puntaje, comentario);              
                // 3. Obtener IDs para insertar
                int idUsuario = 0;
                int idPelicula = -1;               
                if (usuario instanceof UsuarioFinal) {
                    idUsuario = Factory.getUsuariosFinalDAO().devolverIdUsuarioFinal((UsuarioFinal) usuario);
                }
                if (Factory.getPeliculasDAO() != null) {
                    idPelicula = Factory.getPeliculasDAO().encontrarIdPelicula(p);
                }               
                if (idPelicula == -1) {
                    vista.mostrarMensaje("Esta peli no tiene ID (capaz vino de la API). No se puede guardar en BD local.");
                    return;
                }
                // 4. Insertar en BD
                if (Factory.getReseniasDAO() != null) {
                    // insertarResenia(idUsuario, idPelicula, resena, aprobado)
                    Factory.getReseniasDAO().insertarResenia(idUsuario, idPelicula, nuevaResena, 1);                 
                    dialog.dispose();
                    vista.mostrarMensaje("¡Calificación registrada correctamente!");                    
                    actualizarVistaConListaVisible(); // Refrescar tabla para bloquear el botón                  
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
    
    public void actualizarCargando(boolean in){
        if (in && cargasActuales==0){
            cargasActuales++;
            vista.setCargando(true);
            vista.setEstadoBotones(false);
        }
        else{
            if (cargasActuales>0){
                cargasActuales--;
            }
            else{
                vista.setCargando(false);
                vista.setEstadoBotones(true);
            }
        }
    }
}