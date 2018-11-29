package stock.evolution.selecao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import stock.data.record.Record;
import stock.evolution.hypotheses.Hypoteses;
import stock.evolution.model.chromosome.Chromossome;

@Service
public class SelecaoService {

	private ExecutorService executor = Executors.newFixedThreadPool(50);
	
	private List<Record> records;	
	
	public SelecaoService(List<Record> records) {
		this.records = records;
	}
	
	public List<Hypoteses> selecionar(List<Hypoteses> hipoteses) {
		 
		hipoteses
		.stream()
		.map(Hypoteses::getCromossomos)
		.forEach(chromossomes -> {
			chromossomes
			.stream()
			.map(Chromossome::getFatorDeCotacao);
			
		});
		//TODO
		return null;
	}
}
