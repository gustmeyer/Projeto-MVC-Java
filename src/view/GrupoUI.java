package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import controller.GrupoController;
import model.Grupo;
import view.tables.UsuariosDoGrupoTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class GrupoUI extends JInternalFrame {
	private JTable tableUsuarios;
	private Grupo grupo;
	private JTextField txtNomeGrupo;
	private JTextField txtEsporte;
	private JTextField txtNumParticipantes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoUI frame = new GrupoUI(null);
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
	public GrupoUI(Grupo grupoInfo) {
		this.grupo = grupoInfo;
		setClosable(true);
		setBounds(5, 5, 660, 460);
		
		JPanel panel = new JPanel();
		panel.setBounds(142, 182, 363, 165);
		
		txtNomeGrupo = new JTextField();
		txtNomeGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeGrupo.setBounds(139, 27, 366, 28);
		txtNomeGrupo.setEditable(false);
		txtNomeGrupo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNomeGrupo.setColumns(10);
		txtNomeGrupo.setText(grupo.getNome());
		
		JLabel lblEsporte = new JLabel("Esporte:");
		lblEsporte.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsporte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEsporte.setBounds(127, 66, 75, 23);
		
		txtEsporte = new JTextField();
		txtEsporte.setHorizontalAlignment(SwingConstants.CENTER);
		txtEsporte.setBounds(210, 66, 126, 23);
		txtEsporte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEsporte.setEditable(false);
		txtEsporte.setColumns(10);
		txtEsporte.setText(grupo.getEsporte().getNomeEsporte());
		
		JLabel lblUsuarios = new JLabel("Lista de Usuarios");
		lblUsuarios.setBounds(142, 141, 121, 30);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNumDeParticiapantes = new JLabel("Num de Particiapantes");
		lblNumDeParticiapantes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumDeParticiapantes.setBounds(139, 98, 126, 23);
		
		txtNumParticipantes = new JTextField();
		txtNumParticipantes.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumParticipantes.setBounds(290, 98, 47, 23);
		txtNumParticipantes.setEditable(false);
		txtNumParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumParticipantes.setColumns(10);
		txtNumParticipantes.setText( String.valueOf(grupo.getListaUsuarios().size()));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
		);
		
		tableUsuarios = new JTable();
		tableUsuarios.setModel(new UsuariosDoGrupoTableModel(new GrupoController().listaUsuariosGrupo(grupo.getIdgrupo())));
		scrollPane.setViewportView(tableUsuarios);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(null);
		getContentPane().add(txtNomeGrupo);
		getContentPane().add(lblNumDeParticiapantes);
		getContentPane().add(lblEsporte);
		getContentPane().add(txtEsporte);
		getContentPane().add(txtNumParticipantes);
		getContentPane().add(lblUsuarios);
		getContentPane().add(panel);

	}
}
