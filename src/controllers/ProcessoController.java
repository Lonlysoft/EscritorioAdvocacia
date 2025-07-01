package controllers;

import java.io.Serializable;
import java.util.ArrayList;
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

		if (processos.get(pDto.getSigla()) != null) {
			throw new ProcessoException("Já existe Processo cadastrado para a sigla: " + ProcessoDto.getSigla());
		}

		Processo t = new Processo(pDto.getSigla(), pDto.getNome(), pDto.getSecao());

		processos.put(t.getSigla(), t);
		
		MainController.save();
	}

	public void updateProcesso(ProcessoDto ProcessoDto) throws ProcessoException {

		Processo p = processos.get(ProcessoDto.getSigla());

		if (p == null)
			throw new ProcessoException("Não tem Processo cadastrado para a sigla: " + ProcessoDto.getSigla());

		p.setNome(p.getNome());
		p.setSecao(p.getSecao());
		
		MainController.save();
	}

	public ProcessoDto getProcesso(String sigla) throws ProcessoException {

		Processo p = processos.get(sigla);

		if (p == null)
			throw new ProcessoException("Não tem Processo cadastrado para a sigla: " + sigla);

		ProcessoDto pDto = new ProcessoDto(p.getSigla(), p.getNome(), p.getSecao());

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
