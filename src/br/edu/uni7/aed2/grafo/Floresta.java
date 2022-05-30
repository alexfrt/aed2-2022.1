package br.edu.uni7.aed2.grafo;

import java.util.*;

public class Floresta<V, E> extends Grafo<V, E> {

    private final Set<Arvore<V, E>> arvores;
    private final Map<Vertice<V, E>, Arvore<V, E>> arvoresDosVertices;

    public Floresta() {
        super(new HashSet<>());
        arvores = new HashSet<>();
        arvoresDosVertices = new HashMap<>();
    }

    public void addArvore(Arvore<V, E> arvore) {
        arvores.add(arvore);
        for (var vertice : arvore.getVertices()) {
            arvoresDosVertices.put(vertice, arvore);
        }
    }

    public int numeroDeArvores() {
        return arvores.size();
    }

    public Arvore<V, E> getArvoreDoVertice(Vertice<V, E> vertice) {
        return arvoresDosVertices.get(vertice);
    }

    public void unirArvores(Aresta<V, E> menorAresta) {
        var arvoreA = arvoresDosVertices.get(menorAresta.getVerticeA());
        var arvoreB = arvoresDosVertices.get(menorAresta.getVerticeB());

        var vertices = new ArrayList<>(arvoresDosVertices.keySet());
        var verticeA = vertices.get(vertices.indexOf(menorAresta.getVerticeA()));
        var verticeB = vertices.get(vertices.indexOf(menorAresta.getVerticeB()));

        var aresta = new Aresta<V, E>(menorAresta.getDescricao(), verticeA, verticeB, menorAresta.getPeso());
        verticeA.adicionar(aresta);
        verticeB.adicionar(aresta);

        var verticesDaArvore = new HashSet<Vertice<V, E>>();
        verticesDaArvore.addAll(arvoreA.getVertices());
        verticesDaArvore.addAll(arvoreB.getVertices());

        Arvore<V, E> novaArvore = new Arvore<>(verticesDaArvore);
        for (var vertice : verticesDaArvore) {
            arvoresDosVertices.put(vertice, novaArvore);
        }

        arvores.remove(arvoreA);
        arvores.remove(arvoreB);
        arvores.add(novaArvore);
    }

    public List<Arvore<V, E>> getArvores() {
        return new ArrayList<>(arvores);
    }
}
