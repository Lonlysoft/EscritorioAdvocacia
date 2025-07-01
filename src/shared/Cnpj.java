package shared;

import exceptions.CnpjException;

public class Cnpj {
	private final String value;
	public Cnpj(String value) throws CnpjException {
		this.validate(value);
		this.value = value;
	}
	private void validate(String value) throws CnpjException{
		if(value == null || value.isBlank()) {
			throw new CnpjException("Cnpj não declarado");
		}
	}
	public String getValue() {
		return value;
	}
}