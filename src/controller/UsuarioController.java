package controller;

import java.util.List;

import dao.UsuarioDAO;
import model.Grupo;
import model.Usuario;

public class UsuarioController {
	
	public List<Grupo> gruposDoUsuario(Integer idUser){
		return new UsuarioDAO().MontarListaGrupoUser(idUser);
	}
	
	public void sairDoGrupo(Integer iduser, Integer idGrupo) {
		new UsuarioDAO().sairDoGrupo(iduser, idGrupo);
	}
	
	public Integer salvarUsuario(Usuario usuario) {
		return new UsuarioDAO().salvarUsuario(usuario);
	}
	
	public void editarUsuario(Usuario usuario) {
		new UsuarioDAO().editarUsuario(usuario);
	}
	
	public Usuario logar(String Email) {
		return new UsuarioDAO().logarUsuario(Email);
	}

}
