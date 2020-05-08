package com.portal.fotografia.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.fotografia.domain.enuns.StatusSolicitacao;
@Entity
public class Convite implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer usuarioRemetenteId;
	private Integer usuarioDestinatarioId;
	private String mensagemSolicitacao;
	private StatusSolicitacao statusSolicitacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	

	public Convite() {
	}	

	public Convite(Integer id, Integer usuarioRemetenteId, Integer usuarioDestinatario, String mensagemSolicitacao,
		StatusSolicitacao statusSolicitacao) {
		super();
		this.id = id;
		this.usuarioRemetenteId = usuarioRemetenteId;
		this.usuarioDestinatarioId = usuarioDestinatario;
		this.mensagemSolicitacao = mensagemSolicitacao;
		this.statusSolicitacao = statusSolicitacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUsuarioRemetenteId() {
		return usuarioRemetenteId;
	}

	public void setUsuarioRemetenteId(Integer usuarioRemetenteId) {
		this.usuarioRemetenteId = usuarioRemetenteId;
	}

	public Integer getUsuarioDestinatarioId() {
		return usuarioDestinatarioId;
	}

	public void setUsuarioDestinatarioId(Integer usuarioDestinatario) {
		this.usuarioDestinatarioId = usuarioDestinatario;
	}

	public String getMensagemSolicitacao() {
		return mensagemSolicitacao;
	}

	public void setMensagemSolicitacao(String mensagemSolicitacao) {
		this.mensagemSolicitacao = mensagemSolicitacao;
	}
	public StatusSolicitacao getStatusSolicitacao() {
		return statusSolicitacao;
	}

	public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convite other = (Convite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
