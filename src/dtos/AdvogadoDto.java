package dtos;

public class AdvogadoDto{
	private String cpf;
	private String registro;
	private String str;

	public AdvogadoDto(String cpf, String registro) {
		this.cpf = cpf;
		this.registro = registro;
	}
	
	public AdvogadoDto(String cpf, String registro, String str) {
		this.cpf = cpf;
		this.registro = registro;
		this.str = str;
	}
	
	public String getRegistro() {
		return registro;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public String toString(){
		return this.str;
	}
}