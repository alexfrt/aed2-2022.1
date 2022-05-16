package br.edu.uni7.aed2.grafo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArvoreGeradoraMinima {

    public static <V, E> Arvore<V, E> prim(Grafo<V, E> grafo) {

        Arvore<V, E> arvore = new Arvore<>();

        //escolher vertice inicial
        Vertice<V, E> vertice = grafo.getVertices().iterator().next();
        arvore.addVertice(vertice);

        //montar lista de arestas candidatas
        Set<Aresta<V, E>> arestasCandidatas = new HashSet<>(vertice.getArestas());

        //enquanto lista não estiver vazia
        while (!arestasCandidatas.isEmpty()) {
            //seleciona a aresta com menor custo
            Aresta<V, E> arestaMaisBarata = null;
            for (var aresta : arestasCandidatas) {
                if (arestaMaisBarata == null || arestaMaisBarata.getPeso() > aresta.getPeso()) {
                    arestaMaisBarata = aresta;
                }
            }

            Vertice<V, E> verticeSelecionado = null;
            if (arvore.getVertices().contains(arestaMaisBarata.getVerticeA())) {
                verticeSelecionado = arestaMaisBarata.getVerticeB();
            } else {
                verticeSelecionado = arestaMaisBarata.getVerticeA();
            }

            arvore.addAresta(arestaMaisBarata);

            //atualizar lista de arestas candidatas
            for (var aresta : verticeSelecionado.getArestas()) {
                Vertice<V, E> vizinho = aresta.getVizinho(verticeSelecionado);
                if (arvore.getVertices().contains(vizinho)) {
                    arestasCandidatas.remove(aresta);
                } else {
                    arestasCandidatas.add(aresta);
                }
            }

        }

        return arvore;
    }

    public static void main(String[] args) {
        boolean[][] matrizAdjacencia = new boolean[][]{
                {false, true, true, false, false},//0
                {true, false, true, true, false}, //1
                {true, true, false, false, true}, //2
                {false, true, false, false, true},//3
                {false, false, true, true, false},//4
        };

        Grafo<Integer, Void> grafo = Grafo.lerMatrizDeAdjacencia(matrizAdjacencia);
        System.out.println(grafo);

        grafo.getAresta(grafo.getVertice(0), grafo.getVertice(1)).setPeso(8);
        grafo.getAresta(grafo.getVertice(0), grafo.getVertice(2)).setPeso(10);
        grafo.getAresta(grafo.getVertice(2), grafo.getVertice(1)).setPeso(5);
        grafo.getAresta(grafo.getVertice(1), grafo.getVertice(3)).setPeso(2);
        grafo.getAresta(grafo.getVertice(2), grafo.getVertice(4)).setPeso(3);
        grafo.getAresta(grafo.getVertice(3), grafo.getVertice(4)).setPeso(7);

        Arvore<Integer, Void> arvorePrim = ArvoreGeradoraMinima.prim(grafo);
        System.out.println(arvorePrim);
    }

}
