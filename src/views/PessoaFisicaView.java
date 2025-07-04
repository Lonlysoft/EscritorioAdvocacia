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

	public PessoaFisicaView(PessoaController pfc) {
		this.pessoaFisicaController = pfc;
		initialize();
	}

	private void initialize() {
		this.setTitle("nova Pessoa Fisica");
		this.setMinimumSize(new Dimension(300, 200));
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));

		this.lblCpf = new JLabel("CPF");
		this.txtCpf = new JTextField(12);
		this.btnBuscarCpf = new JButton("buscar");
		this.pnlCpfBuscar = new JPanel();
		this.pnlCpfBuscar.setLayout(new FlowLayout());

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
		
		this.pnlTxtFields.add(pnlNomeContainer);
		this.pnlTxtFields.add(pnlEmailContainer);
		this.pnlTxtFields.add(pnlTelefoneContainer);
		

		this.add(pnlCpfBuscar);
		this.add(pnlTxtFields);
//		this.add(pnlPessoaFisicaTxtFields);
//		this.add(pnlPessoaFisicaFinalComplex);

	}
}
