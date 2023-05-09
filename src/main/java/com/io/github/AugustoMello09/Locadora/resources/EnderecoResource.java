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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.Services.EnderecoService;
import com.io.github.AugustoMello09.Locadora.entities.Endereco;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Long id) {
		Endereco entity = service.findById(id);
		return ResponseEntity.ok().body(entity);
	}

	@PostMapping
	public ResponseEntity<Endereco> create(
			@RequestParam(value = "user", defaultValue = "0") Long idUser,
			@RequestParam(value = "cidade", defaultValue = "0") Long idCidade,
			@RequestBody Endereco end) {
		Endereco newObj = service.create(end,idUser, idCidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("endereco/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
