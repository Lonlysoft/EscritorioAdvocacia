package views;

import controllers.MainController;
import controllers.TribunalController;
import controllers.ProcessoController;
import controllers.PessoaController;
import dtos.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MenuView extends JFrame {
	
	private static final long serialVersionUID = -6778634079898677280L;
	
	private JButton btnTribunal;
	private JButton btnProcesso;
	private JButton btnAdvogado;
	private JToggleButton btnListas;
	private JPanel pnlListas;
	private JTextArea txtLista;
	private JPanel pnlLinha;
	
	private boolean listar = false;
	
	public MenuView() {
		initialize();
	}
	
	private void initialize() {
		
		this.setTitle("Sistema de Gestão de Processos");
		this.setMinimumSize(new Dimension(300, 64));
		this.setSize(500, 400);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(2, 1));
		
		pnlLinha = new JPanel();
		pnlLinha.setLayout(new GridLayout(1, 4));
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
		btnAdvogado = new JButton("advogado");
		btnAdvogado.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPessoa();
				}
			}
		);
		btnListas = new JToggleButton("listar");
		btnListas.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionToggleModoListar();
				}
			}
		);
		
		this.pnlLinha.add(btnTribunal);
		this.pnlLinha.add(btnProcesso);
		this.pnlLinha.add(btnAdvogado);
		this.pnlLinha.add(btnListas);
		this.add(pnlLinha);
		this.add(txtLista);
	}
	
	private void actionTribunal() {
		if(!this.listar){
			TribunalView tribunalView = new TribunalView(MainController.getTribunalController());
			tribunalView.setVisible(true);
		}
		else{
		
			TribunalController trc = MainController.getTribunalController();
			List<TribunalDto> lista = trc.getTribunais();
			txtLista.setText("");
			for (TribunalDto dto : lista) {
				txtLista.append(dto.getSigla() + "\t" + dto.getNome() + "\t" + dto.getSecao()  +"\n");
			}
		}
	}
	private void actionProcesso(){
		if(!this.listar){
			ProcessoView pv = new ProcessoView(MainController.getProcessoController());
			pv.setVisible(true);
		}
		else{
			ProcessoController prc = MainController.getProcessoController();
			List<ProcessoDto> lista = prc.getProcessos();
			txtLista.setText("");
			for (ProcessoDto dto : lista) {
				txtLista.append(dto.toString() +"\n");
			}
		}
	}
	
	private void actionPessoa(){
		if(!this.listar){
			AdvogadoView pv = new AdvogadoView(MainController.getPessoaController());
			pv.setVisible(true);
		} else{
			PessoaController ac = MainController.getPessoaController();
			List<AdvogadoDto> lista = ac.getAdvogados();
			txtLista.setText("");
			for(AdvogadoDto a : lista){
				txtLista.append(a.toString() + "\n");
			}
		}
	}
	
	private void actionToggleModoListar(){
		this.listar = this.btnListas.isSelected();
		if(this.btnListas.isSelected())
			this.btnListas.setText("modo listando");
		else
			this.btnListas.setText("modo registrar / alterar");
	}
}
