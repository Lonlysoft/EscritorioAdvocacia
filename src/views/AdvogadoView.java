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

public class AdvogadoView extends JFrame {

	private PessoaController advogadoController;

	private JPanel pnlCpfBuscar;
	private JPanel pnlRegistroBuscar;
	private JPanel pnlTxtFields;
	private JPanel pnlNomeContainer;
	private JPanel pnlEmailContainer;
	private JPanel pnlTelefoneContainer;
	private JPanel pnlFooterContainer;

	private JLabel lblNome;
	private JTextField txtNome;

	private JLabel lblCpf;
	private JTextField txtCpf;

	private JButton btnBuscarCpf;

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblTelefone;
	private JTextField txtTelefone;

	private JButton btnSalvar;
	private JButton btnCancelar;

	public AdvogadoView(PessoaController ac) {
		this.advogadoController = ac;
		initialize();
	}

	private void initialize() {
		this.setTitle("novo advogado");
		this.setMinimumSize(new Dimension(300, 200));
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));

		this.lblRegistro = new JLabel("Registro");
		this.txtRegistro = new JTextField(12);
		this.btnBuscarCpf = new JButton("buscar");
		this.pnlRegistroBuscar = new JPanel();
		this.pnlRegistroBuscar.setLayout(new FlowLayout());
		
		this.lblCpf = new JLabel("CPF");
		this.txtCpf = new JTextField(12);
		this.pnlCpfBuscar.add(this.lblCpf);
		this.pnlCpfBuscar.add(this.txtCpf);
		this.pnlCpfBuscar.add(this.btnBuscarCpf);

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
		
		this.pnlTxtFields.add(pnlCpfContainer)
		this.pnlTxtFields.add(pnlNomeContainer);
		this.pnlTxtFields.add(pnlEmailContainer);
		this.pnlTxtFields.add(pnlTelefoneContainer);
		
		this.add(pnlCpfBuscar);
		this.add(pnlTxtFields);
		this.add(pnlFooterContainer);
	}
	
	private void actionBuscarRegistro(){
		String registro;
		AdvogadoDto dto;
		PessoaFisicaDto dto2;
		
		registro = txtRegistro.getText();
		
		try {
			dto = this.pessoaFisicaController.getAdvogado(registro);
			dto2 = this.pessoaFisicaController.getPessoaFisica(dto.getCpf());
			
			txtCpf.setText(cpf);
			txtNome.setText(dto.getNome());
			txtTelefone.setText(dto.getTelefone());
			txtEmail.setText(dto.getEmail());
			
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			//clear();
		}	
	}
	private void actionSalvar(){
		
	}
	private void actionCancelar(){
		
	}
	private void actionBuscarPessoaFisica(){
		String cpf;
		PessoaFisicaDto dto;
		
		cpf = txtCpf.getText();
		
		try {
			dto = this.pessoaFisicaController.getPessoaFisica(cpf);
			
			txtCpf.setText(cpf);
			txtNome.setText(dto.getNome());
			txtTelefone.setText(dto.getTelefone());
			txtEmail.setText(dto.getEmail());
			
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			//clear();
		}	
	}
}