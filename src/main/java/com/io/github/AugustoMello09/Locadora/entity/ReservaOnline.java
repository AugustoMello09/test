package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reservaOnline")
public class ReservaOnline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int qtdReservada;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataReserva;
	
	private Integer statusReserva;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "estoque_id", referencedColumnName = "id")
	private Estoque estoque;

	public ReservaOnline() {

	}

	public ReservaOnline(Long id, int qtdReservada, LocalDate dataReserva, User user, Estoque estoque,
			StatusReserva statusReserva) {
		super();
		this.id = id;
		this.qtdReservada = qtdReservada;
		this.dataReserva = dataReserva;
		this.user = user;
		this.estoque = estoque;
		this.statusReserva = (statusReserva == null)? 0: statusReserva.getCod();
	}
	
	public StatusReserva getStatus() {
		return StatusReserva.toEnum(this.statusReserva);
	}

	public void setStatus(StatusReserva status) {
		this.statusReserva = status.getCod();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtdReservada() {
		return qtdReservada;
	}

	public void setQtdReservada(int qtdReservada) {
		this.qtdReservada = qtdReservada;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDate dataReserva) {
		this.dataReserva = dataReserva;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
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
		ReservaOnline other = (ReservaOnline) obj;
		return Objects.equals(id, other.id);
	}

}
