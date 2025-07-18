package dtos;

import java.util.Date;

public class AudienciaDto{
	private Date data;
	private String recomendacao;
	private String advogado;
	public AudienciaDto(Date data, String recomendacao, String advogado){
		this.data = data;
		this.recomendacao = recomendacao;
		this.advogado = advogado;
	}
	
	public Date getData() {
		return data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public String getAdvogado() {
		return advogado;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("data: " + this.getData() + " \t recomendacao: " + this.getRecomendacao());
		sb.append("\t advogado registro: " + this.advogado);
		return sb.toString();
	}
	
}