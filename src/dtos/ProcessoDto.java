package dtos;

public class ProcessoDto{
	private String numero;
	private String dataAbertura;
	private String siglaTribunal;
	private EFaseProcesso fase;
	private String cadastroCliente;
	private String cadastroParteContraria;
	private ArrayList<AudienciaDto> audiencias = new ArrayList<AudienciaDto>();
	private ArrayList<DespesaDto> custas = new ArrayList<DespesaDto>();
	private String strfy;
	public ProcessoDto(String numero, Date dataAbertura, String siglaTribunal, EFaseProcesso fase, String cadastroCliente, String CadastroParteContraria)){
	  this.dataAbertura = dataAbertura;
	  this.numero = numero;
	  this.siglaTribunal = siglaTribunal;
	  this.cadastroCliente = cadastroCliente;
	  this.cadastroParteContraria = cadastroParteContraria;
	  this.fase = fase;
	}
	public ProcessoDto(String numero, Date dataAbertura, String siglaTribunal, EFaseProcesso fase, String cadastroCliente, String CadastroParteContraria, String strfy)){

	  this.dataAbertura = dataAbertura;

	  this.numero = numero;
	  this.siglaTribunal = siglaTribunal;
	  this.cadastroCliente = cadastroCliente;
	  this.cadastroParteContraria = cadastroParteContraria;
	  this.fase = fase;
	  this.strfy = strfy;
	}
	//generate getters and setters;
	public String getTribunal() {
		return siglaTribunal;
	}

	public String getRegistroCliente() {
		return cliente.getRegistroRF();
	}

	public String getRegistroParteContraria() {
		return parteContraria.getRegistroRF();
	}

	public EFaseProcesso getFase() {
		return fase;
	}

	public void setFase(EFaseProcesso fase) {
		this.fase = fase;
	}

	public String getNumero() {
		return numero;
	}
	
	public Date getDataAbertura() {
		return dataAbertura;
	}
	
	public void addAudiencia(Date data, String recomendacao, String advogadoRegistro) {
		this.audiencias.add(new AudienciaDto(data, recomendacao, advogado));
	}

	public void addDespesa(Date data, String descricao, double valor) {
		this.custas.add(new DespesaDto(data, descricao, valor));
	}

	public String getAudienciasToString() {
		StringBuilder sb = new StringBuilder();
		for (Audiencia i : this.audiencias) {
			sb.append("" + i.toString()+ "\n");
		}
		return sb.toString();
	}
	
	public ArrayList<AudienciaDto> getAudiencias(){
		return this.audiencias;
	}
	
	public ArrayList<DespesaDto> getDespesas(){
		return this.despesas;
	}

	
	public String getCustas() {
		StringBuilder sb = new StringBuilder();
		for (Despesa i : this.custas) {
			sb.append(i.toString() + "\n");
		}
		return sb.toString();
	}

	public double getTotalCustas() {
		double total = 0.0;
		for (Despesa i : custas) {
			total += i.getValor();
		}
		return total;
	}
	
}