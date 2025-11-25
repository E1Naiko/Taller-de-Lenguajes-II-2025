package taller2.plataformatdl2.controller;

import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeUsuarios.UsuarioFinal;
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
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String dniStr = vista.getDNI();
        String email = vista.getEmail();
        String pass = vista.getContrasena();
        String passRepeat = vista.getRepetirContrasena();

        // Validaciones básicas (a lo bruto)
        if (nombre.isEmpty() || apellido.isEmpty() || dniStr.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            vista.mostrarError("¡Completá todos los campos, che!");
            return;
        }

        if (!pass.equals(passRepeat)) {
            vista.mostrarError("Las contraseñas no coinciden, máquina.");
            return;
        }

        try {
            int dni = Integer.parseInt(dniStr);
            
            // Creamos el objeto UsuarioFinal
            // OJO: Los campos extra (Idioma, Géneros, etc.) los mandamos por defecto o nulos por ahora
            UsuarioFinal nuevoUser = new UsuarioFinal(
                nombre, 
                apellido, 
                dni, 
                email, 
                pass, 
                "Español", // Idioma default
                new ArrayList<>(), // Géneros vacíos
                "ListaVacia", 
                "HistorialVacio"
            );

            // Guardamos en BD
            Factory.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUser);
            
            vista.mostrarMensaje("¡Usuario registrado exitosamente! Ahora logueate.");
            vista.dispose(); // Cerramos la ventana de registro

        } catch (NumberFormatException ex) {
            vista.mostrarError("El DNI tiene que ser un número, no letras.");
        } catch (Exception ex) {
            vista.mostrarError("Error al guardar en la base: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}