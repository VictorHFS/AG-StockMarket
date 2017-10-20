package Runner.empresa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmpresaService {
	@Autowired
	EmpresaRepository empresaRepo;
	
	public Empresa get(String nome) {
		return empresaRepo.getOne(nome);
	}
}
