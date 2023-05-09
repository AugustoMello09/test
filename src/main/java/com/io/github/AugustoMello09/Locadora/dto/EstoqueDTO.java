package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entities.Estoque;

public class EstoqueDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private int qtd;

	public EstoqueDTO() {

	}

	public EstoqueDTO(Estoque entity) {
		this.id = entity.getId();
		this.qtd = entity.getQtd();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}
