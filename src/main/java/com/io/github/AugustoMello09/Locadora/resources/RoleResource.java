package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.Services.RoleService;
import com.io.github.AugustoMello09.Locadora.entities.Role;

@RestController
@RequestMapping(value = "/roles")
public class RoleResource {
	
	@Autowired
	private RoleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Role> findById(@PathVariable Long id){
		Role role = service.findById(id);
		return ResponseEntity.ok().body(role);
	}
	
	@PostMapping
	public ResponseEntity<Role> create(@RequestBody Role rol){
		Role newObj = service.create(rol);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
