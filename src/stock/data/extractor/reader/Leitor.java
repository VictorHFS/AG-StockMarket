package stock.data.extractor.reader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Leitor {

	private FileReader arquivo;
	private BufferedReader leitor;

	public ArrayList<String> ler(String caminho) {
		ArrayList<String> texto = new ArrayList<String>();
		String linha;
		try {
			this.arquivo = new FileReader(caminho);
			this.leitor = new BufferedReader(arquivo);
			linha = leitor.readLine();
			while (linha != null) {
				texto.add(linha);
				linha = leitor.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return texto;

	}

}
