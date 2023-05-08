package com.io.github.AugustoMello09.Locadora.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_estoque")
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int qtd;
	private Integer status;

	@JsonIgnore
	@OneToMany(mappedBy = "estoque")
	private List<Filme> filmes = new ArrayList<>();

	@OneToMany(mappedBy = "estoque", fetch = FetchType.EAGER)
	private List<Reserva> reservas = new ArrayList<>();

	@OneToMany(mappedBy = "estoque", fetch = FetchType.EAGER)
	private List<ReservaOnline> reservasOnline = new ArrayList<>();

	public Estoque() {
	}

	public Estoque(Long id, int qtd, StatusEstoque status) {
		super();
		this.id = id;
		this.qtd = qtd;
		this.status = (status == null) ? 3 : status.getCod();
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
		int quantidadeDisponivel = this.getQuantidadeFilmesDisponiveis();
	    if (quantidadeDisponivel >= 10) {
	        this.setStatus(StatusEstoque.DISPONIVEL);
	    } else if (quantidadeDisponivel > 2 && quantidadeDisponivel <= 9) {
	        this.setStatus(StatusEstoque.REPOSICAO);
	    } else {
	        this.setStatus(StatusEstoque.INDISPONIVEL);
	    }
	}    
	
	public StatusEstoque getStatus() {
		return StatusEstoque.toEnum(this.status);
	}

	public void setStatus(StatusEstoque status) {
		this.status = status.getCod();
	}

	public int getQuantidadeFilmesReservados() {
		int quantidade = 0;
		for (Reserva reserva : reservas) {
			quantidade += reserva.getQtdReservada();
		}
		return quantidade;
	}

	public int getQuantidadeFilmesReservadosOnline() {
		int quantidade = 0;
		for (ReservaOnline reserva : reservasOnline) {
			quantidade += reserva.getQtdReservada();
		}
		return quantidade;
	}

	public int getQuantidadeFilmesDisponiveis() {
		int quantidadeTotal = this.getQtd();
		int quantidadeReservada = this.getQuantidadeFilmesReservados();
		int quantidadeReservadaOnline = this.getQuantidadeFilmesReservadosOnline();
		int quantidadeDisponivel = quantidadeTotal - quantidadeReservada - quantidadeReservadaOnline;
		return quantidadeDisponivel;
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public List<ReservaOnline> getReservasOnline() {
		return reservasOnline;
	}

	public void setReservasOnline(List<ReservaOnline> reservasOnline) {
		this.reservasOnline = reservasOnline;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
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
		Estoque other = (Estoque) obj;
		return Objects.equals(id, other.id);
	}

}
