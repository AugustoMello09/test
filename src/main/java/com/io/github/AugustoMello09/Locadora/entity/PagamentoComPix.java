package com.io.github.AugustoMello09.Locadora.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

@Entity
@Table(name = "tb_pagamento_pix")
public class PagamentoComPix extends Pagamento {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;

	public PagamentoComPix() {

	}

	public PagamentoComPix(Long id, Double valor, FormaPagamento  formaPagamento,
			 Multa multa, LocalDateTime dataPagamento) {
		super(id, valor, formaPagamento, multa);
		this.dataPagamento = LocalDateTime.now();
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
