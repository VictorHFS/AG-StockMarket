package stock.data.record;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stock.data.enterprise.Enterprise;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
	
	//List<Registro> getRegistroByEmpresaOrderByCromossomoDataCotacaoAsc(Empresa empresa);

	List<Record> getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(Enterprise empresam, int ano);

	List<Record> getRegistroByEmpresa(Enterprise empresa);	
}
