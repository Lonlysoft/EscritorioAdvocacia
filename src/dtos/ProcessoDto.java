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
	public ProcessoDto(String numero, Date dataAbertura, String siglaTribunal, EFaseProcesso fase, String cadastroCliente, String CadastroParteContraria)){
	  this.dataAbertura = dataAbertura;
	  this.numero = numero;
	  this.siglaTribunal = siglaTribunal;
	  this.cadastroCliente = cadastroCliente;
	  this.cadastroParteContraria = cadastroParteContraria
	  this.fase = fase;
	}
	//generate getters and setters;
}