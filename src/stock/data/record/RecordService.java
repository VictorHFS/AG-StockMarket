package stock.data.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

	@Autowired
	private RecordRepository repository;
	
	public List<Record> findAll(){
		return repository.findAll();
	}
}
