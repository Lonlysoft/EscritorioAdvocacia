package views;

import java.awt.FlowLayout;
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
		this.setSize(500, 400);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(3, 1));
		
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
					actionProcesso();
				}
			}
		);
		btnAdvogado = new JButton("advogados");
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
}
