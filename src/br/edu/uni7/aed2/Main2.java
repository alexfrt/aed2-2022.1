package br.edu.uni7.aed2;

import br.edu.uni7.aed2.grafo.Aresta;
import br.edu.uni7.aed2.grafo.Grafo;
import br.edu.uni7.aed2.grafo.Vertice;

import java.util.HashSet;
import java.util.Set;

public class Main2 {

    public static void main(String[] args) {
        Vertice<String> h1 = new Vertice<>("H1");
        Vertice<String> h2 = new Vertice<>("H2");
        Aresta<String> aresta = new Aresta<>(h1, h2);

        Set<Vertice<String>> vertices = new HashSet<>();
        vertices.add(h1);
        vertices.add(h2);

        Grafo<String> grafo = new Grafo<>(vertices);
    }

}
