public class AudienciaDto{
	private Date data;
	private String recomendacao;
	private String advogado;
	private String stringfied;
  public AudienciaDto(Date data, String recomendacao, String advogado){
    this.data = data;
    this.recomendacao = recomendacao;
    this.advogado = advogado;
  }
  
  public AudienciaDto(Date data, String recomendacao, String advogado, String strfy){
    this.data = data;
    this.recomendacao = recomendacao;
    this.advogado = advogado;
    this.stringfied = strfy;
  }
  
  public String toString() {
		return stringfied;
	}
}