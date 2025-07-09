class ProcessoView extends JFrame{
	private JPanel pnlContainer;
	private JPanel pnlFooterContainer;
	
	private JPanel pnlNumeroBuscar;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JButton btnNumero;
	
	private JPanel pnlTxtFields;
	
	//inicio container formulario
	private JPanel pnlFaseContainer;
	private JLabel lblFase;
	private JComboBox ddmFase;
	
	private JPanel pnlDataContainer;
	private JLabel lblData;
	private JFormattedText txtData;
	
	private JPanel pnlRegistroClienteBuscar;
	private JLabel lblRegistroCliente;
	private JTextField txtRegistroCliente;
	private JButton btnRegistroCliente;
	
	private JPanel pnlRegistroParteContrariaBuscar;
	private JLabel lblRegistroParteContraria;
	private JTextField txtRegistroParteContraria;
	private JButton btnRegistroParteContraria;
	
	//fim formulario principal
	
	private String[] dropdownOptions = {
		"INICIAL", "INSTRUÇÃO", "DECISÃO", "RECURSO"
	};
	
	public ProcessoView(){
	  initialize();
	}
	
	private void initialize(){
		this.setSize(250, 500);
		this.setLayout(new GridLayout(4, 1));
		pnlNumeroBuscar = new JPanel();
		pnlNumeroBuscar.setLayout(new FlowLayout());
		lblNumero = new JLabel("número do Processo: ");
		txtNumero = new JTextField(11);
		pnlNumeroBuscar.add(lblNumero);
		pnlNumeroBuscar.add(txtNumero);
		
		this.pnlDataContainer = new JPanel();
		this.pnlDataContainer.setLayout(new FlowLayout());
		this.lblData = new JLabel("Data");
		this.txtData = new JFormattedText("dd-mm-aaaa");
		
		this.pnlTxtFields.setLayout(new GridLayout(4, 1);
		this.pnlTxtFields.add(pnlRegistroClienteBuscar);
		this.pnlTxtFields.add(pnlRegistroParteContrariaBuscar);
		this.pnlTxtFields.add(pnlFaseContainer);
		
		this.add(this.pnlCpfBuscar);
		this.add(this.pnlTxtFields);
		this.add(this.pnlFooterContainer);
	}
}