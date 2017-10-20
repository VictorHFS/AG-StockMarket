package Runner.historicos;

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
public class RegistroController {
	@Autowired
	RegistroRepository repo;
	@PostMapping
	public ResponseEntity<String> save(@RequestBody Registro registro) {
		repo.save(registro);
		return ResponseEntity.ok(registro.getId());
	}
	@GetMapping
	public List<Registro> getAll() {
		List<Registro> registros = new ArrayList<Registro>();
		repo.findAll().forEach(registros::add);
		return registros;
	}
	@GetMapping("/{id}")
	public Registro get(@PathVariable String id) {
		return repo.findOne(id);
	}
	@GetMapping("/exists/{id}")
	public Boolean exists(@PathVariable String id) {
		return repo.exists(id);
	}
	@PatchMapping
	public void update(@RequestBody Registro registro) {
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
