package shared;

import exceptions.EmailInvalidoException;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {
	private final String value;
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
		"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
	);

	public Email(String value) throws EmailException {
		if (!validarEmail(value)) {
			throw new EmailException("Endereço de e-mail inválido: " + value);
		}
		this.value = value.toLowerCase();
	}

	public static boolean validarEmail(String email) {
		if (email == null || email.isBlank()) {
			return false;
		}
		
		if (email.length() > 254) {
			return false;
		}
		
		return EMAIL_PATTERN.matcher(email).matches();
	}

	public String getValue() {
		return value;
	}

	public String getUsuario() {
		return value.split("@")[0];
	}

	public String getDominio() {
		return value.split("@")[1];
	}
}