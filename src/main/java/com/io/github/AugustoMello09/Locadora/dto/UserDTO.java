package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.io.github.AugustoMello09.Locadora.entity.Endereco;
import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String cpf;

	private List<EnderecoDTO> enderecos = new ArrayList<>();

	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO() {
	}

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		entity.getRoles().forEach(x -> this.roles.add(new RoleDTO(x)));
		entity.getEnderecos().stream().filter(endereco -> !isEnderecoAlreadyAdded(endereco)).map(EnderecoDTO::new)
				.forEach(enderecos::add);
	}

	public boolean isEnderecoAlreadyAdded(Endereco endereco) {
		return enderecos.stream().anyMatch(enderecoDTO -> enderecoDTO.getId().equals(endereco.getId()));
	}

}
