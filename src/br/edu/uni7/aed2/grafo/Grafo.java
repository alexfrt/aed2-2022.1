package br.edu.uni7.aed2.grafo;

import java.util.*;

public class Grafo<V, E> {

    private final Set<Vertice<V, E>> vertices;
    private final Set<Aresta<V, E>> arestas;

    public Grafo(Set<Vertice<V, E>> vertices) {
        this.vertices = vertices;
        this.arestas = new HashSet<>();

        for (Vertice<V, E> vertice : vertices) {
            arestas.addAll(vertice.getArestas());
        }
    }

    public Set<Vertice<V, E>> getVertices() {
        return vertices;
    }

    public Set<Aresta<V, E>> getArestas() {
        return arestas;
    }

    public Aresta<V, E> getAresta(Vertice<V, E> a, Vertice<V, E> b) {
        Aresta<V, E> temp = new Aresta<V, E>(a, b);
        for (Aresta<V, E> aresta : arestas) {
            if (aresta.equals(temp)) {
                return aresta;
            }
        }

        return null;
    }

    public Vertice<V, E> getVertice(V valor) {
        for (Vertice<V, E> vertice : vertices) {
            if (vertice.getValor().equals(valor))
                return vertice;
        }

        throw new IllegalArgumentException("Vértice de valor inexistente no grafo");
    }

    public void bfs(Vertice<V, E> inicio, Visitante visitante) {
        Queue<Vertice<V, E>> fila = new LinkedList<>();
        Queue<Aresta<V, E>> filaArestas = new LinkedList<>();
        Set<Vertice<V, E>> visitados = new HashSet<>();

        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            Vertice<V, E> vertice = fila.poll();

            if (visitante.visitar(vertice, filaArestas.poll())) {
                return;
            }

            for (Aresta<V, E> aresta : vertice.getArestas()) {
                Vertice<V, E> vizinho = aresta.getVizinho(vertice);

                if (!visitados.contains(vizinho)) {
                    fila.add(vizinho);
                    filaArestas.add(aresta);
                    visitados.add(vizinho);
                }
            }
        }
    }

    public void dfs(Vertice<V, E> inicio, Visitante visitante) {
        Stack<Vertice<V, E>> pilha = new Stack<>();
        Stack<Aresta<V, E>> pilhaArestas = new Stack<>();
        Set<Vertice<V, E>> visitados = new HashSet<>();

        pilha.push(inicio);

        while (!pilha.isEmpty()) {
            Vertice<V, E> vertice = pilha.pop();
            Aresta<V, E> aresta = pilhaArestas.isEmpty() ? null : pilhaArestas.pop();

            if (!visitados.contains(vertice)) {
                if (visitante.visitar(vertice, aresta)) {
                    return;
                }

                visitados.add(vertice);

                for (Aresta<V, E> arestaVizinho : vertice.getArestas()) {
                    pilha.push(arestaVizinho.getVizinho(vertice));
                    pilhaArestas.push(arestaVizinho);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "G(V=" + vertices + ", E=" + arestas + ")";
    }

    public static Grafo<Integer, Void> lerMatrizDeAdjacencia(boolean[][] matriz) {
        int n = matriz.length;

        List<Vertice<Integer, Void>> vertices = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Vertice<Integer, Void> v = new Vertice<>(i);
            vertices.add(v);
        }

        for (int i = 0; i < n; i++) {
            Vertice<Integer, Void> verticeA = vertices.get(i);

            for (int j = 0; j < matriz[i].length; j++) {
                Vertice<Integer, Void> verticeB = vertices.get(j);

                if (matriz[i][j]) {
                    Aresta<Integer, Void> aresta = new Aresta<>(verticeA, verticeB);
                    verticeA.adicionar(aresta);
                    verticeB.adicionar(aresta);
                }
            }
        }

        return new Grafo<>(new HashSet<>(vertices));
    }

    public static Grafo<Integer, Void> lerListaDeAdjacencia(List<Integer>[] listaAdjacencia) {
        int n = listaAdjacencia.length;

        List<Vertice<Integer, Void>> vertices = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Vertice<Integer, Void> vertice = new Vertice<>(i);
            vertices.add(vertice);
        }

        for (int i = 0; i < n; i++) {
            Vertice<Integer, Void> verticeA = vertices.get(i);
            List<Integer> adjacentes = listaAdjacencia[i];

            for (Integer adjacente : adjacentes) {
                Vertice<Integer, Void> verticeAdjacente = vertices.get(adjacente);

                Aresta<Integer, Void> aresta = new Aresta<>(verticeA, verticeAdjacente);
                verticeA.adicionar(aresta);
                verticeAdjacente.adicionar(aresta);
            }
        }

        return new Grafo<>(new HashSet<>(vertices));
    }

}
