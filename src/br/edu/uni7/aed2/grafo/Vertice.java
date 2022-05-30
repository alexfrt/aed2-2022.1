package br.edu.uni7.aed2.grafo;

import java.util.*;

public class Vertice<V, E> {

    private final V valor;
    private Integer cor;
    private final Set<Aresta<V, E>> arestas;

    public Vertice(V valor) {
        this.valor = valor;
        this.arestas = new HashSet<>();
        this.cor = null;
    }

    public V getValor() {
        return valor;
    }

    public Integer getCor() {
        return cor;
    }

    public Set<Aresta<V, E>> getArestas() {
        return arestas;
    }

    public Aresta<V, E> getAresta(Vertice<V, E> vizinho) {
        for (Aresta<V, E> aresta : arestas) {
            if (aresta.getVizinho(this).equals(vizinho)) {
                return aresta;
            }
        }

        throw new IllegalArgumentException("O vertice recebido não é vizinho deste vértice");
    }

    public void adicionar(Aresta<V, E> aresta) {
        arestas.add(aresta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice<?, ?> vertice = (Vertice<?, ?>) o;
        return valor.equals(vertice.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    public void colorir() {
        Set<Integer> coresDosVizinhos = new HashSet<>();
        for (var aresta : arestas) {
            Integer cor = aresta.getVizinho(this).getCor();
            coresDosVizinhos.add(cor);
        }

        for (int i = 0; i < coresDosVizinhos.size(); i++) {
            if (!coresDosVizinhos.contains(i)) {
                this.cor = i;
                break;
            }
        }
    }
}
