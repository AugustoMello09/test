package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDTOUpdate implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String descricao;
	private String diretor;

	public FilmeDTOUpdate() {
	}

	public FilmeDTOUpdate(Filme entity) {
		this.nome = entity.getNome();
		this.descricao = entity.getDescricao();
		this.diretor = entity.getDiretor();
	}
}
