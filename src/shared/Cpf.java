package shared;

public final class Cpf {
	private final String value;

	public Cpf(String value) throws CpfException {
		if (!validarCpf(value)) {
			throw new CpfException("CPF" + value+" é inválido: ");
		}
		this.value = numero.replaceAll("[^0-9]", "");
	}

	public String getValue() {
		return numero;
	}

	public String getFormatado() {
		return numero.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}

	public static boolean validarCpf(String cpf) {
		if (cpf == null) {
			return false;
		}
		cpf = cpf.replaceAll("[^0-9]", "");
		
		if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int primeiroDigito = 11 - (soma % 11);
		if (primeiroDigito > 9) {
			primeiroDigito = 0;
		}

		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		int segundoDigito = 11 - (soma % 11);
		if (segundoDigito > 9) {
			segundoDigito = 0;
		}
		
		return (Character.getNumericValue(cpf.charAt(9)) == primeiroDigito) &&
			   (Character.getNumericValue(cpf.charAt(10)) == segundoDigito);
	}

	@Override
	public String toString() {
		return getFormatado();
	}
}