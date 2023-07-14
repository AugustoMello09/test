package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTOInsert;
import com.io.github.AugustoMello09.Locadora.service.ReservaOnlineService;

@RestController
@RequestMapping(value = "/reservasOnline")
public class ReservaOnlineResource {

	@Autowired
	private ReservaOnlineService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ReservaOnlineDTO> findById(@PathVariable Long id) {
		ReservaOnlineDTO reser = service.findReservaOnlineById(id);
		return ResponseEntity.ok().body(reser);
	}

	@PostMapping
	public ResponseEntity<ReservaOnlineDTO> create(@RequestBody ReservaOnlineDTOInsert objDto) {
		ReservaOnlineDTO newObj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PostMapping(value = "/cancelar/{id}")
	public ResponseEntity<Void> cancelar(@PathVariable Long id) {
		service.cancelarReserva(id);
		return ResponseEntity.ok().build();
	}

}
