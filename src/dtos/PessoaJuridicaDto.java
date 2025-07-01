package dtos;

public class PessoaJuridicaDto {
	private final String cnpj;
	private PessoaFisica preposto;
	private String nome;
	private String email;
	private String telefone;
	private final String cpf;

	public PessoaJuridicaDto(String nome, String email, String telefone, String cnpj, PessoaFisica preposto){
		PessoaFisica = preposto;
		this.cnpj = cnpj;
		this.preposto = preposto;
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
