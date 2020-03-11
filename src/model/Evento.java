package model;

import java.util.Date;

public class Evento {
	
	private Integer idevento;
	private String descricao;
	private Date dataEvento;
	private Date horaEvento;
	private String local;
	
	public Integer getIdevento() {
		return idevento;
	}
	public void setIdevento(Integer idevento) {
		this.idevento = idevento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public Date getHoraEvento() {
		return horaEvento;
	}
	public void setHoraEvento(Date horaEvento) {
		this.horaEvento = horaEvento;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	

}
