package model;

import java.util.Date;
import java.util.List;

public class Grupo {
	
	private Integer idgrupo;
	private String nome;
	private GrupoTipo enumTipo;
	private String descricao;
	private Integer criador;
	private Date dataCriacao;
	private GrupoStatus enumStatus;
	private Regiao reigao;
	private Esporte esporte;
	private List<Usuario> listaUsuarios;
	private List<Evento> listaEventos;
	
	public List<Evento> getListaEventos() {
		return listaEventos;
	}
	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}
	public Integer getIdgrupo() {
		return idgrupo;
	}
	public void setIdgrupo(Integer idgrupo) {
		this.idgrupo = idgrupo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public GrupoTipo getEnumTipo() {
		return enumTipo;
	}
	public void setEnumTipo(GrupoTipo enumTipo) {
		this.enumTipo = enumTipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCriador() {
		return criador;
	}
	public void setCriador(Integer criador) {
		this.criador = criador;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public GrupoStatus getEnumStatus() {
		return enumStatus;
	}
	public void setEnumStatus(GrupoStatus enumStatus) {
		this.enumStatus = enumStatus;
	}
	public Regiao getReigao() {
		return reigao;
	}
	public void setReigao(Regiao reigao) {
		this.reigao = reigao;
	}
	public Esporte getEsporte() {
		return esporte;
	}
	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
	

}
