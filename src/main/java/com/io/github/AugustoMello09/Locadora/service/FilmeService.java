package com.io.github.AugustoMello09.Locadora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOUpdate;
import com.io.github.AugustoMello09.Locadora.entity.Categoria;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.CategoriaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public FilmeDTO findById(Long id) {
		Optional<Filme> obj = repository.findById(id);
		Filme entity = obj.orElseThrow(()-> new ObjectNotFoundException("Filme não encontrado"));
		return new FilmeDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<Filme> findAll(Long idCategoria) {
		categoriaService.findById(idCategoria);
		return repository.findAllByCategory(idCategoria);
	}

	@Transactional
	public FilmeDTO create(FilmeDTO fil, Long idCategoria, Long idEstoque) {
		Estoque estoque = estoqueRepository.findById(idEstoque).orElseThrow(
				()-> new ObjectNotFoundException("Estoque não encontrado"));
		Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(
				()-> new ObjectNotFoundException("Categoria não encontrada"));
		Filme entity = new Filme();
		entity.setId(fil.getId());
		entity.setNome(fil.getNome());
		entity.setDescricao(fil.getDescricao());
		entity.setDiretor(fil.getDiretor());
		entity.setValorAluguel(fil.getValorAluguel());
		entity.setCategoria(categoria);
		entity.setEstoque(estoque);
		repository.save(entity);
		return new FilmeDTO(entity);
	}

	@Transactional
	public FilmeDTO update(FilmeDTOUpdate fil, Long id) {
		Filme entity = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Filme não encontrado"));
		entity.setNome(fil.getNome());
		entity.setDescricao(fil.getDescricao());
		entity.setDiretor(fil.getDiretor());
		repository.save(entity);
		return new FilmeDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não pode excluir filmes com associações");
		}
	}

}
