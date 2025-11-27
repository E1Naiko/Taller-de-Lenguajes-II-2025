package taller2.DB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class importarCSVaPeliculasDAO {
    private static final String SEPARADOR = ",";

    public  importarCSVaPeliculasDAO(String dir) {
        String linea = "";
        try (BufferedReader br = new BufferedReader(new
            FileReader(dir))){

                while ((linea = br.readLine()) != null) {
                    String[] fields = linea.split(SEPARADOR);
                    System.out.println(Arrays.toString(fields));
                    System.out.println("-------");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    