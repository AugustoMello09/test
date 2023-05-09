package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.entities.Estoque;
import com.io.github.AugustoMello09.Locadora.entities.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	@Autowired
	private FilmeRepository filmeRepository;

	@Transactional(readOnly = true)
	public Estoque findById(Long id) {
		Optional<Estoque> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ObjectNotFoundException("Estoque não encontrado"));
	}

	@Transactional
	public Estoque create(Estoque est) {
		est.setId(null);
		return repository.save(est);
	}

	@Transactional
	public EstoqueDTO update(Long id, EstoqueDTO objDto) {
		Estoque est = findById(id);
		Optional<Filme> optionalFilme = filmeRepository.findByEstoque(est);
		if (optionalFilme.isPresent()) {
			throw new DataIntegratyViolationException(
			"Este estoque está sendo utilizado por um filme e não pode ser atualizado se não for por alguma operação que altere o valor como DEVOLUÇÂO e LOCAÇÃO");
		}
		est.setQtd(objDto.getQtd());
		repository.save(est);
		return new EstoqueDTO(est);

	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar estoque associado com filme");
		}

	}

}
