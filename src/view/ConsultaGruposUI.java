package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import controller.GrupoController;
import model.Grupo;
import model.UsuarioConectado;
import view.tables.GrupoTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ConsultaGruposUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableGrupos;
	private JTextField txtPesquisar;
	UsuarioConectado us = UsuarioConectado.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaGruposUI frame = new ConsultaGruposUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaGruposUI() {
		setTitle("Buscar Grupos");
		setClosable(true);
		setBounds(15, 15, 724, 467);
		
		JPanel panel = new JPanel();
		
		JButton btnAtualizar = new JButton("Atualizar Lista");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableGrupos.setModel(new GrupoTableModel(new GrupoController().pesquisar()));
			}
		});
		
		txtPesquisar = new JTextField();
		txtPesquisar.setToolTipText("pesquisa de grupo por Nome");
		txtPesquisar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableGrupos.setModel(new GrupoTableModel(new GrupoController().Buscar(txtPesquisar.getText())));
				
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Grupo grupo = new GrupoTableModel(new GrupoController().Buscar(txtPesquisar.getText())).get(tableGrupos.getSelectedRow());
				
				CadastroGrupoUI cadGrupo = new CadastroGrupoUI(grupo);
				cadGrupo.setVisible(true);
				getContentPane().add(cadGrupo, 0);
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grupo grupo = new GrupoTableModel(new GrupoController().Buscar(txtPesquisar.getText())).get(tableGrupos.getSelectedRow());
				new GrupoController().excluir(grupo.getIdgrupo(), grupo);
				
				tableGrupos.setModel(new GrupoTableModel(new GrupoController().pesquisar()));
			}
		});
		
		JButton btnEntrarNoGrupo = new JButton("Entrar no Grupo");
		btnEntrarNoGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Grupo grupo = new GrupoTableModel(new GrupoController().Buscar(txtPesquisar.getText())).get(tableGrupos.getSelectedRow());
				//Grupo grupo = new GrupoTableModel(new GrupoController().pesquisar()).get(tableGrupos.getSelectedRow());
				new GrupoController().entrarNoGrupo(us.getUsuario().getIdusuario(), grupo.getIdgrupo(),grupo.getCriador());
				us.getUsuario().entrarNoGrupo(grupo);
				//JOptionPane.showMessageDialog(null, "Entrou no Grupo com sucesso!");
				
				
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addGap(32)
							.addComponent(btnEntrarNoGrupo))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtualizar)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnEntrarNoGrupo))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
		);
		
		tableGrupos = new JTable();
		tableGrupos.setModel(new GrupoTableModel(new GrupoController().pesquisar()));
		scrollPane.setViewportView(tableGrupos);
		
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
