/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tdas;

import java.util.Objects;

/**
 *
 * @author Administrador
 * @param <E>
 */
public class Arco<E> {

    int peso;
    private Vertice<E> origen;
    private Vertice<E> destino;
    private String pelicula;

    public Arco(Vertice<E> origen, Vertice<E> destino, int peso, String pelicula) {
        this.peso = peso;
        this.origen = origen;
        this.destino = destino;
        this.pelicula = pelicula;
    }

    public Arco(Vertice<E> origen, Vertice<E> destino) {
        this.peso = 0;
        this.origen = origen;
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice<E> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice<E> origen) {
        this.origen = origen;
    }

    public Vertice<E> getDestino() {
        return destino;
    }

    public void setDestino(Vertice<E> destino) {
        this.destino = destino;
    }

    public boolean contains(Vertice<E> vertice) {
        if (vertice == null) {
            return false;
        }
        return this.origen.equals(vertice) || this.destino.equals(vertice);
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Arco<E> arco = (Arco) obj;
        return this.getOrigen().equals(arco.getOrigen()) && this.getDestino().equals(arco.getDestino());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.origen);
        hash = 71 * hash + Objects.hashCode(this.destino);
        return hash;
    }

    @Override
    public String toString() {
        return "(" + origen + ", " + destino + ')';
    }

}
