package br.edu.uni7.aed2.grafo;

import java.util.*;

public class Caminho {

    private final List<Aresta> arestas;

    public Caminho(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    @Override
    public String toString() {
        return arestas.toString();
    }

    public static Caminho encontrarCaminho(Grafo grafo, Vertice x, Vertice y) {
        Stack<Aresta> percurso = new Stack<>();
        grafo.bfs(x, ((vertice, aresta) -> {
            if (aresta != null) {
                percurso.push(aresta);
            }

            return vertice.equals(y);
        }));

        List<Aresta> caminhoXY = new ArrayList<>();
        Vertice verticeFinal = y;

        while (!percurso.isEmpty()) {
            Aresta aresta = percurso.pop();
            if (aresta.pertence(verticeFinal)) {
                verticeFinal = aresta.getVizinho(verticeFinal);
                caminhoXY.add(0, aresta);
            }
        }

        return new Caminho(caminhoXY);
    }

    public static Caminho dijkstra(Grafo grafo, Vertice x, Vertice y) {
        Set<Vertice> n = new HashSet<>(grafo.getVertices());
        Integer[] d = new Integer[n.size()];
        Vertice[] p = new Vertice[n.size()];

        d[x.getValor()] = 0;
        p[x.getValor()] = x;

        for (Aresta aresta : x.getArestas()) {
            int vizinho = aresta.getVizinho(x).getValor();
            d[vizinho] = aresta.getPeso();
            p[vizinho] = x;
        }

        while (!n.isEmpty()) {
            Vertice verticeSelecionado = null;
            for (Vertice verticeAtual : n) {
                int valorDoVerticeAtual = verticeAtual.getValor();
                if (d[valorDoVerticeAtual] == null) {
                    continue;
                }

                if (verticeSelecionado == null) {
                    verticeSelecionado = verticeAtual;
                } else {
                    if (d[valorDoVerticeAtual] < d[verticeSelecionado.getValor()]) {
                        verticeSelecionado = verticeAtual;
                    }
                }
            }

            n.remove(verticeSelecionado);

            for (Aresta aresta : verticeSelecionado.getArestas()) {
                Vertice vizinho = aresta.getVizinho(verticeSelecionado);

                if (n.contains(vizinho)) {
                    int novoCusto = d[verticeSelecionado.getValor()] + aresta.getPeso();
                    if (d[vizinho.getValor()] == null || novoCusto < d[vizinho.getValor()]) {
                        d[vizinho.getValor()] = novoCusto;
                        p[vizinho.getValor()] = verticeSelecionado;
                    }
                }
            }
        }

        List<Aresta> caminho = new ArrayList<>();
        Vertice vertice = y;

        while (!vertice.equals(x)) {
            Vertice predecessor = p[vertice.getValor()];
            caminho.add(0, predecessor.getAresta(vertice));
            vertice = predecessor;
        }

        return new Caminho(caminho);
    }

}
