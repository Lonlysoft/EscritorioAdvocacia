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

		Processo t = new Processo(pDto.getNumero(), pDto.getDataAbertura() pDto.getFase(), );

		processos.put(t.getNumero(), t);

		MainController.save();
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
