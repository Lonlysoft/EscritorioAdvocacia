package controllers;

import domain.Advogado;
import domain.PessoaFisica;
import domain.PessoaJuridica;
import dtos.PessoaFisicaDto;
import dtos.PessoaJuridicaDto;
import dtos.AdvogadoDto;
import exceptions.CpfException;
import exceptions.CnpjException;
import exceptions.EmailException;
import exceptions.PessoaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import shared.Cpf;
import shared.Email;
import shared.Cnpj;

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

		pessoasFisicas.put(pfDto.getCpf(), pf);
		
		MainController.save();
	}

	public void updatePessoaFisica(PessoaFisicaDto pfDto) throws PessoaException, EmailException {

		PessoaFisica pf = pessoasFisicas.get(pfDto.getCpf());

		if (pf == null)
			throw new PessoaException("Não tem pessoa cadastrado para o CPF: " + pfDto.getCpf());

		pf.setNome(pfDto.getNome());
		pf.setEmail(pfDto.getEmail());
		pf.setTelefone(pfDto.getTelefone());
		
		MainController.save();
	}

	public PessoaFisicaDto getPessoaFisica(String cpf) throws PessoaException{

		PessoaFisica pessoa = pessoasFisicas.get(cpf);

		if (pessoa == null)
			throw new PessoaException("Não tem pessoa cadastrado para o CPF: " + cpf);

		PessoaFisicaDto pessoaDto = new PessoaFisicaDto(pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getCadastroRF());

		return pessoaDto;
	}
	
	public List<PessoaFisicaDto> getpessoasFisicas() {

		List<PessoaFisicaDto> lista = new ArrayList<>();

		PessoaFisicaDto pfDto;

		for (PessoaFisica pf : pessoasFisicas.values()) {
			pfDto = new PessoaFisicaDto(pf.getNome(), pf.getEmail(), pf.getTelefone(), pf.getCadastroRF());
			lista.add(pfDto);
		}

		return lista;
	}
	
	public void removePessoaFisica(String cnpj){
		this.pessoasJuridicas.remove(cnpj);
	}
	
	public void createAdvogado(AdvogadoDto advDto) throws PessoaException, CpfException, EmailException {

		if (advogados.get(advDto.getRegistro()) != null) {
			throw new PessoaException("Já existe pessoa cadastrado para o registro: " + advDto.getRegistro());
		}
		
		PessoaFisica pf = pessoasFisicas.get(advDto.getCpf());

		Advogado adv = new Advogado(pf, advDto.getRegistro());

		advogados.put(adv.getRegistro(), adv);
		
		MainController.save();
	}

	public AdvogadoDto getAdvogado(String registro) throws PessoaException{

		Advogado pessoa = advogados.get(registro);

		if (pessoa == null)
			throw new PessoaException("Não tem advogado cadastrado para o registro: " + registro);

		AdvogadoDto advogadoDto = new AdvogadoDto(pessoa.getRegistro(), pessoa.getCadastroRF());

		return advogadoDto;
	}
	
	public List<AdvogadoDto> getAdvogados() {

		List<AdvogadoDto> lista = new ArrayList<>();

		AdvogadoDto advDto;

		for (Advogado adv : advogados.values()) {
			advDto = new AdvogadoDto(adv.getCadastroRF(), adv.getRegistro(), adv.toString());
			lista.add(advDto);
		}

		return lista;
	}
	
	public void createPessoaJuridica(PessoaJuridicaDto pjDto) throws PessoaException, CnpjException, EmailException {

		if (pessoasJuridicas.get(pjDto.getCnpj()) != null) {
			throw new PessoaException("Já existe pessoa cadastrado para o Cnpj: " + pjDto.getCnpj());
		}

		PessoaJuridica pj = new PessoaJuridica(pjDto.getNome(), new Email(pjDto.getEmail()), pjDto.getTelefone(), new Cnpj(pjDto.getCnpj()), pessoasFisicas.get(pjDto.getCpf()));

		pessoasJuridicas.put(pj.getCadastroRF(), pj);
		
		MainController.save();
	}

	public void updatePessoaJuridica(PessoaJuridicaDto pjDto) throws PessoaException, EmailException {
		
		PessoaJuridica pj = pessoasJuridicas.get(pjDto.getCnpj());
		if (pj == null)
			throw new PessoaException("Não tem pessoa cadastrado para o Cnpj: " + pjDto.getCnpj());
		pj.setNome(pjDto.getNome());
		pj.setEmail(pjDto.getEmail());
		pj.setTelefone(pjDto.getTelefone());
		
		MainController.save();
	}

	public PessoaJuridicaDto getPessoaJuridica(String cnpj) throws PessoaException{

		PessoaJuridica pessoa = pessoasJuridicas.get(cnpj);

		if (pessoa == null)
			throw new PessoaException("Não tem pessoa cadastrado para o Cnpj: " + cnpj);

		PessoaJuridicaDto pessoaDto = new PessoaJuridicaDto(pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getCadastroRF(), pessoa.getPreposto().getCadastroRF());

		return pessoaDto;
	}
	
	public void removePessoaJuridica(String cnpj){
		this.pessoasJuridicas.remove(cnpj);
	}
	
	public List<PessoaJuridicaDto> getpessoasJuridicas() {
		List<PessoaJuridicaDto> lista = new ArrayList<>();

		PessoaJuridicaDto pjDto;

		for (PessoaJuridica pj : pessoasJuridicas.values()) {
			pjDto = new PessoaJuridicaDto(pj.getNome(), pj.getEmail(), pj.getTelefone(), pj.getCadastroRF(), pj.getPreposto().getCadastroRF());
			lista.add(pjDto);
		}

		return lista;
	}

}
