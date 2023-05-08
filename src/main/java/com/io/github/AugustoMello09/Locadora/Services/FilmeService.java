package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.entities.Categoria;
import com.io.github.AugustoMello09.Locadora.entities.Estoque;
import com.io.github.AugustoMello09.Locadora.entities.Filme;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private EstoqueService estoqueService;
	
	
	@Transactional(readOnly = true)
	public Filme findById(Long id) {
		Optional<Filme> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ObjectNotFoundException("Filme não encontrado"));
	}

	public Filme create(Filme obj, Long idCategoria, Long idEstoque) {
		Categoria categoria = categoriaService.findById(idCategoria);
		Estoque estoque = estoqueService.findById(idEstoque);
		Filme filme = new Filme();
		filme.setId(null);
		filme.setNome(obj.getNome());
		filme.setDescricao(obj.getDescricao());
		filme.setDiretor(obj.getDiretor());
		filme.setCategoria(categoria);
		filme.setEstoque(estoque);
		return repository.save(filme);
	}

	public FilmeDTO update(Long id, FilmeDTO objDto) {
		Filme entity = findById(id);
		entity.setNome(objDto.getNome());
		entity.setDescricao(objDto.getDescricao());
		entity.setDiretor(objDto.getDiretor());
		repository.save(entity);
		return new FilmeDTO(entity);
		
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar o filme associado a categoria e estoque");
		}
		
	}

}
