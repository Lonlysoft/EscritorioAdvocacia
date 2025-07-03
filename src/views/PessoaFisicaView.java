package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.PessoaFisicaController;

public class PessoaFisicaView extends JFrame {

	private static final long serialVersionUID = -4085879303754218366L;

	private PessoaFisicaController pessoaFisicaController;
	// components
	private JLabel lblNome;
	private JTextField txtNome;

	private JLabel lblCpf;
	private JTextField txtCpf;
	
	private JButton btnBuscarCpf;

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblTel;
	private JTextField txtTelefone;
	
	private JButton btnSalvar;
	private JButton btnCancelar;

	public PessoaFisicaView(PessoaFisicaController pfc) {
		this.pessoaFisicaController = pfc;
		initialize();
	}
	private initialize(){
		this.setTitle("nova Pessoa Fisica");
		this.setSize(200, 300);
		this.setLayout();
		
		this.lblCpf = new JLabel("CPF:");
		
	}
}