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
	
	public static void main(String[] args) {
		
		Graph<String,DefaultEdge> graph = criaGrafo();
		questao1(graph);
	}
	
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

	private static void questao1(Graph<String, DefaultEdge> graph) {
//		String pathGrafo = new File("").getAbsolutePath()+"/files/grafoSimples.txt";
//		Graph<Object, DefaultEdge> graph = ImportSimpleGraphCSV.importarCSV(pathGrafo);
//				
		
		String pathPlanilha = new File("").getAbsolutePath()+"/files/planilha.csv";
		matrizIncidencia(graph,pathPlanilha);
		
	}
	


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


	
	private static void mensagem(String string,int type) {
		 JOptionPane.showMessageDialog(null, string, null, type);
	}

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
