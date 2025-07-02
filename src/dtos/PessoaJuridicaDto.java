package dtos;

public class PessoaJuridicaDto {
	private String cnpj;
	private String cpfPreposto;
	private String nome;
	private String email;
	private String telefone;
	private final String cpf;

	public PessoaJuridicaDto(String nome, String email, String telefone, String cnpj, String cpfPreposto){
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.cpfPreposto = cpfPreposto;
	}

	public void setPreposto(PessoaFisicaDto preposto) {
		this.preposto = preposto;
	}

	public PessoaFisicaDto getPreposto() {
		return preposto;
	}

	public Cnpj getCnpj() {
		return this.cnpj;
	}

	public String getCadastroRF() {
		return this.getCnpj();
	}
}
