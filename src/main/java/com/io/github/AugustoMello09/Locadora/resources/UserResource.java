package com.io.github.AugustoMello09.Locadora.resources;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.UserPagedDTO;
import com.io.github.AugustoMello09.Locadora.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
		UserDTO objDto = service.findById(id);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping
	public ResponseEntity<Page<UserPagedDTO>> findAll(Pageable pageable){
		Page<UserPagedDTO> obj = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(obj); 
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO objDto){
		UserDTO newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTOUpdate objDto){
		UserDTO	newDto = service.update(id, objDto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
