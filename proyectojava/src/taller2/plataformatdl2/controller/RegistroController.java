package taller2.plataformatdl2.controller;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaDB;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaCamposVacios;
import taller2.plataformatdl2.excepciones.ExcepcionPropiaValidacion;
import taller2.plataformatdl2.view.RegistroVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Para listas vacías

public class RegistroController {
    private RegistroVista vista;
    
    public RegistroController(RegistroVista vista) {
        this.vista = vista;
        
        // Listener del botón Registrarse
        this.vista.addRegistrarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        
        // Listener del botón Volver
        this.vista.addVolverListener(e -> vista.dispose());
    }
    
    private void registrarUsuario() {
        try {
            String apellido = vista.getApellido();
            String nombre = vista.getNombre();
            String email = vista.getEmail();
            String dni = vista.getDNI();
            String contraRepetir = vista.getRepetirContrasena();
            String contrasena = vista.getContrasena();
            // --- Validaciones básicas ---
            // Validar campos no null
            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty() || contraRepetir.isEmpty()) {
                throw new ExcepcionPropiaCamposVacios("¡Hay campos en blanco, completar!");
            }
            // Validar contraseña
            if (!contrasena.equals(contraRepetir)) {
                throw new ExcepcionPropiaValidacion("Las contraseñas no coinciden");
            }
            // Validar formato del correo
            if (!email.contains("@gmail.com") && !email.contains("@hotmail.com") && !email.contains("@yahoo.com") && !email.contains("@outlook.com")){
                throw new ExcepcionPropiaValidacion("Error: email no valido");
            }
            // Validar DNI
            int dni2;
            try {
                dni2 = Integer.parseInt(dni);
            } catch (NumberFormatException ex) {
                throw new ExcepcionPropiaValidacion("El DNI tiene que ser un número.");
            }
            
            // Validar Conexión DAO
            if (Factory.getUsuariosFinalDAO() == null) {
                throw new ExcepcionPropiaDB("Error: No hay conexión con la base de datos.");
            }

            // Validar Si existe DNI
            if(Factory.getUsuariosFinalDAO().checkUsuarioViaDNI(dni2)){
                throw new ExcepcionPropiaValidacion("El DNI ya existe.");
            }
        
            // Creamos el objeto UsuarioFinal
            // OJO: Los campos del UML del usuario (Idioma, Géneros, etc.) los mandamos por defecto o nulos por ahora
            UsuarioFinal nuevoUser = new UsuarioFinal(
                nombre, 
                apellido, 
                dni2, 
                email, 
                contrasena, 
                "Español", // Idioma por defecto //TODO Por el momento el idioma por defecto
                new ArrayList<>(), // Géneros lo ponemos vacíos
                "ListaVacia", 
                "HistorialVacio"
            );
            // Validamos si ya existe el usuario
            if (Factory.getUsuariosFinalDAO().existeUsuario(nuevoUser)){
                throw new ExcepcionPropiaValidacion("ERROR: Usuario ya existe.");
            }
            else {
                // Guardamos en BD
                Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUser);
                vista.mostrarMensaje("¡Usuario registrado exitosamente!");
                vista.dispose(); // Cerramos la ventana de registro
            }
            
        } catch (ExcepcionPropiaCamposVacios e) {
            // Atrapamos las excepciones campos vacios
            vista.mostrarError(e.getMessage()); 
        } catch (ExcepcionPropiaDB e) {
            // Atrapamos las excepciones de base de datos
            vista.mostrarError(e.getMessage()); 
        } catch (ExcepcionPropiaValidacion e) {
            // Atrapamos las excepciones de validacion
            vista.mostrarError(e.getMessage()); 
        } catch (Exception e) {
            // ERRORES NO controlados
            e.printStackTrace();
            vista.mostrarError("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}