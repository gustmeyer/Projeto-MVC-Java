package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
	
	private Integer idusuario;
	private String nome;
	private String email;
	private Date Data_Nascimento;
	private SexoEnum sexo;
	private Regiao regiao;
	private List<Esporte> listaEsportes;
	private List<Grupo> ListaGrupos;
	private List<Evento> MeusEventos;
	
	private String tipoUsuarioGrupo;
	
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getData_Nascimento() {
		return Data_Nascimento;
	}
	public void setData_Nascimento(Date data_Nascimento) {
		Data_Nascimento = data_Nascimento;
	}
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	public List<Esporte> getListaEsportes() {
		return listaEsportes;
	}
	public void setListaEsportes(List<Esporte> listaEsportes) {
		this.listaEsportes = listaEsportes;
	}
	public List<Grupo> getListaGrupos() {
		return ListaGrupos;
	}
	public void setListaGrupos(List<Grupo> listaGrupos) {
		ListaGrupos = listaGrupos;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public void entrarNoGrupo(Grupo meuGrupo) {
		if(getListaGrupos() == null) {
			ListaGrupos = new ArrayList<Grupo>();
			ListaGrupos.add(meuGrupo);
		}else {
			ListaGrupos.add(meuGrupo);
		}
	}
	
	public String getTipoUsuarioGrupo() {
		return tipoUsuarioGrupo;
	}
	public void setTipoUsuarioGrupo(String tipoUsuarioGrupo) {
		this.tipoUsuarioGrupo = tipoUsuarioGrupo;
	}
	public List<Evento> getMeusEventos() {
		return MeusEventos;
	}
	public void setMeusEventos(List<Evento> meusEventos) {
		MeusEventos = meusEventos;
	}
	
	
	
	
	
}
