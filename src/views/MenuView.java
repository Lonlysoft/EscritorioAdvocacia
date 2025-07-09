package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controllers.MainController;

public class MenuView extends JFrame {
	
	private static final long serialVersionUID = -6778634079898677280L;
	
	
	private JButton btnTribunal;
	private JButton btnProcesso;
	private JButton btnAdvogado;
	
	public MenuView() {
		initialize();
	}
	
	private void initialize() {
		
		this.setTitle("Sistema de Gestão de Processos");
		this.setMinimumSize(new Dimension(300, 64));
		this.setSize(500, 400);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(1, 3));
		
		btnTribunal = new JButton("Tribunais");
		btnTribunal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTribunal();
			}
		});
		btnProcesso = new JButton("Processos");
		btnProcesso.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//actionProcesso();
				}
			}
		);
		btnAdvogado = new JButton("pessoa");
		btnAdvogado.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPessoa();
				}
			}
		);
		
		this.add(btnTribunal);
		this.add(btnProcesso);
		this.add(btnAdvogado);
	}
	
	private void actionTribunal() {
		
		TribunalView tribunalView = new TribunalView(MainController.getTribunalController());
		tribunalView.setVisible(true);
	}	
	private void actionProcesso(){
		ProcessoView pv = new ProcessoView(MainController.getProcessoController());
		pv.setVisible(true);
	}
	
	private void actionPessoa(){
		PessoaFisicaView pv = new PessoaFisicaView(MainController.getPessoaController());
		pv.setVisible(true);
	}
}
