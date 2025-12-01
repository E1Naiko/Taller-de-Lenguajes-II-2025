package taller2.plataformatdl2.Utilities;

import java.time.LocalTime;
import java.util.List;
import taller2.DB.DAO.Factory;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class Lista_A_Bd{
    private int DEBUG_LIMITE = 0;
    
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