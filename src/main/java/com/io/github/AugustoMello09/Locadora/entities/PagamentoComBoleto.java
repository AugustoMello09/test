package com.io.github.AugustoMello09.Locadora.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_pagamento_boleto")
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataGerada;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Long id, Double valor, EstadoPagamento estado, Locacao locacao,
			LocalDateTime dataVencimento, LocalDateTime dataGerada, LocalDateTime dataPagamento) {
		super(id, valor, estado, locacao);
		this.dataVencimento = this.dataGerada.plusDays(30);
		this.dataGerada = dataGerada;
		if (dataPagamento.isAfter(dataVencimento)) {
			throw new IllegalArgumentException("Boleto Vencido");
		}
		this.dataPagamento = dataPagamento;
	}

}