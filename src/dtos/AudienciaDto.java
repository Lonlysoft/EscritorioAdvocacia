public class AudienciaDto{
  public AudienciaDto(Date data, String recomendacao, String advogado){
    
  }
  public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("data: " + this.getData() + " | recomendacao: " + this.getRecomendacao());
		sb.append("| advogado: " + this.advogado.toString());
		return sb.toString();
	}
}