package domain;

import exceptions.PessoaException;
import shared.Cpf;
import shared.Email;
import shared.Telefone;

public class PessoaFisica extends Pessoa {
	private final Cpf cpf;

	public PessoaFisica(String nome, Email email, Telefone telefone, Cpf cpf) throws PessoaException {
		super(nome, email, telefone);
		this.cpf = cpf;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public String getCadastroRF() {
		return this.getCpf().getValue();
	}
}
