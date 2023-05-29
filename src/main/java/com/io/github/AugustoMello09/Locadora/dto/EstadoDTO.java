package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public EstadoDTO() {
	}

	public EstadoDTO(Estado entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

}
