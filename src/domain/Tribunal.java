package domain;

public class Tribunal implements Serializable{
	private final String sigla;
	private final String nome;
	private final String secao;

	public Tribunal(String sigla, String nome, String secao) {
		this.sigla = sigla;
		this.nome = nome;
		this.secao = secao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getnome() {
		return nome;
	}

	public String getSecao() {
		return secao;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("sigla: " + this.getSigla() + " | nome: " + this.getnome() + " | secao: "
				+ this.getSecao());
		return sb.toString();
	}
}
