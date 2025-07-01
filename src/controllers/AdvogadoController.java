package controller;

public class AdvogadoController{
	private Map<String, Advogado> advogados;
	public AdvogadoController(){
		this.advogados = new TreeMap<>();
	}
	public void addAdvogado(String nome, Email email, Telefone telefone, Cpf cpf, String registro) throws AdvogadoException{
		PessoaFisica p = new PessoaFisica(nome, email, telefone, cpf);
		Advogado a = new Advogado(p, registro);
		advogados.put(a.getRegistro(), t);
	}
}