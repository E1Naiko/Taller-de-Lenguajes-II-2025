```mermaid
sequenceDiagram
    actor Persona
    participant Administrador
    participant Pelicula
    participant PlataformaTDL2
    
    Persona->>Administrador: 1. gestionarAltaDePelicula(datos)
    activate Administrador
    Administrador->>Pelicula: 2. <<create>> new Pelicula(datos)
    
    Administrador->>PlataformaTDL2: 3. agregarContenido(nuevaPelicula)
    activate PlataformaTDL2
    
    Note right of PlataformaTDL2: La plataforma añade la película <br> a su lista interna "catalogo".
    
    PlataformaTDL2-->>Administrador: 4. (retorno)
    deactivate PlataformaTDL2
    Administrador-->>Persona: 5. exito
    deactivate Administrador