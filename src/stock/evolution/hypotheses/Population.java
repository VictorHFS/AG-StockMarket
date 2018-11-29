package stock.evolution.hypotheses;

import java.util.ArrayList;
import java.util.List;

public class Population {
	
	private ArrayList<Hypoteses> hipoteses = new ArrayList<Hypoteses>();

	public int getSize() {
		// TODO Auto-generated method stub
		return this.hipoteses.size();
	}

	public void addAll(List<Hypoteses> list) {
		// TODO Auto-generated method stub
		this.hipoteses.addAll(list);
	}

	public void add(Hypoteses h) {
		// TODO Auto-generated method stub
		this.hipoteses.add(h);
	}

	public ArrayList<Hypoteses> getAllMembers() {
		// TODO Auto-generated method stub
		return this.hipoteses;
	}

	public Hypoteses getMember(int index) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return hipoteses.get(index);
	}
}
