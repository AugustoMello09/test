package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
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
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public ReservaOnlineDTO findReservaOnlineById(Long id) {
		Optional<ReservaOnline> obj = repository.findById(id);
		ReservaOnline entity = obj.orElseThrow(() -> new ObjectNotFoundException("ReservaOnline não encontrada"));
		return new ReservaOnlineDTO(entity);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
	public void cancelarReserva(Long id) {
		ReservaOnline reserva = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ReservaOnline não encontrada"));
		Estoque estoque = reserva.getEstoque();
		estoque.getReservasOnline().remove(reserva);
		repository.delete(reserva);
	}

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','OPERATOR')")
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
	    if (entity.getQtdReservada() > estoque.getQuantidade()) {
	        throw new DataIntegratyViolationException("Quantidade solicitada maior do que a disponível no estoque");
	    }
		entity.setEstoque(estoque);
		repository.save(entity);
		return new ReservaOnlineDTO(entity);
	}

}
