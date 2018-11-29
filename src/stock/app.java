package stock;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import stock.data.enterprise.EnterpriseController;
import stock.data.record.HistoricController;
import stock.data.record.RecordController;

import org.springframework.boot.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
public class app implements ApplicationRunner {
	@Autowired
	HistoricController historicoController;
	@Autowired
	EnterpriseController empresaController;
	@Autowired
	RecordController registroController;
	
	public static void main(String[] args) {
		SpringApplication.run(app.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	}
}
