package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.entities.Cidade;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional(readOnly = true)
	public Cidade findById(Long id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada"));
	}
	
	@Transactional
	public Cidade create(Cidade end) {
		end.setId(null);
		return cidadeRepository.save(end);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			cidadeRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar cidade associado com endereço");
		}
	}
}
