package domain;

import exceptions.PessoaException;
import shared.Cnpj;
import shared.Email;
import shared.Telefone;

public class PessoaJuridica extends Pessoa, Serializer {
	private final Cnpj cnpj;
	private PessoaFisica preposto;

	public PessoaJuridica(String nome, Email email, String telefone, Cnpj cnpj, PessoaFisica preposto) throws PessoaException {
		super(nome, email, telefone);
		
		this.cnpj = cnpj;
		this.preposto = preposto;
	}

	public void setPreposto(PessoaFisica preposto) {
		this.preposto = preposto;
	}

	public PessoaFisica getPreposto() {
		return preposto;
	}

	public Cnpj getCnpj() {
		return this.cnpj;
	}

	public String getCadastroRF() {
		return this.getCnpj().getValue();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("nome: " + this.getNome() + " | cadastro: " + this.getCadastroRF() + " | email: "
				+ this.getEmail());
		sb.append(this.preposto.toString());
		return sb.toString();
	}
}