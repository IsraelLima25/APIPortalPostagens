package com.portal.fotografia.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.fotografia.domain.Usuario;

public class PostagemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private String fotoDiretorio;
	private String titulo;
	private String biografia;
	
	@JsonIgnore
	private Usuario usuario;

	public PostagemDTO() {
		
	}
	
	public PostagemDTO(Integer id, String fotoDiretorio, String titulo,String biografia) {
		this.id = id;
		this.fotoDiretorio = fotoDiretorio;
		this.titulo = titulo;
		this.biografia = biografia;
	}
	
	public PostagemDTO(String fotoDiretorio, String titulo,String biografia) {
		this.fotoDiretorio = fotoDiretorio;
		this.titulo = titulo;
		this.biografia = biografia;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFotoDiretorio() {
		return fotoDiretorio;
	}
	public void setFotoDiretorio(String fotoDiretorio) {
		this.fotoDiretorio = fotoDiretorio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
