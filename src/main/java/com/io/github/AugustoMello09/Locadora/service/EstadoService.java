package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	@Transactional
	public EstadoDTO findById(Long id) {
		Optional<Estado> est = repository.findById(id);
		Estado entity = est.orElseThrow(()-> new ObjectNotFoundException("Estado Não encontrada"));
		return new EstadoDTO(entity);
	}

	@Transactional
	public EstadoDTO create(EstadoDTO estDto) {
		Estado entity = new Estado();
		entity.setId(estDto.getId());
		entity.setName(estDto.getName());
		repository.save(entity);
		return new EstadoDTO(entity);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar estados associados a cidades");
		}
	}

}
