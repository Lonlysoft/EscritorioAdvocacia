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

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblTel;
	private JTextField txtTelefone;

	public PessoaFisicaView() {
		this.pessoaFisicaController = new PessoaFisicaController();
	}
}