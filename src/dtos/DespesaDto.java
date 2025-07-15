public class DespesaDto{
  public Despesa(Date data, String descricao, double valor) {
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
}