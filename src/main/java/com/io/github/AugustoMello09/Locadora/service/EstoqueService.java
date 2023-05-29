package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository repository;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Transactional
	public EstoqueDTO findEstoqueById(Long id) {
		Optional<Estoque> obj = repository.findById(id);
		Estoque entity = obj.orElseThrow(()-> new ObjectNotFoundException("Estoque Não encontrado"));
		return new EstoqueDTO(entity);
	}
	
	@Transactional
	public EstoqueDTO create(EstoqueDTO est) {
		Estoque entity = new Estoque();
		entity.setId(est.getId());
		entity.setQuantidade(est.getQuantidade());
		entity.setStatus(est.getStatus());
		repository.save(entity);
		return new EstoqueDTO(entity);
	}
	
	@Transactional
	public EstoqueDTO update(EstoqueDTO est, Long id) {
		Estoque entity = repository.findById(id).orElseThrow(
				()-> new ObjectNotFoundException("Estoque Não encontrado"));
		Optional<Filme> filme = filmeRepository.findByEstoque(entity);
		if (filme.isPresent()) {
			throw new DataIntegratyViolationException(
					"Este estoque está sendo utilizado por um filme e não pode ser atualizado se não for por alguma operação que altere o valor como DEVOLUÇÂO e LOCAÇÃO");
		}
		entity.setQuantidade(est.getQuantidade());
		repository.save(entity);
		return new EstoqueDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegratyViolationException e) {
			throw new DataIntegrityViolationException("Não pode excluir estoque");
		}
		
	}
	
	
}
