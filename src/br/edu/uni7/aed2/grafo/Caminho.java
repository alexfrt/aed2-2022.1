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
            //selecionar vértice 'v' cujo valor em 'd' seja o menor
            //para cada vizinho 'j' do vértice 'v',
            // calcule o custo 'c' do caminho v-j se 'j' não estiver em 'n'
            // se 'c' for menor que o valor de 'j' em 'd', atualize 'd' e 'p'
            //remova 'v' de 'n'
        }

        return null;
    }

}
