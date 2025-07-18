package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllers.MainController;
import controllers.ProcessoController;
import dtos.AudienciaDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AudienciaView extends JFrame {
	private static final long serialVersionUID = -194625842590731595L;
	
	private ProcessoController processoCtrl;
	private ProcessoView processoView;
	private JPanel pnlLabelTextRecomendacao;
	private JPanel pnlLabelTextAdvogado;
	private JLabel lblRecomendacao;
	private JLabel lblAdvogado;
	private JTextArea txtRecomendacao;
	private JTextField txtAdvogado;
	private JPanel pnlFooterSalvarCancelar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JPanel pnlDataContainer;
	private JLabel lblData;
	private JFormattedTextField txtData;

	public AudienciaView(ProcessoController pCtrl, ProcessoView view) {
		this.processoCtrl = pCtrl;
		this.processoView = view;
		initialize();
		setupActions();
	}

	private void initialize() {
		setTitle("Registrar audiência");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(500, 400);
		setLayout(new BorderLayout());
		
		this.lblData = new JLabel("Data");
		this.txtData = new JFormattedTextField(new SimpleDateFormat("dd-mm-yyyy"));
		
		JPanel pnlMain = new JPanel(new GridLayout(2, 1, 10, 10));
		pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		pnlLabelTextAdvogado = new JPanel(new BorderLayout(5, 5));
		lblAdvogado = new JLabel("Advogado:");
		txtAdvogado = new JTextField();
		pnlLabelTextAdvogado.add(lblAdvogado, BorderLayout.WEST);
		pnlLabelTextAdvogado.add(txtAdvogado, BorderLayout.CENTER);
		
		pnlLabelTextRecomendacao = new JPanel(new BorderLayout(5, 5));
		lblRecomendacao = new JLabel("Recomendação:");
		txtRecomendacao = new JTextArea(5, 20);
		txtRecomendacao.setLineWrap(true);
		txtRecomendacao.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(txtRecomendacao);
		pnlLabelTextRecomendacao.add(lblRecomendacao, BorderLayout.NORTH);
		pnlLabelTextRecomendacao.add(scrollPane, BorderLayout.CENTER);
		
		pnlMain.add(pnlLabelTextAdvogado);
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
				actionSalvarAudiencia();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCancelar();
			}
		});
		
		txtAdvogado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRecomendacao.requestFocusInWindow();
			}
		});
	}

	private void actionSalvarAudiencia() {
		ArrayList<AudienciaDto> arr = processoView.getAudiencias();
		AudienciaDto dto = new AudienciaDto(new Date(txtData.getText()), txtRecomendacao.getText(), txtAdvogado.getText());
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
		txtAdvogado.setText("");
		txtRecomendacao.setText("");
	}
}