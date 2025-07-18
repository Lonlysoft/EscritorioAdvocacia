package controllers;

import domain.*;
import dtos.ProcessoDto;
import dtos.AdvogadoDto;
import dtos.PessoaFisicaDto;
import dtos.AudienciaDto;
import dtos.PessoaJuridicaDto;
import dtos.DespesaDto;
import dtos.TribunalDto;
import exceptions.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import contracts.IPessoa;
import shared.*;

public class ProcessoController implements Serializable {

	private static final long serialVersionUID = -4125026648925415158L;

	private Map<String, Processo> processos;

	protected ProcessoController() {
		processos = new TreeMap<>();
	}

	public void createProcesso(ProcessoDto pDto) throws ProcessoException, PessoaException, TribunalException {
		if (processos.get(pDto.getNumero()) != null) {
			throw new ProcessoException("Já existe Processo cadastrado para o Numero: " + pDto.getNumero());
		}
		
		PessoaController pc = MainController.getPessoaController();
		TribunalController tc = MainController.getTribunalController();
		
		IPessoa cliente = (IPessoa) pc.getPessoaFisica(pDto.getCliente());
		if (cliente == null) {
			cliente = (IPessoa) pc.getPessoaJuridica(pDto.getCliente());
		}
		
		IPessoa parteContraria = (IPessoa) pc.getPessoaFisica(pDto.getParteContraria());
		if (parteContraria == null) {
			parteContraria = (IPessoa) pc.getPessoaJuridica(pDto.getParteContraria());
		}
		
		if (cliente == null || parteContraria == null) {
			throw new ProcessoException("Cliente ou Parte Contrária não encontrados no sistema");
		}
		
		TribunalDto td = tc.getTribunal(pDto.getTribunal());
		if (td == null) {
			throw new ProcessoException("Tribunal não encontrado");
		}
		
		Tribunal tri = new Tribunal(td.getSigla(), td.getNome(), td.getSecao());
		
		Processo p = new Processo(
			pDto.getNumero(),
			pDto.getDataAbertura(),
			tri, 
			pDto.getFase(),
			cliente,
			parteContraria);

		processos.put(p.getNumero(), p);
		MainController.save();
	}
	
	public void deleteProcesso(String numero) throws ProcessoException {
		if (processos.remove(numero) == null) {
			throw new ProcessoException("Processo não encontrado para o número: " + numero);
		}
		MainController.save();
	}
	
	public void addAudienciaToProcesso(String numProcesso, AudienciaDto audienciaDto) throws PessoaException, EmailException, CpfException{
		PessoaController pc = MainController.getPessoaController();
		Processo p = processos.get(numProcesso);
		AdvogadoDto ad = pc.getAdvogado(audienciaDto.getAdvogado());
		PessoaFisicaDto pfDto = pc.getPessoaFisica(ad.getCpf());
		PessoaFisica pf = new PessoaFisica(pfDto.getNome(), new Email(pfDto.getEmail()), pfDto.getTelefone(), new Cpf(pfDto.getCpf()));
		Advogado a = new Advogado(pf, ad.getRegistro());
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

	public void updateProcesso(ProcessoDto pDto) throws ProcessoException, TribunalException{
		Processo p = processos.get(pDto.getNumero());

		if (p == null){
			throw new ProcessoException("Processo não encontrado");
		}
		p.setFase(p.getFase());
		
		TribunalController tc = MainController.getTribunalController();
		TribunalDto td = tc.getTribunal(pDto.getTribunal());
		Tribunal tri = new Tribunal(td.getSigla(), td.getNome(), td.getSecao());
		p.setTribunal(tri);
		
		MainController.save();
	}
	
	public void removeProcesso(String num){
		processos.remove(num);
		MainController.save();
	}

	public ProcessoDto getProcesso(String numero) throws ProcessoException {
		Processo p = processos.get(numero);

		if (p == null)
			throw new ProcessoException("Não tem Processo cadastrado para o numero: " + numero);
		ProcessoDto pDto = new ProcessoDto(p.getNumero(), p.getDataAbertura(), p.getTribunal().getSigla(), p.getFase(), p.getCliente().getCadastroRF(), p.getParteContraria().getCadastroRF());
		
		return pDto;
	}

	public List<ProcessoDto> getProcessos() {
		List<ProcessoDto> lista = new ArrayList<>();

		ProcessoDto pDto;

		for (Processo p : processos.values()) {
			pDto = new ProcessoDto(p.getNumero(), p.getDataAbertura(), p.getTribunal().getSigla(), p.getFase(), p.getCliente().getCadastroRF(), p.getParteContraria().getCadastroRF(), p.toString());
			ArrayList<Audiencia> la = p.getAudiencias();
			ArrayList<Despesa> ld = p.getCustas();
			for(Audiencia i : la){
				pDto.addAudiencia(i.getData(), i.getRecomendacao(), i.getRegistroAdvogado());
			}
			
			for(Despesa i : ld){
				pDto.addDespesa(i.getData(), i.getDescricao(), i.getValor());
			}
			
			lista.add(pDto);
		}

		return lista;
	}
}