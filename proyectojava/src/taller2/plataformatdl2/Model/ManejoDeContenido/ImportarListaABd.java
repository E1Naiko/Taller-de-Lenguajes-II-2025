package taller2.plataformatdl2.Model.ManejoDeContenido;

import java.time.LocalTime;
import java.util.List;
import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.controller.MenuPrincipalController;

public class ImportarListaABd implements Runnable{
    private int DEBUG_LIMITE = 0; // TODO - Esto tiene que estar en 0 para cargar el csv completo
    private MenuPrincipalController menuPrincipal;
    private List<Pelicula> peliculasParseadas;
    
    public ImportarListaABd(MenuPrincipalController menuPrincipal, List<Pelicula> peliculasParseadas){
        this.menuPrincipal = menuPrincipal;
        this.peliculasParseadas = peliculasParseadas;
    }
    
    public void run(){
        System.out.println("ImportarListaABd - Pasando lista a BD");
        menuPrincipal.actualizarCargando(true);
        Factory.getPeliculasDAO().setImprimirDebug(false);
        Factory.getMetadatosDAO().setImprimirDebug(false);
        pasarListaPeliculas_a_BD(peliculasParseadas);
        Factory.getPeliculasDAO().setImprimirDebug(true);
        Factory.getMetadatosDAO().setImprimirDebug(true);
        menuPrincipal.actualizarCargando(false);
        
        System.out.println("ImportarListaABd - Carga terminada");
    }
    
    public void pasarListaPeliculas_a_BD(List<Pelicula> listaPeliculas){
        int ini, fin;
        ini = LocalTime.now().toSecondOfDay();
        int i = 0;
        
        if (!listaPeliculas.isEmpty() && Factory.getPeliculasDAO()!=null){
            
            for (Pelicula peli: listaPeliculas){
                try {
                    i++;
                    if (!Factory.getPeliculasDAO().existePelicula(peli) || !Factory.getMetadatosDAO().existeMetadatos(peli.getMetadatos())){
                        Factory.getPeliculasDAO().insertarPeliculas(peli);
                    }
                    if (DEBUG_LIMITE!= 0 && i>=DEBUG_LIMITE) break;
                }
                catch (Exception e) {
                    System.err.println("Error procesando lista");
                    System.err.println(e);
                }
            }
            fin = LocalTime.now().toSecondOfDay();
            System.out.println("Factory - pasarListaPeliculas_a_BD - Terminado en " + (fin-ini) + " segundos");
        }
    }
}