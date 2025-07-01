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
	
	public MenuView() {
		initialize();
	}
	
	private void initialize() {
		
		this.setTitle("Sistem de Gestão de Processos");
		this.setSize(500, 400);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		btnTribunal = new JButton("Cadastro de Tribunais");
		btnTribunal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionTribunal();
			}
		});
		
		this.add(btnTribunal);
	}
	
	private void actionTribunal() {
		
		TribunalView tribunalView = new TribunalView(MainController.getTribunalController());
		tribunalView.setVisible(true);
	}	
}
