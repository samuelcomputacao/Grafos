package br.com.samuel.grafos;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Questao2 {
	
	/**
	 * Método principal responsável por fazer a tarefa da segunda questão. 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SimpleWeightedGraph<String,DefaultWeightedEdge> graph = criaGrafo();
		GraphPath <String,DefaultWeightedEdge> caminho = menorCaminho(graph);
		
		try {
		File file = new File(new File("").getAbsolutePath()+"/files/caminho.txt");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		bufferedWriter.write("Menor Caminho - "+ caminho.toString());
		bufferedWriter.newLine();
		bufferedWriter.write("Valor caminho: "+caminho.getWeight());
		bufferedWriter.newLine();
		bufferedWriter.write("Quantidade arestas: "+caminho.getLength());
		bufferedWriter.close();
		fileWriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Busca o menor cainho de um vertice até outro de um grafo ponderado
	 * @param graph
	 * @return o menor caminho do vertice a até o vertice d do grafo recebido como parâmetro
	 */
	private static GraphPath<String,DefaultWeightedEdge> menorCaminho(SimpleWeightedGraph<String, DefaultWeightedEdge> graph) {
		
		DijkstraShortestPath <String,DefaultWeightedEdge>  path = new DijkstraShortestPath <String,DefaultWeightedEdge> (graph);
		
		return path.getPath("a", "d");
	}
	
	/**
	 * Cria um grafo ponderado
	 * @return retorna o grafo criado
	 */
	private static SimpleWeightedGraph<String, DefaultWeightedEdge> criaGrafo() {
		SimpleWeightedGraph<String,DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		graph.addVertex("a");	graph.addVertex("b");	graph.addVertex("c");
		graph.addVertex("d");	graph.addVertex("e");	graph.addVertex("f");
		graph.addVertex("g");	graph.addVertex("h");	graph.addVertex("i");
		
		graph.setEdgeWeight(graph.addEdge("a","b"),2);
		graph.setEdgeWeight(graph.addEdge("b","c"),4);
		graph.setEdgeWeight(graph.addEdge("c","d"),2);
		graph.setEdgeWeight(graph.addEdge("d","e"),1);
		graph.setEdgeWeight(graph.addEdge("e","f"),6);
		graph.setEdgeWeight(graph.addEdge("f","a"),7);
		graph.setEdgeWeight(graph.addEdge("a","g"),3);
		graph.setEdgeWeight(graph.addEdge("g","i"),1);
		graph.setEdgeWeight(graph.addEdge("g","h"),3);
		graph.setEdgeWeight(graph.addEdge("g","b"),6);
		graph.setEdgeWeight(graph.addEdge("h","c"),2);
		graph.setEdgeWeight(graph.addEdge("h","d"),8);
		graph.setEdgeWeight(graph.addEdge("h","i"),4);
		graph.setEdgeWeight(graph.addEdge("i","f"),5);
		graph.setEdgeWeight(graph.addEdge("i","e"),2);
		return graph;
	}

}
