package model;

public enum GrupoTipo {
	
	PUBLICO(0,"Grupo Publico"),
	PRIVADO(1,"Grupo Privado");
	
	private Integer tipo;
	private String descricao;
	
	GrupoTipo(Integer tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}
	
	public Integer getTipo() {
		return tipo;
		
	}
	
	public String getDescricao() {
		return descricao;
	}

}
