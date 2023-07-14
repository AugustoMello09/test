package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Multa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MultaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;	
	private Double valor;

	
	public MultaDTO() {

	}

	public MultaDTO(Multa entity) {
		this.id = entity.getId();
		this.valor = entity.getValor();
	}
}
