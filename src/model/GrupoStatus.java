package model;

public enum GrupoStatus {
	
	ATIVO("Grupo ativo!",0),
	INATIVO("Grupo Inativo!",1);
	
	private String descricao;
	private Integer numero;
	
	GrupoStatus(String desc, Integer numero){
		this.descricao = desc;
		this.numero = numero;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public Integer getNumero() {
		return this.numero;
	}
	
}
