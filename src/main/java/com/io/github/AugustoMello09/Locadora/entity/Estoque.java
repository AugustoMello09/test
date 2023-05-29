package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;

import jakarta.persistence.Entity;
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

	private int quantidade;

	private Integer status;

	@OneToMany(mappedBy = "estoque")
	private List<Filme> filmes = new ArrayList<>();

	@OneToMany(mappedBy = "estoque")
	private List<Reserva> reservas = new ArrayList<>();

	@OneToMany(mappedBy = "estoque")
	private List<ReservaOnline> reservasOnline = new ArrayList<>();

	public Estoque() {
	}

	public Estoque(Long id, int quantidade, StatusEstoque status) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.status = (status == null) ? 3 : status.getCod();
	}

	public void setQtd(int quantidade) {
		this.quantidade = quantidade;
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

	public int getQuantidadeFilmesDisponiveis() {
		int quantidadeTotal = this.getQuantidade();
		int quantidadeReservada = this.getQuantidadeFilmesReservados();
		int quantidadeReservadaOnline = this.getQuantidadeFilmesReservadosOnline();
		int quantidadeDisponivel = quantidadeTotal - quantidadeReservada - quantidadeReservadaOnline;
		return quantidadeDisponivel;
	}

	public int getQuantidadeFilmesReservados() {
		return (int) reservas.stream().map(Reserva::getId).distinct().count();
	}

	public int getQuantidadeFilmesReservadosOnline() {
		return (int) reservasOnline.stream().map(ReservaOnline::getId).distinct().count();
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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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