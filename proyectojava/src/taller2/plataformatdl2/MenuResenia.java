package taller2.plataformatdl2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import taller2.DB.DAO.FactoryDAO;
import taller2.plataformatdl2.ManejoDeUsuarios.UsuarioFinal;

public class MenuResenia {
    public MenuResenia(){
    }

    public void cargarUsuarioEnUsuariosFinalDAO(){
        
        UsuarioFinal nuevoUsuario = cargarUsuario();
        FactoryDAO.getUsuariosFinalDAO().insertarUsuarioFinal(nuevoUsuario);
    }

    /** 
     * @return UsuarioFinal
     */
    //Metodo para registrar Usuario
    private static UsuarioFinal cargarUsuario(){
        Scanner scanner= new Scanner(System.in);
        List<String> errores = new ArrayList<>(); //Creo una lista de errores para validar los errores en un solo intento
        // Validar Nombre
        System.out.println("Ingresar Nombre: ");
        String nombre= scanner.nextLine();
        if (nombre.trim().isEmpty()) { // Verifica si el nombre no esta vacio
            errores.add ("El nombre no puede estar vacio");
        } else if (!nombre.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")) {
            errores.add("EL nombre solo puede tener letras y espacios");
        }
        // Validar Email
        System.out.println("Ingresar Email: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            errores.add("El email no puede estar vacio");
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errores.add("El email no cumple con el formato aaa@bbb");
        }
        // Validar Contraseña que no este
        System.out.println("Ingresar Contraseña: ");
        String contrasena = scanner.nextLine();
        if (contrasena.trim().isEmpty()) {
            errores.add("La contraseña no puede estar vacia");
        }
        System.out.println("Ingresar Idioma: ");
        String idioma = scanner.nextLine();
        if (idioma.trim().isEmpty()) {
            errores.add("El idioma no puede estar vacio");
        }
        System.out.println("Ingresar Generos Preferidos: ");
        String generosPreferidos ="Enumerativo"; // TODO Debe ser enumerativo pero por el momento es String
        System.out.println("Ingresar Lista Preferida: ");
        String listaPreferida = "ListaVacia"; //TODO Tambien como en Historial
        System.out.println("Ingresar Historial: ");
        String historial = "ListaVacia"; // TODO Por el momento lo tratamos como string luego cambiamos a lista
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
        System.out.println(nuevoUsuario.toString());
        scanner.close();
        return nuevoUsuario;
    }

}
