package br.edu.uni7.aed2;

import br.edu.uni7.aed2.grafo.Caminho;
import br.edu.uni7.aed2.grafo.Grafo;
import br.edu.uni7.aed2.grafo.Vertice;

public class Main {
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

        grafo.getAresta(grafo.getVertice(0), grafo.getVertice(1)).setPeso(10);
        grafo.getAresta(grafo.getVertice(0), grafo.getVertice(2)).setPeso(16);
        grafo.getAresta(grafo.getVertice(2), grafo.getVertice(1)).setPeso(5);
        grafo.getAresta(grafo.getVertice(1), grafo.getVertice(3)).setPeso(24);
        grafo.getAresta(grafo.getVertice(2), grafo.getVertice(4)).setPeso(3);
        grafo.getAresta(grafo.getVertice(3), grafo.getVertice(4)).setPeso(7);

        Vertice<Integer, Void> x = grafo.getVertice(0);
        Vertice<Integer, Void> y = grafo.getVertice(3);
        var caminho = Caminho.dijkstra(grafo, x, y);
        System.out.println("O caminho entre 0 e 4 é: " + caminho);
    }
}
