/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tdas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Util {
    
    public static HashMap<String, String> cargarActores(String ruta)
    {
        HashMap<String,String> actores =  new HashMap<>();
        try {
            Scanner sc = new Scanner(new File(ruta));
            while(sc.hasNext())
            {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                String codigo = arreglo[0];
                String nombre = arreglo[1];
                actores.put(codigo,nombre);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        }
        return actores;
    }
    
    public static HashMap<String, String> cargarPeliculas(String ruta)
    {
        HashMap<String,String> peliculas =  new HashMap<>();
        try {
            Scanner sc = new Scanner(new File(ruta));
            while(sc.hasNext())
            {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                String codigo = arreglo[0];
                String nombre = arreglo[1];
                peliculas.put(codigo,nombre);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        }
        return peliculas;
    }
    
    public static HashMap<String, LinkedList<String>> cargarPeliculaActor(String ruta)
    {
        HashMap<String,LinkedList<String>> pelicula =  new HashMap<>();
        try {
            Scanner sc = new Scanner(new File(ruta));
            while(sc.hasNext())
            {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                String codigo = arreglo[0];
                String nombre = arreglo[1];
                if(pelicula.keySet().contains(codigo))
                {
                    pelicula.get(codigo).add(nombre);
                }
                else
                {
                    LinkedList<String> lista = new LinkedList<>();
                    lista.add(nombre);
                    pelicula.put(codigo, lista);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        }
        return pelicula;
    }
 
    public static GrafoLAND<String> crearGrafo(HashMap<String, String> actores, 
            HashMap<String, String> peliculas, HashMap<String, LinkedList<String>> infoPeliculaActor)
    {
        GrafoLAND<String> grafo = new GrafoLAND<>();
        for(String actor:actores.keySet())
        {
            grafo.agregarVertice(actor);
        }
        for(String pelicula: infoPeliculaActor.keySet())
        {
            LinkedList<String> listaActor = infoPeliculaActor.get(pelicula);
            for(String actor1:listaActor)
            {
                for(String actor2:listaActor)
                {
                    if(!actor1.equals(actor2))
                    {
                        grafo.agregarArco(actor1, actor2, 1, peliculas.get(pelicula));
                    }
                }
            }
        }
        return grafo;
    }
}
