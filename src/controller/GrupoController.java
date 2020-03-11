package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dao.GrupoDAO;
import model.Grupo;
import model.Usuario;
import model.UsuarioConectado;

public class GrupoController {
	UsuarioConectado userConectado = UsuarioConectado.getInstancia();
	
	public Integer salvar(Grupo grupo) {
		return new GrupoDAO().salvar(grupo);
	}
	
	public List<Grupo> pesquisar() {
		return new GrupoDAO().buscarTodos();
	}
	
	public void excluir(Integer id, Grupo grupo) {
		if(userConectado.getUsuario().getIdusuario() == grupo.getCriador()) {
			new GrupoDAO().excluirGrupo(id);
			JOptionPane.showMessageDialog(null, "Grupo exclu�do com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Somente o Criador do grupo tem permiss�o para Excluir");
		}
		
	}
	
	public void editar(Grupo grupo) {
		if(userConectado.getUsuario().getIdusuario() == grupo.getCriador()) {
			new GrupoDAO().editar(grupo);
			JOptionPane.showMessageDialog(null, "Grupo editado com sucesso");
		}else {
			JOptionPane.showMessageDialog(null, "Somente o Criador do grupo tem permiss�o para Editar");
		}
		
	}
	
	public List<Grupo> Buscar(String p) {
		return new GrupoDAO().buscarPorNome(p);
	}
	
	public void entrarNoGrupo(int idUsuario, int idGrupo, int idCriador) {
		if(userConectado.getUsuario().getListaGrupos() == null) {
			new GrupoDAO().entrarNoGrupo(idUsuario, idGrupo, idCriador);
			JOptionPane.showMessageDialog(null, "Voc� entrou no Grupo!");
		}else {
			List<Grupo> verificar = userConectado.getUsuario().getListaGrupos();
			boolean x = true;
			for(Grupo g : verificar) {
				if(g.getIdgrupo() == idGrupo) {
					x = false;
				}
				
			}
			if(x == true) {
				new GrupoDAO().entrarNoGrupo(idUsuario, idGrupo, idCriador);
				JOptionPane.showMessageDialog(null, "Voc� entrou no Grupo!");
			}else {
				JOptionPane.showMessageDialog(null, "Voc� j� est� neste grupo!");
			}
		}
		
	}
	
	public List<Usuario> listaUsuariosGrupo(Integer idGrupo){
		return new GrupoDAO().listaUsuariosGrupo(idGrupo);
	}
	
}
