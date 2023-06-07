package com.io.github.AugustoMello09.Locadora.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.io.github.AugustoMello09.Locadora.dto.MultaDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComBoletoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComCartaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComPixDTO;
import com.io.github.AugustoMello09.Locadora.service.MultaService;

@RestController
@RequestMapping(value = "/multas")
public class MultaResource {
	
	@Autowired
	private MultaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MultaDTO> findByMulta(@PathVariable Long id){
		MultaDTO mult = service.findById(id);
		return ResponseEntity.ok().body(mult);
	}
	
	@PostMapping("/PagamentoComBoleto")
	public ResponseEntity<Void> pagarMultaComBoleto(
			@RequestParam(value = "multa", defaultValue = "0")Long multaId, 
			@RequestBody PagamentoComBoletoDTO pagamentoDTO) {
		service.pagarMultaBoleto(multaId, pagamentoDTO);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/PagamentoComCartao")
	public ResponseEntity<Void> pagarMultaComCartão(
			@RequestParam(value = "multa", defaultValue = "0")Long multaId, 
		@Valid	@RequestBody PagamentoComCartaoDTO pagamentoDTO) {
		service.pagarMultaCartao(multaId, pagamentoDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/PagamentoComPix")
	public ResponseEntity<Void> pagarMultaComPix(
			@RequestParam(value = "multa", defaultValue = "0")Long multaId, 
			@RequestBody PagamentoComPixDTO pagamentoDTO) {
		service.pagarMultaPix(multaId, pagamentoDTO);
		return ResponseEntity.ok().build();
	}
}
