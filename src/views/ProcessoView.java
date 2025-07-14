package views;

class ProcessoView extends JFrame{
	private ProcessoController processoContolroller;
	private ArrayList<AudienciaDto> audienciaHandler = new ArrayList<>();
	private ArrayList<DespesaDto> despesaHandler = new ArrayList<>();
	
	private JPanel pnlContainer;
	private JPanel pnlFooterContainer;
	
	private JPanel pnlNumeroBuscar;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JButton btnNumeroBuscar;
	
	private JPanel pnlTxtFields;
	
	//inicio container formulario
	private JPanel pnlTribunal
	private JLabel lblTribunal
	private JTextField txtTribunal
	private JButton btnTribunalBuscar
	
	private JPanel pnlFaseContainer;
	private JLabel lblFase;
	private JComboBox<EFaseProcesso> ddmFase;
	private String[4] dropdownOptions = {
		"INICIAL", "INSTRUÇÃO", "DECISÃO", "RECURSO"
	};
	
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
	
	private JPanel pnlAudiencia;
	private JButton AddAud;
	private JButton resetAllAud;
	
	private JPanel pnlDespesa;
	private JButton AddDes;
	private JButton resetAllDes;
	
	
	//fim formulario principal
	
	private JButton btnSalvar;
	private JButton btnRemover;
	private JButton btnListar;
	
	public ProcessoView(){
	  initialize();
	}
	
	private void initialize(){
		this.setSize(250, 500);
		this.setMinimunSize(new Dimension(125, 250));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		lblNumero = new JLabel("número do Processo: ");
		txtNumero = new JTextField(11);
		btnNumeroBuscar = new JButton("buscar");
		
		
		btnNumeroBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscar();
			}
		});
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvar();
			}
		});
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRemover();
			}
		});
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListar();
			}
		});
		
		btnRegistroCliente = new JButton("buscar");
		btnRegistroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscarPessoa(txtRegistroCliente.getText());
			}
		});
		
		btnRegistroParteContraria = new JButton("buscar");
		btnRegistroParteContraria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscarPessoa(txtRegistroParteContraria.getText());
			}
		});
		
		pnlNumeroBuscar.add(txtNumero);
		pnlNumeroBuscar.add(btnNumeroBuscar);
		
		ddmFase = new JComboBox<>(dropdownOptions);
		
		this.pnlDataContainer = new JPanel();
		this.pnlDataContainer.setLayout(new FlowLayout());
		this.lblData = new JLabel("Data");
		this.txtData = new JFormattedTextField("dd-mm-aaaa");
		
		this.add(lblNumero, BorderLayout.WEST);
		this.add(lblFase, BorderLayout.WEST);
		this.add(lblData, BorderLayout.WEST);
		this.add(lblTribunal, BorderLayout.WEST);
		this.add(lblRegistroCliente, BorderLayout.WEST);
		this.add(lblRegistroParteContraria, BorderLayout.WEST);
		
		this.add(pnlNumeroBuscar, BorderLayout.EAST);
		this.add(txtFase, BorderLayout.EAST);
		this.add(txtData, BorderLayout.EAST);
		this.add(txtTribunal, BorderLayout.EAST);
		this.add(pnlRegistroCliente, BorderLayout.EAST);
		this.add(pnlRegistroParteContraria, BorderLayout.EAST);
		
		this.add(pnlFooterContainer, BorderLayout.SOUTH);
	}
	
	private void actionBuscar(){
		String numero;
		ProcessoDto pDto;
		
		numero = txtNumero.getText();
		
		try{
			dto = this.processoContolroller.getProcesso(numero);
			txtData.setText(dto.getData());
			ddmFase.setSelectedItem(dto.getFase());
			txtNumero.setText(dto.getNumero());
			txtRegistroCliente.setText(dto.getCliente());
			txtRegistroParteContraria.setText(dto.getParteContraria());
		}
		catch(ProcessoException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void actionSalvar() {
		ProcessoDto dto;
		try {
			dto = new ProcessoDto();
			dto.setNumero(txtNumero.getText());
			dto.setFase(ddmFase.getSelectedItem().toString());
			dto.setData(txtData.getText());
			dto.setClienteRegistro(txtRegistroCliente.getText());
			dto.setParteContrariaRegistro(txtRegistroParteContraria.getText());
	
			processoController.createProcesso(dto);
			for(DespesaDto d : this.despesaHandler){
				ProcessoController.addDespesaToProcesso(d);
			}
			
			for(AudienciaDto d : this.audienciaHandler){
				ProcessoController.addAudienciaToProcesso(d);
			}
			
			JOptionPane.showMessageDialog(this, "Processo salvo com sucesso.");
			clearInputs();
		} catch (ProcessoException e) {
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
		List<ProcessoDto> lista = processoController.list();
		StringBuilder builder = new StringBuilder();
		for (ProcessoDto dto : lista) {
			builder.append(dto.toString()).append("\n");
		}
	}
	
	protected void actionListarAudiencias() {
		StringBuilder builder = new StringBuilder();
		for (AudienciaDto dto : this.audienciaHandler) {
			builder.append(dto.toString()).append("\n");
		}
		txtAudienciasLista.setText(builder.toString);
	}
	
	protected void actionListarDespesas() {
		StringBuilder builder = new StringBuilder();
		for (DespesaDto dto : this.despesaHandler) {
			builder.append(dto.toString()).append("\n");
		}
		txtAudienciasLista.setText(builder.toString);
	}
	
	
	private void actionBuscarPessoa(String registro) {
		PessoaDto pessoa = pessoaController.getPessoa(registro);
		try{
			Cpf c = new Cpf(registro);
			PessoaFisicaView pfv = new PessoaFisicaView(MainController.getPessoaController(), registro);
			pfv.setVisible(true);
		}try{
			Cnpj c = new Cnpj(registro);
			PessoaJuridicaView pjv = new PessoaJuridicaView(MainController.getPessoaController(), registro);
			pjv.setVisible(true);
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "isso não é um CPF nem um Cnpj, ou seja, não se encontraria em nossa base de dados nem se você quisesse.";
		}
	}
	
	private void actionAdicionarDespesa(){
		DespesaDto dto = new DespesaDto();
		this.despesaHandler.add()
	}
	
	private void actionAdicionarAudiencia(){
		AudienciaDto dto = new AudienciaDto();
		this.AudienciaHandler.add()
	}
	
	private void actionBuscarCliente(){
		try{
			
		}
	}
	
	private void actionBuscarParteContraria(){
		
	}
	
	private void clearInputs(){
		txtData.setText("");
		txtNumero.setText("");
		txtTribunal.setText("");
		txtRegistroCliente.setText("");
		txtRegistroParteContraria.setText("");
	}
	
	protected ArrayList<> getAudiencias(){
		return this.audienciaHandler;
	}
	
	protected ArrayList<> getDespesas(){
		return this.audienciaHandler;
	}
}