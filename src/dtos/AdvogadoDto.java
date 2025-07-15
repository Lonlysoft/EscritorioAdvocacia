package dtos;

public class AdvogadoDto{
	private String cpf;
	private String registro;

	public AdvogadoDto(String cpf, String registro) {
		this.cpf = cpf;
		this.registro = registro;
	}
	
	
	public String getRegistro() {
		return registro;
	}
	
	public String getCpf() {
		return this.cpf;
	}
}