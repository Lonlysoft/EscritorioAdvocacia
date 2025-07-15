package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Processo;
import dtos.ProcessoDto;
import exceptions.ProcessoException;

public class ProcessoController implements Serializable {

	private static final long serialVersionUID = -4125026648925415158L;

	private Map<String, Processo> processos;

	protected ProcessoController() {
		processos = new TreeMap<>();
	}

	public void createProcesso(ProcessoDto pDto) throws ProcessoException {

		if (processos.get(pDto.getNumero()) != null) {
			throw new ProcessoException("Já existe Processo cadastrado para o Numero: " + pDto.getNumero());
		}
		
		PessoaController pc = MainController.getPessoaController();
		
		TribunalController tc = MainController.getTribunalController();
		PessoaFisicaDto clientePf = null;
		PessoaJuridicaDto clientePj = null;
		clientePf = pc.getPessoaFisica(pDto.getCadastroRF());
		clientePj = pc.getPessoaJuridica(pDto.getCadastroRF());
		
		parteContrariaPf = pc.getPessoaFisica(pDto.getCadastroParteContraria());
		parteContrariaPj = pc.getPessoaJuridica(pDto.getCadastroParteContraria());

		Processo p = new Processo(pDto.getNumero(), pDto.getDataAbertura(), tc.getTribunal(pDto.getTribunal()) pDto.getFase(), (clientePf == null)? clientePj : clientePf, (parteContrariaPf == null)? parteContrariaPj : parteContrariaPf);

		processos.put(p.getNumero(), p);

		MainController.save();
	}
	
	public void addAudienciaToProcesso(String numProcesso, AudienciaDto audienciaDto){
		Processo p = processos.get(numProcesso);
		Advogado a = pessoaController.getAdvogado(audienciaDto.getAdvogado());
		p.addAudiencia(audienciaDto.getData(), audienciaDto.getRecomendacao(), a);
	}
	
	public void addDespesaToProcesso(String numero, DespesaDto dto){
		Processo p = this.processos.get(numero);
		p.addDespesa(dto.getData(), dto.getDescricao(), dto.getValor());
	}
	
	public double getTotalCustasDeProcesso(String numero){
		Processo p = this.processos.get(numero);
		return p.getTotalCustas();
	}

	public void updateProcesso(ProcessoDto pDto) throws ProcessoException {

		Processo p = processos.get(pDto.getNumero());

		if (p == null){
			throw new ProcessoException("processo presente não encontrado novo processo criado");
		}
		p.setFase(p.getFase());
		
		TribunalController tc = MainController.getTribunalController();
		p.setTribunal(tc.getTribunal(pDto.getTribunal()));
		
		p.setCliente();
		p.setParteContraria();
		MainController.save();
	}
	
	

	public ProcessoDto getProcesso(String numero) throws ProcessoException {

		Processo p = processos.get(numero);

		if (p == null)
			throw new ProcessoException("Não tem Processo cadastrado para o numero: " + numero);
		ProcessoDto pDto = new ProcessoDto(p.getNumero(), p.getData(), p.getSiglaTribunal(), p.getFase(), p.getCliente(), p.getParteContraria);
		
		
		return pDto;
	}

	public List<ProcessoDto> getprocessos() {

		List<ProcessoDto> lista = new ArrayList<>();

		ProcessoDto pDto;

		for (Processo p : processos.values()) {
			pDto = new ProcessoDto(p.getNumero(), p.getCliente(), p.getParteContraria(), p.get());
			ArrayList<Audiencia> la = p.getAudiencias();
			ArrayList<Despesa> ld = p.getDespesas();
			for(Audiencia i : la){
				AudienciaDto dto = new AudienciaDto(i.getData(), i.getRecomendacao(), i.getRegistroAdvogado())
				pDto.addAudiencia()
			}
			
			lista.add(pDto);
		}

		return lista;
	}

}
