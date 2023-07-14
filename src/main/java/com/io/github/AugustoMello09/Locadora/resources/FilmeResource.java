package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.FilmeDTO;
import com.io.github.AugustoMello09.Locadora.dto.FilmeDTOUpdate;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.service.FilmeService;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeResource {
	
	@Autowired
	private FilmeService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FilmeDTO> findById(@PathVariable Long id){
		FilmeDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*@GetMapping
	public ResponseEntity<List<FilmeDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Long idCategoria
			){
		List<Filme> list = service.findAll(idCategoria);
		List<FilmeDTO> listDto = list.stream().map(obj -> new FilmeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}*/
	
	@PostMapping
	public ResponseEntity<FilmeDTO> create(@Valid @RequestBody FilmeDTO fil){
		FilmeDTO newObj = service.create(fil);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FilmeDTO> update(@PathVariable Long id,@Valid @RequestBody FilmeDTOUpdate fil){
		FilmeDTO obj = service.update(fil, id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
