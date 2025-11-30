package taller2.plataformatdl2.Utilities;

import taller2.DB.DAO.Factory;
import java.time.LocalTime;
import java.util.List;

import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class Lista_A_Bd extends Thread{
    private int DEBUG_LIMITE = 0;
    
    public void pasarListaPeliculas_a_BD(List<Pelicula> listaPeliculas){
        int ini, fin;
        ini = LocalTime.now().toSecondOfDay();
        int i = 0;
        
        if (!listaPeliculas.isEmpty() && Factory.getPeliculasDAO()!=null){
            start();
            while (isAlive()) {
                
                for (Pelicula peli: listaPeliculas){
                    try {
                        join();
                        i++;
                        if (!Factory.getPeliculasDAO().existePelicula(peli) || !Factory.getMetadatosDAO().existeMetadatos(peli.getMetadatos())){
                            Factory.getPeliculasDAO().insertarPeliculas(peli);
                        }
                        if (DEBUG_LIMITE!= 0 && i>=DEBUG_LIMITE) break;
                        yield();
                    } catch(InterruptedException e) {
                        System.out.println(getName() + "join interrupido");                        
                    }
                }
            }
            fin = LocalTime.now().toSecondOfDay();
            System.out.println("Factory - pasarListaPeliculas_a_BD - Terminado en " + (fin-ini) + " segundos");
        }
    }
}