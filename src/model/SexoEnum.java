package model;

public enum SexoEnum {
	
	M(0),
	F(1);
	
	private Integer posicao;
	
	private SexoEnum(Integer posicao) {
		this.posicao = posicao;
	}
	
	public Integer getPosicao() {
		return posicao;
	}
}
