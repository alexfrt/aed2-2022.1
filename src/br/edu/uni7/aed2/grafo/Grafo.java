package br.edu.uni7.aed2.grafo;

import java.util.*;

public class Grafo<V> {

    private final Set<Vertice<V>> vertices;
    private final Set<Aresta> arestas;

    public Grafo(Set<Vertice<V>> vertices) {
        this.vertices = vertices;
        this.arestas = new HashSet<>();

        for (Vertice<V> vertice : vertices) {
            arestas.addAll(vertice.getArestas());
        }
    }

    public Set<Vertice<V>> getVertices() {
        return vertices;
    }

    public Set<Aresta> getArestas() {
        return arestas;
    }

    public Aresta getAresta(Vertice<V> a, Vertice<V> b) {
        Aresta temp = new Aresta(a, b);
        for (Aresta aresta : arestas) {
            if (aresta.equals(temp)) {
                return aresta;
            }
        }

        return null;
    }

    public Vertice<V> getVertice(V valor) {
        for (Vertice<V> vertice : vertices) {
            if (vertice.getValor().equals(valor))
                return vertice;
        }

        throw new IllegalArgumentException("Vértice de valor inexistente no grafo");
    }

    public void bfs(Vertice<V> inicio, Visitante visitante) {
        Queue<Vertice<V>> fila = new LinkedList<>();
        Queue<Aresta> filaArestas = new LinkedList<>();
        Set<Vertice<V>> visitados = new HashSet<>();

        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            Vertice<V> vertice = fila.poll();

            if (visitante.visitar(vertice, filaArestas.poll())) {
                return;
            }

            for (Aresta aresta : vertice.getArestas()) {
                Vertice<V> vizinho = aresta.getVizinho(vertice);

                if (!visitados.contains(vizinho)) {
                    fila.add(vizinho);
                    filaArestas.add(aresta);
                    visitados.add(vizinho);
                }
            }
        }
    }

    public void dfs(Vertice<V> inicio, Visitante visitante) {
        Stack<Vertice<V>> pilha = new Stack<>();
        Stack<Aresta> pilhaArestas = new Stack<>();
        Set<Vertice<V>> visitados = new HashSet<>();

        pilha.push(inicio);

        while (!pilha.isEmpty()) {
            Vertice<V> vertice = pilha.pop();
            Aresta aresta = pilhaArestas.isEmpty() ? null : pilhaArestas.pop();

            if (!visitados.contains(vertice)) {
                if (visitante.visitar(vertice, aresta)) {
                    return;
                }

                visitados.add(vertice);

                for (Aresta arestaVizinho : vertice.getArestas()) {
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

    public static Grafo<Integer> lerMatrizDeAdjacencia(boolean[][] matriz) {
        int n = matriz.length;

        List<Vertice<Integer>> vertices = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Vertice<Integer> v = new Vertice<>(i);
            vertices.add(v);
        }

        for (int i = 0; i < n; i++) {
            Vertice<Integer> verticeA = vertices.get(i);

            for (int j = 0; j < matriz[i].length; j++) {
                Vertice<Integer> verticeB = vertices.get(j);

                if (matriz[i][j]) {
                    Aresta aresta = new Aresta(verticeA, verticeB);
                    verticeA.adicionar(aresta);
                    verticeB.adicionar(aresta);
                }
            }
        }

        return new Grafo<>(new HashSet<>(vertices));
    }

    public static Grafo<Integer> lerListaDeAdjacencia(List<Integer>[] listaAdjacencia) {
        int n = listaAdjacencia.length;

        List<Vertice<Integer>> vertices = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Vertice<Integer> vertice = new Vertice<>(i);
            vertices.add(vertice);
        }

        for (int i = 0; i < n; i++) {
            Vertice<Integer> verticeA = vertices.get(i);
            List<Integer> adjacentes = listaAdjacencia[i];

            for (Integer adjacente : adjacentes) {
                Vertice<Integer> verticeAdjacente = vertices.get(adjacente);

                Aresta aresta = new Aresta(verticeA, verticeAdjacente);
                verticeA.adicionar(aresta);
                verticeAdjacente.adicionar(aresta);
            }
        }

        return new Grafo<>(new HashSet<>(vertices));
    }

}
