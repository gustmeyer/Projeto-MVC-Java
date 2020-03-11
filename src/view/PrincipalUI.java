package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import controller.UsuarioController;
import model.Grupo;
import model.UsuarioConectado;
import view.tables.GruposDoUsuarioTableModel;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class PrincipalUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UsuarioConectado us = UsuarioConectado.getInstancia();
	private JPanel contentPane;
	private JTextField txtNomeAqui;
	private JTextField textEmail;
	private JTextField textRegiao;
	private JTable tableGruposUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
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
	public PrincipalUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 600);
		
		JScrollPane scrollPane = new JScrollPane();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(SystemColor.inactiveCaptionBorder);
		menuBar.setBackground(SystemColor.activeCaptionBorder);
		setJMenuBar(menuBar);

		JMenu Grupos = new JMenu("Grupos");
		Grupos.setHorizontalAlignment(SwingConstants.CENTER);
		Grupos.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		Grupos.setForeground(SystemColor.inactiveCaptionBorder);
		menuBar.add(Grupos);

		JMenuItem mntmPesquisar = new JMenuItem("Pesquisar");
		mntmPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(us.getUsuario() == null) {
					JOptionPane.showMessageDialog(null, "É Preciso Logar!");
				}else {
					ConsultaGruposUI consultaGrupo = new ConsultaGruposUI();
					consultaGrupo.setVisible(true);
					contentPane.add(consultaGrupo, 0);
				}
				
			}
		});
		Grupos.add(mntmPesquisar);

		JMenuItem mntmCriar = new JMenuItem("Criar Novo Grupo");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (us.getUsuario() != null) {
					CadastroGrupoUI cadGrupo = new CadastroGrupoUI(null);
					cadGrupo.setVisible(true);
					contentPane.add(cadGrupo, 0);
				} else {
					JOptionPane.showMessageDialog(null, "Faça o Login para criar um novo grupo.");
				}

			}
		});
		Grupos.add(mntmCriar);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelInfoUsuario = new JPanel();
		panelInfoUsuario.setBounds(5, 61, 336, 231);
		panelInfoUsuario.setForeground(UIManager.getColor("Button.darkShadow"));
		panelInfoUsuario.setBackground(new Color(192, 192, 192));
		panelInfoUsuario.setBorder(
				new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JButton btnEditar = new JButton("Editar Perfil");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditar.setForeground(SystemColor.controlDkShadow);
		btnEditar.setBackground(new Color(220, 220, 220));
		btnEditar.setBounds(57, 197, 211, 23);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (us.getUsuario() == null) {
					JOptionPane.showMessageDialog(null, "Não há usuario logado para editar!");
				} else {
					try {
						CadastroUsuarioUI edit = new CadastroUsuarioUI();
						edit.setVisible(true);
					} catch (ParseException e1) {
						
						e1.printStackTrace();
					}
				}

			}
		});

		txtNomeAqui = new JTextField();
		txtNomeAqui.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		txtNomeAqui.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeAqui.setForeground(new Color(128, 128, 128));
		txtNomeAqui.setBounds(10, 47, 305, 32);
		txtNomeAqui.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (us.getUsuario() != null) {
					txtNomeAqui.setText(us.getUsuario().getNome());
				} else {
					txtNomeAqui.setText("Nome:");
				}
			}
		});

		txtNomeAqui.setEditable(false);
		txtNomeAqui.setColumns(10);
		txtNomeAqui.setText("Nome:");

		textEmail = new JTextField();
		textEmail.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		textEmail.setForeground(new Color(128, 128, 128));
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setBounds(10, 90, 305, 27);
		textEmail.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (us.getUsuario() != null) {
					textEmail.setText(us.getUsuario().getEmail());
				} else {
					textEmail.setText("Email");
				}
			}
		});

		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setText("Email");

		textRegiao = new JTextField();
		textRegiao.setHorizontalAlignment(SwingConstants.CENTER);
		textRegiao.setForeground(new Color(128, 128, 128));
		textRegiao.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		textRegiao.setBounds(10, 128, 305, 32);
		textRegiao.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (us.getUsuario() != null) {
					textRegiao.setText(us.getUsuario().getRegiao().getNomeRegiao());
				} else {
					textRegiao.setText("Regiao");
				}
			}
		});
		textRegiao.setEditable(false);
		textRegiao.setText("Regiao");
		textRegiao.setColumns(10);

		JButton btnLogar = new JButton("Login");
		btnLogar.setBackground(SystemColor.menu);
		btnLogar.setForeground(new Color(128, 128, 128));
		btnLogar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogar.setBounds(5, 20, 161, 30);
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (us.getUsuario() != null) {
					JOptionPane.showMessageDialog(null, "Você já esta logado!");
				} else {
					LoginUI login = new LoginUI();
					login.setVisible(true);
					contentPane.add(login, 0);
				}

			}
		});

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(SystemColor.menu);
		btnLogout.setForeground(new Color(128, 128, 128));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.setBounds(176, 20, 165, 30);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				us.setUsuario(null);
			}
		});
		
				JButton btnSairDoGrupo = new JButton("Sair do Grupo");
				btnSairDoGrupo.setBackground(SystemColor.menu);
				btnSairDoGrupo.setForeground(new Color(105, 105, 105));
				btnSairDoGrupo.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSairDoGrupo.setBounds(695, 257, 136, 35);
				btnSairDoGrupo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(us.getUsuario() == null) {
							JOptionPane.showMessageDialog(null, "É Preciso Logar!");
						}else {
							if(us.getUsuario().getListaGrupos().size() > 0) {
								if(tableGruposUser.getSelectedRow() >= 0) {
									int idUser = us.getUsuario().getIdusuario();
									Grupo grupo = new GruposDoUsuarioTableModel(new UsuarioController().gruposDoUsuario(idUser)).get(tableGruposUser.getSelectedRow());
									new UsuarioController().sairDoGrupo(idUser, grupo.getIdgrupo());
									List<Grupo> g = new UsuarioController().gruposDoUsuario(idUser);
									us.getUsuario().setListaGrupos(g);
									tableGruposUser
									.setModel(new GruposDoUsuarioTableModel(new UsuarioController().gruposDoUsuario(idUser)));
							scrollPane.setViewportView(tableGruposUser);
								}else {
									JOptionPane.showMessageDialog(null, "Nenhum grupo selecionado");
								}
									
							}else {
								JOptionPane.showMessageDialog(null, "Você ainda não está em nenhum grupo");
							}
							
						}
						
					}
				});
		
		JButton btnIrParaGrupo = new JButton("Ir para Grupo");
		btnIrParaGrupo.setBackground(SystemColor.menu);
		btnIrParaGrupo.setForeground(new Color(105, 105, 105));
		btnIrParaGrupo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIrParaGrupo.setBounds(376, 256, 128, 36);
		btnIrParaGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(us.getUsuario() == null) {
					JOptionPane.showMessageDialog(null, "É Preciso Logar!");
				}else {
					if(us.getUsuario().getListaGrupos().size() > 0) {
						int idUser = us.getUsuario().getIdusuario();
						if(tableGruposUser.getSelectedRow() >= 0) {
							Grupo grupo = new GruposDoUsuarioTableModel(new UsuarioController().gruposDoUsuario(idUser)).get(tableGruposUser.getSelectedRow());
							GrupoUI verGrupo = new GrupoUI(grupo);
							verGrupo.setVisible(true);
							contentPane.add(verGrupo, 0);
						}
						else {
							JOptionPane.showMessageDialog(null, "Nenhum grupo selecionado");
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Você ainda não está em nenhum grupo");
					}
					
				}
				
				
			}
		});
		panelInfoUsuario.setLayout(null);
		panelInfoUsuario.add(textRegiao);
		panelInfoUsuario.add(btnEditar);
		panelInfoUsuario.add(txtNomeAqui);
		panelInfoUsuario.add(textEmail);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(114, 11, 96, 14);
		panelInfoUsuario.add(lblUsuario);
		contentPane.setLayout(null);
		
				
				scrollPane.setBounds(12, 39, 430, 179);
				scrollPane.setViewportBorder(null);
				
						JPanel panelGruposUsuario = new JPanel();
						
						panelGruposUsuario.setBounds(376, 20, 454, 231);
						panelGruposUsuario.setForeground(UIManager.getColor("Button.darkShadow"));
						panelGruposUsuario.setBackground(new Color(192, 192, 192));
						panelGruposUsuario.addMouseListener(new MouseAdapter() {
						/*	@Override
							public void mouseEntered(MouseEvent arg0) {
								if (us.getUsuario() != null) {
									int idUser = us.getUsuario().getIdusuario();
									
									tableGruposUser
											.setModel(new GruposDoUsuarioTableModel(new UsuarioController().gruposDoUsuario(idUser)));
									scrollPane.setViewportView(tableGruposUser);
								} else {
									tableGruposUser = new JTable();
									scrollPane.setViewportView(tableGruposUser);
								}
							}*/

							@Override
							public void mouseReleased(MouseEvent arg0) {
								if (us.getUsuario() != null) {
									int idUser = us.getUsuario().getIdusuario();
									
									tableGruposUser
											.setModel(new GruposDoUsuarioTableModel(new UsuarioController().gruposDoUsuario(idUser)));
									scrollPane.setViewportView(tableGruposUser);
								} else {
									tableGruposUser = new JTable();
									scrollPane.setViewportView(tableGruposUser);
								}
							}
						});
						panelGruposUsuario
								.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
						
						JLabel lblMeusGrupos = new JLabel("Meus Grupos");
						lblMeusGrupos.setBounds(12, 11, 90, 17);
						lblMeusGrupos.setForeground(new Color(255, 255, 255));
						lblMeusGrupos.setFont(new Font("Tahoma", Font.BOLD, 14));
								
								JLabel lblClickAquiPara = new JLabel("Click aqui para atualizar a lista");
								lblClickAquiPara.setHorizontalAlignment(SwingConstants.CENTER);
								lblClickAquiPara.setFont(new Font("Tahoma", Font.PLAIN, 12));
								lblClickAquiPara.setForeground(Color.WHITE);
								lblClickAquiPara.setBounds(130, 14, 195, 14);
								tableGruposUser = new JTable();
								scrollPane.setViewportView(tableGruposUser);
								contentPane.add(panelGruposUsuario);
								panelGruposUsuario.setLayout(null);
								panelGruposUsuario.add(lblClickAquiPara);
								panelGruposUsuario.add(scrollPane);
								panelGruposUsuario.add(lblMeusGrupos);
		contentPane.add(panelInfoUsuario);
		contentPane.add(btnLogar);
		contentPane.add(btnLogout);
		contentPane.add(btnSairDoGrupo);
		contentPane.add(btnIrParaGrupo);
		
		JButton btnCriarNovo = new JButton("Criar Novo");
		btnCriarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (us.getUsuario() != null) {
					CadastroGrupoUI cadGrupo = new CadastroGrupoUI(null);
					cadGrupo.setVisible(true);
					contentPane.add(cadGrupo, 0);
				} else {
					JOptionPane.showMessageDialog(null, "Faça o Login para criar um novo grupo.");
				}
				
			}
		});
		btnCriarNovo.setForeground(SystemColor.controlDkShadow);
		btnCriarNovo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCriarNovo.setBackground(SystemColor.menu);
		btnCriarNovo.setBounds(514, 257, 171, 36);
		contentPane.add(btnCriarNovo);
	}
}
