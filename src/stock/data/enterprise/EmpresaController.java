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
public class EmpresaController {
	@Autowired
	EmpresaRepository repo;
	@GetMapping
	public List<Empresa> getAll(){
		return repo.findAll();
	}
	@GetMapping("/{id}")
	public Empresa get(@PathVariable String id) {
		return repo.findOne(id);
	}
	@GetMapping("/exists/{id}")
	public Boolean exists(@PathVariable String id) {
		return repo.exists(id);
	}
	@PostMapping
	public void save(@RequestBody Empresa empresa) {
		repo.save(empresa);
	}
	@PatchMapping
	public void update(@RequestBody Empresa empresa) {
		repo.save(empresa);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		repo.delete(id);
	}
	@DeleteMapping
	public void deleteAll(){
		repo.deleteAll();
	}
}
