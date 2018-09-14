package stock.data.record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registros")
public class RecordController {
	@Autowired
	RecordRepository repo;
	@PostMapping
	public ResponseEntity<String> save(@RequestBody Record registro) {
		repo.save(registro);
		return ResponseEntity.ok(registro.getId());
	}
	@GetMapping
	public List<Record> getAll() {
		List<Record> registros = new ArrayList<Record>();
		repo.findAll().forEach(registros::add);
		return registros;
	}
	@GetMapping("/{id}")
	public Record get(@PathVariable String id) {
		return repo.findOne(id);
	}
	@GetMapping("/exists/{id}")
	public Boolean exists(@PathVariable String id) {
		return repo.exists(id);
	}
	@PatchMapping
	public void update(@RequestBody Record registro) {
		repo.save(registro);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id){
		try {
			repo.delete(id);			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possível excluir o registro");
		}
		return ResponseEntity.ok().build();
	}
	@DeleteMapping
	public ResponseEntity<String> deleteAll() {
		try {
			repo.deleteAll();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possível excluir o resgistro");
		}
		return ResponseEntity.ok().build();
	}
}
