/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tdas;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Administrador
 * @param <E>
 */
public class Vertice<E> {
    private E element;
    private List<Arco> arcos;
    private boolean visited;
    private int distancia;
    private Vertice<E> anterior;
    private String pelicula;

    public Vertice(E element) {
        this.element = element;
        this.arcos = new LinkedList<>();
        this.visited = false;
        this.distancia = Integer.MAX_VALUE;
        this.pelicula = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public List<Arco> getArcos() {
        return arcos;
    }

    public void setArcos(List<Arco> arcos) {
        this.arcos = arcos;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null)
        {
            return false;
        }
        Vertice<E> v = (Vertice)obj;
        return this.getElement().equals(v.getElement());
    }

    @Override
    public String toString() {
        return element.toString();
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertice<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(Vertice<E> anterior) {
        this.anterior = anterior;
    }
    
    
    
    
}
