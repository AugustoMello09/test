package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPagedDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(min = 1, max = 50, message = "Deve ter entre 2 a 50 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	@Email
	@Size(min = 1, max = 50, message = "Deve ter entre 2 a 50 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String email;

	public UserPagedDTO(User entity) {
		this.name = entity.getName();
		this.email = entity.getEmail();
	}

}
