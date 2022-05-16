package br.edu.uni7.aed2.grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Arvore<V, E> extends Grafo<V, E> {

    public Arvore() {
        super(new HashSet<>());
    }

    public void addVertice(Vertice<V, E> vertice) {
        vertices.add(new Vertice<>(vertice.getValor()));
    }

    public void addAresta(Aresta<V, E> aresta) {
        Vertice<V, E> verticeA = aresta.getVerticeA();
        Vertice<V, E> verticeB = aresta.getVerticeB();

        var novoVerticeA = new Vertice<V, E>(verticeA.getValor());
        var novoVerticeB = new Vertice<V, E>(verticeB.getValor());

        List<Vertice<V, E>> vertices = new ArrayList<>(this.vertices);
        int indexOfA = vertices.indexOf(verticeA);
        if (indexOfA != -1) {
            novoVerticeA = vertices.get(indexOfA);
        } else {
            int indexOfB = vertices.indexOf(verticeB);
            if (indexOfB != -1) {
                novoVerticeB = vertices.get(indexOfB);
            } else {
                throw new IllegalArgumentException("Essa aresta pertence a uma floresta");
            }
        }

        var novaAresta = new Aresta<V, E>(aresta.getDescricao(), novoVerticeA, novoVerticeB, aresta.getPeso());
        novoVerticeA.adicionar(novaAresta);
        novoVerticeB.adicionar(novaAresta);

        this.vertices.add(novoVerticeA);
        this.vertices.add(novoVerticeB);
        this.arestas.add(novaAresta);
    }
}
