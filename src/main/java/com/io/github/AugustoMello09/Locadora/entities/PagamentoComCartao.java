package com.io.github.AugustoMello09.Locadora.entities;

import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_pagamento_cartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private int numeroParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(Long id, Double valor, EstadoPagamento estado, Locacao locacao, int numeroParcelas) {
		super(id, valor, estado, locacao);
		this.numeroParcelas = numeroParcelas;
	}

}
