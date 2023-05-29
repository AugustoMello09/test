package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.PagamentoComCartao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoComCartaoDTO extends PagamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int numeroParcelas;
	
	PagamentoComCartaoDTO(){
		
	}
	
	PagamentoComCartaoDTO(PagamentoComCartao entity){
		this.numeroParcelas = entity.getNumeroParcelas();
	}
	
}
