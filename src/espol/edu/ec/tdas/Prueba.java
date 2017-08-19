/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tdas;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Prueba {
    public static void main(String[] args) {
        HashMap<String, String> mapa = Util.cargarActores("actores-test.txt");
        HashMap<String, String> mapa2 = Util.cargarActores("peliculas-test.txt");
        HashMap<String, LinkedList<String>> mapa1 = Util.cargarPeliculaActor("pelicula-actores-test.txt");
        System.out.println(mapa);
        System.out.println(mapa2);
        System.out.println(mapa1);
        GrafoLAND<String> grafo = Util.crearGrafo(mapa, mapa2, mapa1);
        System.out.println(grafo);
        System.out.println(grafo.caminoMasCorto("3590", "1"));
        System.out.println(grafo.caminoMasCorto("3", "1"));
        grafo.caminoMasCortoPeliculas("1534", "1");
    }
}
