package controllers;

import java.io.Serializable;

import persistence.Serializer;

/* 
 * Design Pattern Singleton 
 */
public class MainController implements Serializable {

	private static final long serialVersionUID = 3326741752317644589L;

	private static MainController instance;
	
	private static TribunalController tribunalController;
	
	private static ProcessoController processoController;
	
	private static PessoaFisicaController pessoaFisicaController;
	
	private static PessoaJuridicaController pessoaJuridicaController;
	
	private static AdvogadoController advogadoController;
	
	private static DespesaController despesaController;
	
	private static ProcessoController processoController;
	
	
	private MainController() {
		
		tribunalController = new TribunalController();
		processoController = new ProcessoController();
		pessoaController = new PessoaController();
		
	}

	public static MainController getInstance() {
		return instance;
	}
	
	public static TribunalController getTribunalController() {
		return instance.tribunalController;
	}
	
	public static AdvogadoController getAdvogadoController() {
		return instance.advogadoController;
	}
	
	public static DespesaController getDespesaController() {
		return instance.despesaController;
	}
	
	public static PessoaFisicaController getPessoaFisicaController() {
		return instance.pessoaFisicaController;
	}
	
	public static PessoaJuridicaController getPessoaJuridicaController() {
		return instance.pessoaJuridicaController;
	}
	
	public static ProcessoController getProcessoController() {
		return instance.processoController;
	}
	
	public static void load() {

		instance = Serializer.readFile();

		if (instance == null) {
			instance = new MainController();
		}
	}

	public static void save() {
		Serializer.writeFile(instance);
	}
}
