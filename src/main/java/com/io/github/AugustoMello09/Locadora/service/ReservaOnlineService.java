package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaOnlineRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class ReservaOnlineService {

	@Autowired
	private ReservaOnlineRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public ReservaOnlineDTO findReservaOnlineById(Long id) {
		Optional<ReservaOnline> obj = repository.findById(id);
		ReservaOnline entity = obj.orElseThrow(() -> new ObjectNotFoundException("ReservaOnline não encontrada"));
		return new ReservaOnlineDTO(entity);
	}

	@Transactional
	public void cancelarReserva(ReservaOnlineDTO reservaOnlineDTO, Long id) {
		ReservaOnline reserva = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ReservaOnline não encontrada"));
		reserva.setStatus(reservaOnlineDTO.getStatusReserva());
		Estoque estoque = reserva.getEstoque();
		estoque.getReservasOnline().remove(reserva);
		repository.delete(reserva);
	}

	@Transactional
	public ReservaOnlineDTO create(Long idUser, Long idEstoque, ReservaOnlineDTO objDto) {
		User user = userRepository.findById(idUser)
				.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		Estoque estoque = estoqueRepository.findById(idEstoque)
				.orElseThrow(() -> new ObjectNotFoundException("Estoque não encontrado"));
		ReservaOnline entity = new ReservaOnline();
		entity.setId(objDto.getId());
		entity.setQtdReservada(objDto.getQtdReservada());
		entity.setDataReserva(objDto.getDataReserva());
		entity.setStatus(StatusReserva.ATIVA);
		entity.setUser(user);
		entity.setEstoque(estoque);
		repository.save(entity);
		return new ReservaOnlineDTO(entity);
	}

}
