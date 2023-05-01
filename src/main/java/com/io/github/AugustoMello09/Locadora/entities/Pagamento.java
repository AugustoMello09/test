package com.io.github.AugustoMello09.Locadora.entities;

import java.io.Serializable;
import java.util.Objects;

import com.io.github.AugustoMello09.Locadora.entities.enums.EstadoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valor;
	private Integer estado;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "user_id"),
		@JoinColumn(name = "filme_id")
	})
	private Locacao locacao;

	public Pagamento() {
	}

	public Pagamento(Long id, Double valor, EstadoPagamento estado, Locacao locacao) {
		super();
		this.id = id;
		this.valor = valor;
		this.estado = (estado == null) ? 0 : estado.getCod();
		this.locacao = locacao;
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

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
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

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(this.estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

}
