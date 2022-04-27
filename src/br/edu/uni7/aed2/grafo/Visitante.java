package br.edu.uni7.aed2.grafo;

public interface Visitante<V, E> {

    /**
     * Visita um vértice durante um percurso no grafo
     *
     * @param vertice o vértice sendo visitado
     * @param aresta  a aresta utilizada para alcançar o vértice
     * @return true caso o percurso deva ser encerrado, false caso contrário
     */
    boolean visitar(Vertice<V, E> vertice, Aresta<V, E> aresta);

}
