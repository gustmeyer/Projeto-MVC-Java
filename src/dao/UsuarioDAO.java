package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.Esporte;
import model.Grupo;
import model.GrupoStatus;
import model.GrupoTipo;
import model.Regiao;
import model.SexoEnum;
import model.Usuario;
import util.ConnectionUtil;

public class UsuarioDAO {
	
	private static Connection con = ConnectionUtil.getConnection();
	
	public Integer salvarUsuario(Usuario usuario) {
		int key = 0;
		try {
			
		String sql = "insert into usuario (nome, email, data_nasc,sexo, id_regiao) values (?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		st.setString(1, usuario.getNome());
		st.setString(2, usuario.getEmail());
		Date data = usuario.getData_Nascimento();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		st.setDate(3, dataSql);
		st.setString(4, usuario.getSexo().name());
		st.setInt(5, usuario.getRegiao().getIdregiao());
		st.execute();
		
		ResultSet rs = st.getGeneratedKeys();
		if(rs.next()) {
			key = rs.getInt(1);
		}
		
		for(Esporte e : usuario.getListaEsportes()) {
		String	sql1 = "insert into usuario_esporte (id_usuario, id_esporte) values (?, ?);";
		PreparedStatement st1 = con.prepareStatement(sql1);
		    st1.setInt(1, key);
			st1.setInt(2, e.getIdesporte());
			st1.execute();
			
		}
		
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	public List<Esporte> montarEsportes(Integer id) {
		List<Esporte> listaEsportes = new ArrayList<Esporte>();
		try {
		String sql = "select id_esporte, esporte from lista_usuarios_esporteview where id_usuario ="+id+";" ;
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		
		while(result.next()) {
			Esporte e = new Esporte();
			e.setIdesporte(result.getInt(1));
			e.setNomeEsporte(result.getString(2));
			listaEsportes.add(e);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEsportes;
	}
	
	public void editarUsuario(Usuario usuario) {
		try {
			int id = usuario.getIdusuario();
			String sql = "update usuario set nome = ?, email = ?, data_nasc = ?, sexo = ?, id_regiao = ? where idusuario ="+id+";";
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			Date data = usuario.getData_Nascimento();
			java.sql.Date dataSql = new java.sql.Date(data.getTime());
			st.setDate(3, dataSql);
			st.setString(4, usuario.getSexo().name());
			st.setInt(5, usuario.getRegiao().getIdregiao());
			st.execute();
			
			String sq = "delete from usuario_esporte where id_usuario ="+id+";";
			PreparedStatement s = con.prepareStatement(sq);
			s.execute();
			
			for (Esporte e : usuario.getListaEsportes()) {
				String sql1 = "insert into usuario_esporte (id_esporte, id_usuario) values(?,?);";
				PreparedStatement st1 = con.prepareStatement(sql1);
				st1.setInt(1, e.getIdesporte());
				st1.setInt(2, id);
				st1.execute();
				System.out.println("UpDate no Banco: " + "iduser = " + usuario.getIdusuario() + " :" + e.getIdesporte()
						+ ", " + e.getNomeEsporte());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public Usuario logarUsuario(String Email) {
		Usuario user = new Usuario();
		List<Esporte> list = new ArrayList<Esporte>();
		List<Grupo>listaGrupos = new ArrayList<Grupo>();
		Regiao r = new Regiao();
		try {
			
			String sql = "select nome, email, idusuario, data_nasc, sexo, id_regiao, regiao from buscauserview where email like"+"'" + '%' + Email + '%'+"'" + ';';
			Statement stmt = con.createStatement();
			ResultSet resultado = stmt.executeQuery(sql);
			
			if(resultado != null && resultado.next() ) {
				
				user.setNome(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				int id = resultado.getInt(3);
				user.setIdusuario(id);
				user.setData_Nascimento(resultado.getDate("data_nasc"));
				String sexoTexto = resultado.getString(5);
				user.setSexo(SexoEnum.valueOf(sexoTexto));
				r.setIdregiao(resultado.getInt("id_regiao"));
				r.setNomeRegiao(resultado.getString("regiao"));
				user.setRegiao(r);
				list = new UsuarioDAO().montarEsportes(id);
				listaGrupos = new UsuarioDAO().MontarListaGrupoUser(id);
				user.setListaEsportes(list);
				user.setListaGrupos(listaGrupos);
				
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public List<Grupo> MontarListaGrupoUser(Integer idUser) {
		ArrayList<Grupo> lista = new ArrayList<Grupo>();
		try {
			String sql = "select * from usuario_grupo_tudoview where idusuario ="+ idUser +";";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Grupo g = new Grupo();
				Esporte e = new Esporte();
				Regiao r = new Regiao();
				g.setNome(result.getString(8));
				g.setEnumTipo(GrupoTipo.valueOf(result.getString(9)));
				g.setDescricao(result.getString(10));
				g.setDataCriacao(result.getDate(12));
				int id = result.getInt(7);
				g.setIdgrupo(id);
				g.setEnumStatus(GrupoStatus.valueOf(result.getString(13)));
				g.setCriador(result.getInt(11));
				
				e.setIdesporte(result.getInt(16));
				e.setNomeEsporte(result.getString(17));
				
				r.setIdregiao(result.getInt(14));
				r.setNomeRegiao(result.getString(15));
				
				g.setEsporte(e);
				g.setReigao(r);
				
				List<Usuario> listUsers = new GrupoDAO().listaUsuariosGrupo(id);
				g.setListaUsuarios(listUsers);
				lista.add(g);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public void sairDoGrupo(Integer iduser, Integer idGrupo) {
		try {
			String sql = "delete from usuario_grupo where id_usuario = "+iduser+" and id_grupo = "+idGrupo+";";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Saiu do grupo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
}
