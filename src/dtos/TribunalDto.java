package dtos;

public class TribunalDto{
	private String sigla;
	private String nome;
	private String secao;
	
	public TribunalDto(String sigla, String nome, String secao){
		this.sigla = sigla;
		this.nome = nome;
		this.secao = secao;
	}
	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getSecao() {
		return secao;
	}
}