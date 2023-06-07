package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 3, max = 10, message = "Deve ter entre 3 a 10 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String logradouro;
	
	@Size(min = 1, max = 10, message = "Deve ter entre 1 a 10 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String numero;
	
	@Size(min = 2, max = 24, message = "Deve ter entre 2 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String complemento;
	
	@Size(min = 2, max = 24, message = "Deve ter entre 2 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String bairro;
	
	@Size(min = 2, max = 24, message = "Deve ter entre 2 a 24 caracteres")
	@NotBlank(message = "Campo obrigatório")
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
