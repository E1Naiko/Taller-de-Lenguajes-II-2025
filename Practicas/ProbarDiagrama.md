```mermaid
classDiagram
    class Personaje {
        +String nombre
        +int nivel
        +int experiencia
        +Atributo[] atributos
        +Clase clase
        +Raza raza
        +Habilidad[] habilidades
        +Hechizo[] hechizos
        +Inventario inventario
        +void subirNivel()
        +void lanzarHechizo(Hechizo h)
    }

    class Atributo {
        +String nombre
        +int valor
        +int modificador()
    }

    class Clase {
        +String nombre
        +String dadoVida
        +Habilidad[] habilidadesClase
    }

    class Raza {
        +String nombre
        +String[] bonificaciones
        +String[] rasgos
    }

    class Habilidad {
        +String nombre
        +String descripcion
        +int modificador
    }

    class Hechizo {
        +String nombre
        +int nivel
        +String escuela
        +String descripcion
    }

    class Inventario {
        +Equipo[] equipo
        +int capacidadMax
        +void agregarItem(Equipo e)
    }

    class Equipo {
        +String nombre
        +double peso
        +String tipo
    }

    Personaje --> Atributo : tiene >
    Personaje --> Clase : pertenece a >
    Personaje --> Raza : es de >
    Personaje --> Habilidad : posee >
    Personaje --> Hechizo : conoce >
    Personaje --> Inventario : tiene >
    Inventario --> Equipo : contiene >
    Clase --> Habilidad : otorga >
    Raza --> Habilidad : concede >
