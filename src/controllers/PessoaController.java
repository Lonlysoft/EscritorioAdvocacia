package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Advogado;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import dtos.PessoaFisicaDto;
import exceptions.CpfException;
import exceptions.EmailException;
import exceptions.PessoaException;
import shared.Cpf;
import shared.Email;

public class PessoaController implements Serializable {

	private static final long serialVersionUID = -4125026648925415158L;

	private Map<String, PessoaFisica> pessoasFisicas;
	private Map<String, PessoaJuridica> pessoasJuridicas;
	private Map<String, Advogado> advogados;

	protected PessoaController() {
		pessoasFisicas = new TreeMap<>();
		pessoasJuridicas = new TreeMap<>();
		advogados = new TreeMap<>();
	}

	public void createPessoaFisica(PessoaFisicaDto pfDto) throws PessoaException, CpfException, EmailException {

		if (pessoasFisicas.get(pfDto.getCpf()) != null) {
			throw new PessoaException("Já existe pessoa cadastrado para o CPF: " + pfDto.getCpf());
		}

		PessoaFisica pf = new PessoaFisica(pfDto.getNome(), new Email(pfDto.getEmail()), pfDto.getTelefone(), new Cpf(pfDto.getCpf()));

		pessoasFisicas.put(pf.getCpf(), pf);
		
		MainController.save();
	}

	public void updatePessoaFisica(PessoaFisicaDto pfDto) throws PessoaException, EmailException {

		PessoaFisica pf = pessoasFisicas.get(pfDto.getCpf());

		if (pf == null)
			throw new PessoaException("Não tem pessoa cadastrado para o CPF: " + pfDto.getCpf());

		pf.setNome(pfDto.getNome());
		pf.setEmail(pfDto.getEmail());

		MainController.save();
	}

	public PessoaFisicaDto getPessoaFisica(String cpf) throws PessoaException{

		PessoaFisica pessoa = pessoasFisicas.get(cpf);

		if (pessoa == null)
			throw new PessoaException("Não tem pessoa cadastrado para o CPF: " + cpf);

		PessoaFisicaDto pessoaDto = new PessoaFisicaDto(pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getCpf());

		return pessoaDto;
	}

	public List<PessoaFisicaDto> getpessoas() {

		List<PessoaFisicaDto> lista = new ArrayList<>();

		PessoaFisicaDto pfDto;

		for (PessoaFisica pf : pessoasFisicas.values()) {
			pfDto = new PessoaFisicaDto(pf.getNome(), pf.getEmail(), pf.getTelefone(), pf.getCadastroRF());
			lista.add(pfDto);
		}

		return lista;
	}

}
