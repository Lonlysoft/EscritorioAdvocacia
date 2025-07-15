package domain;
import java.util.Date;

public class Audiencia {
	private final Date data;
	private final String recomendacao;
	private final Advogado advogado;

	public Audiencia(Date data, String recomendacao, Advogado advogado) implements Serializable{
		this.advogado = advogado;
		this.data = data;
		this.recomendacao = recomendacao;
	}

	public Date getData() {
		return data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public Advogado getAdvogado() {
		return advogado;
	}
	
	public String getRegistroAdvogado(){
		return advogado.getRegistro();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("data: " + this.getData() + " | recomendacao: " + this.getRecomendacao());
		sb.append("| advogado: " + this.advogado.toString());
		return sb.toString();
	}
}