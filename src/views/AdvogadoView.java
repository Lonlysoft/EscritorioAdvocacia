package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dtos.AdvogadoDto;
import dtos.PessoaFisicaDto;
import javax.swing.*;

import exceptions.PessoaException;
import exceptions.CpfException;
import exceptions.EmailException;
import controllers.PessoaController;

public class AdvogadoView extends JFrame {
	private static final long serialVersionUID = -2953679038290235L;
	
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
	
	private JLabel lblRegistro;
	private JTextField txtRegistro;
	
	private JButton btnBuscarRegistro;
	
	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblTelefone;
	private JTextField txtTelefone;

	private JButton btnSalvar;
	private JButton btnCancelar;

	public AdvogadoView(PessoaController ac) {
		this.advogadoController = ac;
		initialize();
		setupActionListeners();
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
		
		this.pnlTxtFields.add(pnlCpfBuscar);
		this.pnlTxtFields.add(pnlNomeContainer);
		this.pnlTxtFields.add(pnlEmailContainer);
		this.pnlTxtFields.add(pnlTelefoneContainer);
		
		this.add(pnlCpfBuscar);
		this.add(pnlTxtFields);
		this.add(pnlFooterContainer);
	}
	
	private void setupActionListeners(){
		btnSalvar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					actionSalvar();
				}
			}
		);
		
		btnCancelar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					actionCancelar();
				}
			}
		);
		
		btnBuscarRegistro.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					actionBuscarRegistro();
				}
			}
		);
		
		btnBuscarCpf.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					actionBuscarPessoaFisica();
				}
			}
		);
	}
	
	private void actionSalvar(){
		AdvogadoDto dto = new AdvogadoDto(txtRegistro.getText(), txtCpf.getText());
		PessoaFisicaDto dto2 = new PessoaFisicaDto(
			this.txtNome.getText(),
			this.txtTelefone.getText(),
			this.txtEmail.getText(),
			this.txtCpf.getText()
		);
		
		try{
			this.advogadoController.createPessoaFisica(dto2);
			this.advogadoController.createAdvogado(dto);
			JOptionPane.showMessageDialog(this, "Advogado gravado com sucesso!");
		} catch(PessoaException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
			this.clear();
		} catch(CpfException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch(EmailException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		} 
	}
	
	private void actionBuscarRegistro(){
		String registro;
		AdvogadoDto dto;
		PessoaFisicaDto dto2;
		
		registro = txtRegistro.getText();
		
		try {
			dto = this.advogadoController.getAdvogado(registro);
			dto2 = this.advogadoController.getPessoaFisica(dto.getCpf());
			
			
			txtRegistro.setText(registro);
			txtCpf.setText(dto2.getCpf());
			txtNome.setText(dto2.getNome());
			txtTelefone.setText(dto2.getTelefone());
			txtEmail.setText(dto2.getEmail());
			
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			//clear();
		}
	}
	
	private void actionBuscarPessoaFisica(){
		String cpf;
		PessoaFisicaDto dto;
		
		cpf = txtCpf.getText();
		
		try {
			dto = this.advogadoController.getPessoaFisica(cpf);
			
			txtCpf.setText(cpf);
			txtNome.setText(dto.getNome());
			txtTelefone.setText(dto.getTelefone());
			txtEmail.setText(dto.getEmail());
			
		} catch (PessoaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			clear();
		} 
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
	 
	public void clear(){
		this.txtNome.setText("");
		this.txtCpf.setText("");
		this.txtTelefone.setText("");
		this.txtEmail.setText("");
	}
	
}