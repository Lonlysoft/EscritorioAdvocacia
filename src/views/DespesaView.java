package views;

import java.awt.*;

import javax.swing.*;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllers.ProcessoController;
import dtos.*;

public class DespesaView extends JFrame {
	
	private static final long serialVersionUID = -398354756346235L;
	
	private ProcessoController processoCtrl;
	private ProcessoView processoView;
	private JPanel pnlLabelTextRecomendacao;
	private JLabel lblData;
	private JFormattedTextField txtData;
	private JPanel pnlLabelTextValor;
	private JLabel lblRecomendacao;
	private JLabel lblValor;
	private JTextArea txtRecomendacao;
	private JTextField txtValor;
	private JPanel pnlFooterSalvarCancelar;
	private JButton btnSalvar;
	private JButton btnCancelar;

	public DespesaView(ProcessoController pCtrl, ProcessoView view) {
		this.processoCtrl = pCtrl;
		this.processoView = view;
		initialize();
		setupActions();
	}

	private void initialize() {
		setTitle("Registrar audiência");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLayout(new BorderLayout());
		
		this.lblData = new JLabel("Data");
		this.txtData = new JFormattedTextField(new SimpleDateFormat("dd-mm-yyyy"));
		
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		pnlLabelTextValor = new JPanel(new BorderLayout(5, 5));
		lblValor = new JLabel("Valor:");
		txtValor = new JTextField();
		pnlLabelTextValor.add(lblValor, BorderLayout.WEST);
		pnlLabelTextValor.add(txtValor, BorderLayout.CENTER);
		
		pnlLabelTextRecomendacao = new JPanel(new BorderLayout(5, 5));
		lblRecomendacao = new JLabel("Recomendação:");
		txtRecomendacao = new JTextArea(5, 20);
		txtRecomendacao.setLineWrap(true);
		txtRecomendacao.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(txtRecomendacao);
		pnlLabelTextRecomendacao.add(lblRecomendacao, BorderLayout.NORTH);
		pnlLabelTextRecomendacao.add(scrollPane, BorderLayout.CENTER);
		
		pnlMain.add(pnlLabelTextValor);
		pnlMain.add(pnlLabelTextRecomendacao);

		pnlFooterSalvarCancelar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		btnSalvar = new JButton("Salvar");
		btnCancelar = new JButton("Cancelar");
		pnlFooterSalvarCancelar.add(btnCancelar);
		pnlFooterSalvarCancelar.add(btnSalvar);

		this.add(pnlMain, BorderLayout.CENTER);
		this.add(pnlFooterSalvarCancelar, BorderLayout.SOUTH);
	}

	private void setupActions() {
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSalvarDespesa();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});
		
		txtValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRecomendacao.requestFocusInWindow();
			}
		});
	}

	private void actionSalvarDespesa() {
		ArrayList<DespesaDto> arr = processoView.getDespesas();
		DespesaDto dto = new DespesaDto(new Date(txtData.getText()), txtRecomendacao.getText(), Double.parseDouble(txtValor.getText()));
		arr.add(dto);
		this.dispose();
		limparCampos();
	}
	
	private void actionCancelar() {
		int confirm = JOptionPane.showConfirmDialog(
			this, 
			"Deseja realmente cancelar? Todas as alterações serão perdidas.", 
			"Confirmar Cancelamento", 
			JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			limparCampos();
			this.dispose();
		}
	}
	
	private void limparCampos() {
		txtValor.setText("");
		txtRecomendacao.setText("");
	}
}