package domain;
import java.util.ArrayList;
import java.util.Date;

import contracts.IPessoa;
import enumerations.EFaseProcesso;

public class Processo implements Serializable{
	private final long numero;
	private final Date dataAbertura;
	private final Tribunal tribunal;
	private EFaseProcesso fase;
	private final ArrayList<Audiencia> audiencias = new ArrayList<Audiencia>();
	private final ArrayList<Despesa> custas = new ArrayList<Despesa>();
	private final IPessoa cliente, parteContraria;

	public Processo(long numero, Date dataAbertura, Tribunal tribunal, EFaseProcesso fase, IPessoa cliente,
			IPessoa parteContraria) {
		this.numero = numero;
		this.dataAbertura = dataAbertura;
		this.tribunal = tribunal;
		this.fase = fase;
		this.cliente = cliente;
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

	public String getAudiencias() {
		StringBuilder sb = new StringBuilder();
		for (Audiencia i : this.audiencias) {
			sb.append("" + i.list()+ "\n");
		}
		return sb.toString();
	}

	public String getCustas() {
		StringBuilder sb = new StringBuilder();
		for (Despesa i : this.custas) {
			sb.append(i.list() + "\n");
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
		sb.append("\nProcesso no. " + this.getNumero() + " | fase: " + this.fase + "\n");
		sb.append("Cliente: "+this.cliente.list());
		sb.append("\nParte Contraria: " + this.parteContraria.list());
		sb.append("\nAudiencias até agora: \n" + this.getAudiencias());
		sb.append("cusots até agora: \n" + this.getCustas());
		sb.append("custos totais: " + this.getTotalCustas() + '\n');
		return sb.toString();
	}
}
