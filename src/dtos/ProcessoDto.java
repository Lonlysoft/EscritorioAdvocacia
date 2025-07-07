public class ProcessoDto{
	private String numero;
	private String dataAbertura;
	private String siglaTribunal;
	private EFaseProcesso fase;
	private ArrayList<AudienciaDto> audiencias = new ArrayList<AudienciaDto>();
	private ArrayList<DespesaDto> custas = new ArrayList<DespesaDto>();
	public ProcessoDto(String numero, Date dataAbertura, String siglaTribunal, EFaseProcesso fase, String cadastroCliente,
			String CadastroParteContraria)){
	  
	}
}