package Runner.hipoteses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Runner.empresa.Empresa;

@Repository
public interface HipoteseRepository extends JpaRepository<Hipotese, String> {
	
	List<Hipotese> getHipoteseByEmpresa(Empresa empresa);
	List<Hipotese> getHipoteseFirst100ByEmpresaOrderByIndiceDesc(Empresa empresa);
	List<Hipotese> findTop100ByEmpresaOrderByIndiceDesc(Empresa empresa);	
	List<Hipotese> getHipoteseByEmpresaOrderByPeriodo(Empresa empresa);
	List<UUID> getIdFirst100ByEmpresaOrderByIndiceDesc(Empresa empresa);
	Hipotese getById(UUID id);
}
