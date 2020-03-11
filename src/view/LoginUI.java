package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.UsuarioController;
import dao.UsuarioDAO;
import model.Usuario;
import model.UsuarioConectado;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class LoginUI extends JInternalFrame {
	private JTextField txtEmail;
	public static int n = 0;
	private Usuario user;
	UsuarioConectado us = UsuarioConectado.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBackground(SystemColor.control);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(36, 80, 63, 31);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setBounds(99, 80, 222, 31);
		txtEmail.setToolTipText("Digite seu e-mail aqui");
		txtEmail.setColumns(10);
		
		System.out.println("teste N "+ n);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(SystemColor.controlDkShadow);
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrar.setBackground(SystemColor.menu);
		btnEntrar.setBounds(164, 138, 93, 38);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
			user = new UsuarioController().logar(txtEmail.getText());
			us.setUsuario(user);
				
			if(us.getUsuario() != null) {
				n = 1;
				System.out.println(us.getUsuario().getNome());
			}
				
				dispose();
				}
				catch(NullPointerException e) {
					e.getMessage();
					JOptionPane.showMessageDialog(null, "Usuário não Cadastrado!");
				}
					
			}
		});
		
		CadastroUsuarioUI cad;
		JButton btnNovoCadastro = new JButton("Novo Cadastro");
		btnNovoCadastro.setForeground(SystemColor.controlDkShadow);
		btnNovoCadastro.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovoCadastro.setBackground(SystemColor.menu);
		btnNovoCadastro.setBounds(147, 187, 127, 38);
		btnNovoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroUsuarioUI cad;
				try {
					cad = new CadastroUsuarioUI();
					cad.setVisible(true);
					LoginUI.this.dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnEntrar);
		getContentPane().add(btnNovoCadastro);
		getContentPane().add(lblEmail);
		getContentPane().add(txtEmail);

	}
}
