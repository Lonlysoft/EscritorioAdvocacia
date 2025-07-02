package domain;

import contracts.IPessoa;
import exceptions.PessoaException;
import shared.Email;

public abstract class Pessoa implements IPessoa, Serializable{
	
	private String nome;
	private Email email;
	private String telefone;

	public Pessoa(String nome, Email email, Telefone telefone) throws PessoaException{
		
		this.setNome(nome);
		this.email = email;
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws PessoaException{
		if(nome == null || nome.isBlank()) {
			throw new PessoaException("nome inválido.");
		}
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws PessoaException {
		if(email == null || email.isBlank()) {
			throw new PessoaException("email inválido.");
		}
		this.email = new Email(email);
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public abstract String getCadastroRF();
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("nome: " + this.getNome() + " | cadastro: " + this.getCadastroRF() + " | email: "
				+ this.getEmail());
		return sb.toString();
	}
}

