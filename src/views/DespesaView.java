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

public class DespesaView extends JFrame {

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
		
		btnBuscarCpf.addActionListener(
			new ActionListener(){
				public void actionPerformed(){
					actionBuscar();
				}
			}
		);
		
		this.pnlCpfBuscar = new JPanel();
		this.pnlCpfBuscar.setLayout(new FlowLayout());

		this.pnlCpfBuscar.add(this.lblCpf);
		this.pnlCpfBuscar.add(this.txtCpf);
		this.pnlCpfBuscar.add(this.btnBuscarCpf);

		this.lblNome = new JLabel("Nome");
		this.txtNome = new JTextField(40);

		this.lblEmail = new JLabel("Email");
		this.txtEmail = new JTextField(40);

		this.lblTelefone = new JLabel("Telefone");
		this.txtTelefone = new JTextField(12);

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
		
		this.pnlTxtFields.add(this.pnlNomeContainer);
		this.pnlTxtFields.add(this.pnlEmailContainer);
		this.pnlTxtFields.add(this.pnlTelefoneContainer);
		
		this.add(this.pnlCpfBuscar);
		this.add(this.pnlTxtFields);
		this.add(this.pnlFooterContainer);
	}
	
	private void actionRegistrar(){
		PessoaFisicaDto dto = new PessoaFisicaDto(
			this.txtNome.getText(),
			this.txtTelefone.getText(),
			this.txtEmail.getText(),
			this.txtCpf.getText()
		);
		
		try{
			this.pessoaFisicaController.createPessoaFisica(dto);
			JOptionPane.showMessageDialog(this, "Pessoa Fisica gravada com sucesso!");
		} catch(PessoaException e){
			JWarningPane.showMessageDialog(this, e.getMessage());
			this.clear();
		}
	}
	
	private void actionBuscar() {

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
	
	public void clear(){
		this.txtNome.setText("");
		this.txtCpf.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
	}
	
}
