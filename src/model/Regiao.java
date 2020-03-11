package model;

public class Regiao {
	
	private Integer idregiao;
	private String nomeRegiao;
	
	public Regiao(Integer idregiao, String nomeRegiao) {
		this.idregiao = idregiao;
		this.nomeRegiao = nomeRegiao;
	}
	public Regiao() {
		
	}
	public Integer getIdregiao() {
		return idregiao;
	}
	public void setIdregiao(Integer idregiao) {
		this.idregiao = idregiao;
	}
	public String getNomeRegiao() {
		return nomeRegiao;
	}
	public void setNomeRegiao(String nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}
	
	

}
