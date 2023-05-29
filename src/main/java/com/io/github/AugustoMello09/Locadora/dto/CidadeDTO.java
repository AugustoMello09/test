package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Cidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private EstadoDTO estado;
	
	public CidadeDTO() {}

	public CidadeDTO(Cidade entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.estado = new EstadoDTO(entity.getEstado());
	}
}
