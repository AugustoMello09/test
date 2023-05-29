package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeCategoria;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nomeCategoria = entity.getNomeCategoria();
	}
}
