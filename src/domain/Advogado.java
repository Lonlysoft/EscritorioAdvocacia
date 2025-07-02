package domain;

import contracts.IPessoa;

public class Advogado implements IPessoa, Serializable{
	private final PessoaFisica pessoaFisica;
	private final String registro;

	public Advogado(PessoaFisica p, String registro) {
		this.pessoaFisica = p;
		this.registro = registro;
	}
	
	@Override
	public String getNome() {
		return this.pessoaFisica.getNome();
	}
	
	@Override
	public String getEmail() {
		return this.pessoaFisica.getEmail();
	}

	@Override
	public String getTelefone() {
		return this.pessoaFisica.getTelefone();
	}
	
	public String getRegistro() {
		return registro;
	}
	
	@Override
	public String getCadastroRF() {
		return this.pessoaFisica.getCadastroRF();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("advogado: " + this.pessoaFisica.getNome() + " | cadrastro: " + this.pessoaFisica.getCadastroRF() + " | email: "
				+ this.pessoaFisica.getEmail());
		return sb.toString();
	}
}
