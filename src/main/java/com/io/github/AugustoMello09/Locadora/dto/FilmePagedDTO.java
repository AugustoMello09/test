package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmePagedDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public FilmePagedDTO(Filme entity) {
		id = entity.getId();
		name = entity.getNome();
	}
}
