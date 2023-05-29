package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private CidadeDTO cidade;
	
	public EnderecoDTO() {
	}
	
	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento();
		this.bairro = entity.getBairro();
		this.cep = entity.getCep();
		this.cidade = new CidadeDTO(entity.getCidade());
	}

}
