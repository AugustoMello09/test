package com.io.github.AugustoMello09.Locadora.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
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
	public ResponseEntity<ReservaOnlineDTO> create(@RequestParam(value = "user", defaultValue = "0") Long idUser,
			@RequestParam(value = "estoque", defaultValue = "0") Long idEstoque, @RequestBody ReservaOnlineDTO objDto) {
		ReservaOnlineDTO newObj = service.create(idUser, idEstoque, objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PostMapping(value = "/cancelar/{id}")
	public ResponseEntity<Void> cancelar(@RequestBody ReservaOnlineDTO reservaOnlineDTO, @PathVariable Long id) {
		service.cancelarReserva(reservaOnlineDTO, id);
		return ResponseEntity.ok().build();
	}

}
