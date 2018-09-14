package stock.data.record;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Historic {
	private String id;
	List<Record> registros = new ArrayList<Record>();
	
	public Historic() {	
		this.id = UUID.randomUUID().toString();
	}
	public int getSize() {
		// TODO Auto-generated method stub
		return registros.size();
	}

	public void addAll(List<Record> individuals) {
		// TODO Auto-generated method stub
		registros.addAll(individuals);
	}

	public void add(Record individual) {
		// TODO Auto-generated method stub
		registros.add(individual);
	}

	public List<Record> getAllMembers() {
		// TODO Auto-generated method stub
		return registros;
	}

	public Record getMember(int index) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return registros.get(index);
	}

	public void remove(int index) {
		registros.remove(index);
	}
	public String getId() {
		return this.id;
	}
	public List<Record> getRegistros() {
		return registros;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRegistros(List<Record> registros) {
		this.registros = registros;
	}
}
