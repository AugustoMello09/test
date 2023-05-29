package com.io.github.AugustoMello09.Locadora.entities.enums;

public enum StatusReserva {
	
	ATIVA(0, "Ativa");
	
	private Integer cod;
	private String descricao;

	
	private StatusReserva(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public Integer getCod() {
		return cod;
	}


	public String getDescricao() {
		return descricao;
	}
	
	public static StatusReserva toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusReserva x : StatusReserva.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Cod invalido" + cod);
	}
}
