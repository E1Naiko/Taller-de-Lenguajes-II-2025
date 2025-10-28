package taller2.plataformatdl2.Model.ManejoDeUsuarios;

/**
* Clase que representa un usuario de la plataforma de streaming.   
* Contiene atributos como nombre, email y lista de contenidos vistos.
* 
* @author Alam Meza y Nicolas Peñalba
* @version 1.0 - 2025-09-15
*/

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected int DNI;
    protected String email;
    protected String contrasena;
    
    /**
    * Constructor de la clase Usuario.
    * 
    * @param nombre     El nombre del usuario.
    * @param apellido   El apellido del usuario.
    * @param email      El email del usuario.
    * @param contrasena La contraseña del usuario.
    */
    
    public Usuario(String nombre, String apellido, int DNI, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.email = email;
        this.contrasena = contrasena;
    }
    
    /** 
    * @return String
    */
    // --- Getters y Setters ---
    
    public String getNombre() {
        return nombre;
    }
    
    /** 
    * @param nombre
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /** 
    * @return String
    */
    // --- Getters y Setters ---
    
    public String getApellido() {
        return apellido;
    }
    
    /** 
    * @param apellido
    */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    /** 
    * @return int
    */
    // --- Getters y Setters ---
    
    public int getDNI() {
        return DNI;
    }
    
    /** 
    * @param DNI
    */
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }
    
    /** 
    * @return String
    */
    public String getEmail() {
        return email;
    }
    
    /** 
    * @param email
    */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /** 
    * @return String
    */
    public String getContrasena() {
        return contrasena;
    }
    
    /** 
    * @param contrasena
    */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    /** 
    * @return boolean
    */
    // --- Metodos especificos ---
    
    public boolean login() {
        // Logica de login..
        return true;
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Usuario{" +
        "nombre='" + nombre + '\'' +
        ", apellido='" + apellido + '\'' +
        ", DNI=" + DNI +
        ", email='" + email + '\'' +
        '}';
    }
    
}
