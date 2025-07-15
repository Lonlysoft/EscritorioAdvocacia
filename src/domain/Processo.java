package domain;
import java.util.ArrayList;
import java.util.Date;

import contracts.IPessoa;
import enumerations.EFaseProcesso;

public class Processo implements IPessoa, Serializable{
	
	private final String numero;
	private final Date dataAbertura;
	private final Tribunal tribunal;
	private EFaseProcesso fase;
	private final ArrayList<Audiencia> audiencias = new ArrayList<Audiencia>();
	private final ArrayList<Despesa> custas = new ArrayList<Despesa>();
	private IPessoa cliente, parteContraria;

	public Processo(String numero, Date dataAbertura, Tribunal tribunal, EFaseProcesso fase, IPessoa cliente,
			IPessoa parteContraria) throws ProcessoException{
		this.numero = numero;
		this.dataAbertura = dataAbertura;
		if(tribunal == null){
			throw new ProcessoException("tribunal deve ser declarado");
		}
		this.tribunal = tribunal;
		this.fase = fase;
		
		if(cliente == null){
			throw new ProcessoException("Cliente não declarado");
		}
		this.cliente = cliente;
		if(parteContraria == null){
			throw new ProcessoException("Parte Contraria não declarada");
		}
		this.parteContraria = parteContraria;
	}

	public Tribunal getTribunal() {
		return tribunal;
	}

	public IPessoa getCliente() {
		return cliente;
	}

	public IPessoa getParteContraria() {
		return parteContraria;
	}

	public EFaseProcesso getFase() {
		return fase;
	}

	public void setFase(EFaseProcesso fase) {
		this.fase = fase;
	}

	public long getNumero() {
		return numero;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void addAudiencia(Date data, String recomendacao, Advogado advogado) {
		this.audiencias.add(new Audiencia(data, recomendacao, advogado));
	}

	public void addDespesa(Date data, String descricao, double valor) {
		this.custas.add(new Despesa(data, descricao, valor));
	}
	
	public ArrayList<Audiencia> getAudiencias(){
		ArrayList<Audiencia> l;
		for (Audiencia i : this.audiencias) {
			l.add(new Audiencia(i.getData(), i.getRecomendacao(), i.getAdvogado()));
		}
		return l;
	}
	
	public ArrayList<Despesa> getDespesas(){

		ArrayList<Despesa> l;

		for (Despesa i : this.despesas) {
			l.add(new Despesa(i.getData, i.getDescricao(), i.getValor()));
		}
		return l;
	}

	public String getAudienciasToString() {
		StringBuilder sb = new StringBuilder();
		for (Audiencia i : this.audiencias) {
			sb.append("" + i.toString()+ "\n");
		}
		return sb.toString();
	}

	public String getCustasToString() {
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("tribunal " + this.tribunal.getSigla() + " seção: " + this.tribunal.getSecao()
				+ "\ndescricao: " + this.tribunal.getDescricao()); // court setting
		sb.append("\nProcesso no. " + this.getNumero() + " \t fase: " + this.fase + "\t");
		sb.append("Cliente: "+this.cliente.toString());
		sb.append("\nParte Contraria: " + this.parteContraria.toString());
		sb.append("\nAudiencias até agora: \n" + this.getAudiencias());
		sb.append("cusots até agora: \n" + this.getCustas());
		sb.append("custos totais: " + this.getTotalCustas() + '\n');
		return sb.toString();
	}
}
