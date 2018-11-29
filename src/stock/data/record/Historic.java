package stock.data.record;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import stock.evolution.model.chromosome.Chromossome;


public class Historic {
	private static final Historic HISTORIC = new Historic();
	private final List<Record> records;
	
	public static Historic getInstance() {
		return HISTORIC;
	}
	
	private Historic() {
		this.records = new RecordService().findAll();
	}

	public Chromossome getCromossomoAt(int index) {
		return this.records.get(index).getCromossomo();
	}

	public List<Chromossome> getAllCromossomes() {
		return records
				.stream()
				.map(Record::getCromossomo)
				.collect(Collectors.toList());
	}

	public List<Record> getAllRecords() {
		return records;
	}
}
