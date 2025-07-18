package dtos;

public class PessoaJuridicaDto {
	private String cnpj;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;

	public PessoaJuridicaDto(String nome, String email, String telefone, String cnpj, String cpfPreposto){
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.cpf = cpfPreposto;
	}

	public void setCpf(String preposto) {
		this.cpf = preposto;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public String getPreposto() {
		return this.cpf;
	}

	public String getCnpj() {
		return this.cnpj;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelefone() {
		return this.telefone;
	}

	public String getCadastroRF() {
		return this.getCnpj();
	}
}
