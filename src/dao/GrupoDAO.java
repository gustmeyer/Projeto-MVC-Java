package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Esporte;
import model.Grupo;
import model.GrupoStatus;
import model.GrupoTipo;
import model.Regiao;
import model.Usuario;
import model.UsuarioConectado;
import util.ConnectionUtil;

public class GrupoDAO {
	UsuarioConectado us = UsuarioConectado.getInstancia();
	
	private static Connection con = ConnectionUtil.getConnection();
	
	public List<Grupo> buscarTodos() {
		ArrayList<Grupo> lista = new ArrayList<Grupo>();
		try {
			String sql = "select nome, tipo, descricao, data_criacao as 'Data de criacao', id_esporte, esporte, id_regiao, regiao, idgrupo, status, criador from buscaview; ";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Grupo g = new Grupo();
				Esporte e = new Esporte();
				Regiao r = new Regiao();
				g.setNome(result.getString("nome"));
				g.setEnumTipo(GrupoTipo.valueOf(result.getString("tipo")));
				g.setDescricao(result.getString("descricao"));
				g.setDataCriacao(result.getDate("Data de criacao"));
				int id = result.getInt("idgrupo");
				g.setIdgrupo(id);
				g.setEnumStatus(GrupoStatus.valueOf(result.getString("status")));
				g.setCriador(result.getInt("criador"));
				
				e.setIdesporte(result.getInt("id_esporte"));
				e.setNomeEsporte(result.getString("esporte"));
				
				r.setIdregiao(result.getInt("id_regiao"));
				r.setNomeRegiao(result.getString("regiao"));
				
				g.setEsporte(e);
				g.setReigao(r);
				
				List<Usuario> usuariosDoGrupo = new GrupoDAO().listaUsuariosGrupo(id);
				g.setListaUsuarios(usuariosDoGrupo);
				
				lista.add(g);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public List<Grupo> buscarPorNome(String nome) {
		ArrayList<Grupo> lista = new ArrayList<Grupo>();
		try {
			String sql = "select nome, tipo, descricao, data_criacao as 'Data de criacao', id_esporte, esporte, id_regiao, regiao, idgrupo, status, criador from buscaview where nome like"+"'%"+ nome +"%'"+";";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Grupo g = new Grupo();
				Esporte e = new Esporte();
				Regiao r = new Regiao();
				g.setNome(result.getString("nome"));
				g.setEnumTipo(GrupoTipo.valueOf(result.getString("tipo")));
				g.setDescricao(result.getString("descricao"));
				g.setDataCriacao(result.getDate("Data de criacao"));
				int id = result.getInt("idgrupo");
				g.setIdgrupo(id);
				g.setEnumStatus(GrupoStatus.valueOf(result.getString("status")));
				g.setCriador(result.getInt("criador"));
				
				e.setIdesporte(result.getInt("id_esporte"));
				e.setNomeEsporte(result.getString("esporte"));
				
				r.setIdregiao(result.getInt("id_regiao"));
				r.setNomeRegiao(result.getString("regiao"));
				
				g.setEsporte(e);
				g.setReigao(r);
				
				List<Usuario> usuariosDoGrupo = new GrupoDAO().listaUsuariosGrupo(id);
				g.setListaUsuarios(usuariosDoGrupo);
				
				lista.add(g);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public List<Usuario> listaUsuariosGrupo (Integer idGrupo){
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
		String sql = "select nome, idusuario, user_tipo from usuarios_do_grupo_view where idgrupo ="+idGrupo+";";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		
		while (result.next()) {
			Usuario user = new Usuario();
			user.setIdusuario(result.getInt(2));
			user.setNome(result.getString(1));
			user.setTipoUsuarioGrupo(result.getString(3));
			lista.add(user);
		}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
		
	}

	//metodo para entrar no grupo.
	public void entrarNoGrupo(int idUsuario, int idGrupo, int idCriador) {
		try {
			String sql = "";
			if(idUsuario == idCriador) {
				sql = "insert into usuario_grupo(id_usuario, id_grupo, user_tipo) values ("+idUsuario+","+idGrupo+","+ "'ADM'" +");";
			}else {
				sql = "insert into usuario_grupo(id_usuario, id_grupo, user_tipo) values ("+idUsuario+","+idGrupo+","+ "'PARTICIPANTE'" +");";
			}
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.execute();
			
		} catch (SQLException e) {
			e.getMessage();
			
		}
		
	}

	public Integer salvar(Grupo grupo) {
		int key = 0;
		try {
			String sql = "insert into grupo (nome, tipo, descricao,id_regiao, id_esporte,data_criacao, status, criador) values (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, grupo.getNome());
			stmt.setString(2, grupo.getEnumTipo().name());
			stmt.setString(3, grupo.getDescricao());
			stmt.setInt(4, grupo.getReigao().getIdregiao());
			stmt.setInt(5, grupo.getEsporte().getIdesporte());
			Date data = grupo.getDataCriacao();
			java.sql.Date dataSql = new java.sql.Date(data.getTime());
			stmt.setDate(6, dataSql);
			stmt.setString(7, grupo.getEnumStatus().name());
			stmt.setInt(8, grupo.getCriador());
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			
			System.out.println("Chave: "+ key );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	public void editar(Grupo grupo) {
		try {
			String sql = "update grupo set nome = ?, tipo = ?, descricao = ?, id_regiao = ?, id_esporte = ? where idgrupo = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, grupo.getNome());
			stmt.setString(2, grupo.getEnumTipo().name());
			stmt.setString(3, grupo.getDescricao());
			stmt.setInt(4, grupo.getReigao().getIdregiao());
			stmt.setInt(5, grupo.getEsporte().getIdesporte());
			stmt.setInt(6, grupo.getIdgrupo());
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void excluirGrupo(Integer id) {
		try {
			
			String sql = "select idevento from evento where id_grupo ="+ id + ";";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet result = stmt.executeQuery(sql);
			
			ArrayList<Integer> eventos = new ArrayList<Integer>();
			
			while(result.next()) {
				eventos.add(result.getInt("idevento"));
			}
			
			 for(Integer ids : eventos) {
				sql = "delete from usuarios_evento where id_evento ="+ ids.intValue() +" ;";
				stmt.execute(sql);
			}
			 sql = "delete from evento where id_grupo =" + id + ";";
			 stmt.execute(sql);
			 
			/*
			sql = "SET FOREIGN_KEY_CHECKS=0;";
			stmt.execute(sql);
			for(Integer ids : listaIdUsuarios) {
				sql = "delete from usuario_grupo where id_usuario ="+ ids.intValue() +" ;";
				stmt.execute(sql);
			}
			sql = "SET FOREIGN_KEY_CHECKS=1;";
			stmt.execute(sql); */
			 
			 sql = "delete from usuario_grupo where id_grupo ="+ id +" ;";
			 stmt.execute(sql);
			 
			sql = "delete from grupo where idgrupo ="+ id +" ;";
			stmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
