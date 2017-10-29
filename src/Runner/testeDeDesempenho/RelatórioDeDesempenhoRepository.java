package Runner.testeDeDesempenho;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatórioDeDesempenhoRepository extends JpaRepository<RelatorioDeDesempenho, UUID> {

}
