package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;
import com.io.github.AugustoMello09.Locadora.entity.Pagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double valor;
	private FormaPagamento formaPagamento;

	private MultaDTO multa;

	public PagamentoDTO() {
	}

	public PagamentoDTO(Pagamento entity) {
		this.id = entity.getId();
		this.valor = entity.getValor();
		this.formaPagamento = entity.getformaPagamento();
	}

}
