package com.io.github.AugustoMello09.Locadora.entities.enums;

public enum StatusEstoque {

	DISPONIVEL(0, "Disponível"), REPOSICAO(1, "Reposição"),
	INDISPONIVEL(2, "Indisponível"), UNDEFINED(3,"INDEFINIDO");

	private Integer cod;
	private String descricao;

	private StatusEstoque(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEstoque toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StatusEstoque x : StatusEstoque.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Cod invalido" + cod);
	}
}
