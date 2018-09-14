package stock.evolution.hypotheses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stock.data.enterprise.Empresa;

@Repository
public interface HypothesesRepository extends JpaRepository<Hypotheses, String> {
	
	List<Hypotheses> getHipoteseByEmpresa(Empresa empresa);
	List<Hypotheses> getHipoteseFirst100ByEmpresaOrderByIndiceDesc(Empresa empresa);
	List<Hypotheses> findTop200ByEmpresaOrderByIndiceDesc(Empresa empresa);	
	List<Hypotheses> getHipoteseByEmpresaOrderByPeriodo(Empresa empresa);
	List<UUID> getIdFirst100ByEmpresaOrderByIndiceDesc(Empresa empresa);
	Hypotheses getById(UUID id);
}
