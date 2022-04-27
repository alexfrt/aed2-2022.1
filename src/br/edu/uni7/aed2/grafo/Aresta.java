package br.edu.uni7.aed2.grafo;

public class Aresta<V, E> {

    private final E descricao;
    private final Vertice<V, E> verticeA;
    private final Vertice<V, E> verticeB;
    private Integer peso;

    public Aresta(Vertice<V, E> verticeA, Vertice<V, E> verticeB) {
        this.descricao = null;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.peso = 0;
    }

    public Aresta(Vertice<V, E> verticeA, Vertice<V, E> verticeB, Integer peso) {
        this.descricao = null;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.peso = peso;
    }

    public Aresta(E descricao, Vertice<V, E> verticeA, Vertice<V, E> verticeB, Integer peso) {
        this.descricao = descricao;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.peso = peso;
    }

    public E getDescricao() {
        return descricao;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Vertice<V, E> getVerticeA() {
        return verticeA;
    }

    public Vertice<V, E> getVerticeB() {
        return verticeB;
    }

    public Vertice<V, E> getVizinho(Vertice<V, E> vertice) {
        if (getVerticeA() != vertice && getVerticeB() == vertice) {
            return getVerticeA();
        } else if (getVerticeA() == vertice && getVerticeB() != vertice) {
            return getVerticeB();
        }

        throw new IllegalArgumentException("O vértice informado não pertence a esta aresta");
    }

    public boolean pertence(Vertice<V, E> vertice) {
        return vertice.equals(verticeA) || vertice.equals(verticeB);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta<?, ?> aresta = (Aresta<?, ?>) o;
        return (verticeA.equals(aresta.verticeA) && verticeB.equals(aresta.verticeB) ||
                verticeA.equals(aresta.verticeB) && verticeB.equals(aresta.verticeA));
    }

    @Override
    public int hashCode() {
        return verticeA.hashCode() + verticeB.hashCode();
    }

    @Override
    public String toString() {
        return "(" + verticeA + ", " + verticeB + ")";
    }
}
