package model;


public class UsuarioConectado {
	
	private static UsuarioConectado instancia;
	private Usuario usuario;
	
	public static UsuarioConectado getInstancia() {
		if (instancia == null) {
			instancia  = new UsuarioConectado();
		}
		return instancia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
