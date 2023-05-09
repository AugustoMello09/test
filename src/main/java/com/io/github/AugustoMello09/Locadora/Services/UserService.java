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
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.Role;
import com.io.github.AugustoMello09.Locadora.entities.User;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleService roleService;

	@Transactional(readOnly = true)
	public User findById(Long id) {
		Optional<User> entity = repository.findById(id);
		return entity.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> obj = repository.findAll(pageable);
		return obj.map(entity -> new UserDTO(entity));
	}

	@Transactional
	public User create(User obj) {
		obj.setId(null);
		for (Role role : obj.getRoles()) {
			Role fetchedRole = roleService.findById(role.getId());
			obj.getRoles().add(fetchedRole);
		}
		return repository.save(obj);
	}

	@Transactional
	public UserDTO update(Long id, UserDTO objDto) {
		User entity = findById(id);
		entity.setName(objDto.getName());
		entity.setEmail(objDto.getEmail());
		repository.save(entity);
		return new UserDTO(entity);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar usuário associado com endereço");
		}
	}

}
