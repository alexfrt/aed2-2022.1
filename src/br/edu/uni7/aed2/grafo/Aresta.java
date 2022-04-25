package br.edu.uni7.aed2.grafo;

public class Aresta<V> {

    private final String descricao;
    private final Vertice<V> verticeA;
    private final Vertice<V> verticeB;
    private Integer peso;

    public Aresta(Vertice<V> verticeA, Vertice<V> verticeB) {
        this.descricao = null;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.peso = 0;
    }

    public Aresta(Vertice<V> verticeA, Vertice<V> verticeB, Integer peso) {
        this.descricao = null;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.peso = peso;
    }

    public Aresta(String descricao, Vertice<V> verticeA, Vertice<V> verticeB, Integer peso) {
        this.descricao = descricao;
        this.verticeA = verticeA;
        this.verticeB = verticeB;
        this.peso = peso;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Vertice<V> getVerticeA() {
        return verticeA;
    }

    public Vertice<V> getVerticeB() {
        return verticeB;
    }

    public Vertice<V> getVizinho(Vertice<V> vertice) {
        if (getVerticeA() != vertice && getVerticeB() == vertice) {
            return getVerticeA();
        } else if (getVerticeA() == vertice && getVerticeB() != vertice) {
            return getVerticeB();
        }

        throw new IllegalArgumentException("O vértice informado não pertence a esta aresta");
    }

    public boolean pertence(Vertice<V> vertice) {
        return vertice.equals(verticeA) || vertice.equals(verticeB);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta<V> aresta = (Aresta<V>) o;
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
