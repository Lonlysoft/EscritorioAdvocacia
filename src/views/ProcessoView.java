package views;

import dtos.AdvogadoDto;
import dtos.PessoaFisicaDto;
import dtos.AudienciaDto;
import dtos.PessoaJuridicaDto;
import dtos.DespesaDto;
import dtos.ProcessoDto;
import dtos.TribunalDto;
import exceptions.*;
import contracts.IPessoa;
import enumerations.EFaseProcesso;
import controllers.MainController;
import controllers.ProcessoController;
import controllers.PessoaController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

class ProcessoView extends JFrame {
	private static final long serialVersionUID = -3992635947127490235L;
	
	private ProcessoController processoController;
	private ArrayList<AudienciaDto> audienciaHandler = new ArrayList<>();
	private ArrayList<DespesaDto> despesaHandler = new ArrayList<>();
	
	private JPanel pnlContainer;
	private JPanel pnlFooterContainer;
	
	private JPanel pnlNumeroBuscar;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JButton btnNumeroBuscar;
	
	private JPanel pnlTxtFields;
	
	// Container formulario
	private JPanel pnlTribunal;
	private JLabel lblTribunal;
	private JTextField txtTribunal;
	private JButton btnTribunalBuscar;
	
	private JPanel pnlFaseContainer;
	private JLabel lblFase;
	private JComboBox<EFaseProcesso> ddmFase;
	
	private JPanel pnlDataContainer;
	private JLabel lblData;
	private JFormattedTextField txtData;
	
	private JPanel pnlRegistroClienteBuscar;
	private JLabel lblRegistroCliente;
	private JTextField txtRegistroCliente;
	private JButton btnRegistroCliente;
	
	private JPanel pnlRegistroParteContrariaBuscar;
	private JLabel lblRegistroParteContraria;
	private JTextField txtRegistroParteContraria;
	private JButton btnRegistroParteContraria;
	
	private JTextArea txtAudienciasLista;
	private JScrollPane scrollAudiencias;
	private JTextArea txtDespesasLista;
	private JScrollPane scrollDespesas;
	
	private JPanel pnlAudiencia;
	private JButton btnAddAudiencias;
	private JButton btnResetAllAud;
	
	private JPanel pnlDespesa;
	private JButton btnAddDespesas;
	private JButton btnResetAllDes;
	
	// Footer
	private JButton btnSalvar;
	private JButton btnRemover;
	private JButton btnListar;

	public ProcessoView(ProcessoController ctrler) {
		processoController = ctrler;
		initialize();
	}
	
	private void initialize() {
		this.setTitle("Gerenciamento de Processos");
		this.setSize(800, 600);
		this.setMinimumSize(new Dimension(600, 400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Painel principal
		pnlContainer = new JPanel(new BorderLayout(10, 10));
		pnlContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Painel de busca por número
		pnlNumeroBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblNumero = new JLabel("Número do Processo: ");
		txtNumero = new JTextField(15);
		btnNumeroBuscar = new JButton("Buscar");
		
		pnlNumeroBuscar.add(lblNumero);
		pnlNumeroBuscar.add(txtNumero);
		pnlNumeroBuscar.add(btnNumeroBuscar);
		
		// Painel de campos do formulário
		pnlTxtFields = new JPanel();
		pnlTxtFields.setLayout(new BoxLayout(pnlTxtFields, BoxLayout.Y_AXIS));
		
		// Tribunal
		pnlTribunal = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblTribunal = new JLabel("Tribunal: ");
		txtTribunal = new JTextField(15);
		btnTribunalBuscar = new JButton("Buscar");
		pnlTribunal.add(lblTribunal);
		pnlTribunal.add(txtTribunal);
		pnlTribunal.add(btnTribunalBuscar);
		
		// Fase
		pnlFaseContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblFase = new JLabel("Fase: ");
		ddmFase = new JComboBox<>(EFaseProcesso.values());
		pnlFaseContainer.add(lblFase);
		pnlFaseContainer.add(ddmFase);
		
		// Data
		pnlDataContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblData = new JLabel("Data: ");
		txtData = new JFormattedTextField(new SimpleDateFormat("dd-MM-yyyy"));
		txtData.setColumns(10);
		pnlDataContainer.add(lblData);
		pnlDataContainer.add(txtData);
		
		// Registro Cliente
		pnlRegistroClienteBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblRegistroCliente = new JLabel("Cliente: ");
		txtRegistroCliente = new JTextField(15);
		btnRegistroCliente = new JButton("Buscar");
		pnlRegistroClienteBuscar.add(lblRegistroCliente);
		pnlRegistroClienteBuscar.add(txtRegistroCliente);
		pnlRegistroClienteBuscar.add(btnRegistroCliente);
		
		// Registro Parte Contrária
		pnlRegistroParteContrariaBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblRegistroParteContraria = new JLabel("Parte Contrária: ");
		txtRegistroParteContraria = new JTextField(15);
		btnRegistroParteContraria = new JButton("Buscar");
		pnlRegistroParteContrariaBuscar.add(lblRegistroParteContraria);
		pnlRegistroParteContrariaBuscar.add(txtRegistroParteContraria);
		pnlRegistroParteContrariaBuscar.add(btnRegistroParteContraria);
		
		// Adicionando campos ao painel principal
		pnlTxtFields.add(pnlNumeroBuscar);
		pnlTxtFields.add(pnlTribunal);
		pnlTxtFields.add(pnlFaseContainer);
		pnlTxtFields.add(pnlDataContainer);
		pnlTxtFields.add(pnlRegistroClienteBuscar);
		pnlTxtFields.add(pnlRegistroParteContrariaBuscar);
		
		// Painel de Audiências
		pnlAudiencia = new JPanel(new BorderLayout(5, 5));
		txtAudienciasLista = new JTextArea(5, 20);
		txtAudienciasLista.setEditable(false);
		scrollAudiencias = new JScrollPane(txtAudienciasLista);
		
		JPanel pnlAudienciaButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAddAudiencias = new JButton("Adicionar Audiência");
		btnResetAllAud = new JButton("Limpar");
		pnlAudienciaButtons.add(btnResetAllAud);
		pnlAudienciaButtons.add(btnAddAudiencias);
		
		pnlAudiencia.add(new JLabel("Audiências:"), BorderLayout.NORTH);
		pnlAudiencia.add(scrollAudiencias, BorderLayout.CENTER);
		pnlAudiencia.add(pnlAudienciaButtons, BorderLayout.SOUTH);
		
		// Painel de Despesas
		pnlDespesa = new JPanel(new BorderLayout(5, 5));
		txtDespesasLista = new JTextArea(5, 20);
		txtDespesasLista.setEditable(false);
		scrollDespesas = new JScrollPane(txtDespesasLista);
		
		JPanel pnlDespesaButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAddDespesas = new JButton("Adicionar Despesa");
		btnResetAllDes = new JButton("Limpar");
		pnlDespesaButtons.add(btnResetAllDes);
		pnlDespesaButtons.add(btnAddDespesas);
		
		pnlDespesa.add(new JLabel("Despesas:"), BorderLayout.NORTH);
		pnlDespesa.add(scrollDespesas, BorderLayout.CENTER);
		pnlDespesa.add(pnlDespesaButtons, BorderLayout.SOUTH);
		
		// Painel para audiências e despesas
		JPanel pnlLists = new JPanel(new GridLayout(2, 1, 10, 10));
		pnlLists.add(pnlAudiencia);
		pnlLists.add(pnlDespesa);
		
		// Footer
		pnlFooterContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		btnSalvar = new JButton("Salvar");
		btnRemover = new JButton("Remover");
		btnListar = new JButton("Listar");
		pnlFooterContainer.add(btnListar);
		pnlFooterContainer.add(btnRemover);
		pnlFooterContainer.add(btnSalvar);
		
		// Adicionando tudo ao container principal
		pnlContainer.add(pnlTxtFields, BorderLayout.NORTH);
		pnlContainer.add(pnlLists, BorderLayout.CENTER);
		pnlContainer.add(pnlFooterContainer, BorderLayout.SOUTH);
		
		this.add(pnlContainer);
		
		// Configuração dos listeners
		btnNumeroBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscar();
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvar();
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRemover();
			}
		});
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
		
		btnRegistroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscarPessoa(txtRegistroCliente.getText());
			}
		});
		
		btnRegistroParteContraria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscarPessoa(txtRegistroParteContraria.getText());
			}
		});
		
		btnAddAudiencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAdicionarAudiencia();
			}
		});
		
		btnResetAllAud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLimparAudiencias();
			}
		});
		
		btnAddDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAdicionarDespesa();
			}
		});
		
		btnResetAllDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionLimparDespesas();
			}
		});
	}
	
	private void actionBuscar() {
		String numero;
		ProcessoDto dto;
		
		numero = txtNumero.getText();
		
		try{
			dto = this.processoController.getProcesso(numero);
			txtData.setText(dto.getDataAbertura().toString());
			ddmFase.setSelectedItem(dto.getFase());
			txtTribunal.setText(dto.getTribunal());
			txtNumero.setText(dto.getNumero());
			txtRegistroCliente.setText(dto.getCliente());
			txtRegistroParteContraria.setText(dto.getParteContraria());
			this.audienciaHandler = dto.getAudiencias();
			this.despesaHandler = dto.getDespesas();
			txtAudienciasLista.setText(dto.getAudienciasToString());
		}
		catch(ProcessoException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void actionSalvar() {
		ProcessoDto dto;
		try {
			dto = new ProcessoDto(txtNumero.getText(), new Date(txtData.getText()), txtTribunal.getText(), (EFaseProcesso) ddmFase.getSelectedItem(), txtRegistroCliente.getText(),txtRegistroParteContraria.getText());
			processoController.createProcesso(dto);
			for(DespesaDto d : this.despesaHandler){
				processoController.addDespesaToProcesso(txtNumero.getText(), d);
			}
			
			for(AudienciaDto d : this.audienciaHandler){
				processoController.addAudienciaToProcesso(txtNumero.getText(), d);
			}
			
			JOptionPane.showMessageDialog(this, "Processo salvo com sucesso.");
			clearInputs();
		} catch (ProcessoException e) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar processo: " + e.getMessage());
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar processo: " + e.getMessage());
		} catch (CpfException e) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar processo: " + e.getMessage());
		} catch (TribunalException e) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar processo: " + e.getMessage());
		} catch (EmailException e) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar processo: " + e.getMessage());
		}
		
	}
	
	private void actionRemover() {
		String numero = txtNumero.getText();
		ProcessoDto processo;
		try{
			processo = processoController.getProcesso(numero);
			processoController.removeProcesso(numero);
			JOptionPane.showMessageDialog(this, "Processo removido.");
		} catch(ProcessoException e) {
			JOptionPane.showMessageDialog(this, "Processo não encontrado.");
		}
	}
	
	private void actionListar() {
		List<ProcessoDto> lista = processoController.getProcessos();
		StringBuilder builder = new StringBuilder();
		for (ProcessoDto dto : lista) {
			builder.append(dto.toString()).append("\n");
		}
	}
	
	private void actionBuscarPessoa(String registro) {
		try{
			PessoaController pessoaController = MainController.getPessoaController();
			IPessoa pf = (IPessoa) pessoaController.getPessoaFisica(registro);
			IPessoa pj = (IPessoa) pessoaController.getPessoaJuridica(registro);
		}
		catch (PessoaException e){
			JOptionPane.showMessageDialog(this, "isso não é um CPF nem um Cnpj, ou seja, não se encontraria em nossa base de dados nem se você quisesse.");
		}
	}
	
	private void actionAdicionarAudiencia() {
		AudienciaView av = new AudienciaView(processoController, this);
		av.setVisible(true);
	}
	
	private void actionLimparAudiencias() {
		audienciaHandler.clear();
		txtAudienciasLista.setText("");
	}
	
	private void actionAdicionarDespesa() {
		DespesaView dv = new DespesaView( processoController, this);
		dv.setVisible(true);
	}
	
	private void actionLimparDespesas() {
		despesaHandler.clear();
		txtDespesasLista.setText("");
	}
	protected void actionListarAudiencias() {
		StringBuilder builder = new StringBuilder();
		for (AudienciaDto dto : this.audienciaHandler) {
			builder.append(dto.toString()).append("\n");
		}
		txtAudienciasLista.setText(builder.toString());
	}
	
	protected void actionListarDespesas() {
		StringBuilder builder = new StringBuilder();
		for (DespesaDto dto : this.despesaHandler) {
			builder.append(dto.toString()).append("\n");
		}
		txtAudienciasLista.setText(builder.toString());
	}
	
	public ArrayList<AudienciaDto> getAudiencias(){
		return this.audienciaHandler;
	}
	
	public ArrayList<DespesaDto> getDespesas(){
		return this.despesaHandler;
	}
	
	private void clearInputs(){
		txtData.setText("");
		txtNumero.setText("");
		txtTribunal.setText("");
		txtRegistroCliente.setText("");
		txtRegistroParteContraria.setText("");
	}
	
}