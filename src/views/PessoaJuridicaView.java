package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllers.PessoaController;
import dtos.PessoaFisicaDto;
import dtos.PessoaJuridicaDto;
import exceptions.*;

public class PessoaJuridicaView extends JFrame {

	private static final long serialVersionUID = -4046367434218576L;

	private PessoaController pessoaJuridicaController;

	// Painéis
	private JPanel pnlMain;
	private JPanel pnlCpfBuscar;
	private JPanel pnlCnpjBuscar;
	private JPanel pnlFooterContainer;
	private JPanel pnlNomeContainer;
	private JPanel pnlEmailContainer;
	private JPanel pnlTelefoneContainer;
	private JPanel pnlPrepostoContainer;
	
	// Labels
	private JLabel lblCnpj;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblEmail;
	private JLabel lblTelefone;
	private JLabel lblPrepostoNome;
	private JLabel lblPrepostoTelefone;
	private JLabel lblPrepostoEmail;
	
	// Campos de texto
	private JTextField txtCnpj;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtPrepostoNome;
	private JTextField txtPrepostoTelefone;
	private JTextField txtPrepostoEmail;
	
	// Botões
	private JButton btnCnpj;
	private JButton btnBuscarCpf;
	private JButton btnSalvar;
	private JButton btnCancelar;

	public PessoaJuridicaView(PessoaController pfc) {
		this.pessoaJuridicaController = pfc;
		initialize();
	}

	private void initialize() {
		this.setTitle("Pessoa Jurídica");
		this.setMinimumSize(new Dimension(300, 300));
		this.setSize(600, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Painel principal
		pnlMain = new JPanel(new GridLayout(7, 1, 10, 10));
		pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// CNPJ
		pnlCnpjBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblCnpj = new JLabel("CNPJ:");
		txtCnpj = new JTextField(20);
		btnCnpj = new JButton("Buscar");
		pnlCnpjBuscar.add(lblCnpj);
		pnlCnpjBuscar.add(txtCnpj);
		pnlCnpjBuscar.add(btnCnpj);

		// Nome
		pnlNomeContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblNome = new JLabel("Nome:");
		txtNome = new JTextField(20);
		pnlNomeContainer.add(lblNome);
		pnlNomeContainer.add(txtNome);

		// Email
		pnlEmailContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblEmail = new JLabel("Email:");
		txtEmail = new JTextField(20);
		pnlEmailContainer.add(lblEmail);
		pnlEmailContainer.add(txtEmail);

		// Telefone
		pnlTelefoneContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblTelefone = new JLabel("Telefone:");
		txtTelefone = new JTextField(20);
		pnlTelefoneContainer.add(lblTelefone);
		pnlTelefoneContainer.add(txtTelefone);

		// CPF Preposto
		pnlCpfBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblCpf = new JLabel("CPF Preposto:");
		txtCpf = new JTextField(15);
		btnBuscarCpf = new JButton("Buscar");
		pnlCpfBuscar.add(lblCpf);
		pnlCpfBuscar.add(txtCpf);
		pnlCpfBuscar.add(btnBuscarCpf);

		// Dados do Preposto
		pnlPrepostoContainer = new JPanel(new GridLayout(3, 1, 5, 5));
		
		JPanel pnlPrepostoNome = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblPrepostoNome = new JLabel("Nome Preposto:");
		txtPrepostoNome = new JTextField(20);
		txtPrepostoNome.setEditable(false);
		pnlPrepostoNome.add(lblPrepostoNome);
		pnlPrepostoNome.add(txtPrepostoNome);
		
		JPanel pnlPrepostoTelefone = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblPrepostoTelefone = new JLabel("Telefone Preposto:");
		txtPrepostoTelefone = new JTextField(20);
		txtPrepostoTelefone.setEditable(false);
		pnlPrepostoTelefone.add(lblPrepostoTelefone);
		pnlPrepostoTelefone.add(txtPrepostoTelefone);
		
		JPanel pnlPrepostoEmail = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblPrepostoEmail = new JLabel("Email Preposto:");
		txtPrepostoEmail = new JTextField(20);
		txtPrepostoEmail.setEditable(false);
		pnlPrepostoEmail.add(lblPrepostoEmail);
		pnlPrepostoEmail.add(txtPrepostoEmail);
		
		pnlPrepostoContainer.add(pnlPrepostoNome);
		pnlPrepostoContainer.add(pnlPrepostoTelefone);
		pnlPrepostoContainer.add(pnlPrepostoEmail);
		
		pnlFooterContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		btnSalvar = new JButton("Salvar");
		btnCancelar = new JButton("Cancelar");
		pnlFooterContainer.add(btnCancelar);
		pnlFooterContainer.add(btnSalvar);

		pnlMain.add(pnlCnpjBuscar);
		pnlMain.add(pnlNomeContainer);
		pnlMain.add(pnlEmailContainer);
		pnlMain.add(pnlTelefoneContainer);
		pnlMain.add(pnlCpfBuscar);
		pnlMain.add(pnlPrepostoContainer);
		
		this.add(pnlMain, BorderLayout.CENTER);
		this.add(pnlFooterContainer, BorderLayout.SOUTH);
		
		btnBuscarCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscarPreposto();
			}
		});
		
		btnCnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBuscar();
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRegistrar();
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});
	}
	
	private void actionCancelar() {
		int confirm = JOptionPane.showConfirmDialog(
			this, 
			"Deseja realmente cancelar? Todas as alterações serão perdidas.", 
			"Confirmar Cancelamento", 
			JOptionPane.YES_NO_OPTION);
			
		if (confirm == JOptionPane.YES_OPTION) {
			clear();
			this.dispose();
		}
	}
	
	private void actionRegistrar() {
		PessoaJuridicaDto dto = new PessoaJuridicaDto(
			this.txtNome.getText(),
			this.txtTelefone.getText(),
			this.txtEmail.getText(),
			this.txtCnpj.getText(),
			this.txtCpf.getText()
		);
		
		try{
			this.pessoaJuridicaController.createPessoaJuridica(dto);
			JOptionPane.showMessageDialog(this, "Pessoa Juridica gravada com sucesso!");
		} catch(PessoaException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
			
		} catch(CnpjException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
			
		} catch(EmailException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
			this.clear();
		}
		JOptionPane.showMessageDialog(this, "Pessoa jurídica salva com sucesso!");
	}
	
	private void actionBuscar() {
		String cnpj = txtCnpj.getText().trim();
		
		try {
			PessoaJuridicaDto dto = pessoaJuridicaController.getPessoaJuridica(cnpj);
			
			PessoaFisicaDto dto2 = pessoaJuridicaController.getPessoaFisica(txtCpf.getText());
			
			txtCnpj.setText(dto.getCnpj());
			txtNome.setText(dto.getNome());
			txtTelefone.setText(dto.getTelefone());
			txtEmail.setText(dto.getEmail());
			
			if(dto2 != null) {
				txtCpf.setText(dto.getPreposto());
				txtPrepostoNome.setText(dto2.getNome());
				txtPrepostoTelefone.setText(dto2.getTelefone());
				txtPrepostoEmail.setText(dto2.getEmail());
			}
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		}
	}
	
	private void actionBuscarPreposto() {
		String cpf = txtCpf.getText().trim();
		
		if(cpf.isEmpty()){
			return;
		}
		
		try {
			PessoaFisicaDto dto = pessoaJuridicaController.getPessoaFisica(cpf);
			
			txtCpf.setText(dto.getCpf());
			txtPrepostoNome.setText(dto.getNome());
			txtPrepostoTelefone.setText(dto.getTelefone());
			txtPrepostoEmail.setText(dto.getEmail());
			
		} catch (PessoaException e) {
			int option = JOptionPane.showConfirmDialog(
				this, 
				"Pessoa não encontrada. Deseja cadastrar?", 
				"Aviso", 
				JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				PessoaFisicaView pessoaFisicaView = new PessoaFisicaView(pessoaJuridicaController);
				pessoaFisicaView.setCpf(cpf);
				pessoaFisicaView.setVisible(true);
			}
		}
	}
	
	private void clear() {
		txtCnpj.setText("");
		txtNome.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
		txtCpf.setText("");
		txtPrepostoNome.setText("");
		txtPrepostoTelefone.setText("");
		txtPrepostoEmail.setText("");
	}
}