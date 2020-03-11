package model;

public class Esporte {
	
	private Integer idesporte;
	private String nomeEsporte;
	
	public Esporte(Integer idesporte, String nomeEsporte) {
		this.idesporte = idesporte;
		this.nomeEsporte = nomeEsporte;
	}
	public Esporte() {
		
	}
	public Integer getIdesporte() {
		return idesporte;
	}
	public void setIdesporte(Integer idesporte) {
		this.idesporte = idesporte;
	}
	public String getNomeEsporte() {
		return nomeEsporte;
	}
	public void setNomeEsporte(String nomeEsporte) {
		this.nomeEsporte = nomeEsporte;
	}
	
	

}
