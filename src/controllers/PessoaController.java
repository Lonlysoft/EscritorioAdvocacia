package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Tribunal;
import dtos.TribunalDto;
import exceptions.TribunalException;

public class PessoaController implements Serializable {

	private static final long serialVersionUID = -4125026648925415158L;
	
	private Map<String, PessoaFisica> pessoasFisicas;
	private Map<String, PessoaJuridica> pessoasJuridicas;
	private Map<String, Advogado> advogados;

	protected PessoaController() {
		pessoas = new TreeMap<>();
	}

	public void createPessoaFisica(PessoaFisicaDto pfDto) throws PessoaException {

		if (pessoas.get(tribunalDto.getSigla()) != null) {
			throw new TribunalException("Já existe tribunal cadastrado para a sigla: " + tribunalDto.getSigla());
		}

		PessoaFisica pf = PessoaFisica(pfDto.getNome(), pfDto.getTelefone(), new Email(pfDto.getEmail()), new Cpf(pfDto.getCpf()));

		pessoasFisicas.put(pf.getRegistroRF(), pf
		);
		
		MainController.save();
	}
	

	public void updatePessoaFisica(PessoaFisicaDto pfDto) throws PessoaFisicaException {

		PessoaFisica pf = pessoasFisicas.get(pfDto.getCpf());

		if (tribunal == null)
			throw new PessoaException("Não tem tribunal cadastrado para a sigla: " + tribunalDto.getSigla());

		tribunal.setNome(tribunalDto.getNome());
		tribunal.setSecao(tribunalDto.getSecao());
		
		MainController.save();
	}

	public TribunalDto getPessoaFisica(String sigla) throws TribunalException {

		Tribunal tribunal = pessoas.get(sigla);

		if (tribunal == null)
			throw new TribunalException("Não tem tribunal cadastrado para a sigla: " + sigla);

		TribunalDto tribunalDto = new TribunalDto(tribunal.getSigla(), tribunal.getNome(), tribunal.getSecao());

		return tribunalDto;
	}

	public ListPessoasFisicas<TribunalDto> getpessoas() {

		List<PessoaFisicaDto> lista = new ArrayList<>();

		PessoaFisicaDto pfDto;

		for (PessoaFisica pf : pessoasFisicas.values()) {
			pfDto = new PessoaFisicaDto();
			lista.add(pfDto);
		}

		return lista;
	}
	
	public PessoaFisicaDto findPreposto(cpf){
		return pessoasJuridicas.get(cpf);
	}
}
