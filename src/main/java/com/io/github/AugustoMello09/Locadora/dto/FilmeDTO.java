package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Filme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	private String diretor;
	private Double valorAluguel;

	
	private CategoriaDTO categoria;
	private EstoqueDTO estoque;
	
	public FilmeDTO() {}
	
	public FilmeDTO(Filme entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.descricao = entity.getDescricao();
		this.diretor = entity.getDiretor();
		this.valorAluguel = entity.getValorAluguel();
		this.categoria = new CategoriaDTO(entity.getCategoria());
		this.estoque = new EstoqueDTO(entity.getEstoque());
	}
}
