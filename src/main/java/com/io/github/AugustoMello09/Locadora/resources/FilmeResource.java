package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.Services.FilmeService;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.entities.Filme;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeResource {

	@Autowired
	private FilmeService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Filme> findById(@PathVariable Long id) {
		Filme fil = service.findById(id);
		return ResponseEntity.ok().body(fil);
	}
	
	@PostMapping
	public ResponseEntity<Filme> create(@RequestParam(value = "categoria", defaultValue = "0") Long idCategoria,
			@RequestParam(value = "estoque", defaultValue = "0") Long idEstoque, @RequestBody Filme obj) {
		Filme newObj = service.create(obj, idCategoria, idEstoque);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/filmes/{id}").buildAndExpand(newObj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FilmeDTO> update(@RequestBody FilmeDTO objDto, @PathVariable Long id){
		FilmeDTO filDto = service.update(id, objDto);
		return ResponseEntity.ok().body(filDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
