package com.io.github.AugustoMello09.Locadora.entity;

import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento_cartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private int numeroParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(Long id, Double valor, FormaPagamento  formaPagamento,
			Multa multa	,int numeroParcelas) {
		super(id, valor, formaPagamento, multa);
		this.numeroParcelas = numeroParcelas;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
