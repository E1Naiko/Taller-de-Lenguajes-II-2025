package taller2.plataformatdl2;

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

        System.out.print("Ingrese nombre: ");
        String nombre= scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese idioma: ");
        String idioma = scanner.nextLine();
        System.out.print("Ingrese géneros preferidos (separados por comas): ");
        String generosPreferidos =scanner.nextLine();
        System.out.print("Ingrese lista preferida (opcional): ");
        String listaPreferida = scanner.nextLine();
        System.out.print("Ingrese historial (opcional): ");
        String historial = scanner.nextLine();
        UsuarioFinal nuevoUsuario = new UsuarioFinal(nombre,email,contrasena,idioma,generosPreferidos,listaPreferida,historial);
        System.out.println(nuevoUsuario.toString());
        
        scanner.close();
        return nuevoUsuario;
    }

}
