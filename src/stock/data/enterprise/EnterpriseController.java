package stock.data.enterprise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresas")
public class EnterpriseController {
	@Autowired
	EnterpriseService service;
	@GetMapping
	public List<Enterprise> getAll(){
		return service.getAll();
	}
	@GetMapping("/{id}")
	public Enterprise get(@PathVariable String id) {
		return service.get(id);
	}
	@GetMapping("/exists/{id}")
	public Boolean exists(@PathVariable String id) {
		return service.exists(id);
	}
	@PostMapping
	public void save(@RequestBody Enterprise empresa) {
		service.save(empresa);
	}
	@PatchMapping
	public void update(@RequestBody Enterprise empresa) {
		service.save(empresa);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}
	@DeleteMapping
	public void deleteAll(){
		service.deleteAll();
	}
}
