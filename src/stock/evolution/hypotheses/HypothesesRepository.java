package stock.evolution.hypotheses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stock.data.enterprise.Empresa;

@Repository
public interface HypothesesRepository extends JpaRepository<Hypoteses, String> {
	
	List<Hypoteses> getHipoteseByEmpresa(Empresa empresa);
	List<Hypoteses> getHipoteseFirst100ByEmpresaOrderByIndiceDesc(Empresa empresa);
	List<Hypoteses> findTop200ByEmpresaOrderByIndiceDesc(Empresa empresa);	
	List<UUID> getIdFirst100ByEmpresaOrderByIndiceDesc(Empresa empresa);
	Hypoteses getById(UUID id);
}
