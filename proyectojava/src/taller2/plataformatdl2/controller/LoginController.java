package taller2.plataformatdl2.controller;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.view.LoginVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginVista vista;

    public LoginController(LoginVista vista) {
        this.vista = vista;
        
        // Le decimos al botón qué hacer
        this.vista.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loguearUsuario();
            }
        });
        
        // Acá iría el listener para ir al Registro que todavia falta implementar
    }

    private void loguearUsuario() {
        String user = vista.getUsuario();
        String pass = vista.getContrasena();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarError("¡Completá los campos, concha!");
            return;
        }

        // Usamos el método checkUsuarioViaLogin del factory que tenemos
        boolean existe = Factory.getUsuariosFinalDAO().checkUsuarioViaLogin(user, pass);

        if (existe) {
            System.out.println("Login exitoso. Abriendo Home...");
            vista.dispose(); // Cerramos el login
        } else {
            vista.mostrarError("Usuario o contraseña incorrectos. Probá de nuevo, pibe.");
        }
    }
}