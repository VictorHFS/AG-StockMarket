package stock.evolution.hypotheses;

import java.util.ArrayList;
import java.util.List;

public class Population {
	
	private ArrayList<Hypotheses> hipoteses = new ArrayList<Hypotheses>();

	public int getSize() {
		// TODO Auto-generated method stub
		return this.hipoteses.size();
	}

	public void addAll(List<Hypotheses> list) {
		// TODO Auto-generated method stub
		this.hipoteses.addAll(list);
	}

	public void add(Hypotheses h) {
		// TODO Auto-generated method stub
		this.hipoteses.add(h);
	}

	public ArrayList<Hypotheses> getAllMembers() {
		// TODO Auto-generated method stub
		return this.hipoteses;
	}

	public Hypotheses getMember(int index) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return hipoteses.get(index);
	}
}
