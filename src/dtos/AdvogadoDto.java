package dtos;

public class AdvogadoDto{
	private final PessoaFisica pessoaFisica;
	private final String registro;

	public AdvogadoDto(PessoaFisica p, String registro) {
		this.pessoaFisica = p;
		this.registro = registro;
	}
	
	public String getNome() {
		return this.pessoaFisica.getNome();
	}
	
	public String getEmail() {
		return this.pessoaFisica.getEmail();
	}
	
	public String getTelefone() {
		return this.pessoaFisica.getTelefone();
	}
	
	public String getRegistro() {
		return registro;
	}
	
	public String getCadastroRF() {
		return this.pessoaFisica.getCadastroRF();
	}
	
}