package stock.evolution.hypotheses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stock.data.enterprise.Enterprise;

@Repository
public interface HypothesesRepository extends JpaRepository<Hypotheses, String> {
	
	List<Hypotheses> getHipoteseByEmpresa(Enterprise empresa);
	List<Hypotheses> getHipoteseFirst100ByEmpresaOrderByIndiceDesc(Enterprise empresa);
	List<Hypotheses> findTop200ByEmpresaOrderByIndiceDesc(Enterprise empresa);	
	List<Hypotheses> getHipoteseByEmpresaOrderByPeriodo(Enterprise empresa);
	List<UUID> getIdFirst100ByEmpresaOrderByIndiceDesc(Enterprise empresa);
	Hypotheses getById(UUID id);
}
