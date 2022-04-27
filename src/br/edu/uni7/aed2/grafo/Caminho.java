package br.edu.uni7.aed2.grafo;

import java.util.*;

public class Caminho<G extends Grafo<V, E>, V, E> {

    private final List<Aresta<V, E>> arestas;

    public Caminho(List<Aresta<V, E>> arestas) {
        this.arestas = arestas;
    }

    @Override
    public String toString() {
        return arestas.toString();
    }

    public static <G extends Grafo<V, E>, V, E> Caminho<G, V, E> encontrarCaminho(G grafo, Vertice<V, E> x, Vertice<V, E> y) {
        Stack<Aresta<V, E>> percurso = new Stack<>();
        grafo.bfs(x, ((vertice, aresta) -> {
            if (aresta != null) {
                percurso.push(aresta);
            }

            return vertice.equals(y);
        }));

        List<Aresta<V, E>> caminhoXY = new ArrayList<>();
        Vertice<V, E> verticeFinal = y;

        while (!percurso.isEmpty()) {
            Aresta<V, E> aresta = percurso.pop();
            if (aresta.pertence(verticeFinal)) {
                verticeFinal = aresta.getVizinho(verticeFinal);
                caminhoXY.add(0, aresta);
            }
        }

        return new Caminho<>(caminhoXY);
    }

    public static <G extends Grafo<V, E>, V, E> Caminho<G, V, E> dijkstra(G grafo, Vertice<V, E> x, Vertice<V, E> y) {
        Set<Vertice<V, E>> n = new HashSet<>(grafo.getVertices());
        Map<Vertice<V, E>, Integer> d = new HashMap<>();
        Map<Vertice<V, E>, Vertice<V, E>> p = new HashMap<>();

        for (Vertice<V, E> vertice : grafo.getVertices()) {
            d.put(vertice, null);
            p.put(vertice, null);
        }
        d.put(x, 0);
        p.put(x, x);

        for (Aresta<V, E> aresta : x.getArestas()) {
            Vertice<V, E> vizinho = aresta.getVizinho(x);
            d.put(vizinho, aresta.getPeso());
            p.put(vizinho, x);
        }

        while (!n.isEmpty()) {
            Vertice<V, E> verticeSelecionado = null;
            for (Vertice<V, E> verticeAtual : n) {
                if (d.get(verticeAtual) == null) {
                    continue;
                }

                if (verticeSelecionado == null) {
                    verticeSelecionado = verticeAtual;
                } else {
                    if (d.get(verticeAtual) < d.get(verticeSelecionado)) {
                        verticeSelecionado = verticeAtual;
                    }
                }
            }

            n.remove(verticeSelecionado);

            for (Aresta<V, E> aresta : verticeSelecionado.getArestas()) {
                Vertice<V, E> vizinho = aresta.getVizinho(verticeSelecionado);

                if (n.contains(vizinho)) {
                    int novoCusto = d.get(verticeSelecionado) + aresta.getPeso();
                    if (d.get(vizinho) == null || novoCusto < d.get(vizinho)) {
                        d.put(vizinho, novoCusto);
                        p.put(vizinho, verticeSelecionado);
                    }
                }
            }
        }

        List<Aresta<V, E>> caminho = new ArrayList<>();
        Vertice<V, E> vertice = y;

        while (!vertice.equals(x)) {
            Vertice<V, E> predecessor = p.get(vertice);
            caminho.add(0, predecessor.getAresta(vertice));
            vertice = predecessor;
        }

        return new Caminho<>(caminho);
    }

}
