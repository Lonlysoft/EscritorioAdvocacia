package dtos;

public class PessoaFisicaDto {
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	
	public PessoaFisicaDto(){}

	public PessoaFisicaDto(String nome, String email, String telefone, String cpf) {
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws PessoaException {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public String getCpf() {
		return cpf;
	}
}