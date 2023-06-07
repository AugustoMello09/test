package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

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
