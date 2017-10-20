package Runner.hipoteses;

import java.util.ArrayList;
import java.util.List;

public class Populacao {
	
	private ArrayList<Hipotese> hipoteses = new ArrayList<Hipotese>();

	public int getSize() {
		// TODO Auto-generated method stub
		return this.hipoteses.size();
	}

	public void addAll(List<Hipotese> list) {
		// TODO Auto-generated method stub
		this.hipoteses.addAll(list);
	}

	public void add(Hipotese h) {
		// TODO Auto-generated method stub
		this.hipoteses.add(h);
	}

	public ArrayList<Hipotese> getAllMembers() {
		// TODO Auto-generated method stub
		return this.hipoteses;
	}

	public Hipotese getMember(int index) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return hipoteses.get(index);
	}
}
