package com.io.github.AugustoMello09.Locadora.entities.enums;

public enum FormaPagamento {
	
	CARTAO(0, "Cartão"),
	BOLETO(1, "Boleto"),
	PIX(2, "Pix");
	
	private Integer cod;
	private String descricao;

	
	private FormaPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public Integer getCod() {
		return cod;
	}


	public String getDescricao() {
		return descricao;
	}
	
	public static FormaPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(FormaPagamento x : FormaPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Cod invalido" + cod);
	}
}
