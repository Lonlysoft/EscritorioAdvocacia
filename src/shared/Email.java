package shared;

import exceptions.EmailException;

public class Email {
	private final String value;
	private final String pattern = "";
	public Email(String value) throws EmailException {
		this.validate(value);
		this.value = value;
	}
	private void validate(String value) throws EmailException{
		if(value == null || value.isBlank()) {
			throw new EmailException("Email não declarado");
		}
	}
	public String getValue() {
		return value;
	}
}
