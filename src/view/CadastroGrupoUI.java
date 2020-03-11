package view;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.GrupoController;
import dao.EsporteDAO;
import dao.RegiaoDAO;
import model.Esporte;
import model.Grupo;
import model.GrupoStatus;
import model.GrupoTipo;
import model.Regiao;
import model.UsuarioConectado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroGrupoUI extends JInternalFrame {
	private JTextField txtNome;
	private Grupo grupo;
	UsuarioConectado userAtual = UsuarioConectado.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroGrupoUI frame = new CadastroGrupoUI(null);
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
	public CadastroGrupoUI(Grupo grupoParaEditar) {
		this.grupo = grupoParaEditar;
		setBounds(10, 10, 422, 330);
		
		JPanel panelCadatroGrupo = new JPanel();
		panelCadatroGrupo.setBorder(new TitledBorder(null, "Criar Grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JTextArea textAreaDescricao = new JTextArea();
		JComboBox comboBoxTipo = new JComboBox(GrupoTipo.values());
		
		List<Regiao> list = new RegiaoDAO().montarRegioes();

		ArrayList<String> listS = new ArrayList<String>();
		for (Regiao r : list) {
			listS.add(r.getNomeRegiao());
		}
		String[] listaRegioes = listS.toArray(new String[listS.size()]);
		
		JComboBox comboBoxRegiao = new JComboBox(listaRegioes);
		
		List<Esporte> listEsportes = new EsporteDAO().montarEsportes();
		ArrayList<String> listaE = new ArrayList<String>();
		for(Esporte e : listEsportes) {
			listaE.add(e.getNomeEsporte());
		}
		
		String[] lista = listaE.toArray(new String[listaE.size()]);
		
		JComboBox comboBoxEsportes = new JComboBox(lista);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			 if(grupo != null) {
					grupo.setNome(txtNome.getText());
					grupo.setDescricao(textAreaDescricao.getText());
					grupo.setEnumTipo(GrupoTipo.valueOf(comboBoxTipo.getSelectedItem().toString()));
					grupo.getReigao().setIdregiao(comboBoxRegiao.getSelectedIndex()+1);
					grupo.getReigao().setNomeRegiao((String) comboBoxRegiao.getSelectedItem());
					grupo.getEsporte().setIdesporte(comboBoxEsportes.getSelectedIndex()+1);
					grupo.getEsporte().setNomeEsporte((String) comboBoxEsportes.getSelectedItem());
					
					new GrupoController().editar(grupo);
					
					dispose();
					
				}else {
					if (userAtual.getUsuario()!= null) {
						Grupo grupo = new Grupo();
						Regiao r = new Regiao();
						Esporte esp = new Esporte();

						grupo.setNome(txtNome.getText());
						grupo.setDescricao(textAreaDescricao.getText());
						grupo.setEnumTipo(GrupoTipo.valueOf(comboBoxTipo.getSelectedItem().toString()));
						grupo.setCriador(userAtual.getUsuario().getIdusuario());
						java.util.Date data = new java.util.Date();
						grupo.setDataCriacao(data);
						grupo.setEnumStatus(GrupoStatus.ATIVO);

						r.setIdregiao(comboBoxRegiao.getSelectedIndex()+1);
						r.setNomeRegiao((String) comboBoxRegiao.getSelectedItem());
						esp.setIdesporte(comboBoxEsportes.getSelectedIndex()+1);
						esp.setNomeEsporte((String) comboBoxEsportes.getSelectedItem());

						grupo.setReigao(r);
						grupo.setEsporte(esp);

						int key = new GrupoController().salvar(grupo);
						new GrupoController().entrarNoGrupo(userAtual.getUsuario().getIdusuario(), key,grupo.getCriador());
						userAtual.getUsuario().entrarNoGrupo(grupo);

						JOptionPane.showMessageDialog(null, "Grupo Criado com sucesso!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "É preciso estar Logado para Criar um grupo!");
					}

				}
						
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelCadatroGrupo, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelCadatroGrupo, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lblNome = new JLabel("Nome");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		
		JLabel lblRegiao = new JLabel("Regi\u00E3o");
		
		JLabel lblEsporteRelacionado = new JLabel("Esporte Relacionado");
		
		GroupLayout gl_panelCadatroGrupo = new GroupLayout(panelCadatroGrupo);
		gl_panelCadatroGrupo.setHorizontalGroup(
			gl_panelCadatroGrupo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
					.addGroup(gl_panelCadatroGrupo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAreaDescricao, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDescricao))
						.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRegiao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxRegiao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEsporteRelacionado)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxEsportes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_panelCadatroGrupo.setVerticalGroup(
			gl_panelCadatroGrupo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadatroGrupo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCadatroGrupo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipo))
					.addGap(15)
					.addComponent(lblDescricao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textAreaDescricao, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelCadatroGrupo.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxRegiao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRegiao))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCadatroGrupo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEsporteRelacionado)
						.addComponent(comboBoxEsportes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		panelCadatroGrupo.setLayout(gl_panelCadatroGrupo);
		getContentPane().setLayout(groupLayout);
		
		if(grupo != null) {
			txtNome.setText(grupo.getNome());
			textAreaDescricao.setText(grupo.getDescricao());
			
			comboBoxTipo.setSelectedIndex(grupo.getEnumTipo().getTipo());
			comboBoxRegiao.setSelectedItem(grupo.getReigao().getNomeRegiao());
			
			comboBoxEsportes.setSelectedItem(grupo.getEsporte().getNomeEsporte());
		}

	}
}
