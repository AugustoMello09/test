package com.io.github.AugustoMello09.Locadora.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;

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

	private int qtd;
	private Integer status;

	@OneToMany(mappedBy = "estoque")
	private List<Filme> filmes = new ArrayList<>();

	@OneToMany(mappedBy = "estoque")
	private List<Reserva> reservas = new ArrayList<>();

	@OneToMany(mappedBy = "estoque")
	private List<ReservaOnline> reservasOnline = new ArrayList<>();

	public Estoque() {
	}

	public Estoque(Long id, int qtd, StatusReserva status) {
		super();
		this.id = id;
		this.qtd = qtd;
		this.status = (status == null) ? 0 : status.getCod();
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

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public StatusReserva getStatus() {
		return StatusReserva.toEnum(this.status);
	}

	public void setStatus(StatusReserva status) {
		this.status = status.getCod();
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
