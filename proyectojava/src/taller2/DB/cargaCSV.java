package taller2.DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import taller2.plataformatdl2.Model.ManejoDeContenido.Metadatos;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class cargaCSV{
    private static final String SEPARADOR_COMA = ",";
    private static final String SEPARADOR_COMILLA = ",";
    private List<Pelicula> lista;
    
    public cargaCSV(String dir){
        
    }
    private List<Pelicula> importarCSVaPeliculasDAO(String dir) {
        List<Pelicula> lista = new ArrayList<Pelicula>();
        String linea = "";
        try (BufferedReader br = new BufferedReader(new
            FileReader(dir))){
                
                while ((linea = br.readLine()) != null) {
                    Metadatos metadatos = null;
                    Pelicula peli = null;

                    lista.add(peli);
                }
                
            return lista;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }
    