package taller2.plataformatdl2.controller;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.view.LoginVista;
import taller2.plataformatdl2.view.RegistroVista;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaDB;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaCamposVacios;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaValidacion;

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
        try {
            String user = vista.getUsuario();
            String pass = vista.getContrasena();
            if (user.isEmpty() || pass.isEmpty()) {
                throw new ExcepcionPropiaCamposVacios("Completa los campos, che");
            }
            @SuppressWarnings("unused")
            boolean existe = false;
            try {
                if (Factory.getUsuariosFinalDAO() != null) {
                    System.out.println(user);
                    existe = Factory.getUsuariosFinalDAO().checkUsuarioViaLogin(user, pass);
                } else {
                    // Mensajito por si la DB no levantó 
                    throw new ExcepcionPropiaDB("WARN: DAO nulo, simulando login para pruebas...");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExcepcionPropiaDB("Error conectando a la base: " + e.getMessage());
            }

            // Usamos el método checkUsuarioViaLogin del factory que tenemos
            boolean existeUsuario = Factory.getUsuariosFinalDAO().checkUsuarioViaLogin(user, pass);
            if (existeUsuario) {
                System.out.println("Login exitoso.");
                vista.dispose(); // Cerramos el login
                CargaController carga = new CargaController(user); // Le pasamos el usuario
                carga.iniciarCarga(); 
            } else {
                throw new ExcepcionPropiaDB("Usuario o contraseña incorrectos. Probá de nuevo, pibe.");
            }
        } catch (ExcepcionPropiaDB e) {
            // Aca "atrapamos" los Errores del exepcion de DB
            // Le mostramos al usuario el mensaje limpio que definimos en el throw
            vista.mostrarError(e.getMessage());
        } catch (ExcepcionPropiaCamposVacios e) {
            // Aca la de campos vacios
            vista.mostrarError(e.getMessage());
        } catch (Exception e) {
            // Aca los errores inesperados
            e.printStackTrace();
            vista.mostrarError("Ocurrió un error inesperado: " + e.getMessage());
        }
    }     
    // el metodo para abrir la ventana del registro
    private void abrirVentanaRegistro() {
        RegistroVista vistaReg = new RegistroVista();
        new RegistroController(vistaReg);
        vistaReg.setVisible(true);
    }
}