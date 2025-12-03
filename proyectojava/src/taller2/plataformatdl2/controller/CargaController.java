package taller2.plataformatdl2.controller;

import java.util.List;
import javax.swing.SwingUtilities;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.Usuario;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
import taller2.plataformatdl2.Utilities.ImportarCSV;
import taller2.plataformatdl2.view.CargaVista;
import taller2.plataformatdl2.view.MenuPrincipalVista;

public class CargaController {
    private CargaVista vista;
    private String nombreUsuario; // Es el user/email que viene del Login
    
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
                    // Creamos una instancia anónima o concreta básica para proseguir
                    usuarioCompleto = new Usuario(nombreUsuario, "Temporal", 0, nombreUsuario + "@temp.com", "1234") {};
                }
                final Usuario userFinal = usuarioCompleto;
                
                
                ImportarCSV carga = new ImportarCSV();
                new Thread(carga).start();
                
                
                // --- TRANSICIÓN AL MENÚ ---
                SwingUtilities.invokeLater(() -> {
                    vista.dispose(); // Chau perrito   
                    MenuPrincipalVista menuVista = new MenuPrincipalVista();
                    new MenuPrincipalController(userFinal, menuVista);
                });
            } catch (Exception e) {
                e.printStackTrace();
                // Si explota, cerramos la vista para no dejar al user colgado
                SwingUtilities.invokeLater(() -> vista.dispose());
            }
        });
        worker.start();
    }
}