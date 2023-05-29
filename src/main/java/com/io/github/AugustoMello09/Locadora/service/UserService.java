package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.RoleDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.UserPagedDTO;
import com.io.github.AugustoMello09.Locadora.entity.Role;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	@Transactional
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public Page<UserPagedDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserPagedDTO(x));
	}
	
	@Transactional
	public UserDTO create(UserDTO objDto) {
		User entity = new User();
		copyToEntity(objDto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);

	}
	
	@Transactional
	public UserDTO update(Long id, UserDTOUpdate objDto) {
		User entity = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Usuário não encontrado"));
		entity.setName(objDto.getName());
		entity.setEmail(objDto.getEmail());
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Integrity violation");
		}
	}
	
	private void copyToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setEmail(dto.getEmail());
		entity.getEnderecos().clear();
		for (RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.findById(roleDto.getId()).get();
			entity.getRoles().add(role);
		}
		
	}

	


}
