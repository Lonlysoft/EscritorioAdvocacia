package dtos;

import java.util.ArrayList;
import java.util.Date;
import enumerations.EFaseProcesso;

public class ProcessoDto{
	private String numero;
	private Date dataAbertura;
	private String siglaTribunal;
	private EFaseProcesso fase;
	private String cadastroCliente;
	private String cadastroParteContraria;
	private ArrayList<AudienciaDto> audiencias = new ArrayList<AudienciaDto>();
	private ArrayList<DespesaDto> despesas = new ArrayList<DespesaDto>();
	private String strfy;
	
	public ProcessoDto(
		String numero,
		Date dataAbertura,
		String siglaTribunal,
		EFaseProcesso fase,
		String cadastroCliente,
		String cadastroParteContraria
	){
		this.dataAbertura = dataAbertura;
		this.numero = numero;
		this.siglaTribunal = siglaTribunal;
		this.cadastroCliente = cadastroCliente;
		this.cadastroParteContraria = cadastroParteContraria;
		this.fase = fase;
	}
	
	public ProcessoDto(
		String numero,
		Date dataAbertura,
		String siglaTribunal,
		EFaseProcesso fase,
		String cadastroCliente,
		String cadastroParteContraria,
		String strfy
	){
		this.dataAbertura = dataAbertura;
		this.numero = numero;
		this.siglaTribunal = siglaTribunal;
		this.cadastroCliente = cadastroCliente;
		this.cadastroParteContraria = cadastroParteContraria;
		this.fase = fase;
		this.strfy = strfy;
	};
	
	//generate getters and setters;
	public String getTribunal() {
		return siglaTribunal;
	}

	public String getCliente() {
		return cadastroCliente;
	}

	public String getParteContraria() {
		return cadastroParteContraria;
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
		this.audiencias.add(new AudienciaDto(data, recomendacao, advogadoRegistro));
	}

	public void addDespesa(Date data, String descricao, double valor) {
		this.despesas.add(new DespesaDto(data, descricao, valor));
	}

	public String getAudienciasToString() {
		StringBuilder sb = new StringBuilder();
		for (AudienciaDto i : this.audiencias) {
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
		for (DespesaDto i : this.despesas) {
			sb.append(i.toString() + "\n");
		}
		return sb.toString();
	}

	public double getTotalCustas() {
		double total = 0.0;
		for (DespesaDto i : this.despesas) {
			total += i.getValor();
		}
		return total;
	}
	
	public String toString(){
		return strfy;
	}
	
}