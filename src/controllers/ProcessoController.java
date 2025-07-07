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

		Processo p = new Processo(pDto.getNumero(), pDto.getDataAbertura(), pDto.getFase(), (clientePf == null)? clientePj : clientePf, (parteContrariaPf == null)? parteContrariaPj : parteContrariaPf);

		processos.put(p.getNumero(), p);

		MainController.save();
	}
	
	public void addAudienciaToProcesso(String numProcesso, AudienciaDto audienciaDto){
		Processo p = processos.get(numProcesso);
		p.addAudiencia(audienciaDto.getData(), audienciaDto.getRecomendacao(), /*como eu posso adicionar um advogado aqui???*/);
	}

	public void updateProcesso(ProcessoDto ProcessoDto) throws ProcessoException {

		Processo p = processos.get(ProcessoDto.getNumero());

		if (p == null)
			throw new ProcessoException("Não tem Processo cadastrado para o Numero: " + ProcessoDto.getNumero());

		p.setNumero(p.getNumero());
		p.setData(new Date(p.getData()));

		MainController.save();
	}

	public ProcessoDto getProcesso(String sigla) throws ProcessoException {

		Processo p = processos.get(sigla);

		if (p == null)
			throw new ProcessoException("Não tem Processo cadastrado para a sigla: " + sigla);

		ProcessoDto pDto = new ProcessoDto();

		return pDto;
	}

	public List<ProcessoDto> getprocessos() {

		List<ProcessoDto> lista = new ArrayList<>();

		ProcessoDto pDto;

		for (Processo p : processos.values()) {
			pDto = new ProcessoDto(p.getNumero(), p.getCliente(), p.getParteContraria(), p.get());
			lista.add(pDto);
		}

		return lista;
	}

}
