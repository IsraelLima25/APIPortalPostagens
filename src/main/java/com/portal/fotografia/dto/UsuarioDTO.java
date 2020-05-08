package com.portal.fotografia.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private String nome;
	private String senha;	
	
	private List<PostagemDTO> postagens = new ArrayList<>();
	
	public UsuarioDTO() {
		
	}	
	
	public UsuarioDTO(Integer id, String nome, String senha) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<PostagemDTO> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<PostagemDTO> postagens) {
		this.postagens = postagens;
	}
}
