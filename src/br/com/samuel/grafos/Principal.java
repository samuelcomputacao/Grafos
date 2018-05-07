package br.com.samuel.grafos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


/**
 * Esse é o programa principal. É aqui onde todas as questões serão chamadas para realizar seu trabalho.
 */
public class Principal {
	
	public static void main(String[] args) throws IOException {
		Questao1.main(args);
		Questao2.main(args);
		Questao3.main(args);
		File planilha = new File(new File("").getAbsolutePath()+"/files/planilha.csv");
		File caminho = new File(new File("").getAbsolutePath()+"/files/caminho.txt");
		File bipartido = new File(new File("").getAbsolutePath()+"/files/bipartido.txt");
		Desktop.getDesktop().open(planilha);
		Desktop.getDesktop().open(caminho);
		Desktop.getDesktop().open(bipartido);
	}

}
