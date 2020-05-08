package com.portal.fotografia.domain.enuns;

public enum StatusSolicitacao {
	
	AGUARDANDO(1, "Aguardando"), 
	ACEITO(2, "Aceito"),
	RECUSADO(3,"Recusado");

	private Integer cod;
	private String descricao;

	private StatusSolicitacao(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static StatusSolicitacao toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusSolicitacao c : StatusSolicitacao.values()) {
			if (cod.equals(c.getCod())) {
				return c;
			}
		}

		throw new IllegalArgumentException("Codigo Inválido " + cod);

	}

}
