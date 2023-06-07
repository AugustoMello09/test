package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOUpdate extends UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Size(min = 3, max = 10, message = "Deve ter entre 3 a 10 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	@Email
	@NotBlank(message = "Campo obrigatório")
	private String email;

	public UserDTOUpdate(User entity) {
		this.name = entity.getName();
		this.email = entity.getEmail();
	}
}
