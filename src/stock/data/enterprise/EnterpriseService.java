package stock.data.enterprise;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class EnterpriseService {
	@Autowired
	EnterpriseRepository repo;
	
	public Enterprise get(String id) {
		return repo.getOne(id);
	}
	
	public List<Enterprise> getAll(){
		return repo.findAll();
	}
	
	public Boolean exists(@PathVariable String id) {
		return repo.exists(id);
	}
	public void save(Enterprise empresa) {
		repo.save(empresa);
	}
	
	public void update(Enterprise empresa) {
		repo.save(empresa);
	}
	
	public void delete(String id) {
		repo.delete(id);
	}
	public void deleteAll(){
		repo.deleteAll();
	}
}
