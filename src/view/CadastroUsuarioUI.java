package view;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import controller.UsuarioController;
import dao.EsporteDAO;
import dao.RegiaoDAO;
import dao.UsuarioDAO;
import model.Esporte;
import model.Grupo;
import model.Regiao;
import model.SexoEnum;
import model.Usuario;
import model.UsuarioConectado;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroUsuarioUI extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtEmail;
	SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField txtDataNascimento;
	UsuarioConectado userConectado = UsuarioConectado.getInstancia();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuarioUI frame = new CadastroUsuarioUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public CadastroUsuarioUI() throws ParseException {
		setBounds(100, 100, 558, 487);
		
		JComboBox comboBoxSexo = new JComboBox(SexoEnum.values());
		
		List<Regiao> list = new RegiaoDAO().montarRegioes();

		ArrayList<String> listS = new ArrayList<String>();
		for (Regiao r : list) {
			listS.add(r.getNomeRegiao());
		}
		String[] listaRegioes = listS.toArray(new String[listS.size()]);

		JList listaEsportes = new JList();

		JComboBox comboBoxRegiao = new JComboBox(listaRegioes);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario();
				Regiao r = new Regiao();
				
				u.setNome(txtNome.getText());
				u.setEmail(txtEmail.getText());
				u.setSexo(SexoEnum.valueOf(comboBoxSexo.getSelectedItem().toString()));
				String dataText = txtDataNascimento.getText();
				try {
					Date data = sfd.parse(dataText);
					u.setData_Nascimento(data);
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				r.setNomeRegiao(comboBoxRegiao.getSelectedItem().toString());
				r.setIdregiao(comboBoxRegiao.getSelectedIndex()+1);
				u.setRegiao(r);
				//Montando a Lista de esportes que o usuario selecionou.
				List<Esporte> listaEsporte = new ArrayList<Esporte>();
				int tamanho = listaEsportes.getSelectedIndices().length;
				int[] indices = listaEsportes.getSelectedIndices();
				List<String> esporte = listaEsportes.getSelectedValuesList();
				for(int i=0; i<tamanho ; i++) {
					Esporte esp = new Esporte();
					esp.setIdesporte(indices[i]+1);
					esp.setNomeEsporte(esporte.get(i));
					listaEsporte.add(esp);
				}
				u.setListaEsportes(listaEsporte);
				
				if(userConectado.getUsuario()!=null) {
					u.setIdusuario(userConectado.getUsuario().getIdusuario());
					new UsuarioController().editarUsuario(u);
					JOptionPane.showMessageDialog(null, "Editado com Sucesso!");
					userConectado.setUsuario(u);
					dispose();
				}else {
					int iduser = new UsuarioController().salvarUsuario(u);
					List<Grupo> meusGrupos = new ArrayList<Grupo>();
					u.setListaGrupos(meusGrupos);
					u.setIdusuario(iduser);
					userConectado.setUsuario(u);
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					dispose();
				}
				
				
				//IMPRESSOES PARA TESTE.
				for(Esporte y : listaEsporte) {
					System.out.println(y.getNomeEsporte() + ", "+ y.getIdesporte());
				}
				System.out.println(u.toString()+", "+u.getRegiao().getNomeRegiao()+", "+u.getRegiao().getIdregiao());
				
			}
				
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JPanel panelCadastroUsuario = new JPanel();
		panelCadastroUsuario.setBorder(new TitledBorder(null, "Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNome = new JLabel("Nome");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		
		JLabel lblSexo = new JLabel("Sexo");
		
		
		
		JLabel lblRegiao = new JLabel("Regi\u00E3o");
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setColumns(10);
		
		GroupLayout gl_panelCadastroUsuario = new GroupLayout(panelCadastroUsuario);
		gl_panelCadastroUsuario.setHorizontalGroup(
			gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastroUsuario.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
						.addGroup(gl_panelCadastroUsuario.createSequentialGroup()
							.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataNascimento, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(lblSexo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblRegiao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelCadastroUsuario.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
										.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
								.addComponent(comboBoxSexo, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxRegiao, Alignment.TRAILING, 0, 110, Short.MAX_VALUE)
								.addComponent(txtDataNascimento))
							.addContainerGap(63, Short.MAX_VALUE))))
		);
		gl_panelCadastroUsuario.setVerticalGroup(
			gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCadastroUsuario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataNascimento)
						.addComponent(txtDataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCadastroUsuario.createSequentialGroup()
							.addGap(21)
							.addComponent(comboBoxSexo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCadastroUsuario.createSequentialGroup()
							.addGap(24)
							.addComponent(lblSexo)))
					.addGap(14)
					.addGroup(gl_panelCadastroUsuario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegiao)
						.addComponent(comboBoxRegiao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelCadastroUsuario.setLayout(gl_panelCadastroUsuario);
		
		List<Esporte> dados = new EsporteDAO().montarEsportes();
		ArrayList<String> esportes = new ArrayList<String>();
		for(Esporte esp : dados) {
			esportes.add(esp.getNomeEsporte());
		}
		
		
		JLabel lblListaDeEsportes = new JLabel("Lista de Esportes");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(78)
							.addComponent(btnCancelar))
						.addComponent(scrollPane)
						.addComponent(lblListaDeEsportes))
					.addContainerGap(61, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelCadastroUsuario, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelCadastroUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblListaDeEsportes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSalvar, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
		);
		
		
		listaEsportes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		scrollPane.setViewportView(listaEsportes);
		listaEsportes.setListData(esportes.toArray());
		listaEsportes.setModel(new AbstractListModel() {
			
			public int getSize() {
				return esportes.toArray().length;
			}
			public Object getElementAt(int index) {
				return esportes.toArray()[index];
			}
		});
		
		if(userConectado.getUsuario() != null) {
			txtNome.setText(userConectado.getUsuario().getNome());
			txtEmail.setText(userConectado.getUsuario().getEmail());
			sdf.format(userConectado.getUsuario().getData_Nascimento());
			txtDataNascimento.setText(sdf.format(userConectado.getUsuario().getData_Nascimento()));
			comboBoxSexo.setSelectedIndex(userConectado.getUsuario().getSexo().getPosicao());
			comboBoxRegiao.setSelectedIndex(userConectado.getUsuario().getRegiao().getIdregiao()-1);
			
			List<Esporte> lis = userConectado.getUsuario().getListaEsportes();
			int size = lis.size();
			int vetor[] = new int[size];
			for(int i=0; i<size; i++) {
				
				vetor[i] = lis.get(i).getIdesporte()-1;
						
			}
			listaEsportes.setSelectedIndices(vetor);
		}
		
		getContentPane().setLayout(groupLayout);

	}
	
}

