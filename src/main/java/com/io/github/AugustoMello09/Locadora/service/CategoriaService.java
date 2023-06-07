package com.io.github.AugustoMello09.Locadora.service;

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
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj.orElseThrow(()-> new ObjectNotFoundException("Categoria Não encontrada"));
		return new CategoriaDTO(entity);
	}
	
	@Transactional
	public Page<CategoriaDTO> findAllPaged(Pageable pageable) {
		Page<Categoria> list = repository.findAll(pageable);
		return list.map(x -> new CategoriaDTO(x));
	}
	
	@Transactional
	public CategoriaDTO create(CategoriaDTO catDto) {
		Categoria entity = new Categoria();
		entity.setId(catDto.getId());
		entity.setNomeCategoria(catDto.getNomeCategoria());
		repository.save(entity);
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO update(CategoriaDTO catDto, Long id) {
		Categoria cat = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Categoria Não encontrada"));
		cat.setNomeCategoria(catDto.getNomeCategoria());
		repository.save(cat);
		return new CategoriaDTO(cat);
	}

	public void delete(Long id) {
		findById(id);
	    try {
	    	repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Categoria não pode ser excluida porque está associado com filmes");
		}	
	}

}