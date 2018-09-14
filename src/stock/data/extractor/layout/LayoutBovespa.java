package stock.data.extractor.layout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class LayoutBovespa {

	public stock.data.extractor.layout.LayoutRecord tipoDeRegistro(String registro) {
		if (registro.startsWith("00")) {
			return stock.data.extractor.layout.LayoutRecord.Header;
		}
		if (registro.startsWith("01")) {
			return stock.data.extractor.layout.LayoutRecord.Contacao;
		}
		if (registro.startsWith("99")) {
			return stock.data.extractor.layout.LayoutRecord.Trailer;
		}
		return null;
	}

	/*
	 * Este metodo passa como parametro um registro de uma cota��o e traz como
	 * resultado a data desta cota��o
	 */
	public Date dataCotacao(String registro) {
		/*
		 * //variavel que formata a data DateFormat format; //aqui � definido o formato
		 * q a data apresentara format = new SimpleDateFormat("AAAMMDD",Locale.ENGLISH);
		 * try { //este metodo retorna um date com base nos parametros de seu construtor
		 * return format.parse(registro.substring(3, 10)); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } return null;
		 */
		/*
		int ano = Integer.parseInt(registro.substring(2, 5).trim());
		int mes = Integer.parseInt(registro.substring(6, 7).trim());
		int dia = Integer.parseInt(registro.substring(8, 9).trim());
		return new Date(ano, mes, dia);
		*/
		// variavel que formata a data
		DateFormat format;
		// aqui � definido o formato q a data apresentara
		format = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
		try {
			// este metodo retorna um date com base nos parametros de seu construtor
			return format.parse(registro.substring(2, 6)+"-"+registro.substring(6, 8)+"-"+registro.substring(8, 10));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String Id(String registro) {
		return UUID.randomUUID().toString();//registro.substring(0,49).trim();
	}
	public String nomeResCotacao(String registro) {
		return registro.substring(27, 38).trim();
	}

	public Double precoDeAbertura(String registro) {
		return Double.parseDouble("0" + registro.substring(57, 69).trim());
	}

	public Double precoMaximo(String registro) {
		return Double.parseDouble("0" + registro.substring(70, 82).trim());
	}

	public Double precoMinimo(String registro) {
		return Double.parseDouble("0" + registro.substring(83, 95).trim());
	}

	public Double precoMedio(String registro) {
		return Double.parseDouble("0" + registro.substring(96, 108).trim());
	}

	public Double precoDoUltimoNegocio(String registro) {
		return Double.parseDouble("0" + registro.substring(109, 121).trim());
	}

	public Double precoDaMelhorOfertaDeCompra(String registro) {
		return Double.parseDouble("0" + registro.substring(122, 134).trim());
	}

	public Double precoDaMelhorOfertaDeVenda(String registro) {
		return Double.parseDouble("0" + registro.substring(135, 147).trim());
	}

	public int numeroDeNegociacoes(String registro) {
		if (registro.substring(148, 152).trim() != "") {
			return Integer.parseInt("0" + registro.substring(148, 152).trim());
		} else
			return 0;
	}

	public int quantidadeTotalDeTitulosNegociados(String registro) {
		try {
			return Integer.parseInt(registro.substring(153, 170).trim());
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public Double volumeTotalDeTitulosNegociados(String registro) {
		return Double.parseDouble("0" + registro.substring(171, 188).trim());
	}

	public Double precoDeExercicios(String registro) {
		return Double.parseDouble("0" + registro.substring(189, 201).trim());
	}

	public int indicadorDeCorrecaoDePreco(String registro) {
		return Integer.parseInt(new String("0" + registro.charAt(202)).trim());
	}

	/*
	 * Este metodo passa como parametro um registro de uma cota��o e traz como
	 * resultado a data do vencimento desta cota��o
	 */
	public Date dataDeVencimento(String registro) {
		// variavel que formata a data
		DateFormat format;
		// aqui � definido o formato q a data apresentara
		format = new SimpleDateFormat("YYYYMMDD", Locale.ENGLISH);
		try {
			// este metodo retorna um date com base nos parametros de seu construtor
			String sub = registro.substring(202, 210);					
			return format.parse(sub);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int fatorDeCotacao(String registro) {
		return Integer.parseInt("0" + registro.substring(211, 217).trim());
	}

	public Double precoDeExercicioEmPontos(String registro) {
		return Double.parseDouble("0" + registro.substring(218, 230).trim());
	}

}
