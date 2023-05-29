package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Reserva;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private int quantidade;
	private StatusEstoque status;

	private List<ReservaDTO> reservas = new ArrayList<>();
	private List<ReservaOnlineDTO> reservasOnline = new ArrayList<>();

	private int quantidadeFilmesReservados;
	private int quantidadeFilmesReservadosOnline;
	private int quantidadeFilmesDisponiveis;

	public EstoqueDTO() {
	}

	public EstoqueDTO(Estoque entity) {
		this.id = entity.getId();
		this.quantidade = entity.getQuantidade();
		this.status = entity.getStatus();
		this.quantidadeFilmesReservados = entity.getQuantidadeFilmesReservados();
		this.quantidadeFilmesReservadosOnline = entity.getQuantidadeFilmesReservadosOnline();
		this.quantidadeFilmesDisponiveis = entity.getQuantidadeFilmesDisponiveis();
		entity.getReservas().stream().filter(reserva -> !isReservaAlreadyAdded(reserva)).map(ReservaDTO::new)
				.forEach(reservas::add);
		entity.getReservasOnline().stream().filter(reservaOnline -> !isReservaOnlineAlreadyAdded(reservaOnline))
				.map(ReservaOnlineDTO::new).forEach(reservasOnline::add);
	}

	private boolean isReservaAlreadyAdded(Reserva reserva) {
		return reservas.stream().anyMatch(reservaDTO -> reservaDTO.getId().equals(reserva.getId()));
	}

	private boolean isReservaOnlineAlreadyAdded(ReservaOnline reservaOnline) {
		return reservasOnline.stream()
				.anyMatch(reservaOnlineDTO -> reservaOnlineDTO.getId().equals(reservaOnline.getId()));
	}
}
