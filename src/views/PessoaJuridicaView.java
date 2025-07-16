package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.PessoaController;

public class PessoaFisicaView extends JFrame {

	private static final long serialVersionUID = -4085879303754218366L;

	private PessoaController pessoaFisicaController;

	private JPanel pnlCpfBuscar;
	private JPanel pnlCnpjBuscar;
	private JPanel pnlFooterContainer;
	
	private JLabel lblCnpj;
	private JTextField txtCnpj;
	private JButton btnCnpj;

	private JLabel lblNome;
	private JTextField txtNome;
	
	private JLabel lblCnpj;
	private JTextField txtCnpj;
	
	private JLabel lblCpfPreposto;
	private JTextField txtCpfPreposto;
	private JButton btnBuscarCpf;

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblTelefone;
	private JTextField txtTelefone;

	private JButton btnSalvar;
	private JButton btnCancelar;

	public PessoaJuridicaView(PessoaController pfc) {
		this.pessoaFisicaController = pfc;
		initialize();
	}

	private void initialize() {
		this.setTitle("nova Pessoa Fisica");
		this.setMinimumSize(new Dimension(300, 200));
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.lblCpf = new JLabel("CPF");
		this.txtCpf = new JTextField(12);
		this.btnBuscarCpf = new JButton("buscar");
		
		btnBuscarCpf.addActionListener(
			new ActionListener(){
				public void actionPerformed(){
					actionBuscar();
				}
			}
		);
		
		this.pnlCnpjBuscar = new JPanel();
		this.pnlCnpjBuscar.setLayout(new GridLayout(1, 3, 3, 3));
		this.txtCnpj = new JTextField(20);
		this.btnCnpj = new Jbutton("buscar");
		this.lblCnpj = new JLabel("CNPJ");
		
		this.pnlCpfBuscar = new JPanel();
		this.pnlCpfBuscar.setLayout(new FlowLayout());

		this.pnlCpfBuscar.add(this.lblCpf);
		this.pnlCpfBuscar.add(this.txtCpf);
		this.pnlCpfBuscar.add(this.btnBuscarCpf);
		
		this.pnlCnpjBuscar.add(this.lblCnpj);
		this.pnlCnpjBuscar.add(this.txtCnpj);
		this.pnlCnpjBuscar.add(this.btnCnpj);
		
		this.lblNome = new JLabel("Nome");
		this.txtNome = new JTextField();

		this.lblEmail = new JLabel("Email");
		this.txtEmail = new JTextField();

		this.lblTelefone = new JLabel("Telefone");
		this.txtTelefone = new JTextField();

		this.pnlNomeContainer = new JPanel();

		this.pnlNomeContainer.add(this.lblNome);
		this.pnlNomeContainer.add(this.txtNome);

		this.pnlEmailContainer = new JPanel();

		this.pnlEmailContainer.add(this.lblEmail);
		this.pnlEmailContainer.add(this.txtEmail);
		
		this.pnlTelefoneContainer = new JPanel();
		
		this.pnlTelefoneContainer.add(this.lblTelefone);
		this.pnlTelefoneContainer.add(this.txtTelefone);

		this.pnlFooterContainer = new JPanel();

		this.pnlFooterContainer.add(this.btnCancelar);
		this.pnlFooterContainer.add(this.btnSalvar);
		
		this.pnlTxtFields = new JPanel();
		
		this.pnlTxtFields.add(pnlNomeContainer);
		this.pnlTxtFields.add(pnlEmailContainer);
		this.pnlTxtFields.add(pnlTelefoneContainer);
		
		this.add(pnlCnpjBuscar);
		this.add(pnlTxtFields);
		this.add(pnlFooterContainer);
		this.add(pnlCpfBuscar);
	}
	
	private void actionRegistrar(){
		
	}
	
	private void actionBuscar(){
		String cnpj;
		PessoaJuridicaDto dto;
		cnpj = txtCnpj.getText();
		
		try{
			dto = pessoaJuridicaController.getPessoaJuridica();
			
			txtCnpj.setText(cnpj;
			txtNome.setText(dto.getNome());
			txtTelefone.setText(dto.getTelefone());
			txtEmail.setText(dto.getEmail());
		}
	}
	
	private void actionBuscarPreposto() {

		String cpf;
		PessoaFisicaDto dto;
		
		cpf = txtCpf.getText();
		
		try {
			dto = pessoaJuridicaController.getPessoaFisica(cpf);
			
			txtPrepostoCpf.setText(cpf);
			txtPrepostoNome.setText(dto.getNome());
			txtPrepostoTelefone.setText(dto.getTelefone());
			txtPrepostoEmail.setText(dto.getEmail());
			
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}	
	}
	
}
