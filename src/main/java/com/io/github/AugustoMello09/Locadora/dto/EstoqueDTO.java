package com.io.github.AugustoMello09.Locadora.dto;

import com.io.github.AugustoMello09.Locadora.entities.Estoque;

public class EstoqueDTO {

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
