package com.io.github.AugustoMello09.Locadora.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_pagamento_pix")
public class PagamentoComPix extends Pagamento {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;

	public PagamentoComPix() {

	}

	public PagamentoComPix(Long id, Double valor, EstadoPagamento estado, Locacao locacao,
			LocalDateTime dataPagamento) {
		super(id, valor, estado, locacao);
		this.dataPagamento = dataPagamento;
	}

}
