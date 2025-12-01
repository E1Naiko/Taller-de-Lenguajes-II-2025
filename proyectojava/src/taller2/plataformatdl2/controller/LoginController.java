package taller2.plataformatdl2.controller;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.view.LoginVista;
import taller2.plataformatdl2.view.RegistroVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginVista vista;

    public LoginController(LoginVista vista) {
        this.vista = vista;
        
        // Le decimos al botón de login qué hacer
        this.vista.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loguearUsuario();
            }
        });  
        // Acá iría el listener para ir al Registro que todavia falta implementar //Ya se implemento xdxd
        this.vista.addRegistroListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            abrirVentanaRegistro();
        }
    });
        this.vista.setVisible(true);
    }

    private void loguearUsuario() {
        String user = vista.getUsuario();
        String pass = vista.getContrasena();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarError("¡Completá los campos, pibe!");
            return;
        }

        @SuppressWarnings("unused")
        boolean existe = false;
        try {
            if (Factory.getUsuariosFinalDAO() != null) {
                System.out.println(user);
                existe = Factory.getUsuariosFinalDAO().checkUsuarioViaLogin(user, pass);
            } else {
                // Mensajito por si la DB no levantó 
                System.err.println("WARN: DAO nulo, simulando login para pruebas...");
                existe = true; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            vista.mostrarError("Error conectando a la base: " + e.getMessage());
            return;
        }

        // Usamos el método checkUsuarioViaLogin del factory que tenemos
        boolean existeUsuario = Factory.getUsuariosFinalDAO().checkUsuarioViaLogin(user, pass);

        if (existeUsuario) {
            System.out.println("Login exitoso.");
            vista.dispose(); // Cerramos el login
            CargaController carga = new CargaController(user); // Le pasamos el usuario
            carga.iniciarCarga();
            
        } else {
            vista.mostrarError("Usuario o contraseña incorrectos. Probá de nuevo, pibe.");
        }
    }
    
    private void abrirVentanaRegistro() {
        RegistroVista vistaReg = new RegistroVista();
        new RegistroController(vistaReg);
        vistaReg.setVisible(true);
    }
}