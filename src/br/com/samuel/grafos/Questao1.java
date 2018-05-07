package br.com.samuel.grafos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.ExportException;
public class Questao1 {
	
	/**
	 * Problema da primeira questão. Aqui ele chama um método responsável por criar um grafo e manda executar a tarefa da questão 1. 
	 */
	public static void main(String[] args) {
		
		Graph<String,DefaultEdge> graph = criaGrafo();
		questao1(graph);
	}
	
	/**
	 * Responsável por criar um grafo
	 * @return um grafo simples
	 */
	private static Graph<String,DefaultEdge> criaGrafo() {
		Graph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		graph.addVertex("a");		graph.addVertex("d");	
		graph.addVertex("b");		graph.addVertex("e");
		graph.addVertex("c");		graph.addVertex("f");
		
		graph.addEdge("a", "b");	graph.addEdge("d", "c"); 	graph.addEdge("e", "c");
		graph.addEdge("b", "e");	graph.addEdge("c", "a");	graph.addEdge("e", "d");
		graph.addEdge("e", "f");	graph.addEdge("b", "c");
		graph.addEdge("f", "d");	graph.addEdge("b", "d");
		return graph;
	}
	
	/**
	 * Cria uma planilha para salvar os dados da matriz incidência
	 */
	private static void questao1(Graph<String, DefaultEdge> graph) {

		String pathPlanilha = new File("").getAbsolutePath()+"/files/planilha.csv";
		matrizIncidencia(graph,pathPlanilha);
		
	}
	

	/**
	 * Cria a matriz incidência de um grafo passado como parâmetro
	 * @param grafo para gerar matriz
	 * @param path : caminho onde será salva a matriz
	 */
	private static void matrizIncidencia(Graph<String, DefaultEdge> grafo,String path) {
		
		Set<DefaultEdge> arestas = grafo.edgeSet();
		Set<String> vertices = grafo.vertexSet();		
		
		List<String> listVertex = setToList(vertices);
		Iterator<DefaultEdge> iterator = arestas.iterator();
		
		Collections.sort(listVertex);
		
		try {
			writeMatriz(grafo, listVertex, iterator,path);			
		}catch (FileNotFoundException e) {
			mensagem("Planilha aberta!",JOptionPane.ERROR_MESSAGE);
		}catch (ExportException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}


	/**
	 * Uma mensagem de erro caso ocorra um problema na execução
	 * @param string
	 * @param type
	 */
	private static void mensagem(String string,int type) {
		 JOptionPane.showMessageDialog(null, string, null, type);
	}

	/**
	 * Escreve os dados na planilha
	 * @param grafo : Grafo base para gerar  matriz
	 * @param vertices : vertices do grafo
	 * @param iterator : iterator reponsável por varrer o set arestas
	 * @param path : caminho onde será salvo
	 * @throws IOException : exceção que pode ser lançada
	 * @throws ExportException : exceção que pode ser lançada
	 */
	private static void writeMatriz(Graph<String, DefaultEdge> grafo, List<String> vertices,Iterator<DefaultEdge> iterator,String path) throws IOException, ExportException {
		
		File planilha = new File(path);
		FileWriter fileWriter = new FileWriter(planilha);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		List<DefaultEdge> arestas = new ArrayList<DefaultEdge>();
		while(iterator.hasNext()) arestas.add(iterator.next());
		
		for(DefaultEdge aresta: grafo.edgeSet()) {
			bufferedWriter.write(";");
			String source = grafo.getEdgeSource(aresta);
			String target = grafo.getEdgeTarget(aresta);
			bufferedWriter.write(source+target);
		}
		
		bufferedWriter.newLine();
		for(String verte:vertices) {
			bufferedWriter.write(verte);
			for(DefaultEdge aresta: arestas) {
				bufferedWriter.write(";");
				String source = grafo.getEdgeSource(aresta);
				String target = grafo.getEdgeTarget(aresta);
				if(source.equals(verte) || target.equals(verte)) {
					bufferedWriter.write("1");
				}else {
					bufferedWriter.write("0");
				}
				
			}
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
		fileWriter.close();
	}
	
	/**
	 * Converte uma lista em um set de String
	 * @param set : refere-se ao conjunto que servirá como base para a lista
	 * @return um lista com valores semelhantes ao do conjunto
	 */
	private static List<String> setToList(Set<String> set) {
		
		List<String> retorno = new ArrayList<String>();
		Iterator<String> iterator  = set.iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			retorno.add(str);
		}
		return retorno;
	}



}
