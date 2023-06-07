package com.io.github.AugustoMello09.Locadora.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

@Entity
@Table(name = "tb_pagamento_boleto")
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

	public PagamentoComBoleto(Long id, Double valor, FormaPagamento formaPagamento, Multa multa, LocalDateTime dataPagamento) {
	    this.setId(id);
	    this.setValor(valor);
	    this.setformaPagamento(formaPagamento);
	    this.setMulta(multa);
	    this.setDataPagamento(dataPagamento);
		this.dataVencimento = this.dataGerada.plusDays(30);
		this.dataGerada = LocalDateTime.now();
		if (dataPagamento.isAfter(dataVencimento)) {
			throw new IllegalArgumentException("Boleto Vencido");
		}
		this.dataPagamento = dataPagamento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataGerada() {
		return dataGerada;
	}

	public void setDataGerada(LocalDateTime dataGerada) {
		this.dataGerada = dataGerada;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
