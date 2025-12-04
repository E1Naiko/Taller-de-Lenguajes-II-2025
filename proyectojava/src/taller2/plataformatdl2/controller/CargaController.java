package taller2.plataformatdl2.controller;

import java.util.List;
import javax.swing.SwingUtilities;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.Usuario;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
import taller2.plataformatdl2.Model.ManejoDeContenido.ImportarCSVaLista;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;
import taller2.plataformatdl2.view.CargaVista;
import taller2.plataformatdl2.view.MenuPrincipalVista;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaDB;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaCamposVacios;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaValidacion;

public class CargaController {
    private CargaVista vista;
    private String nombreUsuario; // Es el user/email que viene del Login
    private Usuario userFinal;
    private ImportarCSVaLista cargaLista;
    private List<Pelicula> lista = null;
    
    public CargaController(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.vista = new CargaVista();
    }
    
    public void iniciarCarga() {
        vista.setVisible(true);
        Thread worker = new Thread(() -> {
            try {
                System.out.println("CARGA - Inicializando Factory...");
                System.out.println("CARGA - Buscando datos del usuario: " + nombreUsuario);
                // Simulamos un cachito extra para que se luzca el GIF del perrito
                Thread.sleep(3500);
                Usuario usuarioCompleto = null;
                // --- BÚSQUEDA EN BASE DE DATOS ---
                if (Factory.getUsuariosFinalDAO() != null) {
                    List<UsuarioFinal> listaUsuarios = Factory.getUsuariosFinalDAO().obtenerUsuarios();
                    if (listaUsuarios != null) {
                        for (UsuarioFinal u : listaUsuarios) {
                            // Comparamos ignorando mayúsculas/minúsculas para evitar quilombos
                            if (u.getEmail().trim().equalsIgnoreCase(nombreUsuario.trim())) {
                                usuarioCompleto = u;
                                System.out.println("CARGA - ¡Usuario encontrado en la BD! ID: " + Factory.getUsuariosFinalDAO().devolverIdUsuarioFinal(u));
                                break;
                            }
                        }
                    }
                }
                // Fallback por si no lo encontramos (para que no explote la app)
                if (usuarioCompleto == null) {
                    System.err.println("CARGA - No se encontró el usuario real. Usando temporal.");
                    // Creamos una instancia anónima para proseguir
                    usuarioCompleto = new Usuario(nombreUsuario, "Temporal", 0, nombreUsuario + "@temp.com", "1234") {};
                }
                userFinal = usuarioCompleto;
                // Espera a que se termine la carga de peliculas en memoria para pasar a siguiente pantalla
                SwingUtilities.invokeLater(() -> {
                    cargaLista = new ImportarCSVaLista(this);
                    lista = cargaLista.getPeliculasParseadas();
                    new Thread(cargaLista).start(); // Cuando termine, llamará a onCSVTerminado()
                });             
            } catch (Exception e) {
                e.printStackTrace();
             // Si explota, cerramos la vista para no dejar al user colgado
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        vista.dispose();
                    }
                });
            }
        });
        worker.start();
    }
    
    
    public void cargaCSVTerminada() {
        System.out.println("CargaController - Importación CSV terminada.");
        vista.dispose();
        
        SwingUtilities.invokeLater(() -> {
            // Aquí poné lo que querías ejecutar luego de la línea 57
            MenuPrincipalVista menuVista = new MenuPrincipalVista();
            
            new MenuPrincipalController(userFinal, menuVista, lista);
        });
    }
}