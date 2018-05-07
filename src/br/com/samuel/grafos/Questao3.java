package br.com.samuel.grafos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Questao3 {
	
	/**
	 * Método rersponsável por executar a tarefa da terceira questão. Aqui é criadp um arquivo txt que será
	 * salvo o resultado do processamento
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Graph<String, DefaultEdge> graph = criaGrafo();
		
		PatonCycleBase<String, DefaultEdge> base = new PatonCycleBase<String,DefaultEdge>(graph);
		List<List<String>> circulos = base.findCycleBase();
		
		try {
		File file = new File(new File("").getAbsolutePath()+"/files/bipartido.txt");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(circulos.toString());
		bufferedWriter.newLine();
		if(bipartido(circulos)) {
			bufferedWriter.write("É bipartido");
		}else {
			bufferedWriter.write("Não é bipartido");
		}
		bufferedWriter.close();
		fileWriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica se um grafo é bipartido
	 * @param circulos: uma lista de todos os circulos encontrado no grafo 
	 * @return um bolleano respresentando se o grafo é bipartido ou não
	 */
	private static boolean bipartido(List<List<String>> circulos) {
		for(List<String> circulo: circulos) {
			if(impar(circulo.size())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * verifica se um número é impar
	 * @param i : número que será verificado
	 * @return um bolleano
	 */
	private static boolean impar(int i) {
		return (i%2)!= 0;
	}
	
	/**
	 * Cria um grafo que será usado na questão
	 * @return retorna o grafo criado
	 */
	private static Graph<String, DefaultEdge> criaGrafo() {
		Graph<String, DefaultEdge> graph = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
		
		graph.addVertex("a");	graph.addVertex("b");	graph.addVertex("c");
		graph.addVertex("d");	graph.addVertex("e");	graph.addVertex("f");
//		graph.addVertex("g");
//		
//		graph.addEdge("a", "b");	graph.addEdge("a", "c");	graph.addEdge("c", "d");
//		graph.addEdge("b", "d");	graph.addEdge("d", "e");	graph.addEdge("d", "f");
//		graph.addEdge("f", "g");	graph.addEdge("e", "g");
		
		graph.addEdge("a", "b"); 	graph.addEdge("a", "c");
		graph.addEdge("b", "e"); 	graph.addEdge("b", "c");
		graph.addEdge("b", "d"); 	graph.addEdge("e", "f");
		graph.addEdge("e", "c");	graph.addEdge("e", "d");
		graph.addEdge("f", "d");	graph.addEdge("d", "c");
		
		return graph;
	}

}
