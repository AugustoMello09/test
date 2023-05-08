package com.io.github.AugustoMello09.Locadora.dto;

import com.io.github.AugustoMello09.Locadora.entities.Filme;

public class FilmeDTO {

	private Long id;
	private String nome;
	private String descricao;
	private String diretor;
	private CategoriaDTO categoria;
	private EstoqueDTO estoque;

	public FilmeDTO() {

	}

	public FilmeDTO(Filme entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.descricao = entity.getDescricao();
		this.diretor = entity.getDiretor();
		this.categoria = new CategoriaDTO(entity.getCategoria());
		this.estoque = new EstoqueDTO(entity.getEstoque());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public EstoqueDTO getEstoque() {
		return estoque;
	}

	public void setEstoque(EstoqueDTO estoque) {
		this.estoque = estoque;
	}

}
