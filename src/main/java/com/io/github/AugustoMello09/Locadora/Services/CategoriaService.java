package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CategoriaDTO;
import com.io.github.AugustoMello09.Locadora.entities.Categoria;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada"));
	}
	
	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAll(Pageable pageable) {
		Page<Categoria> entity = repository.findAll(pageable);
		return entity.map(x -> new CategoriaDTO(x)); 
	}
	
	@Transactional
	public Categoria create(Categoria cat) {
		cat.setId(null);
		return repository.save(cat);
	}
	
	@Transactional
	public Categoria update(Long id, CategoriaDTO objDto) {
		Categoria entity = findById(id);
		entity.setNomeCategoria(objDto.getNomeCategoria());
		return repository.save(entity);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar categoria associado com filme");
		}
	}

}
