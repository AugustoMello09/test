package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.entities.Estado;
import com.io.github.AugustoMello09.Locadora.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;
	
	@Transactional(readOnly = true)
	public Estado findById(Long id) {
		Optional<Estado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Estado não encontrada"));
	}
	
	@Transactional
	public Estado create(Estado est) {
		est.setId(null);
		return repository.save(est);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar Estado associado a cidade");
		}
	}
		
}

