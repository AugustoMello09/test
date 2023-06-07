package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public CidadeDTO findById(Long id) {
		Optional<Cidade> obj = repository.findById(id);
		Cidade entity = obj.orElseThrow(() -> new ObjectNotFoundException("Cidade Não encontrada"));
		return new CidadeDTO(entity);
	}

	@Transactional
	public CidadeDTO create(CidadeDTO cidDto, Long idEstado) {
		Cidade cidade = new Cidade();
		cidade.setId(cidDto.getId());
		cidade.setName(cidDto.getName());
		Estado estado = estadoRepository.findById(idEstado)
				.orElseThrow(() -> new ObjectNotFoundException("Estado Não encontrado"));
		cidade.setEstado(estado);
		repository.save(cidade);
		return new CidadeDTO(cidade);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar cidades associadas a estado");
		}
	}
}
