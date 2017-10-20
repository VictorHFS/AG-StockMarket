package Runner.historicos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Runner.empresa.Empresa;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, String> {
	
	//List<Registro> getRegistroByEmpresaOrderByCromossomoDataCotacaoAsc(Empresa empresa);

	List<Registro> getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(Empresa empresam, int ano);

	List<Registro> getRegistroByEmpresa(Empresa empresa);	
}
