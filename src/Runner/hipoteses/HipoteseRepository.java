package Runner.hipoteses;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Runner.empresa.Empresa;

@Repository
public interface HipoteseRepository extends JpaRepository<Hipotese, String> {
	
	List<Hipotese> getHipoteseByEmpresa(Empresa empresa);
	List<Hipotese> getHipoteseByEmpresaOrderByIndice(Empresa empresa);
	List<Hipotese> getHipoteseByEmpresaOrderByPeriodo(Empresa empresa);
}
