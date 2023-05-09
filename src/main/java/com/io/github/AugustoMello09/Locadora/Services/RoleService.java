package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.entities.Role;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		Optional<Role> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ObjectNotFoundException("Role não encontrado"));
	}
	
	@Transactional
	public Role create(Role rol) {
		rol.setId(null);
		return repository.save(rol);
	}
	
	
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar a Role associado com um usuário");
		}
		
	}

	
}
