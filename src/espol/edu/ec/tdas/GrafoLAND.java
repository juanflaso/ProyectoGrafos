/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tdas;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author User
 * @param <E>
 */
public class GrafoLAND<E> {

    private List<Vertice> vertices;

    public GrafoLAND() {
        this.vertices = new LinkedList<>();
    }

    public boolean agregarVertice(E element) {
        Vertice<E> v = new Vertice<>(element);
        if (element == null || vertices.contains(v)) {
            return false;
        } else {
            vertices.add(v);
            return true;
        }
    }

    public boolean agregarArco(E origen, E destino, int peso, String pelicula) {
        Vertice<E> verticeOrigen = getVertice(origen);
        Vertice<E> verticeDestino = getVertice(destino);
        if (verticeOrigen != null && verticeDestino != null) {
            Arco<E> a1 = new Arco<>(verticeOrigen, verticeDestino, peso, pelicula);
            Arco<E> a2 = new Arco<>(verticeDestino, verticeOrigen, peso, pelicula);
            if (!verticeOrigen.getArcos().contains(a1)) {
                verticeOrigen.getArcos().add(a1);
                verticeDestino.getArcos().add(a2);
                return true;
            }
        }
        return false;
    }

    boolean esAdyacente(E element1, E element2) {
        Vertice<E> vertice1 = getVertice(element1);
        Vertice<E> vertice2 = getVertice(element2);
        if (vertice1 != null && vertice2 != null) {
            for (Arco<E> arco : vertice1.getArcos()) {
                if (arco.getOrigen().equals(vertice1) && arco.getDestino().equals(vertice2)
                        || arco.getOrigen().equals(vertice2) && arco.getDestino().equals(vertice1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int gradoEntrada(E element) {
        Vertice<E> vertice = getVertice(element);
        int cont = 0;
        if (vertice != null) {
            for (Vertice<E> v : vertices) {
                for (Arco<E> arco : v.getArcos()) {
                    if (arco.getDestino().equals(vertice)) {
                        cont += 1;
                    }
                }
            }
            return cont;
        }
        return -1;
    }

    public int gradoSalida(E element) {
        Vertice<E> vertice = getVertice(element);
        if (vertice != null) {
            return vertice.getArcos().size();
        }
        return -1;
    }

    public boolean eliminarArco(E elementoOrigen, E elementoDestino) {
        Vertice<E> verticeOrigen = getVertice(elementoOrigen);
        Vertice<E> verticeDestino = getVertice(elementoDestino);
        Arco<E> arco1 = new Arco<>(verticeOrigen, verticeDestino);
        Arco<E> arco2 = new Arco<>(verticeDestino, verticeOrigen);
        if (verticeDestino != null && verticeOrigen != null) {
            for (Iterator<Arco> it = verticeOrigen.getArcos().iterator(); it.hasNext();) {
                Arco<E> a = it.next();
                if (a.equals(arco1)) {
                    it.remove();
                }
            }
            for (Iterator<Arco> it = verticeDestino.getArcos().iterator(); it.hasNext();) {
                Arco<E> a = it.next();
                if (a.equals(arco2)) {
                    it.remove();
                }
            }
            return true;
        }

        return false;
    }

    public boolean eliminarVertice(E elemento) {
        Vertice<E> verticeAEliminar = getVertice(elemento);
        if (verticeAEliminar != null) {
            for (Iterator<Vertice> it = vertices.iterator(); it.hasNext();) {
                Vertice<E> vertice = it.next();
                if (verticeAEliminar.equals(vertice)) {
                    it.remove();
                } else {
                    for (Iterator<Arco> j = vertice.getArcos().iterator(); j.hasNext();) {
                        Arco<E> arco = j.next();

                        if (arco.contains(verticeAEliminar)) {
                            j.remove();
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    private Vertice<E> getVertice(E element) {
        Vertice<E> v = new Vertice<>(element);
        for (Vertice<E> vertice : this.vertices) {
            if (vertice.equals(v)) {
                return vertice;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "[";
        String a = "[";
        for (Vertice<E> vertice : vertices) {
            s += vertice + ", ";
            for (Arco<E> arco : vertice.getArcos()) {
                a += arco + ", ";
            }
        }
        if (s.length() > 2) {
            s = s.substring(0, s.length() - 2);
        }
        if (a.length() > 2) {
            a = a.substring(0, a.length() - 2);
        }
        s = "Vertices:" + s + "]\nArcos:" + a + "]";

        return s;
    }

    public LinkedList<E> bfs(E element) {
        LinkedList<E> lista = new LinkedList<>();
        Queue<Vertice> cola = new LinkedList<>();
        Vertice<E> vertice = getVertice(element);
        cola.offer(vertice);
        if (vertice != null) {
            vertice.setVisited(true);
        }
        while (vertice != null && !cola.isEmpty()) {
            Vertice<E> temp = cola.poll();
            lista.add(temp.getElement());
            for (Arco<E> arco : temp.getArcos()) {
                if (!arco.getDestino().isVisited()) {
                    cola.offer(arco.getDestino());
                    arco.getDestino().setVisited(true);
                }
            }
        }
        this.ponerComoNoVisitados();
        return lista;
    }

    public LinkedList<E> dfs(E element) {
        LinkedList<E> lista = new LinkedList<>();
        Deque<Vertice> deque = new LinkedList<>();
        Vertice<E> vertice = getVertice(element);
        deque.push(vertice);
        if (vertice != null) {
            vertice.setVisited(true);
        }
        while (vertice != null && !deque.isEmpty()) {
            Vertice<E> temp = deque.pop();
            lista.add(temp.getElement());
            for (Arco<E> arco : temp.getArcos()) {
                if (!arco.getDestino().isVisited()) {
                    deque.push(arco.getDestino());
                    arco.getDestino().setVisited(true);
                }
            }
        }
        this.ponerComoNoVisitados();
        return lista;
    }

    public void ponerComoNoVisitados() {
        for (Vertice<E> vertice : this.vertices) {
            vertice.setVisited(false);
        }
    }

    public boolean esConexo() {
        if (this.vertices.isEmpty()) {
            return false;
        }
        Vertice<E> vertice = this.vertices.get(0);
        LinkedList<E> listaBFC = this.bfs(vertice.getElement());
        return this.vertices.size() == listaBFC.size();
    }

    private void dijkstra(Vertice<E> inicio) {
        limpiarDijkstra();
        PriorityQueue<Vertice> cola = new PriorityQueue<>((Vertice v1, Vertice v2) -> v1.getDistancia() - v2.getDistancia());
        inicio.setDistancia(0);
        cola.offer(inicio);
        while (!cola.isEmpty()) {
            Vertice<E> minimo = cola.poll();
            minimo.setVisited(true);
            for (Arco<E> arco : minimo.getArcos()) {
                Vertice<E> destino = arco.getDestino();
                if (!destino.isVisited() && destino.getDistancia() > (minimo.getDistancia() + arco.getPeso())) {
                    destino.setDistancia(minimo.getDistancia() + arco.getPeso());
                    destino.setAnterior(minimo);
                    destino.setVisited(true);
                    destino.setPelicula(arco.getPelicula());
                    cola.offer(destino);
                }
            }
        }
    }

    public LinkedList<E> caminoMasCorto(E origen, E destino) {
        Vertice<E> inicio = getVertice(origen);
        Vertice<E> fin = getVertice(destino);
        LinkedList<E> camino = new LinkedList<>();
        if (inicio != null && fin != null) {
            dijkstra(inicio);
            Vertice<E> temporal = fin;
            do {
                camino.addFirst(temporal.getElement());
                temporal = temporal.getAnterior();
            } while (temporal != null);
        }
        this.limpiarDijkstra();
        if (camino.size() > 1) {
            return camino;
        } else {
            return new LinkedList<E>();
        }
    }

    public LinkedList<String> caminoMasCortoPeliculas(E origen, E destino) {
        Vertice<E> inicio = getVertice(origen);
        Vertice<E> fin = getVertice(destino);
        LinkedList<String> camino = new LinkedList<>();
        if (inicio != null && fin != null) {
            dijkstra(inicio);
            Vertice<E> temporal = fin;
            try {
                do {
                    camino.addFirst(temporal.getElement() + " actuó en " + temporal.getPelicula() + " con " + temporal.getAnterior().getElement());
                    temporal = temporal.getAnterior();
                } while (temporal.getAnterior() != null);
            } catch (NullPointerException e) {
                camino.clear();
                camino.add(origen + " no ha actuado en ninguna película con " + destino);
                return camino;
            }
        }
        this.limpiarDijkstra();
        return camino;
    }

    private void limpiarDijkstra() {
        for (Vertice<E> vertice : this.vertices) {
            vertice.setAnterior(null);
            vertice.setDistancia(Integer.MAX_VALUE);
            vertice.setPelicula(null);
        }
        ponerComoNoVisitados();
    }

}
