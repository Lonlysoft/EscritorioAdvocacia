package domain;
import java.util.Date;

public class Despesa {
	private final Date data;
	private final String descricao;
	private final double valor;

	public Despesa(Date data, String descricao, double valor) implements Serializable{
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("data: " + this.getData() + " | descricao: " + this.getDescricao() + " | valor: "
				+ this.getValor());
		return sb.toString();
	}
}
