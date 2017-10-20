package Runner.historicos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Historico {
	private String id;
	List<Registro> registros = new ArrayList<Registro>();
	
	public Historico() {	
		this.id = UUID.randomUUID().toString();
	}
	public int getSize() {
		// TODO Auto-generated method stub
		return registros.size();
	}

	public void addAll(List<Registro> individuals) {
		// TODO Auto-generated method stub
		registros.addAll(individuals);
	}

	public void add(Registro individual) {
		// TODO Auto-generated method stub
		registros.add(individual);
	}

	public List<Registro> getAllMembers() {
		// TODO Auto-generated method stub
		return registros;
	}

	public Registro getMember(int index) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return registros.get(index);
	}

	public void remove(int index) {
		registros.remove(index);
	}
	public String getId() {
		return this.id;
	}
	public List<Registro> getRegistros() {
		return registros;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}
}
