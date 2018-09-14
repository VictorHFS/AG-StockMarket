package stock.data.record.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

import stock.data.record.service.SalvarRegistro;

@Service
public class SalvarRegistroFactory implements FactoryBean<SalvarRegistro> {
	private int factoryId;
	private int salvarRegistroId;
	private String registro;
	@Override
	public SalvarRegistro getObject() throws Exception {
		// TODO Auto-generated method stub
		return new SalvarRegistro(registro);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return SalvarRegistro.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setRegistro(String registro) {
		this.registro=registro;
	}
}
