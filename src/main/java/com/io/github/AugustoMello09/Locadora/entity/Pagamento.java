package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonSubTypes({ @JsonSubTypes.Type(value = PagamentoComBoleto.class, name = "boleto"),
	@JsonSubTypes.Type(value = PagamentoComCartao.class, name = "cartao"),
	@JsonSubTypes.Type(value = PagamentoComPix.class, name = "pix") })
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;
	private Integer formaPagamento;

	@ManyToOne
	@JoinColumn(name = "multa_id")
	private Multa multa;

	public Pagamento() {
	}

	public Pagamento(Long id, Double valor, FormaPagamento formaPagamento, Multa multa) {
		super();
		this.id = id;
		this.valor = valor;
		this.formaPagamento = (formaPagamento == null) ? 0 : formaPagamento.getCod();
		this.multa = multa;
	}

	public Multa getMulta() {
		return multa;
	}

	public void setMulta(Multa multa) {
		this.multa = multa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}

	public FormaPagamento getformaPagamento() {
		return FormaPagamento.toEnum(this.formaPagamento);
	}

	public void setformaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCod();
	}

}
