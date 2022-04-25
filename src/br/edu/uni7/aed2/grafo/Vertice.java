package br.edu.uni7.aed2.grafo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertice {

    private final Integer valor;
    private final Set<Aresta> arestas;

    public Vertice(Integer valor) {
        this.valor = valor;
        this.arestas = new HashSet<>();
    }

    public Integer getValor() {
        return valor;
    }

    public Set<Aresta> getArestas() {
        return arestas;
    }

    public Aresta getAresta(Vertice vizinho) {
        for (Aresta aresta : arestas) {
            if (aresta.getVizinho(this).equals(vizinho)) {
                return aresta;
            }
        }

        throw new IllegalArgumentException("O vertice recebido não é vizinho deste vértice");
    }

    public void adicionar(Aresta aresta) {
        arestas.add(aresta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
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
}
