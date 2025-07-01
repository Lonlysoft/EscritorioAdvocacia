package controllers;

import java.io.Serializable;

import persistence.Serializer;

/* 
 * Design Pattern Singleton 
 */
public class MainController implements Serializable {

	private static final long serialVersionUID = 3326741752317644589L;

	private static MainController instance;
	
	private TribunalController tribunalController;
	
	private ProcessoController processoController;
	
	private PessoaFisicaController pessoaFisicaController;
	
	private PessoaJuridicaController pessoaJuridicaController;
	
	private AdvogadoController advogadoController;
	
	private DespesaController despesaController;
	
	private ProcessoController processoController;
	
	
	private MainController() {
		
		tribunalController = new TribunalController();
		
		processoController = new ProcessoController();
		
		pessoaFisicaController = new PessoaFisicaController();
		
		pessoaJuridicaController = new PessoaJuridicaController();
		
		advogadoController = new AdvogadoController();
		
		despesaController = new DespesaController();
		
		processoController = new ProcessoController();
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
