package br.edu.uni7.aed2.grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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

        grafo.getAresta(grafo.getVertice(0), grafo.getVertice(1)).setPeso(5);
        grafo.getAresta(grafo.getVertice(0), grafo.getVertice(2)).setPeso(5);
        grafo.getAresta(grafo.getVertice(2), grafo.getVertice(1)).setPeso(5);
        grafo.getAresta(grafo.getVertice(1), grafo.getVertice(3)).setPeso(2);
        grafo.getAresta(grafo.getVertice(2), grafo.getVertice(4)).setPeso(3);
        grafo.getAresta(grafo.getVertice(3), grafo.getVertice(4)).setPeso(7);

        Arvore<Integer, Void> arvorePrim = ArvoreGeradoraMinima.prim(grafo);
        System.out.println("Prim:");
        System.out.println(arvorePrim);

        System.out.println();

        Arvore<Integer, Void> arvoreKruskal = ArvoreGeradoraMinima.kruskal(grafo);
        System.out.println("Kruskal: ");
        System.out.println(arvoreKruskal);
    }

    private static <V, E> Arvore<V, E> kruskal(Grafo<V, E> grafo) {

        //criar uma floresta com todos os vértices do grafo
        var floresta = new Floresta<V, E>();
        for (var vertice : grafo.getVertices()) {
            var arvore = new Arvore<V, E>();
            arvore.addVertice(vertice);

            floresta.addArvore(arvore);
        }

        //ordenar as arestas, do menor para o maior peso
        var arestas = new ArrayList<>(grafo.getArestas());
        arestas.sort(Comparator.comparing(Aresta::getPeso));

        while (floresta.numeroDeArvores() > 1) {
            var menorAresta = arestas.remove(0);

            Arvore<V, E> arvoreA = floresta.getArvoreDoVertice(menorAresta.getVerticeA());
            Arvore<V, E> arvoreB = floresta.getArvoreDoVertice(menorAresta.getVerticeB());
            if (!arvoreA.equals(arvoreB)) {
                // faz o merge
                floresta.unirArvores(menorAresta);
            }
        }

        //retorna a única árvore da floresta
        return floresta.getArvores().get(0);
    }

}
