package shared;

import exceptions.TelefoneException;

public class Telefone {
	private final String value;
	public Telefone(String value) throws TelefoneException {
		this.validate(value);
		this.value = value;
	}
	private void validate(String value) throws TelefoneException{
		if(value == null || value.isBlank()) {
			throw new TelefoneException("telefone não declarado");
		}
	}
	public String getValue() {
		return value;
	}
}
